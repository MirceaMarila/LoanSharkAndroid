package com.example.loansharkfe.controller.interfaces;

import androidx.recyclerview.widget.RecyclerView;

public interface FriendsListController {

    void startProfileActivity();

    void startManagePendingRequestsActivity();

    void fillInRecyclerView(RecyclerView recyclerView) throws Exception;
}
