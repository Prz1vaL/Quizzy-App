package main.java.Quizzy.View;

import main.java.Quizzy.Controller.QuizzyController;

import java.util.Scanner;

public class CommandLineInterface {

    QuizzyController quizzyController = new QuizzyController();

    private Scanner scanner;

    public void run() {
        System.out.println("Hello, User! Welcome to Quizzy!");
        logo();
        start();
    }

    private void logo() {
        String logo = "  ______      __    __   __   ________   ________  ____    ____ \n" +
                " /  __  \\    |  |  |  | |  | |       /  |       /  \\   \\  /   / \n" +
                "|  |  |  |   |  |  |  | |  | `---/  /   `---/  /    \\   \\/   /  \n" +
                "|  |  |  |   |  |  |  | |  |    /  /       /  /      \\_    _/   \n" +
                "|  `--'  '--.|  `--'  | |  |   /  /----.  /  /----.    |  |     \n" +
                " \\_____\\_____\\\\______/  |__|  /________| /________|    |__|     \n" +
                "                                                        1.0      \n";
        System.out.println(logo);
    }
    private void start() {
        Scanner scanner = new Scanner(System.in);
        introMessage();
        String line = scanner.nextLine();
       //TODO : WHILE LOOP TO LOOP THE CHOICES AND THE SYSTEM DOES NOT CRASH.

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


}
