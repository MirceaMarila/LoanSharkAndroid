package com.example.loansharkfe.service.implementations;

import com.example.loansharkfe.dto.EventCreate;
import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.repository.implementations.LoanSharkEventRepository;
import com.example.loansharkfe.repository.interfaces.EventRepository;
import com.example.loansharkfe.service.interfaces.EventService;
import com.example.loansharkfe.util.NetworkingRunnable;

public class LoanSharkEventService implements EventService {

    private EventRepository eventRepository;

    public LoanSharkEventService() {
        this.eventRepository = new LoanSharkEventRepository();
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
}
