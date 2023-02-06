package com.example.loansharkfe.controller.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loansharkfe.model.FriendCard;

import java.util.ArrayList;
import java.util.List;

public interface ManagePendingRequestsController {

    void startFriendsListActivity();

    void fillInRecyclerView() throws Exception;

    void showRequestDialog(Integer position) throws Exception;
}
