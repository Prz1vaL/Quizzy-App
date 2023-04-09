package main.java.Quizzy.View;

import main.java.Quizzy.Controller.QuizzyController;

public class CommandLineInterface {

    QuizzyController quizzyController = new QuizzyController();

    public void run() {
        System.out.println("Hello, User! Welcome to Quizzy!");
        logo();
    }

    private void logo() {
        System.out.println("  _______ _   _ _______ ______ _____  ______ _______ ");
        System.out.println(" |__   __| \\ | |__   __|  ____|  __ \\|  ____|__   __|");
        System.out.println("    | |  |  \\| |  | |  | |__  | |__) | |__     | |   ");
        System.out.println("    | |  | . ` |  | |  |  __| |  _  /|  __|    | |   ");
        System.out.println("    | |  | |\\  |  | |  | |____| | \\ \\| |____   | |   ");
        System.out.println("    |_|  |_| \\_|  |_|  |______|_|  \\_\\______|  |_|   ");
    }
}
