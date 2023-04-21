package main.java.Quizzy.Service;

import main.java.Quizzy.Model.AccountType;
import main.java.Quizzy.Model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StudentService implements Serializable {

    Map<String, Student> students = new HashMap<>();

    public void register(String fullName, String email, String username, String sha256, AccountType accountType, ArrayList<String> coursesEnrolled, Date dateCreated) {
        // Check for empty string in fullName
        if (fullName.isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        // Create a new HashMap for quizScores for each student
        Map<Integer, Integer> quizScores = new HashMap<>();
        Student student = new Student(fullName.toLowerCase(), email.toLowerCase(), sha256, username.toLowerCase(), accountType, coursesEnrolled, dateCreated, quizScores);
        students.put(username.toLowerCase(), student);

    }

    public Map<String, Student> validateLogin(String userName, String password) {
        if (students.isEmpty()) {
            throw new IllegalArgumentException("No users found");
        }
        Map<String, Student> studentInfo = new HashMap<>();
        // CHECK IF the USER-NAME OR PASSWORD IS EMPTY
        if (userName.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        //Check if userName and password match
        if (students.containsKey(userName.toLowerCase())) {
            if (students.get(userName.toLowerCase()).getPassword().equals(password)) {
                studentInfo.put(userName.toLowerCase(), studentInfo.get(userName.toLowerCase()));
            }
        } else {
            throw new IllegalArgumentException("Username or password is incorrect");
        }
        return studentInfo;
    }

    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/resources/studentData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(students);
        o.close();
        f.close();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("src/resources/studentData.ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
        students = (Map<String, Student>) oi.readObject();
        oi.close();
        fi.close();
    }


}
