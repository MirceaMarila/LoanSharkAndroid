package com.example.loansharkfe.controller.implementations;

import android.content.Intent;

import com.example.loansharkfe.controller.interfaces.CreateEventController;
import com.example.loansharkfe.view.CreateEventActivity;
import com.example.loansharkfe.view.EventsActivity;
import com.example.loansharkfe.view.MenuActivity;

public class LoanSharkCreateEventController implements CreateEventController {

    private final CreateEventActivity createEventActivity;

    public LoanSharkCreateEventController(CreateEventActivity createEventActivity) {
        this.createEventActivity = createEventActivity;
    }

    public void startEventsActivity() {
        Intent intent = new Intent(createEventActivity, EventsActivity.class);
        createEventActivity.startActivity(intent);
        createEventActivity.finish();
    }
}
