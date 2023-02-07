package com.example.loansharkfe.dto;

import java.util.List;

public class SubEventCreate {

    private String name;
    private String description;
    private Integer adminId;
    private List<Integer> membersIds;
    private Integer parentEventId;

    public SubEventCreate(String name, String description, Integer adminId, List<Integer> membersIds, Integer parentEventId) {
        this.name = name;
        this.description = description;
        this.adminId = adminId;
        this.membersIds = membersIds;
        this.parentEventId = parentEventId;
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

    public Integer getParentEventId() {
        return parentEventId;
    }

    public void setParentEventId(Integer parentEventId) {
        this.parentEventId = parentEventId;
    }
}

