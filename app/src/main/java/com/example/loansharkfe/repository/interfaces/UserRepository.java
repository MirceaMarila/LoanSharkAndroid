package com.example.loansharkfe.repository.interfaces;

import com.example.loansharkfe.dto.UsersIdsRequest;
import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.util.NetworkingRunnable;

public interface UserRepository {

    NetworkingRunnable createLoginRunnable(UserLogin userLogin);

    NetworkingRunnable createSaveNewUserRunnable(UserCreate userCreate);

    NetworkingRunnable getUserByUsernameRunnable(String username, String jwt);

    NetworkingRunnable getUserByEmailRunnable(String email, String jwt);

    NetworkingRunnable sendFriendRequestRunnable(Integer myId, UsersIdsRequest usersIdsRequest, String jwt);

}
