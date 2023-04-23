package tests.modelTests;

import main.java.Quizzy.model.QuizBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizBoardTest {

    private QuizBoard quizBoard;
    private Date dateCreated;
    private Date modifiedDate;
    private Map<String, Float> studentScores;

    @BeforeEach
    void setUp() {
        dateCreated = new Date();
        modifiedDate = new Date();
        studentScores = new HashMap<>();
        studentScores.put("student1", 80.0f);
        studentScores.put("student2", 90.0f);

        quizBoard = new QuizBoard(1, "QuizBoard 1", "Course 1", dateCreated, "Teacher 1", modifiedDate, "Teacher 2", 100.0f, studentScores, 10);
    }

    @Test
    void testGetQuizID() {
        assertEquals(1, quizBoard.getQuizID());
    }

    @Test
    void testSetQuizID() {
        quizBoard.setQuizID(2);
        assertEquals(2, quizBoard.getQuizID());
    }

    @Test
    void testGetQuizBoardName() {
        assertEquals("QuizBoard 1", quizBoard.getQuizBoardName());
    }

    @Test
    void testSetQuizBoardName() {
        quizBoard.setQuizBoardName("QuizBoard 2");
        assertEquals("QuizBoard 2", quizBoard.getQuizBoardName());
    }

    @Test
    void testGetCourseName() {
        assertEquals("Course 1", quizBoard.getCourseName());
    }

    @Test
    void testSetCourseName() {
        quizBoard.setCourseName("Course 2");
        assertEquals("Course 2", quizBoard.getCourseName());
    }

    @Test
    void testGetDateCreated() {
        assertEquals(dateCreated, quizBoard.getDateCreated());
    }

    @Test
    void testSetDateCreated() {
        Date newDateCreated = new Date();
        quizBoard.setDateCreated(newDateCreated);
        assertEquals(newDateCreated, quizBoard.getDateCreated());
    }

    @Test
    void testGetCreatedByTeacher() {
        assertEquals("Teacher 1", quizBoard.getCreatedByTeacher());
    }

    @Test
    void testSetCreatedByTeacher() {
        quizBoard.setCreatedByTeacher("Teacher 3");
        assertEquals("Teacher 3", quizBoard.getCreatedByTeacher());
    }

    @Test
    void testGetModifiedDate() {
        assertEquals(modifiedDate, quizBoard.getModifiedDate());
    }

    @Test
    void testSetModifiedDate() {
        Date newModifiedDate = new Date();
        quizBoard.setModifiedDate(newModifiedDate);
        assertEquals(newModifiedDate, quizBoard.getModifiedDate());
    }

    @Test
    void testGetModifiedByTeacher() {
        assertEquals("Teacher 2", quizBoard.getModifiedByTeacher());
    }

    @Test
    void testSetModifiedByTeacher() {
        quizBoard.setModifiedByTeacher("Teacher 4");
        assertEquals("Teacher 4", quizBoard.getModifiedByTeacher());
    }

    @Test
    void testGetTotalScore() {
        assertEquals(100.0f, quizBoard.getTotalScore());
    }

    @Test
    void testSetTotalScore() {
        quizBoard.setTotalScore(120.0f);
        assertEquals(120.0f, quizBoard.getTotalScore());
    }

    @Test
    void testGetStudentScores() {
        assertEquals(studentScores, quizBoard.getStudentScores());
    }

    @Test
    void testSetStudentScores() {
        Map<String, Float> newStudentScores = new HashMap<>();
        newStudentScores.put("student3", 95.0f);
        newStudentScores.put("student4", 85.0f);

        quizBoard.setStudentScores(newStudentScores);
        assertEquals(newStudentScores, quizBoard.getStudentScores());
    }

    @Test
    void testGetNumberOfQuestions() {
        assertEquals(10, quizBoard.getNumberOfQuestions());
    }

    @Test
    void testSetNumberOfQuestions() {
        quizBoard.setNumberOfQuestions(15);
        assertEquals(15, quizBoard.getNumberOfQuestions());
    }
}

