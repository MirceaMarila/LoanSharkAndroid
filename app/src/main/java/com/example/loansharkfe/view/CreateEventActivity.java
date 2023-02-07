package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkCreateEventController;
import com.example.loansharkfe.controller.interfaces.CreateEventController;

import java.util.ArrayList;

public class CreateEventActivity extends AppCompatActivity {

    private CreateEventController createEventController;
    public ArrayList<Integer> members = new ArrayList<>();;

    public EditText name, description;
    public Button addMembers, createEvent;
    public ProgressBar progressBar;
    public TextView errorMessage, selectedMembers;
    public ArrayList<View> all_elements;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        name = findViewById(R.id.eventNameCreateEvent);
        description = findViewById(R.id.eventDescriptionCreateEvent);
        addMembers = findViewById(R.id.addMembersBtnCreateEvent);
        createEvent = findViewById(R.id.createEventBtnCreateEvent);
        progressBar = findViewById(R.id.progressBarCreateEvent);
        errorMessage = findViewById(R.id.errorMessageCreateEvent);
        selectedMembers = findViewById(R.id.selectedMembersCreateEvent);
        progressBar.setVisibility(View.GONE);
        errorMessage.setTextColor(Color.RED);

        all_elements = new ArrayList<View>();
        all_elements.add(name);
        all_elements.add(description);
        all_elements.add(addMembers);
        all_elements.add(createEvent);

        createEventController = new LoanSharkCreateEventController(this);

        addMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    members = createEventController.selectMembersFromFriends(members, selectedMembers);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEventController.createNewEvent();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        createEventController.startEventsActivity();
    }

    public Context getContext(){
        return this;
    }
}