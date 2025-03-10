package com.universitymanagement.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField studentIdField;
    @FXML
    private PasswordField studentPasswordField;
    @FXML
    private TextField facultyIdField;
    @FXML
    private PasswordField facultyPasswordField;
    @FXML
    private TextField adminIdField;
    @FXML
    private PasswordField adminPasswordField;

    @FXML
    public void handleLoginAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String fxmlFile = "";
        String title = "";

        if (clickedButton.getText().equals("Login as Student")) {
            if (validateLogin("student", studentIdField.getText(), studentPasswordField.getText())) {
                fxmlFile = "/com/universitymanagement/roleViews/student-dashboard-view.fxml";
                title = "Student Dashboard";
                UserRoleAuth.setUserRole(UserRoleAuth.Role.STUDENT);
            }
        } else if (clickedButton.getText().equals("Login as Faculty")) {
            if (validateLogin("faculty", facultyIdField.getText(), facultyPasswordField.getText())) {
                fxmlFile = "/com/universitymanagement/roleViews/faculty-dashboard-view.fxml";
                title = "Faculty Dashboard";
                UserRoleAuth.setUserRole(UserRoleAuth.Role.FACULTY);
            }
        } else if (clickedButton.getText().equals("Login as Admin")) {
            if (validateLogin("admin", adminIdField.getText(), adminPasswordField.getText())) {
                fxmlFile = "/com/universitymanagement/roleViews/admin-dashboard-view.fxml";
                title = "Admin Dashboard";
                UserRoleAuth.setUserRole(UserRoleAuth.Role.ADMIN);
            }
        }

        if (!fxmlFile.isEmpty()) {
            loadDashboard(fxmlFile, title);
        } else {
            showAlert("Login Failed", "Invalid ID or password.");
        }
    }

    private boolean validateLogin(String expectedUser, String enteredUser, String password) {
        return expectedUser.equals(enteredUser) && "password".equals(password);
    }

    private void loadDashboard(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) studentIdField.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the dashboard view: " + e.getMessage());
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
