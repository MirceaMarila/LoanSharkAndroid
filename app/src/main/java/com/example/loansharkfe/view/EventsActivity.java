package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkEventsController;
import com.example.loansharkfe.controller.implementations.LoanSharkSignInController;
import com.example.loansharkfe.controller.interfaces.EventsController;
import com.example.loansharkfe.controller.interfaces.SignInController;

public class EventsActivity extends AppCompatActivity {

    private EventsController eventsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        eventsController = new LoanSharkEventsController(this);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        eventsController.startMenuActivity();
    }
}