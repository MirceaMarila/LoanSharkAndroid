package com.example.loansharkfe.controller.interfaces;

import android.widget.TextView;

import java.util.ArrayList;

public interface CreateEventController {

    void startEventsActivity();

    ArrayList<Integer> selectMembersFromFriends(ArrayList<Integer> alreadySelectedMembersIds, TextView textView) throws Exception;

    void setSelectedMembersTextView(TextView textView, ArrayList<Integer> ids) throws Exception;

    void createNewEvent();
}
