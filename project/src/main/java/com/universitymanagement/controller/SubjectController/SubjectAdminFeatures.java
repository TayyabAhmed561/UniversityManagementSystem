package com.universitymanagement.controller.SubjectController;

import com.universitymanagement.model.Subject;
import com.universitymanagement.service.SubjectService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private final SubjectService subjectService = new SubjectService();
    private final ObservableList<Subject> subjectList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        subjectCodeColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));

        // Initialize the table with subjects
        subjectList.addAll(subjectService.getAllSubjects());
        subjectTable.setItems(subjectList);
    }

    @FXML
    private void handleAddSubject() {
        String subjectCode = subjectCodeField.getText().trim();
        String subjectName = subjectNameField.getText().trim();

        if (subjectCode.isEmpty() || subjectName.isEmpty()) {
            showAlert("Validation Error", "Subject code and name cannot be empty.");
            return;
        }

        boolean isAdded = subjectService.addSubject(subjectCode, subjectName);
        if (isAdded) {
            subjectList.add(new Subject(subjectCode, subjectName));
            subjectCodeField.clear();
            subjectNameField.clear();
        } else {
            showAlert("Duplicate Code", "A subject with this code already exists.");
        }
    }

    @FXML
    private void handleDeleteSubject() {
        Subject selectedSubject = subjectTable.getSelectionModel().getSelectedItem();
        if (selectedSubject == null) {
            showAlert("Selection Error", "No subject selected.");
            return;
        }

        boolean isDeleted = subjectService.deleteSubject(selectedSubject.getSubjectCode());
        if (isDeleted) {
            subjectList.remove(selectedSubject);
        } else {
            showAlert("Deletion Error", "Failed to delete the selected subject.");
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
