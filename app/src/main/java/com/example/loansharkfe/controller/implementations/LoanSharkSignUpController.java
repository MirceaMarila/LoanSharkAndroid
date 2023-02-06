package com.example.loansharkfe.controller.implementations;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.loansharkfe.constants.BugTypes;
import com.example.loansharkfe.controller.interfaces.SignUpController;
import com.example.loansharkfe.dto.GenericResponse;
import com.example.loansharkfe.exceptions.ErrorFromServer;
import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.service.implementations.LoanSharkUserService;
import com.example.loansharkfe.service.implementations.SharedPreferencesService;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.Json;
import com.example.loansharkfe.util.NetworkingRunnable;
import com.example.loansharkfe.view.MenuActivity;
import com.example.loansharkfe.view.SignInActivity;
import com.example.loansharkfe.view.SignUpActivity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;

public class LoanSharkSignUpController implements SignUpController {


    private final SignUpActivity signUpActivity;
    private final ProgressBarController progressBarController;
    private final UserService userService;
    private final Json json;
    private final BugReportController bugReportController;
    private final SharedPreferencesService sharedPreferencesService;

    public LoanSharkSignUpController(SignUpActivity signUpActivity) {
        this.signUpActivity = signUpActivity;
        this.userService = new LoanSharkUserService();
        this.json = Json.getInstance();
        this.progressBarController = new ProgressBarController(signUpActivity.all_elements, signUpActivity.progressBar);
        this.bugReportController = new BugReportController(signUpActivity.getContext(), signUpActivity.errorMessage, signUpActivity.all_elements, signUpActivity.progressBar, signUpActivity);
        this.sharedPreferencesService = new SharedPreferencesService(signUpActivity.getApplicationContext());
    }


    public void setTextFromSignIn() {

        UserLogin userLogin = null;

        try {
            userLogin = json.objectMapper.readValue(signUpActivity.getIntent().getStringExtra("userLogin"), UserLogin.class);
        } catch (JsonProcessingException e) {
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.CODE);
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
            progressBarController.showProgressBar();
            thread.start();
            thread.join();
            progressBarController.hideProgressBar();

            if (createNewUserRunnable.getException() != null)
                throw createNewUserRunnable.getException();

             Integer code = createNewUserRunnable.getGenericResponse().getResponseCode();

             if (code != 201)
                 throw new ErrorFromServer(createNewUserRunnable.getGenericResponse().getResponseMessage());

            User user = json.objectMapper.readValue(createNewUserRunnable.getGenericResponse().getBody(), User.class);
            sharedPreferencesService.postSharedPreferences("user", user.getUsername());
            startMenuActivity();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "A user with this username or email has already been created!", BugTypes.USER);
        } catch (ErrorFromServer e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Change the credentials and try again!", BugTypes.USER);
        } catch (FieldCompletedIncorrectly e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "One field is empty or incorrectly completed!", BugTypes.USER);
        } catch (InterruptedException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.OTHER);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.CODE);
        } catch (ProtocolException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.CODE);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.CODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.CODE);
        } catch (ConnectException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Connection problem. Check internet connection and try again. The server might be offline.", BugTypes.OTHER);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Connection problem. Check internet connection and try again. The server might be offline.", BugTypes.OTHER);

        } catch (IOException e) {
            e.printStackTrace();
            if (e.getMessage().equals("No authentication challenges found"))
                bugReportController.handleBug(e, "Invalid credentials!", BugTypes.USER);
            else
                bugReportController.handleBug(e, "Something went wrong. Check internet connection and try again!", BugTypes.OTHER);

        } catch (Exception e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Check internet connection and try again!", BugTypes.OTHER);
        }
    }

    public void startSignInActivity() {

        Intent intent = new Intent(signUpActivity, SignInActivity.class);
        signUpActivity.startActivity(intent);
        signUpActivity.finish();

    }

    public void startMenuActivity() {

        Intent intent = new Intent(signUpActivity, MenuActivity.class);
        signUpActivity.startActivity(intent);
        signUpActivity.finish();

    }
}
