package com.example.loansharkfe.service.interfaces;

import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.util.NetworkingRunnable;

public interface UserService {

    NetworkingRunnable createLoginRunnable(UserLogin userLogin) throws FieldCompletedIncorrectly;

    Boolean validateEmail(String email);

    User saveNewUser(User user);

}
