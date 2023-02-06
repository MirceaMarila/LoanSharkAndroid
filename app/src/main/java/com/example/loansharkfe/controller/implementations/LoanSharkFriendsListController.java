package com.example.loansharkfe.controller.implementations;

import android.content.Intent;

import com.example.loansharkfe.controller.interfaces.FriendsListController;
import com.example.loansharkfe.view.FriendsListActivity;
import com.example.loansharkfe.view.MenuActivity;
import com.example.loansharkfe.view.ProfileActivity;

public class LoanSharkFriendsListController implements FriendsListController {

    private final FriendsListActivity friendsListActivity;
    private AddFriendController addFriendController;



    public LoanSharkFriendsListController(FriendsListActivity friendsListActivity) {
        this.friendsListActivity = friendsListActivity;
        this.addFriendController = new AddFriendController(friendsListActivity.getContext(), friendsListActivity);
    }

    public void startProfileActivity() {
        Intent intent = new Intent(friendsListActivity, ProfileActivity.class);
        friendsListActivity.startActivity(intent);
        friendsListActivity.finish();
    }
}
