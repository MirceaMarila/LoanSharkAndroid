package com.example.loansharkfe.model;

public class FriendCard {

    private int image;
    private String username;
    private String firstName;
    private String lastName;

    public FriendCard(int image, String username, String firstName, String lastName) {
        this.image = image;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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
