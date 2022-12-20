package com.example.loansharkfe.repository.interfaces;

import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.util.NetworkingRunnable;

public interface UserRepository {

    public NetworkingRunnable createLoginRunnable(UserLogin userLogin);

    public NetworkingRunnable createSaveNewUserRunnable(UserCreate userCreate);

}
