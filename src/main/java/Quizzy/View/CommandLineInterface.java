package main.java.Quizzy.View;

import main.java.Quizzy.Controller.QuizzyController;

public class CommandLineInterface {

    QuizzyController quizzyController = new QuizzyController();

    public void run() {
        System.out.println("Hello, User! Welcome to Quizzy!");
        logo();
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
}
