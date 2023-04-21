package main.java.Quizzy.Service;

import main.java.Quizzy.Model.AccountType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class StudentService implements Serializable {
    private String fullName;

    private String email;

    private String password;

    private String username;

    private AccountType accountType;

    private ArrayList<String> coursesEnrolled;

    private Date dateCreated;

    private Map<Integer, Integer> quizScores;
}
