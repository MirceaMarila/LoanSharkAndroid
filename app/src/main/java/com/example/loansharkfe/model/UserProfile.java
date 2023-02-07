package com.example.loansharkfe.model;

import java.util.HashMap;

public class UserProfile {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private HashMap<String, String> image;

    public UserProfile() {
    }

    public UserProfile(Integer id, String username, String firstName, String lastName, String description, HashMap<String, String> image) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, String> getImage() {
        return image;
    }

    public void setImage(HashMap<String, String> image) {
        this.image = image;
    }
}
