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

    private boolean loginStatus = false;

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

    private static void teacherMenuMessage() {
        System.out.println("***************************************");
        System.out.println("1. Create a Quiz");
        System.out.println("2. View Quizzes");
        System.out.println("3. View Students");
        System.out.println("4. View Courses");
        System.out.println("5. View My Details");
        System.out.println("6. Delete my Account");
        System.out.println("7. Change Password");
        System.out.println("8. Logout");
        System.out.println("Please select an option: ");
        System.out.println("***************************************");
    }

    private void login() {
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
            loginStatus = true;
            System.out.println("***************************************");
            System.out.println("Welcome " + userName + "!");
            if (userInfo.get(userName).getAccountType() == AccountType.TEACHER) {
                System.out.println("You are a Teacher!");
                teacherMenu();
            } else {
                System.out.println("You are a Student!");
                // TODO : studentMenu();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            login();
        }
    }

    private void teacherMenu() {
        teacherMenuMessage();
        String line = scanner.nextLine();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> {
                    //TODO: createQuiz();
                }
                case '2' -> {
                    //TODO: viewQuizzes();
                }
                case '3' -> {
                    //TODO: viewStudents();
                }
                case '4' -> {
                    //TODO: viewCourses();
                }
                case '5' -> {
                    viewMyDetails();
                }
                case '6' -> {
                    deleteMyAccount();
                }
                case '7' -> {
                    //TODO : changePassword();
                }
                case '8' -> {
                    System.out.println("Logging out...");
                    loginStatus = false;
                    start();
                }
                default -> {
                    System.out.println("Please select a valid option: ");
                    teacherMenu();
                }
            }
        } else {
            System.out.println("Please select a valid option: ");
            teacherMenu();
        }

    }

    private void viewMyDetails() {
        System.out.println("***************************************");
        System.out.println("*** My Details ***");
        System.out.println("***************************************");
        String userName = "";
        String fullName = "";
        String email = "";
        String accountType = "";
        for (Map.Entry<String, User> entry : userInfo.entrySet()) {
            userName = entry.getKey();
            fullName = entry.getValue().getFullName();
            email = entry.getValue().getEmail();
            accountType = entry.getValue().getAccountType().toString();
        }
        System.out.println("Username: " + userName);
        System.out.println("Full Name: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Account Type: " + accountType);
        System.out.println("***************************************");
        teacherMenu();
    }

    private void deleteMyAccount() {
        String userName = "";
        for (Map.Entry<String, User> entry : userInfo.entrySet()) {
            userName = entry.getKey();
        }
        System.out.println("Deleting Account...");
        System.out.println("Are you sure you want to delete your account? (Y/N)");
        String line = scanner.nextLine().trim().toLowerCase();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case 'y' -> {
                    quizzyController.deleteAccount(userName);
                    System.out.println("Account Deleted Successfully!");
                    loginStatus = false;
                    start();
                }
                case 'n' -> {
                    teacherMenu();
                }
                default -> {
                    System.out.println("Please select a valid option: ");
                    deleteMyAccount();
                }
            }
        } else {
            System.out.println("Please select a valid option: ");
            deleteMyAccount();
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
        String sha256;
        AccountType accountType = null;
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
            String courses = scanner.nextLine().trim();
            if(courses.isEmpty() || courses.isBlank()){
                throw new IllegalArgumentException("Courses cannot be empty!");
            }
          // Add courses to a list
            for (String course : courses.split(",")) {
                coursesEnrolled.add(course);
            }

            // Courses should not be repeated in the arrayList
            for (int i = 0; i < coursesEnrolled.size(); i++) {
                for (int j = i + 1; j < coursesEnrolled.size(); j++) {
                    if (coursesEnrolled.get(i).equals(coursesEnrolled.get(j))) {
                        coursesEnrolled.remove(j);
                    }
                }
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
