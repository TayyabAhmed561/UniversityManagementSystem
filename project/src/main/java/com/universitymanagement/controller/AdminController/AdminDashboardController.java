package com.universitymanagement.controller.AdminController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;

public class AdminDashboardController {

    @FXML
    public void manageUsers(ActionEvent event) {
        try {
            loadView("/com/universitymanagement/roleViews/user-management-view.fxml", "User Management", event);
        } catch (Exception e) {
            showAlert("Error", "Failed to load user management view: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void viewReports(ActionEvent event) {
        try {
            loadView("/com/universitymanagement/roleViews/reports-view.fxml", "Reports", event);
        } catch (Exception e) {
            showAlert("Error", "Failed to load reports view: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleManageSubjects(ActionEvent event) {
        try {
            loadView("/com/universitymanagement/roleViews/subjects-management-view.fxml", "Subject Management", event);
        } catch (Exception e) {
            showAlert("Error", "Failed to load subject management view: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadView(String fxmlFile, String title, ActionEvent event) throws IOException {
        // Print the classpath for debugging
        System.out.println("Attempting to load: " + fxmlFile);
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceURL = classLoader.getResource("");
        if (resourceURL != null) {
            System.out.println("Root resource directory: " + resourceURL.getPath());
        } else {
            System.out.println("Could not determine root resource directory.");
        }

        // Try different possible resource paths
        Parent viewRoot = null;
        IOException lastException = null;

        // Add more potential paths
        String[] possiblePaths = {
                fxmlFile,
                "/com/universitymanagement/roleViews/" + fxmlFile.substring(fxmlFile.lastIndexOf('/') + 1),
                "/roleViews/" + fxmlFile.substring(fxmlFile.lastIndexOf('/') + 1),
                "roleViews/" + fxmlFile.substring(fxmlFile.lastIndexOf('/') + 1),
                "/views/" + fxmlFile.substring(fxmlFile.lastIndexOf('/') + 1),
                "views/" + fxmlFile.substring(fxmlFile.lastIndexOf('/') + 1),
                "/" + fxmlFile.substring(fxmlFile.lastIndexOf('/') + 1),
                fxmlFile.substring(fxmlFile.lastIndexOf('/') + 1)
        };

        for (String path : possiblePaths) {
            try {
                System.out.println("Attempting to load: " + path);
                URL url = getClass().getResource(path);
                if (url != null) {
                    System.out.println("Resource found at: " + url.toString());
                    viewRoot = FXMLLoader.load(url);
                    if (viewRoot != null) {
                        System.out.println("Successfully loaded: " + path);
                        break;
                    }
                } else {
                    System.out.println("Resource not found at: " + path);
                }
            } catch (IOException e) {
                lastException = e;
                System.out.println("Failed to load: " + path + " - Error: " + e.getMessage());
            }
        }

        if (viewRoot == null) {
            throw lastException != null ? lastException :
                    new IOException("Could not find FXML resource. Please ensure the file exists.");
        }

        // Get current stage
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.setTitle(title);

        try {
            System.out.println("About to set scene with dashboard root");
            stage.setScene(new Scene(viewRoot));
            System.out.println("Scene set successfully");
            stage.show();
            System.out.println("Stage shown successfully");
        } catch (Exception e) {
            System.err.println("Error setting scene: " + e.getMessage());
            e.printStackTrace();
            showAlert("Scene Error", "Error setting scene: " + e.getMessage());
            // Convert to IOException since that's what this method declares
            throw new IOException("Error setting scene: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}