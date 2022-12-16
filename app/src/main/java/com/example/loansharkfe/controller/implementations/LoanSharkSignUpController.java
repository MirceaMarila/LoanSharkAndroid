package com.example.loansharkfe.controller.implementations;

import com.example.loansharkfe.controller.interfaces.SignUpController;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.service.implementations.LoanSharkUserService;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.Json;
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
}
