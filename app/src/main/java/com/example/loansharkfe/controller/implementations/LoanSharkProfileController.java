package com.example.loansharkfe.controller.implementations;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.loansharkfe.controller.interfaces.ProfileController;
import com.example.loansharkfe.dto.ImageDto;
import com.example.loansharkfe.model.UserProfile;
import com.example.loansharkfe.service.implementations.LoanSharkUserService;
import com.example.loansharkfe.service.implementations.SharedPreferencesService;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.Json;
import com.example.loansharkfe.view.FriendsListActivity;
import com.example.loansharkfe.view.MenuActivity;
import com.example.loansharkfe.view.ProfileActivity;

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
            UserProfile currentUser = userService.getUserProfileById(Integer.parseInt(sharedPreferencesService.getSharedPreferences("id")), profileActivity.getApplicationContext(), null);
            profileActivity.username.setText(currentUser.getUsername());
            profileActivity.description.setText(currentUser.getDescription());
            profileActivity.firstName.setText(currentUser.getFirstName());
            profileActivity.lastName.setText(currentUser.getLastName());

            byte[] decodedString = Base64.decode(currentUser.getImage().get("data"), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profileActivity.profilePicture.setImageBitmap(decodedByte);
    }

    public void updateProfilePicture(String image) throws Exception {
        ImageDto imageDto = new ImageDto(image);
        userService.createUpdateProfilePictureRunnable(imageDto, profileActivity.getApplicationContext());
    }


}
