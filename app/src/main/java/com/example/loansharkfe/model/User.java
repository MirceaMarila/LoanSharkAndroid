package com.example.loansharkfe.model;

import java.util.List;

public class User {

    private Integer id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private Boolean enabled;
    private Boolean accountNonLocked;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private List<Integer> rolesIds;
    private List<Integer> friendsIds;
    private List<Integer> pendingFriendRequestsUsersIds;
    private Integer imageId;
    private String description;


    public User() {
    }

    public User(Integer id, String email, String username, String firstName, String lastName, Boolean enabled, Boolean accountNonLocked, Boolean accountNonExpired, Boolean credentialsNonExpired, List<Integer> roles, List<Integer> friends, List<Integer> pendingFriendRequests, Integer imageId, String description) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.rolesIds = roles;
        this.friendsIds = friends;
        this.pendingFriendRequestsUsersIds = pendingFriendRequests;
        this.imageId = imageId;
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public List<Integer> getRolesIds() {
        return rolesIds;
    }

    public void setRolesIds(List<Integer> rolesIds) {
        this.rolesIds = rolesIds;
    }

    public List<Integer> getFriendsIds() {
        return friendsIds;
    }

    public void setFriendsIds(List<Integer> friendsIds) {
        this.friendsIds = friendsIds;
    }

    public List<Integer> getPendingFriendRequestsUsersIds() {
        return pendingFriendRequestsUsersIds;
    }

    public void setPendingFriendRequestsUsersIds(List<Integer> pendingFriendRequestsUsersIds) {
        this.pendingFriendRequestsUsersIds = pendingFriendRequestsUsersIds;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
