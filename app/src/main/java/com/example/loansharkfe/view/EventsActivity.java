package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkEventsController;
import com.example.loansharkfe.controller.implementations.LoanSharkSignInController;
import com.example.loansharkfe.controller.interfaces.EventsController;
import com.example.loansharkfe.controller.interfaces.SignInController;
import com.example.loansharkfe.model.EventsOfUser;
import com.example.loansharkfe.util.RecyclerItemClickListener;

public class EventsActivity extends AppCompatActivity {

    public EventsController eventsController;
    public Button createEvent;
    public RecyclerView eventsRecyclerView;
    private EventsOfUser eventsOfUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        createEvent = findViewById(R.id.createNewEventBtnEvents);
        eventsRecyclerView = findViewById(R.id.eventsRecyclerViweEvents);

        eventsController = new LoanSharkEventsController(this);
        try {
            eventsOfUser = eventsController.fillInRecyclerView();
        } catch (Exception e) {
            e.printStackTrace();
        }

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventsController.startCreateEventActivity();
            }
        });

        eventsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, eventsRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        try {
                            eventsController.startEventProfileActivity(eventsOfUser.getEvents().get(position));
                        }
                        catch (Exception exception){
                            exception.printStackTrace();
                        }
                    }
                })
        );
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        eventsController.startMenuActivity();
    }

    public Context getContext(){
        return this;
    }
}