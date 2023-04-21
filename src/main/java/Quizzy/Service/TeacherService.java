package main.java.Quizzy.Service;

import main.java.Quizzy.Model.AccountType;
import main.java.Quizzy.Model.Teacher;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TeacherService implements Serializable {

    private Map<String, Teacher> teachers = new HashMap<>();


    /**
     * Converts a password to a hashed password.
     * Common method for both Teacher and Student.
     *
     * @param password - password to be hashed.
     * @return hashed password.
     */
    public String hashPassword(String password) {
        //Check for empty string
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        String hashedPassword = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            hashedPassword = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
        }
        return hashedPassword;
    }


    public void validateEmail(String email) {
        // First check for empty string
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        // Second check for email-format regex
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email is not in the correct format");
        }
    }

    public void validateUsername(String username) {
        // First check for empty string
        if (username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        // Second check for username-format regex
        if (!username.matches("^[A-Za-z0-9+_.-]+$")) {
            throw new IllegalArgumentException("Username is not in the correct format");
        }
        // Check if the userName already exists
        if (teachers.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
    }

    public void register(String fullName, String email, String username, String sha256, AccountType accountType, ArrayList<String> coursesEnrolled, Date dateCreated) {
        // Check for empty string in fullName
        if (fullName.isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        // Create a new teacher
        Teacher teacher = new Teacher(fullName.toLowerCase(), email.toLowerCase(), username.toLowerCase(), sha256, accountType, coursesEnrolled, dateCreated);
        teachers.put(username.toLowerCase(), teacher);
    }

    /**
     * Saves the user - data to a file
     *
     * @throws IOException - if the file is not found
     */
    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/resources/teacherData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(teachers);
        o.close();
        f.close();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("src/resources/teacherData.ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
        teachers = (Map<String, Teacher>) oi.readObject();
        oi.close();
        fi.close();
    }

    public Map<String, Teacher> validateLogin(String userName, String password) {
        if (teachers.isEmpty()) {
            throw new IllegalArgumentException("No users found");
        }
        Map<String, Teacher> userInfo = new HashMap<>();
        // CHECK IF the USER-NAME OR PASSWORD IS EMPTY
        if (userName.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        //Check if userName and password match
        if (teachers.containsKey(userName.toLowerCase())) {
            if (teachers.get(userName.toLowerCase()).getPassword().equals(password)) {
                userInfo.put(userName.toLowerCase(), teachers.get(userName.toLowerCase()));
            }
        } else {
            throw new IllegalArgumentException("Username or password is incorrect");
        }
        return userInfo;
    }

    public void deleteAccount(String userName) {
        if (teachers.containsKey(userName.toLowerCase())) {
            teachers.remove(userName.toLowerCase());
        } else {
            throw new IllegalArgumentException("Username does not exist");
        }
    }

    public void validatePassword(String userName, String currentPassword) {
        if (currentPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        String hashedPassword = hashPassword(currentPassword);
        if (!teachers.get(userName.toLowerCase()).getPassword().equals(hashedPassword)) {
            throw new IllegalArgumentException("Password is incorrect");
        }
    }

    public void changePassword(String userName, String newPassword) {
        String hashedPassword = hashPassword(newPassword);
        if (newPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        // Check if old password and new password are the same
        if (teachers.get(userName.toLowerCase()).getPassword().equals(hashedPassword)) {
            throw new IllegalArgumentException("New password cannot be the same as the old password");
        } else {
            teachers.get(userName.toLowerCase()).setPassword(hashedPassword);
        }
    }


    public void validateIfTeacherCourse(String courseName, String userName) {
        if (courseName.isEmpty() || userName.isEmpty()) {
            throw new IllegalArgumentException("Empty fields are not allowed");
        }
        if (!teachers.get(userName.toLowerCase()).getCoursesEnrolled().contains(courseName.toLowerCase())) {
            throw new IllegalArgumentException("You are not enrolled in this course or this course does not exist.");
        }
    }

    public void addCourse(String courseName, String userName) {
        if (courseName.isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }
        if (teachers.get(userName.toLowerCase()).getCoursesEnrolled().contains(courseName.toLowerCase())) {
            throw new IllegalArgumentException("You are already enrolled in this course");
        }
        teachers.get(userName.toLowerCase()).getCoursesEnrolled().add(courseName.toLowerCase());
    }

    public void removeCourse(String courseName, String userName) {
        if (courseName.isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }
        if (!teachers.get(userName.toLowerCase()).getCoursesEnrolled().contains(courseName.toLowerCase())) {
            throw new IllegalArgumentException("You are not enrolled in this course");
        }
        teachers.get(userName.toLowerCase()).getCoursesEnrolled().remove(courseName.toLowerCase());
    }


}
