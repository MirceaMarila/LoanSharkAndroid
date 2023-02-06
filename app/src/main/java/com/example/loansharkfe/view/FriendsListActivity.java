package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.AddFriendController;
import com.example.loansharkfe.controller.implementations.LoanSharkFriendsListController;
import com.example.loansharkfe.controller.interfaces.FriendsListController;

public class FriendsListActivity extends AppCompatActivity {

    private Button addFriend, managePandingRequests;
    private RecyclerView friendsListRecyclerView;
    private FriendsListController friendsListController;
    private AddFriendController addFriendController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        addFriend = findViewById(R.id.addFriendFriendsList);
        managePandingRequests = findViewById(R.id.managePendingRequestsFriendsList);
        friendsListRecyclerView = findViewById(R.id.friendsListReclyclerViewFriendsList);

        friendsListController = new LoanSharkFriendsListController(this);
        addFriendController = new AddFriendController(this, this);

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefreshFriendsList);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //refreshData(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFriendController.addFriend();
            }
        });

        managePandingRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendsListController.startManagePendingRequestsActivity();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        friendsListController.startProfileActivity();
    }

    public Context getContext(){
        return this;
    }
}