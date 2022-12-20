package com.example.loansharkfe.service.implementations;

import com.example.loansharkfe.exceptions.FieldCompletedIncorrectly;
import com.example.loansharkfe.model.User;
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


    public NetworkingRunnable createLoginRunnable(UserLogin userLogin) throws FieldCompletedIncorrectly {
        if (userLogin.getUsernameOrEmail().isEmpty())
            throw new FieldCompletedIncorrectly("Username/Email must not be empty");

        if (userLogin.getPassword().isEmpty())
            throw new FieldCompletedIncorrectly("Password must not be empty!");

        return userRepository.createLoginRunnable(userLogin);
    }

    public NetworkingRunnable saveNewUser(User user) {
        return null;
    }

}
