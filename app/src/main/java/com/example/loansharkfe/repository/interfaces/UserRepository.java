package com.example.loansharkfe.repository.interfaces;

import com.example.loansharkfe.dto.UsersIdsRequest;
import com.example.loansharkfe.model.BytesImage;
import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.util.NetworkingRunnable;

public interface UserRepository {

    NetworkingRunnable createLoginRunnable(UserLogin userLogin);

    NetworkingRunnable createSaveNewUserRunnable(UserCreate userCreate);

    NetworkingRunnable getUserByUsernameRunnable(String username, String jwt);

    NetworkingRunnable getUserByEmailRunnable(String email, String jwt);

    NetworkingRunnable getUserByIdRunnable(Integer id, String jwt);

    NetworkingRunnable sendFriendRequestRunnable(Integer myId, UsersIdsRequest usersIdsRequest, String jwt);

    NetworkingRunnable updateProfilePictureRunnable(Integer myId, BytesImage bytesImage, String jwt);

    NetworkingRunnable acceptFriendRequestRunnable(Integer myId, Integer friendId, String jwt);

    NetworkingRunnable declineFriendRequestRunnable(Integer myId, Integer friendId, String jwt);
}
