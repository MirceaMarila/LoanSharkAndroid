package com.example.loansharkfe.model;

import java.util.List;

public class Event {

    private Integer id;
    private String name;
    private String description;
    private Integer parentEventId;
    private Integer adminId;
    private List<Integer> membersIds;
    private List<Integer> debtIds;
    private List<Integer> subEventsIds;

    public Event(Integer id, String name, String description, Integer parentEventId, Integer adminId, List<Integer> membersIds, List<Integer> debtIds, List<Integer> subEventsIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentEventId = parentEventId;
        this.adminId = adminId;
        this.membersIds = membersIds;
        this.debtIds = debtIds;
        this.subEventsIds = subEventsIds;
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

    public List<Integer> getMembersIds() {
        return membersIds;
    }

    public void setMembersIds(List<Integer> membersIds) {
        this.membersIds = membersIds;
    }

    public List<Integer> getDebtIds() {
        return debtIds;
    }

    public void setDebtIds(List<Integer> debtIds) {
        this.debtIds = debtIds;
    }

    public List<Integer> getSubEventsIds() {
        return subEventsIds;
    }

    public void setSubEventsIds(List<Integer> subEventsIds) {
        this.subEventsIds = subEventsIds;
    }
}
