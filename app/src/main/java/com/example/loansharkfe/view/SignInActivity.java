package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkSignInController;
import com.example.loansharkfe.controller.interfaces.SignInController;

public class SignInActivity extends AppCompatActivity {

    public EditText usernameOrEmailEditText, passwordEditText;
    public Button signIn, signUp;


    private SignInController signInController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameOrEmailEditText = findViewById(R.id.username_signin);
        passwordEditText = findViewById(R.id.password_signin);
        signIn = findViewById(R.id.button_signin_signin);
        signUp = findViewById(R.id.button_signup_signin);


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
}