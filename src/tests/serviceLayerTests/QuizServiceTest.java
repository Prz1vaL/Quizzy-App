package tests.serviceLayerTests;

import main.java.Quizzy.model.Quiz;
import main.java.Quizzy.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuizServiceTest {

    private QuizService quizService;

    @BeforeEach
    void setUp() {
        quizService = new QuizService();
    }

    @Test
    void testAddQuestion() {
        int quizBoardID = 1;
        quizService.addQuestion(quizBoardID, "What is 2 + 2?", "4", 1);
        Map<Integer, Quiz> questions = quizService.viewAllQuestions(quizBoardID);
        assertEquals(1, questions.size());
        assertEquals("What is 2 + 2?", questions.get(1).getQuizQuestion());
    }

    @Test
    void testDeleteQuestion() {
        int quizBoardID = 1;
        quizService.addQuestion(quizBoardID, "What is 2 + 2?", "4", 1);
        quizService.deleteQuestion(quizBoardID, 1);
        Map<Integer, Quiz> questions = quizService.viewAllQuestions(quizBoardID);
        assertEquals(0, questions.size());
    }

    @Test
    void testViewAllQuestions() {
        int quizBoardID = 1;
        quizService.addQuestion(quizBoardID, "What is 2 + 2?", "4", 1);
        quizService.addQuestion(quizBoardID, "What is 3 + 3?", "6", 1);
        Map<Integer, Quiz> questions = quizService.viewAllQuestions(quizBoardID);
        assertEquals(2, questions.size());
    }

    @Test
    void testTakeQuiz() {
        int quizBoardID = 1;
        quizService.addQuestion(quizBoardID, "What is 2 + 2?", "4", 1);
        quizService.addQuestion(quizBoardID, "What is 3 + 3?", "6", 1);
        Map<Integer, Quiz> quiz = quizService.takeQuiz(quizBoardID);
        assertEquals(2, quiz.size());
    }

    @Test
    void testGetQuizTotalScore() {
        int quizBoardID = 1;
        quizService.addQuestion(quizBoardID, "What is 2 + 2?", "4", 1);
        quizService.addQuestion(quizBoardID, "What is 3 + 3?", "6", 2);
        float totalScore = quizService.getQuizTotalScore(quizBoardID);
        assertEquals(3, totalScore);
    }

    @Test
    void testDeleteNonexistentQuestion() {
        int quizBoardID = 1;
        assertThrows(IllegalArgumentException.class, () -> quizService.deleteQuestion(quizBoardID, 1));
    }

    @Test
    void testDeleteQuestionWithMismatchedQuizBoardID() {
        int quizBoardID1 = 1;
        int quizBoardID2 = 2;
        quizService.addQuestion(quizBoardID1, "What is 2 + 2?", "4", 1);
        assertThrows(IllegalArgumentException.class, () -> quizService.deleteQuestion(quizBoardID2, 1));
    }
}
