package com.example.loansharkfe.service.interfaces;

import android.content.Context;

import com.example.loansharkfe.controller.implementations.ProgressBarController;
import com.example.loansharkfe.dto.ImageDto;
import com.example.loansharkfe.dto.UsersIdsRequest;
import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.model.UserProfile;
import com.example.loansharkfe.util.NetworkingRunnable;

public interface UserService {

    NetworkingRunnable createLoginRunnable(UserLogin userLogin) throws FieldCompletedIncorrectly;

    Boolean validateEmail(String email);

    NetworkingRunnable createSaveNewUserRunnable(UserCreate userCreate) throws FieldCompletedIncorrectly;

    User getUserByUsername(String username, Context applicationContext, ProgressBarController progressBarController) throws Exception;

    User getUserById(Integer id, Context applicationContext, ProgressBarController progressBarController) throws Exception;

    User getUserByEmail(String email, Context applicationContext, ProgressBarController progressBarController) throws Exception;

    NetworkingRunnable createSendFriendRequestRunnable(Integer myId, UsersIdsRequest usersIdsRequest, String jwt);

    void createUpdateProfilePictureRunnable(ImageDto imageDto, Context applicationContext) throws Exception;

    NetworkingRunnable createAcceptFriendRequestRunnable(Integer myId, Integer friendId, Context applicationContext);

    NetworkingRunnable createDeclineFriendRequestRunnable(Integer myId, Integer friendId, Context applicationContext);

    UserProfile getUserProfileById(Integer id, Context applicationContext, ProgressBarController progressBarController) throws Exception;
}
