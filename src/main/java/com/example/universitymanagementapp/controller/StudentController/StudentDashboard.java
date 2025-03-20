package com.example.universitymanagementapp.controller.StudentController;

import com.example.universitymanagementapp.controller.EventController.EventStudentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.example.universitymanagementapp.controller.CourseController.CourseStudentController;

import java.io.IOException;

public class StudentDashboard {

    @FXML
    public MenuButton toggleMenuButton;
    @FXML
    public MenuItem dashboard;
    @FXML
    public MenuItem courseSelection;
    @FXML
    public MenuItem subjectSelection;
    @FXML
    public MenuItem studentSelection;
    @FXML
    public MenuItem eventSelection;
    @FXML
    public MenuItem logout;

    @FXML
    public AnchorPane contentPane; // For dynamic content loading

    private String currentPage = "student-dashboard.fxml"; // Track the current page
    private String studentId;
    private String studentName; // Store the logged-in student's ID
    private Node initialDashboardContent; // Store the initial dashboard content


    @FXML
    public void initialize() {
        contentPane.setPickOnBounds(false); // Ensures it doesn’t block clicks

        // Store the initial content of the contentPane (the dashboard UI)
        if (!contentPane.getChildren().isEmpty()) {
            initialDashboardContent = contentPane.getChildren().get(0);
        }

        // Load default content (already loaded by FXML)
        currentPage = "student-dashboard.fxml";
    }

    // Method to set the student ID from UserLoginController
    public void setStudentId(String studentId) {
        this.studentId = studentId;
        System.out.println("Student ID set in StudentDashboard: " + studentId);
        loadPage(currentPage); // Reload with student context
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
        System.out.println("Student name set in dashboard: " + studentName);
    }

    // Load a page into the contentPane
    private void loadPage(String fxmlFile) {
        if (fxmlFile.equals("student-dashboard.fxml")) {
            if (initialDashboardContent != null) {
                contentPane.getChildren().clear();
                contentPane.getChildren().add(initialDashboardContent);
                currentPage = "student-dashboard.fxml";
            }
            return;
        }

        // Prevent loading the same page again
        if (fxmlFile.equals(currentPage)) {
            return; // Do nothing if already on the same page
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/" + fxmlFile));
            Parent newPage = loader.load();

            // Pass studentId to controllers that need it
            if (fxmlFile.equals("student-course-selection.fxml")) {
                CourseStudentController controller = loader.getController();
                if (studentId != null) {
                    controller.setStudentId(studentId);
                }
            } else if (fxmlFile.equals("student-student-selection.fxml")) {
                StudentStudentController controller = loader.getController();
                System.out.println("Setting studentId in StudentStudentController: " + studentId);
                if (studentId != null) {
                    controller.setStudentId(studentId);
                } else {
                    System.out.println("studentId is null in StudentDashboard when loading student-student.fxml");
                }
            } else if (fxmlFile.equals("student-event-selection.fxml")) {
                EventStudentController controller = loader.getController();
                controller.setStudentUsername(studentId);
                controller.setStudentName(studentName);
            }

            contentPane.getChildren().clear();
            contentPane.getChildren().add(newPage);

            AnchorPane.setTopAnchor(newPage, 0.0);
            AnchorPane.setBottomAnchor(newPage, 0.0);
            AnchorPane.setLeftAnchor(newPage, 0.0);
            AnchorPane.setRightAnchor(newPage, 0.0);

            currentPage = fxmlFile;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML: " + fxmlFile);
        }
    }

    @FXML
    public void handleDashboardAction(ActionEvent actionEvent) {
        loadPage("student-dashboard.fxml"); // Reloads or does nothing if already there
    }

    @FXML
    public void handleCourseSelection(ActionEvent actionEvent) {
        loadPage("student-course-selection.fxml");
    }

    @FXML
    public void handleSubjectSelection(ActionEvent actionEvent) {
        loadPage("student-subject-selection.fxml");
    }

    @FXML
    public void handleStudentSelection(ActionEvent actionEvent) {
        loadPage("student-student-selection.fxml");
    }

    @FXML
    public void handleEventSelection(ActionEvent actionEvent) {
        loadPage("student-event-selection.fxml");
    }

    @FXML
    public void handleLogoutAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/login-page.fxml"));
            Parent loginPage = loader.load();
            Stage stage = (Stage) toggleMenuButton.getScene().getWindow();
            stage.setScene(new Scene(loginPage));
            stage.setTitle("Login Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading login page: " + e.getMessage());
        }
    }

    public void handleNotificationSelection(ActionEvent actionEvent) {

    }
}
