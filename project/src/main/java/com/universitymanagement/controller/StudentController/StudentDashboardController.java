package com.universitymanagement.controller.StudentController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.IOException;

public class StudentDashboardController {

    @FXML
    public void handleViewProfile(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/student/student-profile-student-view.fxml", "View My Profile", event);
    }

    @FXML
    public void handleViewFacultyProfiles(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/faculty/faculty-student-view.fxml", "View Faculty Profiles", event);
    }

    @FXML
    public void handleViewEvents(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/events/event-user-view.fxml", "View Available Events", event);
    }

    @FXML
    public void handleViewCourses(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/course/student-course-view.fxml", "View My Courses", event);
    }

    @FXML
    public void handleLogoutAction(ActionEvent event) {
        loadView("/com/universitymanagement/auth/login-view.fxml", "Login", event);
        System.out.println("Logged out successfully.");
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
