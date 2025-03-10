package com.universitymanagement.controller.StudentController;

import com.universitymanagement.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentAdminFeatures {

    @FXML
    private TextField idField, nameField, addressField, phoneField, emailField, statusField, semesterField;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> idColumn, nameColumn;

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Sample data for testing without DAO
        studentList.add(new Student("S20250001", "Alice Smith", "123 Maple St.", "555-1234", "alice@example.edu", "Undergraduate", "Fall 2025"));
        studentList.add(new Student("S20250002", "Bob Johnson", "456 Oak St.", "555-5678", "bob@example.edu", "Graduate", "Fall 2025"));
        studentList.add(new Student("S20250003", "Carol Williams", "789 Pine St.", "555-9012", "carol@example.edu", "PhD", "Fall 2025"));

        studentTable.setItems(studentList);
    }

    @FXML
    public void handleAddStudent(ActionEvent event) {
        Student student = new Student(
                idField.getText(),
                nameField.getText(),
                addressField.getText(),
                phoneField.getText(),
                emailField.getText(),
                statusField.getText(),
                semesterField.getText()
        );
        studentList.add(student);
        clearFields();
        showAlert("Success", "Student added successfully!");
    }

    @FXML
    public void handleDeleteStudent(ActionEvent event) {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            studentList.remove(selectedStudent);
            showAlert("Success", "Student deleted successfully!");
        } else {
            showAlert("Error", "Please select a student to delete.");
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

    private void clearFields() {
        idField.clear();
        nameField.clear();
        addressField.clear();
        phoneField.clear();
        emailField.clear();
        statusField.clear();
        semesterField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
