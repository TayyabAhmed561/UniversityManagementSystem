package com.universitymanagement.controller.SubjectController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class SubjectAdminFeatures {

    @FXML
    private ListView<String> subjectListView;

    private final ObservableList<String> subjects = FXCollections.observableArrayList(
            "Mathematics - Calculus I",
            "English - Literature Basics",
            "Computer Science - Introduction to Programming",
            "Chemistry - Introduction to Chemistry"
    );

    @FXML
    public void initialize() {
        subjectListView.setItems(subjects);
    }

    @FXML
    public void handleBackAction(ActionEvent event) throws IOException {
        loadView("/com/universitymanagement/roleViews/admin-dashboard-view.fxml", "Admin Dashboard", event);
    }

    @FXML
    public void handleAddSubject(ActionEvent event) {
        TextInputDialog codeDialog = new TextInputDialog();
        codeDialog.setTitle("Add Subject");
        codeDialog.setHeaderText("Enter Subject Code");
        codeDialog.setContentText("Subject Code:");

        Optional<String> codeResult = codeDialog.showAndWait();
        codeResult.ifPresent(subjectCode -> {
            TextInputDialog nameDialog = new TextInputDialog();
            nameDialog.setTitle("Add Subject");
            nameDialog.setHeaderText("Enter Subject Name");
            nameDialog.setContentText("Subject Name:");

            Optional<String> nameResult = nameDialog.showAndWait();
            nameResult.ifPresent(subjectName -> {
                String newSubject = subjectCode + " - " + subjectName;
                subjects.add(newSubject);
                showAlert("Success", "Subject added successfully: " + newSubject);
            });
        });
    }

    @FXML
    public void handleEditSubject(ActionEvent event) {
        String selectedSubject = subjectListView.getSelectionModel().getSelectedItem();
        if (selectedSubject == null) {
            showAlert("Error", "Please select a subject to edit.");
            return;
        }

        TextInputDialog nameDialog = new TextInputDialog(selectedSubject.split(" - ")[1]);
        nameDialog.setTitle("Edit Subject");
        nameDialog.setHeaderText("Edit Subject Name");
        nameDialog.setContentText("Subject Name:");

        Optional<String> nameResult = nameDialog.showAndWait();
        nameResult.ifPresent(newSubjectName -> {
            int selectedIndex = subjectListView.getSelectionModel().getSelectedIndex();
            String newSubject = selectedSubject.split(" - ")[0] + " - " + newSubjectName;
            subjects.set(selectedIndex, newSubject);
            showAlert("Success", "Subject updated successfully: " + newSubject);
        });
    }

    private void loadView(String fxmlFile, String title, ActionEvent event) throws IOException {
        Parent viewRoot = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(viewRoot));
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
