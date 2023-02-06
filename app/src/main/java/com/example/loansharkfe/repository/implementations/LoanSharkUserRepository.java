package com.example.loansharkfe.repository.implementations;

import com.example.loansharkfe.config.PathConfig;
import com.example.loansharkfe.dto.UsersIdsRequest;
import com.example.loansharkfe.model.BytesImage;
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
                            genericResponse = request.post(PathConfig.loginPath, userLogin, null);
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
                    genericResponse = request.post(PathConfig.registerPath, userCreate, null);
                } catch (Exception e){
                    exception = e;
                }
            }
        };
    }

    public NetworkingRunnable getUserByUsernameRunnable(String username, String jwt) {
        return new NetworkingRunnable() {
            @Override
            public void run() {
                try{
                    genericResponse = request.get(PathConfig.getUserByUsernamePath + "/" + username, null, jwt);
                } catch (Exception e){
                    exception = e;
                }
            }
        };
    }

    public NetworkingRunnable getUserByEmailRunnable(String email, String jwt) {
        return new NetworkingRunnable() {
            @Override
            public void run() {
                try{
                    genericResponse = request.get(PathConfig.getUserByEmailPath + "/" + email, null, jwt);
                } catch (Exception e){
                    exception = e;
                }
            }
        };
    }

    public NetworkingRunnable getUserByIdRunnable(Integer id, String jwt) {
        return new NetworkingRunnable() {
            @Override
            public void run() {
                try{
                    genericResponse = request.get(PathConfig.getUserByIdPath + "/" + id, null, jwt);
                } catch (Exception e){
                    exception = e;
                }
            }
        };
    }

    public NetworkingRunnable sendFriendRequestRunnable(Integer myId, UsersIdsRequest usersIdsRequest, String jwt) {
        return new NetworkingRunnable() {
            @Override
            public void run() {
                try{
                    genericResponse = request.put(PathConfig.sendFriendRequestPath + "/" + myId, usersIdsRequest, jwt);
                } catch (Exception e){
                    exception = e;
                }
            }
        };
    }

    public NetworkingRunnable updateProfilePictureRunnable(Integer myId, BytesImage bytesImage, String jwt) {
        return new NetworkingRunnable() {
            @Override
            public void run() {
                try{
                    genericResponse = request.put(PathConfig.updateProfilePicturePath + "/" + myId, bytesImage, jwt);
                } catch (Exception e){
                    exception = e;
                }
            }
        };
    }

    public NetworkingRunnable acceptFriendRequestRunnable(Integer myId, Integer friendId, String jwt) {
        return new NetworkingRunnable() {
            @Override
            public void run() {
                try{
                    genericResponse = request.put(PathConfig.manageFriendRequestPath + "/" + myId + "/accept/" + friendId, null, jwt);
                } catch (Exception e){
                    exception = e;
                }
            }
        };
    }

    public NetworkingRunnable declineFriendRequestRunnable(Integer myId, Integer friendId, String jwt) {
        return new NetworkingRunnable() {
            @Override
            public void run() {
                try{
                    genericResponse = request.put(PathConfig.manageFriendRequestPath + "/" + myId + "/decline/" + friendId, null, jwt);
                } catch (Exception e){
                    exception = e;
                }
            }
        };
    }

}
