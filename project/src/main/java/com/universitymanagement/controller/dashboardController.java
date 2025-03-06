package com.universitymanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class dashboardController {

    @FXML
    private StackPane contentPane;

    @FXML
    public void loadHome() {
        System.out.println("Home loaded!");
    }

    @FXML
    public void handleLogout() {
        System.out.println("Logout successful!");
    }

    @FXML
    public void loadCourseManagement() {
        System.out.println("Course Management loaded!");
    }

    @FXML
    public void loadSubjectManagement() {
        System.out.println("Subject Management loaded!");
    }

    @FXML
    public void loadStudentManagement() {
        System.out.println("Student Management loaded!");
    }

    @FXML
    public void loadFacultyManagement() {
        System.out.println("Faculty Management loaded!");
    }

    @FXML
    public void loadEventManagement() {
        System.out.println("Event Management loaded!");
    }
}
