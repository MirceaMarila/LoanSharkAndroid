package com.example.loansharkfe.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkSignUpController;
import com.example.loansharkfe.controller.interfaces.SignUpController;


public class SignUpActivity extends AppCompatActivity {

    public EditText email, username, password, first_name, last_name;
    public Button sign_up;


    private SignUpController signUpController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.email_signup);
        username = findViewById(R.id.username_signup);
        password = findViewById(R.id.password_signup);
        first_name = findViewById(R.id.firstname_signup);
        last_name = findViewById(R.id.lastname_signup);
        sign_up = findViewById(R.id.button_signup_sigup);


        signUpController = new LoanSharkSignUpController(this);

        signUpController.setTextFromSignIn();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO("Use controller to create an user then automatically login")
                //TODO("Start new activity. Should be the same that you land on after signing in")
            }
        });

    }
}