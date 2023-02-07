package com.example.loansharkfe.repository.interfaces;

import com.example.loansharkfe.dto.EventCreate;
import com.example.loansharkfe.util.NetworkingRunnable;

public interface EventRepository {

    NetworkingRunnable createSaveNewEventRunnable(EventCreate eventCreate, String jwt);
}
