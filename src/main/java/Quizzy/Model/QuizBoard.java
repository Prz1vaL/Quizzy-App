package main.java.Quizzy.Model;

import java.util.Date;
import java.util.Map;

public class QuizBoard {

    private int quizID;

    private String quizBoardName;

    private String courseName;

    private Date dateCreated;

    private String createdByTeacher;


    private Date modifiedDate;

    private String modifiedByTeacher;

    private float totalScore;

    private Map<String, Integer> studentScores;

    private int numberOfQuestions;


    public QuizBoard(int quizID, String quizBoardName, String courseName, Date dateCreated, String createdByTeacher, Date modifiedDate, String modifiedByTeacher, float totalScore, Map<String, Integer> studentScores, int numberOfQuestions) {
        this.quizID = quizID;
        this.quizBoardName = quizBoardName;
        this.courseName = courseName;
        this.dateCreated = dateCreated;
        this.createdByTeacher = createdByTeacher;
        this.modifiedDate = modifiedDate;
        this.modifiedByTeacher = modifiedByTeacher;
        this.totalScore = totalScore;
        this.studentScores = studentScores;
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getQuizBoardName() {
        return quizBoardName;
    }

    public void setQuizBoardName(String quizBoardName) {
        this.quizBoardName = quizBoardName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatedByTeacher() {
        return createdByTeacher;
    }

    public void setCreatedByTeacher(String createdByTeacher) {
        this.createdByTeacher = createdByTeacher;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedByTeacher() {
        return modifiedByTeacher;
    }

    public void setModifiedByTeacher(String modifiedByTeacher) {
        this.modifiedByTeacher = modifiedByTeacher;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public Map<String, Integer> getStudentScores() {
        return studentScores;
    }

    public void setStudentScores(Map<String, Integer> studentScores) {
        this.studentScores = studentScores;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
