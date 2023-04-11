package main.java.Quizzy.View;

import main.java.Quizzy.Controller.QuizzyController;

import java.util.Scanner;

public class CommandLineInterface {

    QuizzyController quizzyController = new QuizzyController();

    private Scanner scanner;

    public void run() {
        start();
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
                            //TODO : Login();
                        }
                        case '2' -> {
                            register();
                        }
                        case '3' -> {

                            // TODO : saveData();
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

    private void register() {
        //TODO: quizzyController.register();
        System.out.println("*** Register ***");
        System.out.println("Please enter your username: ");
    }


}
