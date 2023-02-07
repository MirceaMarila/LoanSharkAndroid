package com.example.loansharkfe.model;

import java.util.List;

public class EventsOfUser {

    private List<Event> events;

    public EventsOfUser() {
    }

    public EventsOfUser(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
