package com.example.loansharkfe.controller.interfaces;

public interface SignUpController {

    void setTextFromSignIn();

    void createNewUser();

    void startSignInActivity();

    void startMenuActivity();

    void updateProfilePicture(String image) throws Exception;

}
