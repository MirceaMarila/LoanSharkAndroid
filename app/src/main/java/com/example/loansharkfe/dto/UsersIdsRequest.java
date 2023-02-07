package com.example.loansharkfe.dto;

import java.util.List;

public class UsersIdsRequest {

    private List<Integer> usersIds;

    public UsersIdsRequest(List<Integer> usersIds) {
        this.usersIds = usersIds;
    }

    public List<Integer> getUsersIds() {
        return usersIds;
    }
}
