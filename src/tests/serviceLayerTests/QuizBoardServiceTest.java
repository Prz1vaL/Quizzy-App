package tests.serviceLayerTests;

import main.java.Quizzy.model.QuizBoard;
import main.java.Quizzy.service.QuizBoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuizBoardServiceTest {
        private QuizBoardService quizBoardService;

        @BeforeEach
        void setUp() {
            quizBoardService = new QuizBoardService();
        }

        @Test
        void testCreateQuizBoard() {
            int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
            assertNotNull(quizID);
        }

        @Test
        void testViewQuizBoardsByCourse() {
            int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
            Map<Integer, QuizBoard> quizBoards = quizBoardService.viewQuizBoardsByCourse("Math", "teacher1");
            assertEquals(1, quizBoards.size());
            assertTrue(quizBoards.containsKey(quizID));
        }

        @Test
        void testViewAllQuizBoards() {
            int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
            Map<Integer, QuizBoard> quizBoards = quizBoardService.viewAllQuizBoards("teacher1");
            assertEquals(1, quizBoards.size());
            assertTrue(quizBoards.containsKey(quizID));
        }

        @Test
        void testDeleteQuizBoard() {
            int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
            quizBoardService.deleteQuizBoard(String.valueOf(quizID));
            Map<Integer, QuizBoard> quizBoards = quizBoardService.viewAllQuizBoards("teacher1");
            assertEquals(0, quizBoards.size());
        }

        @Test
        void testViewQuizBoardByQuizBoardID() {
            int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
            Map<Integer, QuizBoard> quizBoards = quizBoardService.viewQuizBoardByQuizBoardID("teacher1", String.valueOf(quizID));
            assertEquals(1, quizBoards.size());
            assertTrue(quizBoards.containsKey(quizID));
        }

        @Test
        void testEditQuizBoard() {
            int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
            Map<Integer, QuizBoard> quizBoards = quizBoardService.editQuizBoard("teacher1", String.valueOf(quizID));
            assertEquals(1, quizBoards.size());
            assertTrue(quizBoards.containsKey(quizID));
        }

        @Test
        void testUpdateQuizBoardQuestion() {
            int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
            quizBoardService.updateQuizBoardQuestion(quizID);
            assertEquals(1, quizBoardService.viewQuizBoardByQuizBoardID("teacher1", String.valueOf(quizID)).get(quizID).getNumberOfQuestions());
        }

        @Test
        void testDeleteQuizBoardQuestion() {
            int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
            quizBoardService.updateQuizBoardQuestion(quizID);
            quizBoardService.deleteQuizBoardQuestion(quizID);
            assertEquals(0, quizBoardService.viewQuizBoardByQuizBoardID("teacher1", String.valueOf(quizID)).get(quizID).getNumberOfQuestions());
        }

    @Test
    void testGetQuizBoardCourse() {
        int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
        String courseName = quizBoardService.getQuizBoardCourse(quizID);
        assertEquals("math", courseName);
    }

    @Test
    void testGetQuizBoardName() {
        int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
        String quizBoardName = quizBoardService.getQuizBoardName(quizID);
        assertEquals("quiz 1", quizBoardName);
    }

    @Test
    void testUpdateStudentScoreBoard() {
        int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
        quizBoardService.updateStudentScoreBoard("student1", quizID, 80);
        assertEquals(80, quizBoardService.viewQuizBoardByQuizBoardID("teacher1", String.valueOf(quizID)).get(quizID).getStudentScores().get("student1"));
    }

    @Test
    void testCheckIfQuizTaken() {
        int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
        quizBoardService.updateStudentScoreBoard("student1", quizID, 80);
        assertThrows(IllegalArgumentException.class, () -> quizBoardService.checkIfQuizTaken("student1", quizID));
    }

    @Test
    void testViewTeacherQuizResults() {
        int quizID = quizBoardService.createQuizBoard("Quiz 1", "Math", new Date(), "teacher1");
        quizBoardService.updateStudentScoreBoard("student1", quizID, 80);
        Map<String, Float> results = quizBoardService.viewTeacherQuizResults(quizID);
        assertEquals(80, results.get("student1"));
    }

}
