package com.example.loansharkfe.model;

public class FriendCard {

    private String image;
    private String username;
    private String firstName;
    private String lastName;

    public FriendCard(String image, String username, String firstName, String lastName) {
        this.image = image;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
