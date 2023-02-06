package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkManagePendingRequestsController;
import com.example.loansharkfe.controller.interfaces.ManagePendingRequestsController;

public class ManagePendingRequestsActivity extends AppCompatActivity {

    private ManagePendingRequestsController managePendingRequestsController;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pending_requests);
        recyclerView = findViewById(R.id.recyclerViewManagePendingRequests);

        managePendingRequestsController = new LoanSharkManagePendingRequestsController(this);

//        recyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        // do whatever
//                    }
//                })
//        );
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        managePendingRequestsController.startFriendsListActivity();
    }
}