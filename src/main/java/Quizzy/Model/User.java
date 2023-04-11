package main.java.Quizzy.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * User class - Stores the user's information
 */
public class User implements Serializable {
    private String fullName;

    private String email;

    private String password;

    private String username;

    private AccountType accountType;

    private ArrayList<String> coursesEnrolled;

    public User(String fullName, String email, String username, String password, AccountType accountType, ArrayList<String> coursesEnrolled) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.accountType = accountType;
        this.coursesEnrolled = coursesEnrolled;
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
}
