package com.example.loansharkfe.controller.implementations;


import android.content.Intent;
import android.widget.Toast;

import com.example.loansharkfe.controller.interfaces.SignInController;
import com.example.loansharkfe.dto.JwtResponse;
import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.service.implementations.LoanSharkUserService;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.Json;
import com.example.loansharkfe.util.NetworkingRunnable;
import com.example.loansharkfe.view.SignInActivity;
import com.example.loansharkfe.view.SignUpActivity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;

public class LoanSharkSignInController implements SignInController {

    private final SignInActivity signInActivity;

    private final UserService userService;

    private final Json json;

    public LoanSharkSignInController(SignInActivity signInActivity) {
        this.signInActivity = signInActivity;
        this.userService = new LoanSharkUserService();
        this.json = Json.getInstance();
    }


    private UserLogin createUserLogin() {
        String usernameOrEmail = signInActivity.usernameOrEmailEditText.getText().toString();
        String password = signInActivity.passwordEditText.getText().toString();

        return new UserLogin(usernameOrEmail, password);
    }


    public void login() {

        UserLogin userLogin = createUserLogin();

        try {
            NetworkingRunnable loginRunnable = userService.createLoginRunnable(userLogin);
            Thread loginThread = new Thread(loginRunnable);
            loginThread.start();

            //TODO("SPINNER')

            loginThread.join();

            if (loginRunnable.getException() != null)
                throw loginRunnable.getException();

            String jwt = null;
            jwt = json.objectMapper.readValue(loginRunnable.getGenericResponse().getBody(), JwtResponse.class).getJwt();

            Toast.makeText(signInActivity.getApplicationContext(), jwt, Toast.LENGTH_LONG).show();
            //TODO("Verify if exceptions are caught in the correct category")
        } catch (FieldCompletedIncorrectly e) {
            e.printStackTrace();
            //TODO("PIBKAC")
        } catch (InterruptedException e) {
            e.printStackTrace();
            //TODO("Handle exception. Thread died unexpectedly. Probably Android killed it for memory or inactivity reasons")
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //TODO(Handle exception. Url is probably invalid. Check code)
        } catch (ProtocolException e) {
            e.printStackTrace();
            //TODO("Handle exception. Protocol error. Check code.")
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //TODO("Handle exception. Json serialization failed. Maybe you got an unexpected error response from server like BadRequest. Check code")
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //Todo("Handle exception. Encoding should be UTF-8 for everything and errors should not appear but who knows. Check if server sends Chinese characters")
        } catch (IOException e) {
            e.printStackTrace();
            //TODO("Handle exception. Anything related to network not caught until now. Server offline, server unavailable, no internet connection
            // reading from a closed connection, etc.")
        } catch (Exception e) {
            e.printStackTrace();
            //TODO("Handle exception. Catastrophic failure of the application by an unknown reason. Check code(can happen even if code is correct)")
        }
    }

    public void startSignUpActivity() {

        UserLogin userLogin = createUserLogin();

        String userLoginAsString = null;
        try {
            userLoginAsString = json.objectMapper.writeValueAsString(userLogin);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(signInActivity, SignUpActivity.class);

        intent.putExtra("userLogin", userLoginAsString);

        signInActivity.startActivity(intent);

        signInActivity.finish();

    }

}
