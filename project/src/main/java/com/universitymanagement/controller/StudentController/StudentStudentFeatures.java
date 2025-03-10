package com.universitymanagement.controller.StudentController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentStudentFeatures {

    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;

    @FXML
    public void handleViewProfile(ActionEvent event) {
        System.out.println("Viewing student profile...");
        showAlert("Profile View", "Displaying student profile.");
    }

    @FXML
    public void handleEditProfile(ActionEvent event) {
        System.out.println("Editing student profile...");
        showAlert("Edit Profile", "Profile edited successfully.");
        loadView("/com/universitymanagement/roleViews/student/student-management-admin-view.fxml", "Edit Profile", event);
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        System.out.println("Going back to the student dashboard...");
        loadView("/com/universitymanagement/roleViews/student-dashboard-view.fxml", "Student Dashboard", event);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
