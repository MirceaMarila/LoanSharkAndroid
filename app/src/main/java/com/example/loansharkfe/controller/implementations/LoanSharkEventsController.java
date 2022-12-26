package com.example.loansharkfe.controller.implementations;

import android.content.Intent;

import com.example.loansharkfe.controller.interfaces.EventsController;
import com.example.loansharkfe.view.EventsActivity;
import com.example.loansharkfe.view.MenuActivity;

public class LoanSharkEventsController implements EventsController {

    private final EventsActivity eventsActivity;

    public LoanSharkEventsController(EventsActivity eventsActivity) {
        this.eventsActivity = eventsActivity;
    }

    public void startMenuActivity() {
        Intent intent = new Intent(eventsActivity, MenuActivity.class);
        eventsActivity.startActivity(intent);
        eventsActivity.finish();
    }
}
