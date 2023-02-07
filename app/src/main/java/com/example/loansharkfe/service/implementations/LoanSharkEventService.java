package com.example.loansharkfe.service.implementations;

import com.example.loansharkfe.dto.EventCreate;
import com.example.loansharkfe.exceptions.ErrorFromServer;
import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.Event;
import com.example.loansharkfe.model.EventsOfUser;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.repository.implementations.LoanSharkEventRepository;
import com.example.loansharkfe.repository.interfaces.EventRepository;
import com.example.loansharkfe.service.interfaces.EventService;
import com.example.loansharkfe.util.Json;
import com.example.loansharkfe.util.NetworkingRunnable;

public class LoanSharkEventService implements EventService {

    private EventRepository eventRepository;
    private Json json;

    public LoanSharkEventService() {
        this.eventRepository = new LoanSharkEventRepository();
        this.json = Json.getInstance();

    }

    public NetworkingRunnable createSaveNewEventRunnable(EventCreate eventCreate, String jwt) throws FieldCompletedIncorrectly {

        if (eventCreate.getName().isEmpty())
            throw new FieldCompletedIncorrectly("Username must not be empty!");

        if (eventCreate.getDescription().isEmpty())
            throw new FieldCompletedIncorrectly("Description must not be empty!");

        if (eventCreate.getMembersIds().size() == 0)
            throw new FieldCompletedIncorrectly("You must add some members!");

        return eventRepository.createSaveNewEventRunnable(eventCreate, jwt);

    }

    public EventsOfUser getEventsOfUser(String jwt, Integer myId) throws Exception {
        NetworkingRunnable getAllEventsRunnable = eventRepository.createGetEventsOfUserRunnable(myId, jwt);
        Thread thread = new Thread(getAllEventsRunnable);
        thread.start();
        thread.join();

        if (getAllEventsRunnable.getException() != null)
            throw getAllEventsRunnable.getException();

        else
            return json.objectMapper.readValue(getAllEventsRunnable.getGenericResponse().getBody(), EventsOfUser.class);
    }

    public Event getEventById(String jwt, Integer eventId) throws Exception {
        NetworkingRunnable getEventByIdRunnable = eventRepository.createGetEventByIdRunnable(eventId, jwt);
        Thread thread = new Thread(getEventByIdRunnable);
        thread.start();
        thread.join();

        if (getEventByIdRunnable.getException() != null)
            throw getEventByIdRunnable.getException();

        else
            return json.objectMapper.readValue(getEventByIdRunnable.getGenericResponse().getBody(), Event.class);
    }
}
