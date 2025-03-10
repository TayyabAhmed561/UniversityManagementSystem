package com.universitymanagement.controller.SubjectController;

import com.universitymanagement.model.Subject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditSubjectController {

    @FXML
    private TextField codeField;

    @FXML
    private TextField nameField;

    private Subject selectedSubject;

    public void initialize(Subject subject) {
        this.selectedSubject = subject;
        codeField.setText(subject.getSubjectCode());
        nameField.setText(subject.getSubjectName());
    }

    @FXML
    public void handleEditSubject(ActionEvent event) {
        String newCode = codeField.getText().trim();
        String newName = nameField.getText().trim();

        if (newCode.isEmpty() || newName.isEmpty()) {
            showAlert("Error", "Both fields are required.");
            return;
        }

        selectedSubject.setSubjectCode(newCode);
        selectedSubject.setSubjectName(newName);

        showAlert("Success", "Subject edited successfully!");

        // Load the Admin Dashboard view after editing
        handleBackAction(event);
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
