package com.example.loansharkfe.model;

import java.util.HashMap;
import java.util.List;

public class User {

    private Integer id;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean enabled;
    private Boolean accountNonLocked;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private List<Integer> roles;
    private List<Integer> friendsIds;
    private List<Integer> pendingFriendRequestsUsersIds;


    public User() {
    }

    public User(Integer id, String email, String username, String password, String firstName, String lastName, Boolean enabled, Boolean accountNonLocked, Boolean accountNonExpired, Boolean credentialsNonExpired, List<Integer> roles, List<Integer> friends, List<Integer> pendingFriendRequests) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.roles = roles;
        this.friendsIds = friends;
        this.pendingFriendRequestsUsersIds = pendingFriendRequests;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
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
}
