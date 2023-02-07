package com.example.loansharkfe.controller.implementations;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loansharkfe.R;
import com.example.loansharkfe.dto.UsersIdsRequest;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.repository.implementations.LoanSharkUserRepository;
import com.example.loansharkfe.repository.interfaces.UserRepository;
import com.example.loansharkfe.service.implementations.LoanSharkUserService;
import com.example.loansharkfe.service.implementations.SharedPreferencesService;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.Json;
import com.example.loansharkfe.util.NetworkingRunnable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AddFriendController {

    private final Context context;
    private ProgressBarController progressBarController;
    private final AppCompatActivity activity;
    private final UserService userService;
    private SharedPreferencesService sharedPreferencesService;
    private final Json json;

    public AddFriendController(Context context, AppCompatActivity activity) {
        this.context = context;
        this.activity = activity;
        this.json = Json.getInstance();
        this.userService = new LoanSharkUserService();
        this.sharedPreferencesService = new SharedPreferencesService(activity.getApplicationContext());

    }

    public void addFriend(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialog_view = activity.getLayoutInflater().inflate(R.layout.add_friend, null);

        TextView errorMessageView = dialog_view.findViewById(R.id.errorMessageAddFriend);
        Button sendRequest = dialog_view.findViewById(R.id.sendRequestBtnAddFriend);
        EditText friendUsername = dialog_view.findViewById(R.id.textFieldAddFriend);
        ProgressBar progressBar = dialog_view.findViewById(R.id.progressBarAddFriend);
        progressBar.setVisibility(View.GONE);

        List<View> allElements = new ArrayList<>();
        allElements.add(sendRequest);
        allElements.add(friendUsername);
        progressBarController = new ProgressBarController(allElements, progressBar);

        builder.setView(dialog_view);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();

        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (String.valueOf(friendUsername.getText()).equals(""))
                        errorMessageView.setText("Username field must not be empty!");

                    else {
                        errorMessageView.setText("");
                        User userFound = userService.getUserByUsername(String.valueOf(friendUsername.getText()), activity.getApplicationContext(), progressBarController);
                        User currentUser = userService.getUserByUsername(sharedPreferencesService.getSharedPreferences("user"), activity.getApplicationContext(), progressBarController);

                        Integer friendId = userFound.getId();
                        Integer myId = currentUser.getId();
                        List<Integer> friendsIds = new ArrayList<>();
                        friendsIds.add(friendId);
                        UsersIdsRequest usersIdsRequest = new UsersIdsRequest(friendsIds);

                        String jwt = sharedPreferencesService.getSharedPreferences("jwt");
                        NetworkingRunnable sendFriendRequestRunnable = userService.createSendFriendRequestRunnable(myId, usersIdsRequest, jwt);
                        Thread sendFriendRequest = new Thread(sendFriendRequestRunnable);

                        progressBarController.showProgressBar();
                        sendFriendRequest.start();
                        sendFriendRequest.join();
                        progressBarController.hideProgressBar();

                        if (sendFriendRequestRunnable.getException() == null){
                            errorMessageView.setTextColor(Color.GREEN);
                            errorMessageView.setText("Request sent to " + friendUsername.getText() + "!");
                            progressBarController.showProgressBar();
                            Handler handler =  new Handler();
                            handler.postDelayed(dialog::dismiss, 1500);
                        }

                        else throw sendFriendRequestRunnable.getException();

                    }}
                catch (FileNotFoundException e){
                    e.printStackTrace();
                    errorMessageView.setText("User with username \"" + friendUsername.getText() + "\" does not exist or a friend request was already sent to that user!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (NullPointerException e){
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
