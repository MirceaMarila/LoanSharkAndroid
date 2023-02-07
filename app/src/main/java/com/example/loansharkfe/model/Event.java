package com.example.loansharkfe.model;

import java.util.List;

public class Event {

    private Integer id;
    private String name;
    private String description;
    private Integer parentEventId;
    private String parentEventName;
    private Integer adminId;
    private String adminUsername;
    private List<String> membersUsernames;
    private List<String> subEventCards;

    public Event() {
    }

    public Event(Integer id, String name, String description, Integer parentEventId, Integer adminId, String adminUsername, List<String> membersUsernames, List<String> subEventCards, String parentEventName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentEventId = parentEventId;
        this.adminId = adminId;
        this.adminUsername = adminUsername;
        this.membersUsernames = membersUsernames;
        this.subEventCards = subEventCards;
        this.parentEventName = parentEventName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getParentEventId() {
        return parentEventId;
    }

    public void setParentEventId(Integer parentEventId) {
        this.parentEventId = parentEventId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public List<String> getMembersUsernames() {
        return membersUsernames;
    }

    public void setMembersUsernames(List<String> membersUsernames) {
        this.membersUsernames = membersUsernames;
    }

    public List<String> getSubEventCards() {
        return subEventCards;
    }

    public void setSubEventCards(List<String> subEventCards) {
        this.subEventCards = subEventCards;
    }

    public String getParentEventName() {
        return parentEventName;
    }

    public void setParentEventName(String parentEventName) {
        this.parentEventName = parentEventName;
    }
}
