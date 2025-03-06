package com.universitymanagement.controller.StudentController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentDashboardController {

    @FXML
    public void viewCourses() {
        System.out.println("Displaying student courses...");
    }

    @FXML
    public void registerClasses() {
        System.out.println("Registering for classes...");
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
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(viewRoot));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
