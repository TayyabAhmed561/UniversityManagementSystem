package com.example.universitymanagementapp.auth.authenticator;

//handles authentication for all users including admin




import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.universitymanagementapp.model.Admins;
import com.example.universitymanagementapp.model.Faculty;
import com.example.universitymanagementapp.model.User;
import com.example.universitymanagementapp.utils.PasswordHasher;
import com.example.universitymanagementapp.dao.FacultyDAO;
import com.example.universitymanagementapp.dao.StudentDAO;
import com.example.universitymanagementapp.dao.AdminDAO;

public class UserAuthentication {
    private static final List<User> users = new CopyOnWriteArrayList<>();
    private static FacultyDAO facultyDAO;
    private static StudentDAO studentDAO;
    private static AdminDAO adminDAO; // Renamed for clarity

    // Initialize DAOs and populate users list
    public static void initialize(FacultyDAO facultyDAO, StudentDAO studentDAO, AdminDAO adminDAO) {
        if (facultyDAO == null || studentDAO == null || adminDAO == null) {
            throw new IllegalArgumentException("DAOs must not be null");
        }
        UserAuthentication.facultyDAO = facultyDAO;
        UserAuthentication.studentDAO = studentDAO;
        UserAuthentication.adminDAO = adminDAO;
        populateUsers();
    }

    public static synchronized void populateUsers() {
        users.clear();
        users.addAll(facultyDAO.getAllFaculty());
        users.addAll(studentDAO.getAllStudents());
        users.addAll(adminDAO.getAllAdmins());
    }

    public static User authenticate(String username, String password) {
        System.out.println("Authenticating: " + username);
        String inputHash = PasswordHasher.hashPassword(password);
        for (User user : users) {
            System.out.println("Checking user: " + user.getUsername() + ", Stored hash: " + user.getPassword());
            System.out.println("Input hash: " + inputHash);
            if (user.getUsername().equals(username) &&
                    PasswordHasher.verifyPassword(password, user.getPassword())) {
                System.out.println("Match found: " + user);
                return user;
            }
        }
        System.out.println("No match found");
        return null;
    }

    public static synchronized void refreshUsers() {
        populateUsers();
    }

    public static class AuthenticationResult {
        private final User user;
        private final String facultyName;

        public AuthenticationResult(User user, String facultyName) {
            this.user = user;
            this.facultyName = facultyName;
        }

        public User getUser() {
            return user;
        }

        public String getFacultyName() {
            return facultyName;
        }
    }
}