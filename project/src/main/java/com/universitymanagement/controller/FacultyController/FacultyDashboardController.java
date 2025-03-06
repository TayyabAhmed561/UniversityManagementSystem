package com.universitymanagement.controller.FacultyController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class FacultyDashboardController {

    @FXML
    public void manageCourses() {
        System.out.println("Managing faculty courses...");
    }

    @FXML
    public void viewPerformance() {
        System.out.println("Viewing student performance...");
    }

    @FXML
    public void handleViewSubjects(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/subjects-view.fxml", "View Subjects", event);
    }

    @FXML
    public void handleLogoutAction(ActionEvent event) {
        loadView("/com/universitymanagement/auth/login-view.fxml", "Login", event);
        System.out.println("Logged out successfully.");
    }

    private void loadView(String fxmlFile, String title, ActionEvent event) {
        try {
            Parent viewRoot = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(viewRoot));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
