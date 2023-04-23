package tests.serviceLayerTests;

import main.java.Quizzy.model.AccountType;
import main.java.Quizzy.model.Teacher;
import main.java.Quizzy.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceTest {

    private TeacherService teacherService;
    private String fullName = "John Doe";
    private String email = "john@example.com";
    private String username = "johndoe";
    private String password = "password";
    private String sha256Password;

    @BeforeEach
    void setUp() {
        teacherService = new TeacherService();
        sha256Password = teacherService.hashPassword(password);
        teacherService.register(fullName, email, username, sha256Password, AccountType.TEACHER, new ArrayList<>(), new Date());
    }

    @Test
    void testHashPassword() {
        String hashedPassword = teacherService.hashPassword(password);
        assertFalse(hashedPassword.isEmpty());
    }

    @Test
    void testValidateEmail() {
        assertDoesNotThrow(() -> teacherService.validateEmail(email));
        assertThrows(IllegalArgumentException.class, () -> teacherService.validateEmail("invalid.email"));
    }

    @Test
    void testValidateUsername() {
        assertThrows(IllegalArgumentException.class, () -> teacherService.validateUsername(""));
        assertThrows(IllegalArgumentException.class, () -> teacherService.validateUsername(username));
    }

    @Test
    void testRegister() {
        String newUsername = "janedoe";
        teacherService.register("Jane Doe", "jane@example.com", newUsername, sha256Password, AccountType.TEACHER, new ArrayList<>(), new Date());
        assertThrows(IllegalArgumentException.class, () -> teacherService.validateLogin(newUsername, ""));
    }

    @Test
    void testValidateLogin() {
        Map<String, Teacher> loginInfo = teacherService.validateLogin(username, sha256Password);
        assertEquals(username.toLowerCase(), loginInfo.keySet().iterator().next());
    }

    @Test
    void testDeleteAccount() {
        teacherService.deleteAccount(username);
        assertThrows(IllegalArgumentException.class, () -> teacherService.validateLogin(username, sha256Password));
    }

    @Test
    void testValidatePassword() {
        assertDoesNotThrow(() -> teacherService.validatePassword(username, password));
    }

    @Test
    void testChangePassword() {
        String newPassword = "new_password";
        teacherService.changePassword(username, newPassword);
        String newHashedPassword = teacherService.hashPassword(newPassword);
        assertThrows(IllegalArgumentException.class, () -> teacherService.validatePassword(username, password));
        assertDoesNotThrow(() -> teacherService.validatePassword(username, newPassword));
    }

    @Test
    void testValidateIfTeacherCourse() {
        String courseName = "Math";
        Map<String, Teacher> loginInfo = teacherService.validateLogin(username, sha256Password);
        Teacher teacher = loginInfo.get(username.toLowerCase());
        teacher.getCoursesEnrolled().add(courseName.toLowerCase());
        assertDoesNotThrow(() -> teacherService.validateIfTeacherCourse(courseName, username));
    }

    @Test
    void testAddCourse() {
        String courseName = "Math";
        teacherService.addCourse(courseName, username);
        assertThrows(IllegalArgumentException.class, () -> teacherService.addCourse(courseName, username));
    }

    @Test
    void testRemoveCourse() {
        String courseName = "Math";
        teacherService.addCourse(courseName, username);
        teacherService.removeCourse(courseName, username);
        assertThrows(IllegalArgumentException.class, () -> teacherService.removeCourse(courseName, username));
    }
}