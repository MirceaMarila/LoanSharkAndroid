package com.example.loansharkfe.dto;

import java.util.List;

public class EventCreate {

    private String name;
    private String description;
    private Integer adminId;
    private List<Integer> membersIds;

    public EventCreate(String name, String description, Integer adminId, List<Integer> membersIds) {
        this.name = name;
        this.description = description;
        this.adminId = adminId;
        this.membersIds = membersIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public List<Integer> getMembersIds() {
        return membersIds;
    }

    public void setMembersIds(List<Integer> membersIds) {
        this.membersIds = membersIds;
    }
}
