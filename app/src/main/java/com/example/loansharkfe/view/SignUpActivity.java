package com.example.loansharkfe.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkSignUpController;
import com.example.loansharkfe.controller.interfaces.SignUpController;

import java.util.ArrayList;
import java.util.List;


public class SignUpActivity extends AppCompatActivity {

    public EditText email, username, password, firstName, lastName;
    public TextView errorMessage;
    public Button signUp;
    public ProgressBar progressBar;
    public ArrayList<View> all_elements;
    private SignUpController signUpController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.email_signup);
        username = findViewById(R.id.username_signup);
        password = findViewById(R.id.password_signup);
        firstName = findViewById(R.id.firstname_signup);
        lastName = findViewById(R.id.lastname_signup);
        signUp = findViewById(R.id.button_signup_sigup);
        progressBar = findViewById(R.id.progressBarSignUp);
        errorMessage = findViewById(R.id.errorMessageSignUp);

        all_elements = new ArrayList<View>();
        all_elements.add(email);
        all_elements.add(username);
        all_elements.add(password);
        all_elements.add(firstName);
        all_elements.add(lastName);
        all_elements.add(signUp);
        all_elements.add(errorMessage);

        progressBar.setVisibility(View.GONE);
        errorMessage.clearComposingText();

        signUpController = new LoanSharkSignUpController(this);

        signUpController.setTextFromSignIn();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO("Use controller to create an user then automatically login")
                //TODO("Start new activity. Should be the same that you land on after signing in")
                signUpController.createNewUser();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        signUpController.startSignInActivity();
    }

    public Context getContext(){
        return this;
    }
}