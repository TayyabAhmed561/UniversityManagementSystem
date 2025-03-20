package com.example.universitymanagementapp;

import com.example.universitymanagementapp.auth.authenticator.UserAuthentication;
import com.example.universitymanagementapp.dao.*;

import com.example.universitymanagementapp.model.Admins;
import com.example.universitymanagementapp.model.Student;
import com.example.universitymanagementapp.model.User;
import com.example.universitymanagementapp.utils.PasswordHasher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.example.universitymanagementapp.utils.ExImporter;
import com.example.universitymanagementapp.dao.AdminDAO;

public class UniversityManagementApp extends Application {

    public static CourseDAO courseDAO;
    public static StudentDAO studentDAO;
    public static FacultyDAO facultyDAO;
    public static SubjectDAO subjectDAO;
    public static EventDAO eventDAO;
    public static AdminDAO adminDAO;

    // Static block to initialize DAOs before main is called
    static {
        courseDAO = new CourseDAO();
        studentDAO = new StudentDAO(courseDAO); // Pass CourseDAO to StudentDAO
        facultyDAO = new FacultyDAO();
        subjectDAO = new SubjectDAO();
        eventDAO = new EventDAO();
        adminDAO = new AdminDAO();
    }

    @Override
    public void start(Stage stage) throws IOException {



        FXMLLoader fxmlLoader = new FXMLLoader(UniversityManagementApp.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 601, 498);
        stage.setTitle("Login Page");
        stage.setResizable(true);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        importDataOnStartup();
        launch();
    }

    private static void importDataOnStartup() {
        try {
            System.out.println(PasswordHasher.hashPassword("password123"));
            System.out.println("Starting data import...");
            // Clear existing data in DAOs to start fresh
            System.out.println("Clearing existing data in DAOs...");
            courseDAO.clearCourses();
            studentDAO.clearStudents();
            facultyDAO.clearFaculty();
            subjectDAO.clearSubjects();
            eventDAO.clearEvents();
            adminDAO.clearAdmins();

            // Add admin
            Admins admin = new Admins("admin1", "pass", "name");
            adminDAO.addAdmin(admin);

            // Use the static DAO instances for importing data
            ExImporter importer = new ExImporter(courseDAO, studentDAO, facultyDAO, subjectDAO, eventDAO);
            importer.importData();
            courseDAO.synchronizeEnrollments();
            UserAuthentication.initialize(facultyDAO, studentDAO, adminDAO);
            UserAuthentication.populateUsers();

            System.out.println("Data import completed successfully.");

            System.out.println("Imported Courses: " + courseDAO.getAllCourses());
            System.out.println("Imported Students: " + studentDAO.getAllStudents());
            System.out.println("Imported Faculty: " + facultyDAO.getAllFaculty());
            System.out.println("Imported Subjects: " + subjectDAO.getAllSubjects());
            System.out.println("Imported Events: " + eventDAO.getAllEvents());

        } catch (Exception e) {
            System.err.println("Error importing data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}