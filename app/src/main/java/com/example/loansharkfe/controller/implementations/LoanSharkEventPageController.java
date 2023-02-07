package com.example.loansharkfe.controller.implementations;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loansharkfe.controller.interfaces.EventPageController;
import com.example.loansharkfe.model.Event;
import com.example.loansharkfe.model.EventsOfUser;
import com.example.loansharkfe.model.FriendCard;
import com.example.loansharkfe.model.UserProfile;
import com.example.loansharkfe.service.implementations.LoanSharkEventService;
import com.example.loansharkfe.service.implementations.SharedPreferencesService;
import com.example.loansharkfe.service.interfaces.EventService;
import com.example.loansharkfe.util.Adapter;
import com.example.loansharkfe.view.EventPageActivity;
import com.example.loansharkfe.view.EventsActivity;
import com.example.loansharkfe.view.MenuActivity;

import java.util.ArrayList;
import java.util.List;

public class LoanSharkEventPageController implements EventPageController {

    private final EventPageActivity eventPageActivity;
    private SharedPreferencesService sharedPreferencesService;
    private EventService eventService;

    public LoanSharkEventPageController(EventPageActivity eventPageActivity) {
        this.eventPageActivity = eventPageActivity;
        this.sharedPreferencesService = new SharedPreferencesService(eventPageActivity.getApplicationContext());
        this.eventService = new LoanSharkEventService();
    }

    public void fillInAllTheFields(Integer eventId) throws Exception {
        Event currentEvent = eventService.getEventById(sharedPreferencesService.getSharedPreferences("jwt"), eventId);
        eventPageActivity.name.setText(currentEvent.getName());
        eventPageActivity.description.setText(currentEvent.getDescription());
        eventPageActivity.admin.setText(currentEvent.getAdminUsername());
        String membersString = "";
        for (String memberUsername: currentEvent.getMembersUsernames())
            membersString += memberUsername + ", ";
        eventPageActivity.members.setText(membersString);
    }

    public void startEventsActivity() {
        Intent intent = new Intent(eventPageActivity, EventsActivity.class);
        eventPageActivity.startActivity(intent);
        eventPageActivity.finish();
    }
}
