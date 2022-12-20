package com.example.loansharkfe.controller.implementations;

import android.widget.Toast;

import com.example.loansharkfe.controller.interfaces.SignUpController;
import com.example.loansharkfe.dto.GenericResponse;
import com.example.loansharkfe.exceptions.ErrorFromServer;
import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.service.implementations.LoanSharkUserService;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.Json;
import com.example.loansharkfe.util.NetworkingRunnable;
import com.example.loansharkfe.view.SignUpActivity;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LoanSharkSignUpController implements SignUpController {


    private final SignUpActivity signUpActivity;

    private final UserService userService;

    private final Json json;

    public LoanSharkSignUpController(SignUpActivity signUpActivity) {
        this.signUpActivity = signUpActivity;
        this.userService = new LoanSharkUserService();
        this.json = Json.getInstance();
    }


    public void setTextFromSignIn() {

        UserLogin userLogin = null;

        try {
            userLogin = json.objectMapper.readValue(signUpActivity.getIntent().getStringExtra("userLogin"), UserLogin.class);
        } catch (JsonProcessingException e) {
            //TODO("Json serialization failed. Check code")
        }


        if (userLogin == null)
            return;

        if (!userLogin.getPassword().isEmpty())
            signUpActivity.password.setText(userLogin.getPassword());

        if (userLogin.getUsernameOrEmail().isEmpty())
            return;

        if (userService.validateEmail(userLogin.getUsernameOrEmail()))
            signUpActivity.email.setText(userLogin.getUsernameOrEmail());
        else
            signUpActivity.username.setText(userLogin.getUsernameOrEmail());
    }

    public void createNewUser(){
        UserCreate userCreate = new UserCreate(
                signUpActivity.email.getText().toString(),
                signUpActivity.username.getText().toString(),
                signUpActivity.password.getText().toString(),
                signUpActivity.firstName.getText().toString(),
                signUpActivity.lastName.getText().toString()
                );

        try {
            NetworkingRunnable createNewUserRunnable = userService.createSaveNewUserRunnable(userCreate);
            Thread thread = new Thread(createNewUserRunnable);
            thread.start();
            //todo spinner
            thread.join();

            if (createNewUserRunnable.getException() != null)
                throw createNewUserRunnable.getException();

             Integer code = createNewUserRunnable.getGenericResponse().getResponseCode();

             //todo: la 401 pot sa nu primesc body (ex daca pun bare token invalid)
            //todo: la restu iau body pentru mesaj eroare

             if (code != 201)
                 throw new ErrorFromServer(code.toString());

            User user = json.objectMapper.readValue(createNewUserRunnable.getGenericResponse().getBody(), User.class);
            Toast.makeText(signUpActivity.getApplicationContext(), user.getUsername(), Toast.LENGTH_LONG).show();


        } catch (FieldCompletedIncorrectly e) {
            e.printStackTrace();
            e.getMessage();//mesaju de la throw
            //TODO: error mesage on screen = FieldCompletedIncorrectly
        } catch (InterruptedException e) {
            e.printStackTrace();
            //todo: sth went wrong wrong, try again
        } catch (Exception e) {
            e.printStackTrace();
        }
        //todo copiazale pe toate de dinc-olo
    }
}
