package com.universitymanagement.controller.SubjectController;

import com.universitymanagement.model.Subject;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SubjectStudentFeatures {

    @FXML
    private ListView<Subject> subjectListView;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        // Bind the ListView directly to the ObservableList from SubjectManager
        subjectListView.setItems(SubjectManager.getSubjects());
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public void refreshSubjectList() {
        // Explicitly refresh the ListView in case it does not auto-update
        subjectListView.setItems(null);
        subjectListView.setItems(SubjectManager.getSubjects());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
