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
        // For the initial creation of the quiz board,the values of some parameters
        // that are passed in the constructor are zero or null.
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

    public void deleteQuizBoard(String quizBoardID) {
        if (quizBoardMap.isEmpty() && quizIDS.isEmpty()) {
            throw new IllegalArgumentException("No Quiz Boards exist");
        }
        if (quizBoardMap.containsKey(Integer.parseInt(quizBoardID))) {
            quizBoardMap.remove(Integer.parseInt(quizBoardID));
            for (Integer number : quizIDS) {
                if (number == Integer.parseInt(quizBoardID)) {
                    quizIDS.remove(number);
                    break;
                }
            }
        } else {
            throw new IllegalArgumentException("Quiz Board does not exist");
        }
    }

    public Map<Integer, QuizBoard> viewQuizBoardByQuizBoardID(String teacherUserName, String quizBoardID) {
        Map<Integer, QuizBoard> quizBoardMapByQuizBoardID = new HashMap<>();
        if (quizBoardMap.containsKey(Integer.parseInt(quizBoardID))) {
            if (quizBoardMap.get(Integer.parseInt(quizBoardID)).getCreatedByTeacher().equals(teacherUserName.toLowerCase())) {
                quizBoardMapByQuizBoardID.put(Integer.parseInt(quizBoardID), quizBoardMap.get(Integer.parseInt(quizBoardID)));
            } else {
                throw new IllegalArgumentException("Quiz Board does not exist");
            }
        } else {
            throw new IllegalArgumentException("Quiz Board does not exist");
        }
        return quizBoardMapByQuizBoardID;
    }

    public Map<Integer, QuizBoard> editQuizBoard(String userName, String quizBoardID) {
        Map<Integer, QuizBoard> quizBoardMapByQuizBoardID = new HashMap<>();
        if (quizBoardMap.containsKey(Integer.parseInt(quizBoardID))) {
            if (quizBoardMap.get(Integer.parseInt(quizBoardID)).getCreatedByTeacher().equals(userName.toLowerCase())) {
                quizBoardMapByQuizBoardID.put(Integer.parseInt(quizBoardID), quizBoardMap.get(Integer.parseInt(quizBoardID)));
            } else {
                throw new IllegalArgumentException("Quiz Board does not exist");
            }
        } else {
            throw new IllegalArgumentException("Quiz Board does not exist");
        }
        return quizBoardMapByQuizBoardID;
    }


    public void updateQuizBoardQuestion(int quizBoardID) {
        if (quizBoardMap.containsKey(quizBoardID)) {
            quizBoardMap.get(quizBoardID).setNumberOfQuestions(quizBoardMap.get(quizBoardID).getNumberOfQuestions() + 1);
        } else {
            throw new IllegalArgumentException("Quiz Board does not exist");
        }
    }

    public void deleteQuizBoardQuestion(int quizBoardID) {
        if (quizBoardMap.containsKey(quizBoardID)) {
            quizBoardMap.get(quizBoardID).setNumberOfQuestions(quizBoardMap.get(quizBoardID).getNumberOfQuestions() - 1);
        } else {
            throw new IllegalArgumentException("Quiz Board does not exist");
        }
    }

    public String getQuizBoardCourse(int quizBoardID) {
        if (quizBoardMap.containsKey(quizBoardID)) {
            return quizBoardMap.get(quizBoardID).getCourseName();
        } else {
            throw new IllegalArgumentException("Quiz Board does not exist");
        }
    }

    public String getQuizBoardName(int quizBoardID) {
        if (quizBoardMap.containsKey(quizBoardID)) {
            return quizBoardMap.get(quizBoardID).getQuizBoardName();
        } else {
            throw new IllegalArgumentException("Quiz Board does not exist");
        }
    }
}
