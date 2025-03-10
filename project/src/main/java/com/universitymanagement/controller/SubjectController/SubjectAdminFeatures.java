package com.universitymanagement.controller.SubjectController;

import com.universitymanagement.controller.BackButtonController;
import com.universitymanagement.model.Subject;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SubjectAdminFeatures {

    @FXML
    private TextField subjectCodeField;

    @FXML
    private TextField subjectNameField;

    @FXML
    private TableView<Subject> subjectTable;

    @FXML
    private TableColumn<Subject, String> subjectCodeColumn;

    @FXML
    private TableColumn<Subject, String> subjectNameColumn;

    @FXML
    public void handleAddSubject(ActionEvent event) {
        String code = subjectCodeField.getText().trim();
        String name = subjectNameField.getText().trim();

        if (code.isEmpty() || name.isEmpty()) {
            showAlert("Error", "Subject code and name cannot be empty.");
            return;
        }

        SubjectManager.addSubject(code, name);
        refreshSubjectList();
        subjectCodeField.clear();
        subjectNameField.clear();
    }

    @FXML
    public void handleDeleteSubject(ActionEvent event) {
        Subject selectedSubject = subjectTable.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete this subject?", ButtonType.YES, ButtonType.NO);
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    SubjectManager.removeSubject(selectedSubject.getSubjectCode());
                    refreshSubjectList();
                }
            });
        } else {
            showAlert("Error", "Please select a subject to delete.");
        }
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        BackButtonController backButtonController = new BackButtonController();
        backButtonController.handleBackAction(event);
    }

    private void refreshSubjectList() {
        ObservableList<Subject> subjects = SubjectManager.getSubjects();
        subjectTable.setItems(subjects);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
