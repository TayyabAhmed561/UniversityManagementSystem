package com.universitymanagement.controller.AdminController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    public void manageUsers(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/user-management-view.fxml", "User Management", event);
    }

    @FXML
    public void viewReports(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/reports-view.fxml", "Reports", event);
    }

    @FXML
    public void handleViewSubjects(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/subject/subjects-view.fxml", "View Subjects", event);
    }

    @FXML
    public void handleManageSubjects(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/subject/subject-management-view.fxml", "Manage Subjects", event);
    }

    @FXML
    public void handleAddSubject(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/subject/add-subject-view.fxml", "Add Subject", event);
    }

    @FXML
    public void handleEditSubject(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/subject/edit-subject-view.fxml", "Edit Subject", event);
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/admin-dashboard-view.fxml", "Admin Dashboard", event);
    }

    @FXML
    public void handleLogoutAction(ActionEvent event) {
        loadView("/com/universitymanagement/auth/login-view.fxml", "Login", event);
        System.out.println("Logged out successfully.");
    }

    @FXML
    public void handleManageStudents(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/student/student-management-admin-view.fxml", "Manage Students", event);
    }

    @FXML
    public void handleManageFaculty(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/faculty/faculty-admin-view.fxml", "Manage Faculty", event);
    }

    @FXML
    public void handleManageEvents(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/events/event-admin-view.fxml", "Manage Events", event);
    }

    @FXML
    public void handleManageCourses(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/course/admin-course-management-view.fxml", "Manage Courses", event);
    }
    public void handleExitAction(ActionEvent event) {
        System.out.println("Exiting application...");
        System.exit(0);
    }



    private void loadView(String fxmlFile, String title, ActionEvent event) {
        try {
            Parent viewRoot = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(viewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the view: " + fxmlFile);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
