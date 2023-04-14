package main.java.Quizzy.Service;

import main.java.Quizzy.Model.QuizBoard;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class QuizBoardService {

    private Map<Integer, QuizBoard> quizBoardMap;

    private ArrayList<Integer> quizIDS = new ArrayList<>();


    public int createQuizBoard(String quizBoardName, String courseName, Date dateCreated, String createdBy) {
        // Check for empty string and null
        if (quizBoardName == null || quizBoardName.isEmpty()) {
            throw new IllegalArgumentException("Fields cannot be empty");
        }
        Random random = new Random();
        int quizID = random.nextInt(1000000);
        // CHECK IF QUIZ ID IS UNIQUE
        while (quizIDS.contains(quizID)) {
            quizID = random.nextInt(1000000);
            quizIDS.add(quizID);
            // QuizBoard quizBoard = new QuizBoard(quizID, quizBoardName.toLowerCase(), courseName.toLowerCase(), dateCreated, createdBy.toLowerCase());
            //quizBoardMap.put(quizID, quizBoard);
        }
        return quizID;
    }


}
