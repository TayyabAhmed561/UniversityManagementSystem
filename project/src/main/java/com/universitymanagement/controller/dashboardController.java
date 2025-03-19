package com.universitymanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class dashboardController {

    @FXML
    private StackPane contentPane;

    @FXML
    private VBox menuItems;  // FIXED: Now declared to prevent "Cannot resolve symbol 'menuItems'"

    @FXML
    private ToggleButton menuToggle; // Toggle button for sidebar

    @FXML
    public void loadDashboard() {
        System.out.println("Dashboard loaded!");
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

    // Toggle Sidebar Menu Visibility
    @FXML
    public void toggleMenu(ActionEvent actionEvent) {
        if (menuItems != null) {
            boolean isVisible = menuItems.isVisible();
            menuItems.setVisible(!isVisible);
        }
    }
}
