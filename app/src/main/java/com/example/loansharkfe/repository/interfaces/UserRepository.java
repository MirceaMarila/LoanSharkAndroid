package com.example.loansharkfe.repository.interfaces;

import com.example.loansharkfe.model.User;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.util.NetworkingRunnable;

public interface UserRepository {

    public NetworkingRunnable createLoginThread(UserLogin userLogin);

    public User save(User user);

}
