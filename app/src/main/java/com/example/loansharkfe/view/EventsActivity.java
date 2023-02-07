package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkEventsController;
import com.example.loansharkfe.controller.implementations.LoanSharkSignInController;
import com.example.loansharkfe.controller.interfaces.EventsController;
import com.example.loansharkfe.controller.interfaces.SignInController;

public class EventsActivity extends AppCompatActivity {

    private EventsController eventsController;
    private Button createEvent, deleteEvent;
    private RecyclerView eventsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        createEvent = findViewById(R.id.createNewEventBtnEvents);
        deleteEvent = findViewById(R.id.deleteEventBtnEvents);
        eventsRecyclerView = findViewById(R.id.eventsRecyclerViweEvents);

        eventsController = new LoanSharkEventsController(this);

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventsController.startCreateEventActivity();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        eventsController.startMenuActivity();
    }
}