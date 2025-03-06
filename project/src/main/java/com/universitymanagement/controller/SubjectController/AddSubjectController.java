package com.universitymanagement.controller.SubjectController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddSubjectController {

    @FXML
    private TextField codeField;

    @FXML
    private TextField nameField;

    @FXML
    public void handleAddSubject(ActionEvent event) {
        String code = codeField.getText().trim().toUpperCase();
        String name = nameField.getText().trim();

        if (code.isEmpty() || name.isEmpty()) {
            showAlert("Error", "Both fields are required.");
            return;
        }

        if (!code.matches("^[A-Z]{2,4}[0-9]{3}$")) {
            showAlert("Error", "Invalid subject code. Use the format (e.g., CS101).");
            return;
        }

        if (SubjectManager.addSubject(code, name)) {
            showAlert("Success", "Subject added successfully!");
        } else {
            showAlert("Error", "Subject code already exists.");
        }
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        try {
            Parent viewRoot = FXMLLoader.load(getClass().getResource("/com/universitymanagement/roleViews/admin-dashboard-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(viewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Admin Dashboard.");
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
