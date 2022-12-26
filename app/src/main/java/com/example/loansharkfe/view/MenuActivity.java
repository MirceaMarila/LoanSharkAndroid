package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.MenuController;

public class MenuActivity extends AppCompatActivity {

    public Button viewEvents, viewProfile, signOut;
    private MenuController menuController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        viewEvents = findViewById(R.id.eventsBtnMenu);
        viewProfile = findViewById(R.id.profileBtnMenu);
        signOut = findViewById(R.id.signOutBtnMenu);

        menuController = new MenuController(this);

        viewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // view events
            }
        });

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // view profile
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuController.deleteJwt();
                menuController.startSignInActivity();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        menuController.startSignInActivity();
    }
}