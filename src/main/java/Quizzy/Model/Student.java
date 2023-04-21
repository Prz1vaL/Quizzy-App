package main.java.Quizzy.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {
    private String fullName;

    private String email;

    private String password;

    private String username;

    private AccountType accountType;

    private ArrayList<String> coursesEnrolled;

    private Date dateCreated;

    private Map<Integer, Float> quizScores = new HashMap<>();

    public Student(String fullName, String email, String password, String username, AccountType accountType, ArrayList<String> coursesEnrolled, Date dateCreated, Map<Integer, Float> quizScores) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.accountType = accountType;
        this.coursesEnrolled = coursesEnrolled;
        this.dateCreated = dateCreated;
        this.quizScores = quizScores;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public ArrayList<String> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public void setCoursesEnrolled(ArrayList<String> coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Map<Integer, Float> getQuizScores() {
        return quizScores;
    }

    public void setQuizScores(Map<Integer, Float> quizScores) {
        this.quizScores = quizScores;
    }
}
