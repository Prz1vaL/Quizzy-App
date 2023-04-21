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

    private Map<Integer, Integer> quizScores = new HashMap<>();

}
