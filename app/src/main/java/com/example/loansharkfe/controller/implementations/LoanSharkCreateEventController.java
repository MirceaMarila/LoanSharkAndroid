package com.example.loansharkfe.controller.implementations;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loansharkfe.R;
import com.example.loansharkfe.constants.BugTypes;
import com.example.loansharkfe.controller.interfaces.CreateEventController;
import com.example.loansharkfe.dto.EventCreate;
import com.example.loansharkfe.exceptions.ErrorFromServer;
import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.Event;
import com.example.loansharkfe.model.FriendCard;
import com.example.loansharkfe.model.User;
import com.example.loansharkfe.model.UserProfile;
import com.example.loansharkfe.service.implementations.LoanSharkEventService;
import com.example.loansharkfe.service.implementations.LoanSharkUserService;
import com.example.loansharkfe.service.implementations.SharedPreferencesService;
import com.example.loansharkfe.service.interfaces.EventService;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.Adapter;
import com.example.loansharkfe.util.Json;
import com.example.loansharkfe.util.NetworkingRunnable;
import com.example.loansharkfe.util.RecyclerItemClickListener;
import com.example.loansharkfe.view.CreateEventActivity;
import com.example.loansharkfe.view.EventsActivity;
import com.example.loansharkfe.view.MenuActivity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class LoanSharkCreateEventController implements CreateEventController {

    private final CreateEventActivity createEventActivity;
    private UserService userService;
    private EventService eventService;
    private SharedPreferencesService sharedPreferencesService;
    private LinearLayoutManager layoutManager;
    private Adapter adapter;
    private final Json json;
    private final ProgressBarController progressBarController;
    private final BugReportController bugReportController;


    public LoanSharkCreateEventController(CreateEventActivity createEventActivity) {
        this.createEventActivity = createEventActivity;
        this.userService = new LoanSharkUserService();
        this.sharedPreferencesService = new SharedPreferencesService(createEventActivity.getApplicationContext());
        this.eventService = new LoanSharkEventService();
        this.json = Json.getInstance();
        this.progressBarController = new ProgressBarController(createEventActivity.all_elements, createEventActivity.progressBar);
        this.bugReportController = new BugReportController(createEventActivity.getContext(), createEventActivity.errorMessage, createEventActivity.all_elements, createEventActivity.progressBar, createEventActivity);
    }

    public void startEventsActivity() {
        Intent intent = new Intent(createEventActivity, EventsActivity.class);
        createEventActivity.startActivity(intent);
        createEventActivity.finish();
    }

    public ArrayList<Integer> selectMembersFromFriends(ArrayList<Integer> alreadySelectedMembersIds, TextView textView) throws Exception {
        User currentUser = userService.getUserByUsername(sharedPreferencesService.getSharedPreferences("user"), createEventActivity.getApplicationContext(), null);
        List<Integer> friendsIds = currentUser.getFriendsIds();
        for (Integer friendId: alreadySelectedMembersIds)
            friendsIds.remove(Integer.valueOf(friendId));

        ArrayList<String> selectedMembersUsernames = new ArrayList<>();
        ArrayList<Integer> selectedMembersIds = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(createEventActivity.getContext());
        View dialog_view = createEventActivity.getLayoutInflater().inflate(R.layout.add_members_dialog, null);

        Button addSelected = dialog_view.findViewById(R.id.addSelectedAddMembers);
        RecyclerView recyclerView = dialog_view.findViewById(R.id.recyclerViewAddMembers);
        TextView selectedFriends = dialog_view.findViewById(R.id.membersSelectedAddMembers);

        fillRecyclerView(recyclerView, friendsIds);

        builder.setView(dialog_view);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(dialog_view.getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        try {
                            UserProfile currentFriend = userService.getUserProfileById(friendsIds.get(position), createEventActivity.getApplicationContext(), null);
                            selectedMembersUsernames.add(currentFriend.getUsername());
                            selectedMembersIds.add(currentFriend.getId());
                            String message = "Members: ";

                            for (String member: selectedMembersUsernames)
                                message += member + ", ";

                            selectedFriends.setText(message);

                            for (Integer friendId: selectedMembersIds)
                                friendsIds.remove(Integer.valueOf(friendId));

                            fillRecyclerView(recyclerView, friendsIds);

                        }
                        catch (Exception exception){
                            exception.printStackTrace();
                        }
                    }
                })
        );

        addSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    setSelectedMembersTextView(textView, selectedMembersIds);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

        selectedMembersIds.addAll(alreadySelectedMembersIds);
        setSelectedMembersTextView(textView, selectedMembersIds);
        return selectedMembersIds;
    }

    private void fillRecyclerView(RecyclerView recyclerView, List<Integer> friendsIds) throws Exception {

        List<FriendCard> friendsList = new ArrayList<>();
        for (Integer friendId: friendsIds){
            UserProfile currentFriend = userService.getUserProfileById(friendId, createEventActivity.getApplicationContext(), null);
            friendsList.add(new FriendCard(currentFriend.getImage().get("data"),currentFriend.getUsername(), currentFriend.getFirstName(),currentFriend.getLastName()));
        }

        layoutManager = new LinearLayoutManager(createEventActivity.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(friendsList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setSelectedMembersTextView(TextView textView, ArrayList<Integer> ids) throws Exception {
        String message = "Added members: ";
        for (Integer friendId: ids){
            UserProfile currentFriend = userService.getUserProfileById(friendId, createEventActivity.getApplicationContext(), null);
            message += currentFriend.getUsername() + ", ";
        }

        textView.setText(message);
    }

    public void createNewEvent(){
        EventCreate eventCreate = new EventCreate(
          createEventActivity.name.getText().toString(),
          createEventActivity.description.getText().toString(),
          Integer.parseInt(sharedPreferencesService.getSharedPreferences("id")),
          createEventActivity.members
        );

        try {
            NetworkingRunnable createNewEventRunnable = eventService.createSaveNewEventRunnable(eventCreate, sharedPreferencesService.getSharedPreferences("jwt"));
            Thread thread = new Thread(createNewEventRunnable);
            progressBarController.showProgressBar();
            thread.start();
            thread.join();
            progressBarController.hideProgressBar();

            if (createNewEventRunnable.getException() != null)
                throw createNewEventRunnable.getException();

            Integer code = createNewEventRunnable.getGenericResponse().getResponseCode();

            if (code != 201)
                throw new ErrorFromServer(createNewEventRunnable.getGenericResponse().getResponseMessage());

            startEventsActivity();


        } catch (ErrorFromServer e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.USER);
        } catch (FieldCompletedIncorrectly e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "One field is empty or incorrectly completed!", BugTypes.USER);
        } catch (InterruptedException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.OTHER);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.CODE);
        } catch (ProtocolException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.CODE);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.CODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Please try again!", BugTypes.CODE);
        } catch (ConnectException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Connection problem. Check internet connection and try again. The server might be offline.", BugTypes.OTHER);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Connection problem. Check internet connection and try again. The server might be offline.", BugTypes.OTHER);

        } catch (IOException e) {
            e.printStackTrace();
            if (!e.getMessage().equals("No authentication challenges found"))
                bugReportController.handleBug(e, "Something went wrong. Check internet connection and try again!", BugTypes.OTHER);

        } catch (Exception e) {
            e.printStackTrace();
            bugReportController.handleBug(e, "Something went wrong. Check internet connection and try again!", BugTypes.OTHER);
        }
    }
}
