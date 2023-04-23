package tests.modelTests;

import main.java.Quizzy.model.AccountType;
import main.java.Quizzy.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {

    private Student student;
    private Date dateCreated;
    private ArrayList<String> coursesEnrolled;
    private Map<Integer, Float> quizScores;

    @BeforeEach
    void setUp() {
        dateCreated = new Date();
        coursesEnrolled = new ArrayList<>();
        coursesEnrolled.add("Course 1");
        coursesEnrolled.add("Course 2");

        quizScores = new HashMap<>();
        quizScores.put(1, 80.0f);
        quizScores.put(2, 90.0f);

        student = new Student("John Doe", "john.doe@example.com", "password123", "johndoe", AccountType.STUDENT, coursesEnrolled, dateCreated, quizScores);
    }

    @Test
    void testGetFullName() {
        assertEquals("John Doe", student.getFullName());
    }

    @Test
    void testSetFullName() {
        student.setFullName("Jane Doe");
        assertEquals("Jane Doe", student.getFullName());
    }

    @Test
    void testGetEmail() {
        assertEquals("john.doe@example.com", student.getEmail());
    }

    @Test
    void testSetEmail() {
        student.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", student.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", student.getPassword());
    }

    @Test
    void testSetPassword() {
        student.setPassword("newpassword123");
        assertEquals("newpassword123", student.getPassword());
    }

    @Test
    void testGetUsername() {
        assertEquals("johndoe", student.getUsername());
    }

    @Test
    void testSetUsername() {
        student.setUsername("janedoe");
        assertEquals("janedoe", student.getUsername());
    }

    @Test
    void testGetAccountType() {
        assertEquals(AccountType.STUDENT, student.getAccountType());
    }

    @Test
    void testSetAccountType() {
        student.setAccountType(AccountType.TEACHER);
        assertEquals(AccountType.TEACHER, student.getAccountType());
    }

    @Test
    void testGetCoursesEnrolled() {
        assertEquals(coursesEnrolled, student.getCoursesEnrolled());
    }

    @Test
    void testSetCoursesEnrolled() {
        ArrayList<String> newCoursesEnrolled = new ArrayList<>();
        newCoursesEnrolled.add("Course 3");
        newCoursesEnrolled.add("Course 4");

        student.setCoursesEnrolled(newCoursesEnrolled);
        assertEquals(newCoursesEnrolled, student.getCoursesEnrolled());
    }

    @Test
    void testGetDateCreated() {
        assertEquals(dateCreated, student.getDateCreated());
    }

    @Test
    void testSetDateCreated() {
        Date newDateCreated = new Date();
        student.setDateCreated(newDateCreated);
        assertEquals(newDateCreated, student.getDateCreated());
    }

    @Test
    void testGetQuizScores() {
        assertEquals(quizScores, student.getQuizScores());
    }

    @Test
    void testSetQuizScores() {
        Map<Integer, Float> newQuizScores = new HashMap<>();
        newQuizScores.put(3, 95.0f);
        newQuizScores.put(4, 85.0f);

        student.setQuizScores(newQuizScores);
        assertEquals(newQuizScores
                , student.getQuizScores());
    }
}
