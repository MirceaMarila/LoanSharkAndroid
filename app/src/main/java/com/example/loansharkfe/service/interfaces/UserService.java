package com.example.loansharkfe.service.interfaces;

import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.util.NetworkingRunnable;

public interface UserService {

    NetworkingRunnable createLoginRunnable(UserLogin userLogin) throws FieldCompletedIncorrectly;

    Boolean validateEmail(String email);

    NetworkingRunnable createSaveNewUserRunnable(UserCreate userCreate) throws FieldCompletedIncorrectly;

}
