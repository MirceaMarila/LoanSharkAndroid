package com.example.loansharkfe.controller.implementations;

import android.content.Intent;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.interfaces.ProfileController;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.service.implementations.LoanSharkUserService;
import com.example.loansharkfe.service.implementations.SharedPreferencesService;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.Json;
import com.example.loansharkfe.view.EventsActivity;
import com.example.loansharkfe.view.FriendsListActivity;
import com.example.loansharkfe.view.MenuActivity;
import com.example.loansharkfe.view.ProfileActivity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.lang.reflect.Field;

public class LoanSharkProfileController implements ProfileController {

    private final ProfileActivity profileActivity;
    private final SharedPreferencesService sharedPreferencesService;
    private final Json json;
    private final UserService userService;

    public LoanSharkProfileController(ProfileActivity profileActivity) {
        this.profileActivity = profileActivity;
        this.sharedPreferencesService = new SharedPreferencesService(profileActivity.getApplicationContext());
        this.json = Json.getInstance();
        this.userService = new LoanSharkUserService();
    }

    public void startMenuActivity() {
        Intent intent = new Intent(profileActivity, MenuActivity.class);
        profileActivity.startActivity(intent);
        profileActivity.finish();
    }

    public void startFriendsListActivity(){
        Intent intent = new Intent(profileActivity, FriendsListActivity.class);
        profileActivity.startActivity(intent);
        profileActivity.finish();
    }

    public void fillInAllTheFields() throws Exception {
            User currentUser = userService.getUserByUsername(sharedPreferencesService.getSharedPreferences("user"), profileActivity.getApplicationContext(), null);
            profileActivity.username.setText(currentUser.getUsername());
            profileActivity.email.setText(currentUser.getEmail());
            profileActivity.firstName.setText(currentUser.getFirstName());
            profileActivity.lastName.setText(currentUser.getLastName());
//            profileActivity.profilePicture.setBackgroundResource(R.drawable.default_profile_pic);
    }


}
