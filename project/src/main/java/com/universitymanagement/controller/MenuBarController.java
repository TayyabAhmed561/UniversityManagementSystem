package com.universitymanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class MenuBarController {

    @FXML private VBox menuBox;
    @FXML private Accordion menuAccordion;
    @FXML private TitledPane managementPane;

    private boolean isMenuOpen = true;

    // Toggle menu visibility
    @FXML
    private void toggleMenu() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), menuBox);
        if (isMenuOpen) {
            transition.setToX(-200); // Slide out
        } else {
            transition.setToX(0); // Slide in
        }
        transition.play();
        isMenuOpen = !isMenuOpen;
    }

    // Auto-close menu when mouse exits
    @FXML
    private void handleMouseExit() {
        if (isMenuOpen) {
            toggleMenu();
        }
    }

    @FXML
    public void loadDashboard() { System.out.println("Dashboard loaded!"); }

    @FXML
    public void handleLogout() { System.out.println("User logged out!"); }

    @FXML
    public void loadCourseManagement() { System.out.println("Course Management loaded!"); }

    @FXML
    public void loadSubjectManagement() { System.out.println("Subject Management loaded!"); }

    @FXML
    public void loadStudentManagement() { System.out.println("Student Management loaded!"); }

    @FXML
    public void loadFacultyManagement() { System.out.println("Faculty Management loaded!"); }

    @FXML
    public void loadEventManagement() { System.out.println("Event Management loaded!"); }

    @FXML
    public void loadUserManagement() { System.out.println("User Management loaded!"); }

    @FXML
    public void loadReports() { System.out.println("Reports loaded!"); }
}
