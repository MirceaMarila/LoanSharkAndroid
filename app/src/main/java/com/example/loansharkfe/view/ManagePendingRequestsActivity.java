package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkManagePendingRequestsController;
import com.example.loansharkfe.model.FriendCard;
import com.example.loansharkfe.util.RecyclerItemClickListener;
import com.example.loansharkfe.controller.interfaces.ManagePendingRequestsController;

import java.util.ArrayList;
import java.util.List;

public class ManagePendingRequestsActivity extends AppCompatActivity {

    private ManagePendingRequestsController managePendingRequestsController;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pending_requests);
        recyclerView = findViewById(R.id.recyclerViewManagePendingRequests);

        managePendingRequestsController = new LoanSharkManagePendingRequestsController(this, recyclerView);
        try {
            managePendingRequestsController.fillInRecyclerView();
        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        try {
                            managePendingRequestsController.showRequestDialog(position);
                        }
                        catch (Exception exception){
                            exception.printStackTrace();
                        }
                    }
                })
        );
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        managePendingRequestsController.startFriendsListActivity();
    }

    public Context getContext(){
        return this;
    }
}