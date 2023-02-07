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
    public Button signUp, chooseFromGalley;
    public ProgressBar progressBar;
    public ArrayList<View> all_elements;
    private SignUpController signUpController;
    private final int GALLERY_REQ_CODE = 1000;


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
        chooseFromGalley = findViewById(R.id.chooseProfilePictureSignUp);

        all_elements = new ArrayList<View>();
        all_elements.add(email);
        all_elements.add(username);
        all_elements.add(password);
        all_elements.add(firstName);
        all_elements.add(lastName);
        all_elements.add(signUp);
        all_elements.add(errorMessage);
        all_elements.add(description);
        all_elements.add(chooseFromGalley);

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

        chooseFromGalley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GALLERY_REQ_CODE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            if (requestCode==GALLERY_REQ_CODE){

                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    selectedImage.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] b = baos.toByteArray();
                    String encImage = Base64.encodeToString(b, Base64.DEFAULT);

                    signUpController.updateProfilePicture(encImage);
                }
                catch (FileNotFoundException exception){
                    exception.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
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