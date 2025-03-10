package com.universitymanagement.controller.FacultyController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.IOException;

public class FacultyDashboardController {

    @FXML
    public void handleViewStudentInformation(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/faculty/faculty-faculty-view.fxml", event, "View Student Information");
    }

    @FXML
    public void handleManageCourses(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/course/faculty-course-view.fxml", event, "Manage My Courses");
    }

    @FXML
    public void handleViewEvents(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/events/events-faculty-view.fxml", event, "View Events");
    }

    @FXML
    public void handleLogoutAction(ActionEvent event) {
        loadView("/com/universitymanagement/auth/login-view.fxml", event, "Login");
    }

    private void loadView(String fxmlPath, ActionEvent event, String title) {
        try {
            System.out.println("Loading FXML file: " + fxmlPath);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));

            if (loader.getLocation() == null) {
                System.out.println("Error: FXML file not found at " + fxmlPath);
                showAlert("Error", "FXML file not found: " + fxmlPath);
                return;
            }

            Parent viewRoot = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(viewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the view: " + title);
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
