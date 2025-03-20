package com.example.universitymanagementapp.controller.AdminController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class AdminSettingsController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    public void initialize() {
        // Populate the profile fields with dummy data for now
        nameField.setText("Admin User");
        usernameField.setText("admin");
        passwordField.setText("admin123");
    }

    @FXML
    private void handleSaveProfile() {
        // Placeholder for save action
        System.out.println("Save Profile button clicked");
        System.out.println("Name: " + nameField.getText());
        System.out.println("Username: " + usernameField.getText());
        System.out.println("Password: " + passwordField.getText());
    }

    @FXML
    private void handleClearProfile() {
        // Clear the profile form
        nameField.clear();
        usernameField.clear();
        passwordField.clear();
    }

    @FXML
    private void handleChangePassword() {
        // Simulate password change validation
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showAlert("Error", "New password and confirmation do not match.");
            return;
        }

        // Simulate a successful password change
        System.out.println("Change Password button clicked");
        System.out.println("Current Password: " + currentPassword);
        System.out.println("New Password: " + newPassword);
        showAlert("Success", "Password changed successfully!");
        handleClearPassword();
    }

    @FXML
    private void handleClearPassword() {
        // Clear the password change form
        currentPasswordField.clear();
        newPasswordField.clear();
        confirmPasswordField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

