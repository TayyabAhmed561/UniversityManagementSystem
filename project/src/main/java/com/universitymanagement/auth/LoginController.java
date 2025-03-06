package com.universitymanagement.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML private TextField studentIdField;
    @FXML private PasswordField studentPasswordField;
    @FXML private TextField facultyIdField;
    @FXML private PasswordField facultyPasswordField;
    @FXML private TextField adminIdField;
    @FXML private PasswordField adminPasswordField;

    @FXML
    public void handleStudentLoginAction(ActionEvent event) {
        loginUser("student", studentIdField.getText(), studentPasswordField.getText(),
                "/roleViews/student-dashboard-view.fxml", "Student Dashboard");
    }

    @FXML
    public void handleFacultyLoginAction(ActionEvent event) {
        loginUser("faculty", facultyIdField.getText(), facultyPasswordField.getText(),
                "/roleViews/faculty-dashboard-view.fxml", "Faculty Dashboard");
    }

    @FXML
    public void handleAdminLoginAction(ActionEvent event) {
        loginUser("admin", adminIdField.getText(), adminPasswordField.getText(),
                "/roleViews/admin-dashboard-view.fxml", "Admin Dashboard");
    }

    private void loginUser(String expectedUser, String enteredUser, String password, String fxmlPath, String title) {
        if (expectedUser.equals(enteredUser) && "password".equals(password)) {
            try {
                // Avoid double prefixing
                String fullPath = "/roleViews/" + fxmlPath;
                System.out.println("Attempting to load FXML file from: " + fullPath);

                URL resource = getClass().getResource(fullPath);
                if (resource == null) {
                    System.out.println("FXML file not found: " + fullPath);
                    showAlert("Error", "FXML file not found: " + fullPath);
                    return;
                }

                Parent dashboardRoot = FXMLLoader.load(resource);
                Stage stage = (Stage) adminIdField.getScene().getWindow();
                stage.setTitle(title);
                stage.setScene(new Scene(dashboardRoot));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to load the dashboard view: " + e.getMessage());
            }
        } else {
            showAlert("Login Failed", "Invalid ID or password.");
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
