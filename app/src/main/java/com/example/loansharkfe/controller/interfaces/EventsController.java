package com.example.loansharkfe.controller.interfaces;

import com.example.loansharkfe.model.Event;
import com.example.loansharkfe.model.EventsOfUser;

public interface EventsController {

    void startMenuActivity();

    void startCreateEventActivity();

    EventsOfUser fillInRecyclerView() throws Exception;

    void startEventProfileActivity(Event event);
}
