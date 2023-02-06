package com.example.loansharkfe.controller.implementations;

import android.content.Intent;

import com.example.loansharkfe.controller.interfaces.ManagePendingRequestsController;
import com.example.loansharkfe.view.FriendsListActivity;
import com.example.loansharkfe.view.ManagePendingRequestsActivity;
import com.example.loansharkfe.view.MenuActivity;

public class LoanSharkManagePendingRequestsController implements ManagePendingRequestsController {

    private final ManagePendingRequestsActivity managePendingRequestsActivity;

    public LoanSharkManagePendingRequestsController(ManagePendingRequestsActivity managePendingRequestsActivity) {
        this.managePendingRequestsActivity = managePendingRequestsActivity;
    }

    public void startFriendsListActivity() {
        Intent intent = new Intent(managePendingRequestsActivity, FriendsListActivity.class);
        managePendingRequestsActivity.startActivity(intent);
        managePendingRequestsActivity.finish();
    }
}
