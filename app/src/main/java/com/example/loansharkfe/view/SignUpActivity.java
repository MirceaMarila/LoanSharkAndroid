package com.example.loansharkfe.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkSignUpController;
import com.example.loansharkfe.controller.interfaces.SignUpController;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class SignUpActivity extends AppCompatActivity {

    public EditText email, username, password, firstName, lastName, description;
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
        description = findViewById(R.id.descriptionSignUp);

        all_elements = new ArrayList<View>();
        all_elements.add(email);
        all_elements.add(username);
        all_elements.add(password);
        all_elements.add(firstName);
        all_elements.add(lastName);
        all_elements.add(signUp);
        all_elements.add(errorMessage);
        all_elements.add(description);

        progressBar.setVisibility(View.GONE);
        errorMessage.clearComposingText();

        signUpController = new LoanSharkSignUpController(this);

        signUpController.setTextFromSignIn();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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