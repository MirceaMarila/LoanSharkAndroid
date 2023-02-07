package com.example.loansharkfe.service.implementations;

import android.content.Context;

import com.example.loansharkfe.controller.implementations.ProgressBarController;
import com.example.loansharkfe.dto.ImageDto;
import com.example.loansharkfe.dto.UsersIdsRequest;
import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.model.UserProfile;
import com.example.loansharkfe.repository.implementations.LoanSharkUserRepository;
import com.example.loansharkfe.repository.interfaces.UserRepository;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.Json;
import com.example.loansharkfe.util.NetworkingRunnable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoanSharkUserService implements UserService {

    private UserRepository userRepository;
    private Json json;

    public LoanSharkUserService() {
        this.userRepository = new LoanSharkUserRepository();
        this.json = Json.getInstance();

    }


    public Boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+$");
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }

    private Boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }


    public NetworkingRunnable createLoginRunnable(UserLogin userLogin) throws FieldCompletedIncorrectly {
        if (userLogin.getUsernameOrEmail().isEmpty())
            throw new FieldCompletedIncorrectly("Username/Email must not be empty");

        if (userLogin.getPassword().isEmpty())
            throw new FieldCompletedIncorrectly("Password must not be empty!");

        return userRepository.createLoginRunnable(userLogin);
    }

    public NetworkingRunnable createSaveNewUserRunnable(UserCreate userCreate) throws FieldCompletedIncorrectly {
        if (!validateEmail(userCreate.getEmail()))
            throw new FieldCompletedIncorrectly("Empty field or invalid email format!");

        if (!validatePassword(userCreate.getPassword()))
            throw new FieldCompletedIncorrectly("Password must contain minimum eight characters, " +
                    "at least one uppercase letter, one lowercase letter and one number");

        if(userCreate.getUsername().contains("@"))
            throw new FieldCompletedIncorrectly("Username can not contain @");

        if (userCreate.getUsername().isEmpty())
            throw new FieldCompletedIncorrectly("UserCreate name must not be empty!");

        if (userCreate.getFirstName().isEmpty())
            throw new FieldCompletedIncorrectly("First Name must not be empty!");

        if (userCreate.getLastName().isEmpty())
            throw new FieldCompletedIncorrectly("Last Name must not be empty!");

        if (userCreate.getDescription().isEmpty())
            throw new FieldCompletedIncorrectly("Description must not be empty!");

        return userRepository.createSaveNewUserRunnable(userCreate);

    }

    public User getUserById(Integer id, Context applicationContext, ProgressBarController progressBarController) throws Exception {
        SharedPreferencesService sharedPreferencesService = new SharedPreferencesService(applicationContext);
        String jwt = sharedPreferencesService.getSharedPreferences("jwt");
        NetworkingRunnable getUserByIdRunnable = userRepository.getUserByIdRunnable(id, jwt);

        Thread getUserById = new Thread(getUserByIdRunnable);
        if (progressBarController != null)
            progressBarController.showProgressBar();
        getUserById.start();
        getUserById.join();
        if (progressBarController != null)
            progressBarController.hideProgressBar();

        if (getUserByIdRunnable.getException() != null)
            throw getUserByIdRunnable.getException();

        return json.objectMapper.readValue(getUserByIdRunnable.getGenericResponse().getBody(), User.class);
    }

    public UserProfile getUserProfileById(Integer id, Context applicationContext, ProgressBarController progressBarController) throws Exception {
        SharedPreferencesService sharedPreferencesService = new SharedPreferencesService(applicationContext);
        String jwt = sharedPreferencesService.getSharedPreferences("jwt");
        NetworkingRunnable getUserProfileByIdRunnable = userRepository.getUserProfileByIdRunnable(id, jwt);

        Thread getUserProfileById = new Thread(getUserProfileByIdRunnable);
        if (progressBarController != null)
            progressBarController.showProgressBar();
        getUserProfileById.start();
        getUserProfileById.join();
        if (progressBarController != null)
            progressBarController.hideProgressBar();

        if (getUserProfileByIdRunnable.getException() != null)
            throw getUserProfileByIdRunnable.getException();

        return json.objectMapper.readValue(getUserProfileByIdRunnable.getGenericResponse().getBody(), UserProfile.class);
    }

    public User getUserByUsername(String username, Context applicationContext, ProgressBarController progressBarController) throws Exception {
        SharedPreferencesService sharedPreferencesService = new SharedPreferencesService(applicationContext);
        String jwt = sharedPreferencesService.getSharedPreferences("jwt");
        NetworkingRunnable getUserByUsernameRunnable = userRepository.getUserByUsernameRunnable(username, jwt);

        Thread getUserByUsername = new Thread(getUserByUsernameRunnable);
        if (progressBarController != null)
            progressBarController.showProgressBar();
        getUserByUsername.start();
        getUserByUsername.join();
        if (progressBarController != null)
            progressBarController.hideProgressBar();

        if (getUserByUsernameRunnable.getException() != null)
            throw getUserByUsernameRunnable.getException();

        return json.objectMapper.readValue(getUserByUsernameRunnable.getGenericResponse().getBody(), User.class);
    }

    public User getUserByEmail(String email, Context applicationContext, ProgressBarController progressBarController) throws Exception {
        SharedPreferencesService sharedPreferencesService = new SharedPreferencesService(applicationContext);
        String jwt = sharedPreferencesService.getSharedPreferences("jwt");
        NetworkingRunnable getUserByEmailRunnable = userRepository.getUserByEmailRunnable(email, jwt);

        Thread checkUserExistance = new Thread(getUserByEmailRunnable);
        if (progressBarController != null)
            progressBarController.showProgressBar();
        checkUserExistance.start();
        checkUserExistance.join();
        if (progressBarController != null)
            progressBarController.hideProgressBar();

        if (getUserByEmailRunnable.getException() != null)
            throw getUserByEmailRunnable.getException();

        return json.objectMapper.readValue(getUserByEmailRunnable.getGenericResponse().getBody(), User.class);
    }

    public NetworkingRunnable createSendFriendRequestRunnable(Integer myId, UsersIdsRequest usersIdsRequest, String jwt){
        return userRepository.sendFriendRequestRunnable(myId, usersIdsRequest, jwt);
    }

    public void createUpdateProfilePictureRunnable(ImageDto imageDto, Context applicationContext) throws Exception {
        SharedPreferencesService sharedPreferencesService = new SharedPreferencesService(applicationContext);
        String jwt = sharedPreferencesService.getSharedPreferences("jwt");
        Integer myId = getUserByUsername(sharedPreferencesService.getSharedPreferences("user"), applicationContext, null).getId();

        NetworkingRunnable updateProfilePictureRunnable = userRepository.updateProfilePictureRunnable(myId, imageDto, jwt);

        Thread updateProfilePicture = new Thread(updateProfilePictureRunnable);
        updateProfilePicture.start();
        updateProfilePicture.join();

        if (updateProfilePictureRunnable.getException() != null)
            throw updateProfilePictureRunnable.getException();
    }

    public NetworkingRunnable createAcceptFriendRequestRunnable(Integer myId, Integer friendId, Context applicationContext){
        SharedPreferencesService sharedPreferencesService = new SharedPreferencesService(applicationContext);
        String jwt = sharedPreferencesService.getSharedPreferences("jwt");

        return userRepository.acceptFriendRequestRunnable(myId, friendId, jwt);
    }

    public NetworkingRunnable createDeclineFriendRequestRunnable(Integer myId, Integer friendId, Context applicationContext){
        SharedPreferencesService sharedPreferencesService = new SharedPreferencesService(applicationContext);
        String jwt = sharedPreferencesService.getSharedPreferences("jwt");

        return userRepository.declineFriendRequestRunnable(myId, friendId, jwt);
    }

}
