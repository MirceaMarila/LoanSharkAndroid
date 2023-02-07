package com.example.loansharkfe.repository.implementations;

import com.example.loansharkfe.config.PathConfig;
import com.example.loansharkfe.dto.EventCreate;
import com.example.loansharkfe.repository.interfaces.EventRepository;
import com.example.loansharkfe.util.NetworkingRunnable;
import com.example.loansharkfe.util.Request;

public class LoanSharkEventRepository implements EventRepository {

    private final Request request = Request.getInstance();

    public LoanSharkEventRepository() {
    }

    public NetworkingRunnable createSaveNewEventRunnable(EventCreate eventCreate, String jwt) {
        return new NetworkingRunnable() {
            @Override
            public void run() {
                try{
                    genericResponse = request.post(PathConfig.createNewEventPath, eventCreate, jwt);
                } catch (Exception e){
                    exception = e;
                }
            }
        };
    }
}
