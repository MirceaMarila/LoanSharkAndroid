package com.example.loansharkfe.controller.implementations;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.interfaces.ManagePendingRequestsController;
import com.example.loansharkfe.model.FriendCard;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.model.UserProfile;
import com.example.loansharkfe.service.implementations.LoanSharkUserService;
import com.example.loansharkfe.service.implementations.SharedPreferencesService;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.Adapter;
import com.example.loansharkfe.util.NetworkingRunnable;
import com.example.loansharkfe.view.FriendsListActivity;
import com.example.loansharkfe.view.ManagePendingRequestsActivity;
import com.example.loansharkfe.view.MenuActivity;

import java.util.ArrayList;
import java.util.List;

public class LoanSharkManagePendingRequestsController implements ManagePendingRequestsController {

    private final ManagePendingRequestsActivity managePendingRequestsActivity;
    private final UserService userService;
    private final SharedPreferencesService  sharedPreferencesService;
    private LinearLayoutManager layoutManager;
    private List<FriendCard> friendsList;
    private Adapter adapter;
    private RecyclerView recyclerView;

    public LoanSharkManagePendingRequestsController(ManagePendingRequestsActivity managePendingRequestsActivity, RecyclerView recyclerView) {
        this.managePendingRequestsActivity = managePendingRequestsActivity;
        this.userService = new LoanSharkUserService();
        this.sharedPreferencesService = new SharedPreferencesService(managePendingRequestsActivity.getApplicationContext());
        this.recyclerView = recyclerView;
    }

    public void startFriendsListActivity() {
        Intent intent = new Intent(managePendingRequestsActivity, FriendsListActivity.class);
        managePendingRequestsActivity.startActivity(intent);
        managePendingRequestsActivity.finish();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void fillInRecyclerView() throws Exception {
        User currentUser = userService.getUserByUsername(sharedPreferencesService.getSharedPreferences("user"), managePendingRequestsActivity.getApplicationContext(), null);
        List<Integer> pendingFriendRequestsUsersIds = currentUser.getPendingFriendRequestsUsersIds();

        friendsList = new ArrayList<>();
        for (Integer friendId: pendingFriendRequestsUsersIds){
            UserProfile currentFriend = userService.getUserProfileById(friendId, managePendingRequestsActivity.getApplicationContext(), null);
            friendsList.add(new FriendCard(currentFriend.getImage().get("data"),currentFriend.getUsername(), currentFriend.getFirstName(),currentFriend.getLastName()));
        }

        layoutManager = new LinearLayoutManager(managePendingRequestsActivity.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(friendsList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void showRequestDialog(Integer position) throws Exception {

        AlertDialog.Builder builder = new AlertDialog.Builder(managePendingRequestsActivity.getContext());
        View dialog_view = managePendingRequestsActivity.getLayoutInflater().inflate(R.layout.friend_request_dialog, null);

        TextView message = dialog_view.findViewById(R.id.messageFriendRequestDialog);
        TextView errorMessage = dialog_view.findViewById(R.id.errorFriendRequestDialog);
        Button accept = dialog_view.findViewById(R.id.acceptBtnFriendRequestDialog);
        Button decline = dialog_view.findViewById(R.id.declineBtnFriendRequestDialog);
        ProgressBar progressBar = dialog_view.findViewById(R.id.progressBarFriendRequestDialog);

        ProgressBarController progressBarController;
        List<View> allElements = new ArrayList<>();
        allElements.add(accept);
        allElements.add(decline);
        progressBarController = new ProgressBarController(allElements, progressBar);

        builder.setView(dialog_view);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();

        String friendCardUsername = friendsList.get(position).getUsername();
        progressBarController.showProgressBar();
        User currentUser = userService.getUserByUsername(sharedPreferencesService.getSharedPreferences("user"), managePendingRequestsActivity.getApplicationContext(), null);
        User pendingFriend = userService.getUserByUsername(friendCardUsername, managePendingRequestsActivity.getApplicationContext(), null);
        progressBarController.hideProgressBar();
        message.setText(friendCardUsername + " wants to be your shark!");

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    NetworkingRunnable acceptFriendRequestRunnable = userService.createAcceptFriendRequestRunnable(currentUser.getId(), pendingFriend.getId(), managePendingRequestsActivity.getApplicationContext());
                    Thread acceptFriendRequest = new Thread(acceptFriendRequestRunnable);

                    progressBarController.showProgressBar();
                    acceptFriendRequest.start();
                    acceptFriendRequest.join();
                    progressBarController.hideProgressBar();

                    if (acceptFriendRequestRunnable.getException() == null) {
                        fillInRecyclerView();
                        errorMessage.setTextColor(Color.GREEN);
                        errorMessage.setText("Request accepted!");
                        progressBarController.showProgressBar();
                        Handler handler =  new Handler();
                        handler.postDelayed(dialog::dismiss, 1500);
                    }
                    else {
                        errorMessage.setTextColor(Color.RED);
                        errorMessage.setText("Something went wrong, please try again!");

                    }
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                } catch (Exception exception){
                    exception.printStackTrace();
                }

            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    NetworkingRunnable declineFriendRequestRunnable = userService.createDeclineFriendRequestRunnable(currentUser.getId(), pendingFriend.getId(), managePendingRequestsActivity.getApplicationContext());
                    Thread declineFriendRequest = new Thread(declineFriendRequestRunnable);

                    progressBarController.showProgressBar();
                    declineFriendRequest.start();
                    declineFriendRequest.join();
                    progressBarController.hideProgressBar();

                    if (declineFriendRequestRunnable.getException() == null) {
                        fillInRecyclerView();
                        errorMessage.setTextColor(Color.GREEN);
                        errorMessage.setText("Request declined!");
                        progressBarController.showProgressBar();
                        Handler handler =  new Handler();
                        handler.postDelayed(dialog::dismiss, 1500);
                    }
                    else {
                        errorMessage.setTextColor(Color.RED);
                        errorMessage.setText("Something went wrong, please try again!");

                    }
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                } catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
    }
}
