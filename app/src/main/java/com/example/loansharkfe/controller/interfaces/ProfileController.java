package com.example.loansharkfe.controller.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProfileController {

    void startMenuActivity();

    void startFriendsListActivity();

    void fillInAllTheFields() throws Exception;
}
