package com.example.loansharkfe.controller.implementations;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loansharkfe.controller.interfaces.FriendsListController;
import com.example.loansharkfe.model.FriendCard;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.model.UserProfile;
import com.example.loansharkfe.service.implementations.LoanSharkUserService;
import com.example.loansharkfe.service.implementations.SharedPreferencesService;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.Adapter;
import com.example.loansharkfe.view.FriendsListActivity;
import com.example.loansharkfe.view.ManagePendingRequestsActivity;
import com.example.loansharkfe.view.MenuActivity;
import com.example.loansharkfe.view.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

public class LoanSharkFriendsListController implements FriendsListController {

    private final FriendsListActivity friendsListActivity;
    private AddFriendController addFriendController;
    private UserService userService;
    private LinearLayoutManager layoutManager;
    private Adapter adapter;
    private SharedPreferencesService sharedPreferencesService;



    public LoanSharkFriendsListController(FriendsListActivity friendsListActivity) {
        this.friendsListActivity = friendsListActivity;
        this.addFriendController = new AddFriendController(friendsListActivity.getContext(), friendsListActivity);
        this.userService = new LoanSharkUserService();
        this.sharedPreferencesService = new SharedPreferencesService(friendsListActivity.getApplicationContext());
    }

    public void startProfileActivity() {
        Intent intent = new Intent(friendsListActivity, ProfileActivity.class);
        friendsListActivity.startActivity(intent);
        friendsListActivity.finish();
    }

    public void startManagePendingRequestsActivity(){
        Intent intent = new Intent(friendsListActivity, ManagePendingRequestsActivity.class);
        friendsListActivity.startActivity(intent);
        friendsListActivity.finish();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void fillInRecyclerView(RecyclerView recyclerView) throws Exception {
        User currentUser = userService.getUserByUsername(sharedPreferencesService.getSharedPreferences("user"), friendsListActivity.getApplicationContext(), null);
        UserProfile currentUserProfile = userService.getUserProfileById(Integer.parseInt(sharedPreferencesService.getSharedPreferences("id")), friendsListActivity.getApplicationContext(), null);
        List<Integer> pendingFriendRequestsUsersIds = currentUser.getFriendsIds();

        List<FriendCard> friendsList = new ArrayList<>();
        for (Integer friendId: pendingFriendRequestsUsersIds){
            UserProfile currentFriend = userService.getUserProfileById(friendId, friendsListActivity.getApplicationContext(), null);
            friendsList.add(new FriendCard(currentUserProfile.getImage().get("data"),currentFriend.getUsername(), currentFriend.getFirstName(),currentFriend.getLastName()));
        }

        layoutManager = new LinearLayoutManager(friendsListActivity.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(friendsList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
