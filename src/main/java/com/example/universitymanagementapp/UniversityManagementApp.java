package com.example.universitymanagementapp;

import com.example.universitymanagementapp.auth.authenticator.UserAuthentication;
import com.example.universitymanagementapp.dao.*;
import com.example.universitymanagementapp.model.Admins;
import com.example.universitymanagementapp.utils.ExcelImporter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UniversityManagementApp extends Application {
    public static CourseDAO courseDAO = new CourseDAO();
    public static StudentDAO studentDAO = new StudentDAO();
    public static FacultyDAO facultyDAO = new FacultyDAO();
    public static SubjectDAO subjectDAO = new SubjectDAO();
    public static EventDAO eventDAO = new EventDAO();
    public static AdminDAO adminDAO = new AdminDAO();

    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(UniversityManagementApp.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 601, 498);
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        importDataOnStartup();
        launch();
    }

    private static void importDataOnStartup() {
        try {
            System.out.println("Starting data import...");
            CourseDAO courseDAO = UniversityManagementApp.courseDAO;
            StudentDAO studentDAO = UniversityManagementApp.studentDAO;
            FacultyDAO facultyDAO = UniversityManagementApp.facultyDAO;
            SubjectDAO subjectDAO = UniversityManagementApp.subjectDAO;
            EventDAO eventDAO = UniversityManagementApp.eventDAO;
            AdminDAO adminDAO = UniversityManagementApp.adminDAO;

            //add admin
            Admins admin = new Admins("admin1", "pass", "name");
            adminDAO.addAdmin(admin);

            ExcelImporter importer = new ExcelImporter(courseDAO, studentDAO, facultyDAO, subjectDAO, eventDAO);
            importer.importData();
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
