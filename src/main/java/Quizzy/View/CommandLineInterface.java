package main.java.Quizzy.View;

import main.java.Quizzy.Controller.QuizzyController;
import main.java.Quizzy.Model.AccountType;
import main.java.Quizzy.Model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandLineInterface implements Serializable {

    QuizzyController quizzyController = new QuizzyController();
    ArrayList<String> coursesEnrolled = new ArrayList<>();
    private Scanner scanner;

    private Map<String, User> userInfo = new HashMap<>();

    public void run() {
        load();
        start();
    }

    private void load() {
        System.out.println("Loading Data...");
        try {
            quizzyController.loadData();
            System.out.println("Data Loaded Successfully!");
            System.out.println("***************************************");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void logo() {
        String logo = """
                  ______      __    __   __   ________   ________  ____    ____\s
                 /  __  \\    |  |  |  | |  | |       /  |       /  \\   \\  /   /\s
                |  |  |  |   |  |  |  | |  | `---/  /   `---/  /    \\   \\/   / \s
                |  |  |  |   |  |  |  | |  |    /  /       /  /      \\_    _/  \s
                |  `--'  '--.|  `--'  | |  |   /  /----.  /  /----.    |  |    \s
                 \\_____\\_____\\\\______/  |__|  /________| /________|    |__|    \s
                                                                        1.0     \s
                """;
        System.out.println(logo);
    }

    private void introMessage() {
        System.out.println("***************************************");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Save Data");
        System.out.println("4. Exit");
        System.out.println("Please select an option: ");
        System.out.println("***************************************");
    }

    private void start() {
        logo();
        introMessage();
        String line;
        scanner = new Scanner(System.in);
        try {
            do {
                line = scanner.nextLine();
                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1' -> {
                            login();
                        }
                        case '2' -> {
                            register();
                        }
                        case '3' -> {
                            saveData();
                        }
                        case '4' -> {
                            System.out.println("Thank you for using Quizzy!");
                            System.exit(0);
                        }
                        default -> System.out.println("Please select a valid option: ");
                    }
                } else {
                    System.out.println("Please select a valid option: ");

                    start();
                }

            } while (line.charAt(0) != '4' || line.length() != 1);
        } catch (Exception e) {
            System.out.println("Please select a valid option: ");
            start();
        }

    }

    private void login() {
        //TODO: Implement Login
        System.out.println("***************************************");
        System.out.println("*** Login Page ***");
        System.out.println("***************************************");
        System.out.println("Please enter your Username: ");
        String userName = scanner.nextLine().trim();
        System.out.println("Please enter your Password: ");
        String password = scanner.nextLine().trim();
        try {
            String hashedPassword = quizzyController.hashPassword(password);
            userInfo = quizzyController.validateLogin(userName, hashedPassword);
            if (userInfo.isEmpty()) {
                throw new IllegalArgumentException("Invalid Username or Password!");
            }
            System.out.println("Login Successful!");
            System.out.println("***************************************");
            System.out.println("Welcome " + userName + "!");
            if (userInfo.get(userName).getAccountType() == AccountType.TEACHER) {
                System.out.println("You are a Teacher!");
                // TODO: teacherMenu();
            } else {
                System.out.println("You are a Student!");
                // TODO : studentMenu();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            login();
        }
    }

    private void saveData() {
        System.out.println("Saving Data...");
        try {
            quizzyController.saveData();
            System.out.println("Data Saved Successfully!");
            start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void register() {
        String sha256 = "";
        AccountType accountType = AccountType.TEACHER;
        try {
            System.out.println("***************************************");
            System.out.println("*** Registration Page ***");
            System.out.println("***************************************");
            System.out.println("Please enter your Full Name: ");
            String fullName = scanner.nextLine().trim();
            System.out.println("Please enter your Email - ID");
            String email = scanner.nextLine().trim();
            quizzyController.validateEmail(email);
            System.out.println("Please enter your Username: ");
            String username = scanner.nextLine().trim();
            quizzyController.validateUsername(username);
            System.out.println("Please enter your Password: ");
            String password = scanner.nextLine().trim();
            sha256 = quizzyController.hashPassword(password);
            System.out.println("1.Are you a Student?");
            System.out.println("2.Are you a Teacher?");
            System.out.println("Please select an option: ");
            String choice = scanner.nextLine().trim();
            if (choice.length() == 1) {
                switch (choice.charAt(0)) {
                    case '1' -> {
                        accountType = AccountType.STUDENT;
                    }
                    case '2' -> {
                        accountType = AccountType.TEACHER;
                    }
                    default -> System.out.println("Please select a valid option: ");
                }
            } else {
                System.out.println("Please select a valid option: ");
                register();
            }
            System.out.println("Enter the Courses you are enrolled in: (If Multiple, separate by comma)");
            String courses = scanner.nextLine();
            for (String course : courses.split(",")) {
                coursesEnrolled.add(course.toLowerCase().trim());
            }
            quizzyController.register(fullName, email, username, sha256, accountType, coursesEnrolled);
            System.out.println("Registration Successful!");
            System.out.println("Please Login to continue...");
            start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            register();
        }
    }


}
