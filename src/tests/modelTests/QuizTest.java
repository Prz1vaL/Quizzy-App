package tests.modelTests;

import main.java.Quizzy.model.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizTest {

    private Quiz quiz;

    @BeforeEach
    void setUp() {
        quiz = new Quiz(1, 1001, "What is the capital of France?", "Paris", 10.0f);
    }

    @Test
    void testGetQuizBoardID() {
        assertEquals(1, quiz.getQuizBoardID());
    }

    @Test
    void testSetQuizBoardID() {
        quiz.setQuizBoardID(2);
        assertEquals(2, quiz.getQuizBoardID());
    }

    @Test
    void testGetQuizID() {
        assertEquals(1001, quiz.getQuizID());
    }

    @Test
    void testSetQuizID() {
        quiz.setQuizID(1002);
        assertEquals(1002, quiz.getQuizID());
    }

    @Test
    void testGetQuizQuestion() {
        assertEquals("What is the capital of France?", quiz.getQuizQuestion());
    }

    @Test
    void testSetQuizQuestion() {
        quiz.setQuizQuestion("What is the capital of Germany?");
        assertEquals("What is the capital of Germany?", quiz.getQuizQuestion());
    }

    @Test
    void testGetCorrectAnswer() {
        assertEquals("Paris", quiz.getCorrectAnswer());
    }

    @Test
    void testSetCorrectAnswer() {
        quiz.setCorrectAnswer("Berlin");
        assertEquals("Berlin", quiz.getCorrectAnswer());
    }

    @Test
    void testGetQuizPoints() {
        assertEquals(10.0f, quiz.getQuizPoints());
    }

    @Test
    void testSetQuizPoints() {
        quiz.setQuizPoints(15.0f);
        assertEquals(15.0f, quiz.getQuizPoints());
    }
}
