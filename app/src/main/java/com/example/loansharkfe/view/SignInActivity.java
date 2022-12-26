package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkSignInController;
import com.example.loansharkfe.controller.interfaces.SignInController;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {

    public EditText usernameOrEmailEditText, passwordEditText;
    public Button signIn, signUp;
    public ArrayList<View> all_elements;
    private SignInController signInController;
    public ProgressBar progressBar;
    public TextView errorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameOrEmailEditText = findViewById(R.id.username_signin);
        passwordEditText = findViewById(R.id.password_signin);
        signIn = findViewById(R.id.button_signin_signin);
        signUp = findViewById(R.id.button_signup_signin);
        progressBar = findViewById(R.id.progressBarSignIn);
        errorMessage = findViewById(R.id.errorMessageSignIn);

        all_elements = new ArrayList<View>();
        all_elements.add(usernameOrEmailEditText);
        all_elements.add(passwordEditText);
        all_elements.add(signUp);
        all_elements.add(signIn);
        all_elements.add(errorMessage);

        progressBar.setVisibility(View.GONE);
        errorMessage.clearComposingText();

        signInController = new LoanSharkSignInController(this);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 signInController.login();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInController.startSignUpActivity();
            }
        });

    }

    public Context getContext(){
        return this;
    }

}