package main.java.Quizzy.Controller;

import main.java.Quizzy.Model.AccountType;
import main.java.Quizzy.Model.Quiz;
import main.java.Quizzy.Model.QuizBoard;
import main.java.Quizzy.Model.Teacher;
import main.java.Quizzy.Service.QuizBoardService;
import main.java.Quizzy.Service.QuizService;
import main.java.Quizzy.Service.TeacherService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class QuizzyController {

    TeacherService teacherService = new TeacherService();

    QuizBoardService quizBoardService = new QuizBoardService();

    QuizService quizService = new QuizService();

    public String hashPassword(String password) {
        return teacherService.hashPassword(password);
    }

    public void validateEmail(String email) {
        teacherService.validateEmail(email);
    }

    public void validateUsername(String username) {
        teacherService.validateUsername(username);
    }

    public void register(String fullName, String email, String username, String sha256, AccountType accountType, ArrayList<String> coursesEnrolled, Date dateCreated) {
        teacherService.register(fullName, email, username, sha256, accountType, coursesEnrolled, dateCreated);
    }

    public void saveData() throws IOException {
        teacherService.saveData();
        quizBoardService.saveData();
        quizService.saveData();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        teacherService.loadData();
        quizBoardService.loadData();
        quizService.loadData();
    }

    public Map<String, Teacher> validateLogin(String userName, String password) {
        return teacherService.validateLogin(userName, password);
    }

    public void deleteAccount(String userName) {
        teacherService.deleteAccount(userName);
    }

    public void validatePassword(String userName, String currentPassword) {
        teacherService.validatePassword(userName, currentPassword);
    }

    public void changePassword(String userName, String newPassword) {
        teacherService.changePassword(userName, newPassword);
    }

    public ArrayList<String> getStudentsByCourse(String courseName) {
        return teacherService.getStudentsByCourse(courseName);
    }

    public void validateIfTeacherCourse(String courseName, String userName) {
        teacherService.validateIfTeacherCourse(courseName, userName);
    }

    public void addCourse(String courseName, String userName) {
        teacherService.addCourse(courseName, userName);
    }

    public void removeCourse(String courseName, String userName) {
        teacherService.removeCourse(courseName, userName);
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

    public Map<Integer, QuizBoard> viewQuizBoardByQuizBoardID(String teacherUserName, String quizBoardID) {
        return quizBoardService.viewQuizBoardByQuizBoardID(teacherUserName, quizBoardID);
    }


    public Map<Integer, QuizBoard> editQuizBoard(String userName, String quizBoardID) {
        return quizBoardService.editQuizBoard(userName, quizBoardID);
    }

    public void addQuestion(int quizBoardID, String question, String correctAnswer, float marks) {
        quizService.addQuestion(quizBoardID, question, correctAnswer, marks);
    }

    public Map<Integer, Quiz> viewAllQuestions(int quizBoardID) {
        return quizService.viewAllQuestions(quizBoardID);
    }

    public void updateQuizBoardQuestion(int quizBoardID) {
        quizBoardService.updateQuizBoardQuestion(quizBoardID);
    }

    public void deleteQuestion(int quizBoardID, int questionNo) {
        quizService.deleteQuestion(quizBoardID, questionNo);
    }

    public void deleteQuizBoardQuestion(int quizBoardID) {
        quizBoardService.deleteQuizBoardQuestion(quizBoardID);
    }
}
