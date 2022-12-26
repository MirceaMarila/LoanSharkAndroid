package com.example.loansharkfe.controller.implementations;

import android.content.Intent;

import com.example.loansharkfe.service.implementations.SharedPreferencesService;
import com.example.loansharkfe.view.EventsActivity;
import com.example.loansharkfe.view.MenuActivity;
import com.example.loansharkfe.view.ProfileActivity;
import com.example.loansharkfe.view.SignInActivity;

public class MenuController {

    private final MenuActivity menuActivity;
    private final SharedPreferencesService sharedPreferencesService;

    public MenuController(MenuActivity menuActivity) {
        this.menuActivity = menuActivity;
        this.sharedPreferencesService = new SharedPreferencesService(menuActivity.getApplicationContext());
    }

    public void startSignInActivity() {

        Intent intent = new Intent(menuActivity, SignInActivity.class);
        menuActivity.startActivity(intent);
        menuActivity.finish();

    }

    public void startEventsActivity() {

        Intent intent = new Intent(menuActivity, EventsActivity.class);
        menuActivity.startActivity(intent);
        menuActivity.finish();

    }

    public void startProfileActivity() {

        Intent intent = new Intent(menuActivity, ProfileActivity.class);
        menuActivity.startActivity(intent);
        menuActivity.finish();

    }

    public void deleteJwt(){
        sharedPreferencesService.deleteSharedPreferences("jwt");
    }

}
