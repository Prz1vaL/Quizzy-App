package main.java.Quizzy.Service;

import main.java.Quizzy.Model.AccountType;
import main.java.Quizzy.Model.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserService implements Serializable {

    private Map<String, User> users = new HashMap<>();


    /**
     * Converts a password to a hashed password
     *
     * @param password - password to be hashed
     * @return hashed password
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
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
    }

    public void register(String fullName, String email, String username, String sha256, AccountType accountType, ArrayList<String> coursesEnrolled, Date dateCreated) {
        // Check for empty string in fullName
        if (fullName.isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        // Create a new user
        User user = new User(fullName.toLowerCase(), email.toLowerCase(), username.toLowerCase(), sha256, accountType, coursesEnrolled, dateCreated);
        users.put(username.toLowerCase(), user);
    }

    /**
     * Saves the user - data to a file
     *
     * @throws IOException - if the file is not found
     */
    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/resources/userData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(users);
        o.close();
        f.close();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("src/resources/userData.ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
        users = (Map<String, User>) oi.readObject();
        oi.close();
        fi.close();
    }

    public Map<String, User> validateLogin(String userName, String password) {
        if (users.isEmpty()) {
            throw new IllegalArgumentException("No users found");
        }
        Map<String, User> userInfo = new HashMap<>();
        // CHECK IF the USER-NAME OR PASSWORD IS EMPTY
        if (userName.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        //Check if userName and password match
        if (users.containsKey(userName.toLowerCase())) {
            if (users.get(userName.toLowerCase()).getPassword().equals(password)) {
                userInfo.put(userName.toLowerCase(), users.get(userName.toLowerCase()));
            }
        } else {
            throw new IllegalArgumentException("Username or password is incorrect");
        }
        return userInfo;
    }

    public void deleteAccount(String userName) {
        if (users.containsKey(userName.toLowerCase())) {
            users.remove(userName.toLowerCase());
        } else {
            throw new IllegalArgumentException("Username does not exist");
        }
    }
}
