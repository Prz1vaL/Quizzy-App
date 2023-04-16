package main.java.Quizzy.Service;

import main.java.Quizzy.Model.QuizBoard;

import java.io.*;
import java.util.*;

public class QuizBoardService implements Serializable {

    private Map<Integer, QuizBoard> quizBoardMap = new HashMap<>();

    private ArrayList<Integer> quizIDS = new ArrayList<>();


    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/resources/QuizBoardData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(quizBoardMap);
        o.close();
        f.close();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("src/resources/QuizBoardData.ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
        quizBoardMap = (Map<Integer, QuizBoard>) oi.readObject();
        quizIDS = new ArrayList<>(quizBoardMap.keySet());
        oi.close();
        fi.close();
    }


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
        }
        quizIDS.add(quizID);
        // For the initial creation of the quiz board , the values of some parameters that are passed in the constructor are zero or null.
        Map<String, Integer> studentScores = new HashMap<>(); // The student scores are initially zero.
        float totalScore = 0; // The total score of the quiz board is zero initially.
        int numberOfQuestions = 0; // The number of questions in the quiz board is zero initially.
        Date modifiedDate = dateCreated; // The date the quiz board was created is the date it was modified initially.
        String modifiedBy = createdBy; // The teacher who created the quiz board is the one who modified it initially.
        QuizBoard quizBoard = new QuizBoard(quizID, quizBoardName.toLowerCase(), courseName.toLowerCase(), dateCreated, createdBy.toLowerCase(), modifiedDate, modifiedBy.toLowerCase(), totalScore, studentScores, numberOfQuestions);
        quizBoardMap.put(quizID, quizBoard);

        return quizID;
    }


    public Map<Integer, QuizBoard> viewQuizBoardsByCourse(String courseName, String createdBy) {
        // Check for empty string and null
        if (courseName == null || courseName.isEmpty()) {
            throw new IllegalArgumentException("Fields cannot be empty");
        }
        Map<Integer, QuizBoard> quizBoardMapByCourse = new HashMap<>();
        for (Map.Entry<Integer, QuizBoard> entry : quizBoardMap.entrySet()) {
            if (entry.getValue().getCourseName().equals(courseName.toLowerCase()) && entry.getValue().getCreatedByTeacher().equals(createdBy.toLowerCase())) {
                quizBoardMapByCourse.put(entry.getKey(), entry.getValue());
            }
        }
        return quizBoardMapByCourse;
    }

    public Map<Integer, QuizBoard> viewAllQuizBoards(String createdBy) {
        Map<Integer, QuizBoard> quizBoardMapByTeacher = new HashMap<>();
        for (Map.Entry<Integer, QuizBoard> entry : quizBoardMap.entrySet()) {
            if (entry.getValue().getCreatedByTeacher().equals(createdBy.toLowerCase())) {
                quizBoardMapByTeacher.put(entry.getKey(), entry.getValue());
            }
        }
        return quizBoardMapByTeacher;
    }
}
