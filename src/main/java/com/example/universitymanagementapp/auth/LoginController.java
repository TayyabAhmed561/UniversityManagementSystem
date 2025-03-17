package com.example.universitymanagementapp.auth;

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
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    // handle login
    public void handleUserLogin(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().equals("Login")) {
            if(validateLogin("student", usernameField.getText(), passwordField.getText())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapplication/student-dashboard.fxml"));
                try {
                    Parent root = loader.load();
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    stage.setTitle("Student Dashboard");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert("Error", "Failed to load the dashboard view: " + e.getMessage());
                }
            }
            if(validateLogin("faculty", usernameField.getText(), passwordField.getText())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapplication/faculty-dashboard.fxml"));
                try {
                    Parent root = loader.load();
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    stage.setTitle("Faculty Dashboard");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert("Error", "Failed to load the dashboard view: " + e.getMessage());
                }
            }
            if(validateLogin("admin", usernameField.getText(), passwordField.getText())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapplication/admin-dashboard.fxml"));
                try {
                    Parent root = loader.load();
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    stage.setTitle("Admin Dashboard");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert("Error", "Failed to load the dashboard view: " + e.getMessage());
                }
            }
            else{
                showAlert("Login Failed", "Invalid ID or password.");
            }
        }
    }

    private boolean validateLogin(String expectedUser, String enteredUser, String password) {
        return expectedUser.equals(enteredUser) && "password".equals(password);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
