package com.example.loansharkfe.service.interfaces;

import com.example.loansharkfe.dto.EventCreate;
import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.util.NetworkingRunnable;

public interface EventService {

    NetworkingRunnable createSaveNewEventRunnable(EventCreate eventCreate, String jwt) throws FieldCompletedIncorrectly;

}
