package com.universitymanagement.controller.FacultyController;

import com.universitymanagement.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class FacultyFacultyFeatures {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> statusColumn;
    @FXML
    private TableColumn<Student, String> semesterColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));

        studentTable.setItems(getMockStudentData());
    }

    private ObservableList<Student> getMockStudentData() {
        return FXCollections.observableArrayList(
                new Student("S001", "John Doe", "123 Main St", "555-1234", "johndoe@university.edu", "Active", "Fall 2024"),
                new Student("S002", "Jane Smith", "456 Maple St", "555-5678", "janesmith@university.edu", "Active", "Spring 2024"),
                new Student("S003", "Mike Johnson", "789 Oak St", "555-9012", "mikejohnson@university.edu", "Inactive", "Winter 2024"),
                new Student("S004", "Emily Davis", "321 Birch St", "555-8765", "emilydavis@university.edu", "Active", "Summer 2024"),
                new Student("S005", "Robert Lee", "654 Pine St", "555-4321", "robertlee@university.edu", "Active", "Fall 2024")
        );
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        try {
            Parent viewRoot = FXMLLoader.load(getClass().getResource("/com/universitymanagement/roleViews/faculty-dashboard-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Faculty Dashboard");
            stage.setScene(new Scene(viewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Faculty Dashboard.");
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
