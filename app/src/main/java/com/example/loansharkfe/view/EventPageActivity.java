package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkEventPageController;
import com.example.loansharkfe.controller.interfaces.EventPageController;

public class EventPageActivity extends AppCompatActivity {

    public TextView name;
    public TextView description;
    public TextView admin;
    public TextView members;
    public RecyclerView subEvents;
    public Button createSubEvent;

    private EventPageController eventPageController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        name = findViewById(R.id.nameEventPage);
        description = findViewById(R.id.descriptionEventPage);
        admin = findViewById(R.id.adminEventPage);
        members = findViewById(R.id.membersEventPage);
        subEvents = findViewById(R.id.subeventsRecyclerViewEventPage);
        createSubEvent = findViewById(R.id.createSubEventEventPage);
        eventPageController = new LoanSharkEventPageController(this);

        Intent intent = getIntent();
        Integer eventId = Integer.parseInt(intent.getStringExtra("id"));
        try {
            eventPageController.fillInAllTheFields(eventId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        eventPageController.startEventsActivity();
    }
}