package main.java.Quizzy.Controller;

import main.java.Quizzy.Model.AccountType;
import main.java.Quizzy.Model.User;
import main.java.Quizzy.Service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class QuizzyController {

    UserService userService = new UserService();

    public String hashPassword(String password) {
        return userService.hashPassword(password);
    }

    public void validateEmail(String email) {
        userService.validateEmail(email);
    }

    public void validateUsername(String username) {
        userService.validateUsername(username);
    }

    public void register(String fullName, String email, String username, String sha256, AccountType accountType, ArrayList<String> coursesEnrolled) {
        userService.register(fullName, email, username, sha256, accountType, coursesEnrolled);
    }

    public void saveData() throws IOException {
        userService.saveData();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        userService.loadData();
    }

    public Map<String, User> validateLogin(String userName, String password) {
        return userService.validateLogin(userName, password);
    }

    public void deleteAccount(String userName) {
        userService.deleteAccount(userName);
    }
}
