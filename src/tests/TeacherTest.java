package tests;

import main.java.Quizzy.Model.AccountType;
import main.java.Quizzy.Model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherTest {

    private Teacher teacher;
    private Date dateCreated;
    private ArrayList<String> coursesEnrolled;

    @BeforeEach
    void setUp() {
        dateCreated = new Date();
        coursesEnrolled = new ArrayList<>();
        coursesEnrolled.add("Course 1");
        coursesEnrolled.add("Course 2");

        teacher = new Teacher("John Doe", "john.doe@example.com", "johndoe", "password123", AccountType.TEACHER, coursesEnrolled, dateCreated);
    }

    @Test
    void testGetFullName() {
        assertEquals("John Doe", teacher.getFullName());
    }

    @Test
    void testSetFullName() {
        teacher.setFullName("Jane Doe");
        assertEquals("Jane Doe", teacher.getFullName());
    }

    @Test
    void testGetEmail() {
        assertEquals("john.doe@example.com", teacher.getEmail());
    }

    @Test
    void testSetEmail() {
        teacher.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", teacher.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", teacher.getPassword());
    }

    @Test
    void testSetPassword() {
        teacher.setPassword("newpassword123");
        assertEquals("newpassword123", teacher.getPassword());
    }

    @Test
    void testGetUsername() {
        assertEquals("johndoe", teacher.getUsername());
    }

    @Test
    void testSetUsername() {
        teacher.setUsername("janedoe");
        assertEquals("janedoe", teacher.getUsername());
    }

    @Test
    void testGetAccountType() {
        assertEquals(AccountType.TEACHER, teacher.getAccountType());
    }

    @Test
    void testSetAccountType() {
        teacher.setAccountType(AccountType.STUDENT);
        assertEquals(AccountType.STUDENT, teacher.getAccountType());
    }

    @Test
    void testGetCoursesEnrolled() {
        assertEquals(coursesEnrolled, teacher.getCoursesEnrolled());
    }

    @Test
    void testSetCoursesEnrolled() {
        ArrayList<String> newCoursesEnrolled = new ArrayList<>();
        newCoursesEnrolled.add("Course 3");
        newCoursesEnrolled.add("Course 4");

        teacher.setCoursesEnrolled(newCoursesEnrolled);
        assertEquals(newCoursesEnrolled, teacher.getCoursesEnrolled());
    }

    @Test
    void testGetDateCreated() {
        assertEquals(dateCreated, teacher.getDateCreated());
    }

    @Test
    void testSetDateCreated() {
        Date newDateCreated = new Date();
        teacher.setDateCreated(newDateCreated);
        assertEquals(newDateCreated, teacher.getDateCreated());
    }
}
