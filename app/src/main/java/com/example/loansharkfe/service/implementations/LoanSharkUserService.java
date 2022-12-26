package com.example.loansharkfe.service.implementations;

import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.UserCreate;
import com.example.loansharkfe.model.UserLogin;
import com.example.loansharkfe.repository.implementations.LoanSharkUserRepository;
import com.example.loansharkfe.repository.interfaces.UserRepository;
import com.example.loansharkfe.service.interfaces.UserService;
import com.example.loansharkfe.util.NetworkingRunnable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoanSharkUserService implements UserService {

    private UserRepository userRepository;

    public LoanSharkUserService() {
        userRepository = new LoanSharkUserRepository();
    }


    public Boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+$");
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }

    private Boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }


    public NetworkingRunnable createLoginRunnable(UserLogin userLogin) throws FieldCompletedIncorrectly {
        if (userLogin.getUsernameOrEmail().isEmpty())
            throw new FieldCompletedIncorrectly("Username/Email must not be empty");

        if (userLogin.getPassword().isEmpty())
            throw new FieldCompletedIncorrectly("Password must not be empty!");

        return userRepository.createLoginRunnable(userLogin);
    }

    public NetworkingRunnable createSaveNewUserRunnable(UserCreate userCreate) throws FieldCompletedIncorrectly {
        if (!validateEmail(userCreate.getEmail()))
            throw new FieldCompletedIncorrectly("Empty field or invalid email format!");

        if (!validatePassword(userCreate.getPassword()))
            throw new FieldCompletedIncorrectly("Password must contain minimum eight characters, " +
                    "at least one uppercase letter, one lowercase letter and one number");

        if (userCreate.getUsername().isEmpty())
            throw new FieldCompletedIncorrectly("UserCreate name must not be empty!");

        if (userCreate.getFirstName().isEmpty())
            throw new FieldCompletedIncorrectly("First Name must not be empty!");

        if (userCreate.getLastName().isEmpty())
            throw new FieldCompletedIncorrectly("Last Name must not be empty!");

        return userRepository.createSaveNewUserRunnable(userCreate);

    }

}
