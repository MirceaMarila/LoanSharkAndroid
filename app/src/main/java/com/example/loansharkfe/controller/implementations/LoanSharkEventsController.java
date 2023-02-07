package com.example.loansharkfe.controller.implementations;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.interfaces.EventsController;
import com.example.loansharkfe.model.Event;
import com.example.loansharkfe.model.EventsOfUser;
import com.example.loansharkfe.model.FriendCard;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.model.UserProfile;
import com.example.loansharkfe.service.implementations.LoanSharkEventService;
import com.example.loansharkfe.service.implementations.SharedPreferencesService;
import com.example.loansharkfe.service.interfaces.EventService;
import com.example.loansharkfe.util.Adapter;
import com.example.loansharkfe.view.CreateEventActivity;
import com.example.loansharkfe.view.EventPageActivity;
import com.example.loansharkfe.view.EventsActivity;
import com.example.loansharkfe.view.MenuActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LoanSharkEventsController implements EventsController {

    private final EventsActivity eventsActivity;
    private SharedPreferencesService sharedPreferencesService;
    private EventService eventService;
    private LinearLayoutManager layoutManager;
    private Adapter adapter;

    public LoanSharkEventsController(EventsActivity eventsActivity) {
        this.eventsActivity = eventsActivity;
        this.sharedPreferencesService = new SharedPreferencesService(eventsActivity.getApplicationContext());
        this.eventService = new LoanSharkEventService();
    }

    public void startMenuActivity() {
        Intent intent = new Intent(eventsActivity, MenuActivity.class);
        eventsActivity.startActivity(intent);
        eventsActivity.finish();
    }

    public void startCreateEventActivity() {
        Intent intent = new Intent(eventsActivity, CreateEventActivity.class);
        eventsActivity.startActivity(intent);
        eventsActivity.finish();
    }

    @SuppressLint("NotifyDataSetChanged")
    public EventsOfUser fillInRecyclerView() throws Exception {
        EventsOfUser eventsOfUser = eventService.getEventsOfUser(sharedPreferencesService.getSharedPreferences("jwt"), Integer.parseInt(sharedPreferencesService.getSharedPreferences("id")));

        List<FriendCard> eventsList = new ArrayList<>();
        for (Event event: eventsOfUser.getEvents()){
            eventsList.add(new FriendCard(null,
                    event.getName(),
                    String.valueOf(event.getMembersUsernames().size()),
                    "members"));
        }

        layoutManager = new LinearLayoutManager(eventsActivity.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        eventsActivity.eventsRecyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(eventsList);
        eventsActivity.eventsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return eventsOfUser;
    }

    public void startEventProfileActivity(Event event){
        Intent intent = new Intent(eventsActivity, EventPageActivity.class);
        intent.putExtra("id", event.getId().toString());
        eventsActivity.startActivity(intent);
        eventsActivity.finish();
    }
}
