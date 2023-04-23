package tests.serviceLayerTests;

import main.java.Quizzy.model.AccountType;
import main.java.Quizzy.model.Student;
import main.java.Quizzy.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;
    private String fullName = "John Doe";
    private String email = "john@example.com";
    private String username = "johndoe";
    private String password = "password";
    private String sha256Password;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
        sha256Password = studentService.hashPassword(password);
        studentService.register(fullName, email, username, sha256Password, AccountType.STUDENT, new ArrayList<>(), new Date());
    }

    @Test
    void testRegister() {
        String newUsername = "janedoe";
        studentService.register("Jane Doe", "jane@example.com", newUsername, sha256Password, AccountType.STUDENT, new ArrayList<>(), new Date());
        assertThrows(IllegalArgumentException.class, () -> studentService.validateLogin(newUsername, ""));
    }

    @Test
    void testValidateLogin() {
        Map<String, Student> loginInfo = studentService.validateLogin(username, sha256Password);
        assertEquals(username.toLowerCase(), loginInfo.keySet().iterator().next());
    }

    @Test
    void testHashPassword() {
        String hashedPassword = studentService.hashPassword(password);
        assertFalse(hashedPassword.isEmpty());
    }

    @Test
    void testValidatePassword() {
        assertDoesNotThrow(() -> studentService.validatePassword(username, password));
    }

    @Test
    void testChangePassword() {
        String newPassword = "new_password";
        studentService.changePassword(username, newPassword);
        String newHashedPassword = studentService.hashPassword(newPassword);
        assertThrows(IllegalArgumentException.class, () -> studentService.validatePassword(username, password));
        assertDoesNotThrow(() -> studentService.validatePassword(username, newPassword));
    }

    @Test
    void testDeleteAccount() {
        studentService.deleteAccount(username);
        assertThrows(IllegalArgumentException.class, () -> studentService.validatePassword(username, password));
    }

    @Test
    void testGetStudentsByCourse() {
        String courseName = "Math";
        Map<String, Student> loginInfo = studentService.validateLogin(username, sha256Password);
        Student student = loginInfo.get(username.toLowerCase());
        student.getCoursesEnrolled().add(courseName.toLowerCase());
        ArrayList<String> students = studentService.getStudentsByCourse(courseName);
        assertEquals(1, students.size());
        assertEquals(username.toLowerCase(), students.get(0));
    }

    @Test
    void testUpdateStudentScore() {
        int quizBoardID = 1;
        float studentScore = 80;
        studentService.updateStudentScore(username, quizBoardID, studentScore);
        Map<Integer, Float> studentScores = studentService.getStudentScore(quizBoardID);
        assertEquals(1, studentScores.size());
        assertEquals(studentScore, studentScores.get(quizBoardID));
    }
}
