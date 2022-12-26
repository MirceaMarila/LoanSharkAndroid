package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkEventsController;
import com.example.loansharkfe.controller.implementations.LoanSharkProfileController;
import com.example.loansharkfe.controller.interfaces.EventsController;
import com.example.loansharkfe.controller.interfaces.ProfileController;

public class ProfileActivity extends AppCompatActivity {

    public ImageView profilePicture;
    public TextView username, email, firstName, lastName;
    private ProfileController profileController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePicture = findViewById(R.id.pictureProfile);
        username = findViewById(R.id.usernameProfile);
        email = findViewById(R.id.emailProfile);
        firstName = findViewById(R.id.firstNameProfile);
        lastName = findViewById(R.id.lastNameProfile);

        profileController = new LoanSharkProfileController(this);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        profileController.startMenuActivity();
    }
}