package com.example.loansharkfe.repository.implementations;

import com.example.loansharkfe.config.PathConfig;
import com.example.loansharkfe.constants.HTTPMethods;
import com.example.loansharkfe.dto.GenericResponse;
import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.repository.interfaces.UserRepository;
import com.example.loansharkfe.util.NetworkingRunnable;
import com.example.loansharkfe.util.Request;

import java.io.IOException;

public class LoanSharkUserRepository implements UserRepository {

    private final Request request = Request.getInstance();

    public LoanSharkUserRepository() {
    }

    public NetworkingRunnable createLoginRunnable(UserLogin userLogin) {
        return new NetworkingRunnable() {
                    @Override
                    public void run() {
                        try {
                            genericResponse = request.post(PathConfig.loginPath, userLogin);
                        } catch (IOException e) {
                            exception = e;
                        }
                    }
                };
    }


    public NetworkingRunnable createSaveNewUserRunnable(UserCreate userCreate) {
        return new NetworkingRunnable() {
            @Override
            public void run() {
                try{
                    genericResponse = request.post(PathConfig.registerPath, userCreate);
                } catch (Exception e){
                    exception = e;
                }
            }
        };
    }
}
