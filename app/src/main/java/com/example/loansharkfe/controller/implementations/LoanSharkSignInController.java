package com.example.loansharkfe.controller.implementations;


import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loansharkfe.R;
import com.example.loansharkfe.constants.BugTypes;
import com.example.loansharkfe.controller.interfaces.SignInController;
import com.example.loansharkfe.dto.JwtResponse;
import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;

public class LoanSharkSignInController implements SignInController {

    private final SignInActivity signInActivity;
    private final UserService userService;
    private final Json json;
    private final ProgressBarController progressBarController;
    private final SharedPreferencesService sharedPreferencesService;
    private final BugReportController bugReportController;

    public LoanSharkSignInController(SignInActivity signInActivity) {
        this.signInActivity = signInActivity;
        this.userService = new LoanSharkUserService();
        this.json = Json.getInstance();
        this.progressBarController = new ProgressBarController(signInActivity.all_elements, signInActivity.progressBar);
        this.sharedPreferencesService = new SharedPreferencesService(signInActivity.getApplicationContext());
        this.bugReportController = new BugReportController(signInActivity.getContext(), signInActivity.errorMessage, signInActivity.all_elements, signInActivity.progressBar, signInActivity);
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

            progressBarController.showProgressBar();
            loginThread.start();
            loginThread.join();
            progressBarController.hideProgressBar();

            if (loginRunnable.getException() != null)
                throw loginRunnable.getException();

            String jwt = json.objectMapper.readValue(loginRunnable.getGenericResponse().getBody(), JwtResponse.class).getJwt();

            sharedPreferencesService.postSharedPreferences("jwt", jwt);
            startMenuActivity();

            if (loginRunnable.getGenericResponse().getResponseCode() == 401)
                Toast.makeText(signInActivity.getApplicationContext(), "Invalid credentials", Toast.LENGTH_LONG).show();


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

    public void startMenuActivity() {

        Intent intent = new Intent(signInActivity, MenuActivity.class);
        signInActivity.startActivity(intent);
        signInActivity.finish();

    }

}
