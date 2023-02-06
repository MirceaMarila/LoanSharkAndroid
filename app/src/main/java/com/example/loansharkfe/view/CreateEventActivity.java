package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.interfaces.CreateEventController;

public class CreateEventActivity extends AppCompatActivity {

    private CreateEventController createEventController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        createEventController.startEventsActivity();
    }
}