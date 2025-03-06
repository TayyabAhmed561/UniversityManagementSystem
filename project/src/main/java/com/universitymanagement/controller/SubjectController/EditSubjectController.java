package com.universitymanagement.controller.SubjectController;

import com.universitymanagement.model.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditSubjectController {

    @FXML
    private ComboBox<Subject> subjectComboBox;

    @FXML
    private TextField newCodeField;

    @FXML
    private TextField newNameField;

    @FXML
    public void initialize() {
        ObservableList<Subject> subjects = FXCollections.observableArrayList(SubjectManager.getSubjects());
        subjectComboBox.setItems(subjects);
    }

    @FXML
    public void handleEditSubject(ActionEvent event) {
        Subject selectedSubject = subjectComboBox.getSelectionModel().getSelectedItem();
        String newCode = newCodeField.getText().trim();
        String newName = newNameField.getText().trim();

        if (selectedSubject == null || newCode.isEmpty() || newName.isEmpty()) {
            showAlert("Error", "Please select a subject and fill in both fields.");
            return;
        }

        SubjectManager.editSubject(selectedSubject.getSubjectCode(), newCode, newName);
        showAlert("Success", "Subject updated successfully!");

        // Close the window after editing the subject
        ((Stage) subjectComboBox.getScene().getWindow()).close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
