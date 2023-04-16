package main.java.Quizzy.Controller;

import main.java.Quizzy.Model.AccountType;
import main.java.Quizzy.Model.QuizBoard;
import main.java.Quizzy.Model.User;
import main.java.Quizzy.Service.QuizBoardService;
import main.java.Quizzy.Service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class QuizzyController {

    UserService userService = new UserService();

    QuizBoardService quizBoardService = new QuizBoardService();

    public String hashPassword(String password) {
        return userService.hashPassword(password);
    }

    public void validateEmail(String email) {
        userService.validateEmail(email);
    }

    public void validateUsername(String username) {
        userService.validateUsername(username);
    }

    public void register(String fullName, String email, String username, String sha256, AccountType accountType, ArrayList<String> coursesEnrolled, Date dateCreated) {
        userService.register(fullName, email, username, sha256, accountType, coursesEnrolled, dateCreated);
    }

    public void saveData() throws IOException {
        userService.saveData();
        quizBoardService.saveData();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        userService.loadData();
        quizBoardService.loadData();
    }

    public Map<String, User> validateLogin(String userName, String password) {
        return userService.validateLogin(userName, password);
    }

    public void deleteAccount(String userName) {
        userService.deleteAccount(userName);
    }

    public void validatePassword(String userName, String currentPassword) {
        userService.validatePassword(userName, currentPassword);
    }

    public void changePassword(String userName, String newPassword) {
        userService.changePassword(userName, newPassword);
    }

    public ArrayList<String> getStudentsByCourse(String courseName) {
        return userService.getStudentsByCourse(courseName);
    }

    public void validateIfTeacherCourse(String courseName, String userName) {
        userService.validateIfTeacherCourse(courseName, userName);
    }

    public void addCourse(String courseName, String userName) {
        userService.addCourse(courseName, userName);
    }

    public void removeCourse(String courseName, String userName) {
        userService.removeCourse(courseName, userName);
    }

    public int createQuizBoard(String quizBoardName, String courseName, Date dateCreated, String createdBy) {
        return quizBoardService.createQuizBoard(quizBoardName, courseName, dateCreated, createdBy);
    }

    public Map<Integer, QuizBoard> viewQuizBoardsByCourse(String courseName, String createdBy) {
        return quizBoardService.viewQuizBoardsByCourse(courseName, createdBy);
    }

    public Map<Integer, QuizBoard> viewAllQuizBoards(String createdBy) {
        return quizBoardService.viewAllQuizBoards(createdBy);
    }

    public void deleteQuizBoard(String quizBoardID) {
        quizBoardService.deleteQuizBoard(quizBoardID);

    }
}
