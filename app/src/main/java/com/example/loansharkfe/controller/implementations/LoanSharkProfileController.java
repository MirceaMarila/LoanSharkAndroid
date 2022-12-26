package com.example.loansharkfe.controller.implementations;

import android.content.Intent;

import com.example.loansharkfe.controller.interfaces.ProfileController;
import com.example.loansharkfe.view.EventsActivity;
import com.example.loansharkfe.view.MenuActivity;
import com.example.loansharkfe.view.ProfileActivity;

public class LoanSharkProfileController implements ProfileController {

    private final ProfileActivity profileActivity;

    public LoanSharkProfileController(ProfileActivity profileActivity) {
        this.profileActivity = profileActivity;
    }

    public void startMenuActivity() {
        Intent intent = new Intent(profileActivity, MenuActivity.class);
        profileActivity.startActivity(intent);
        profileActivity.finish();
    }
}
