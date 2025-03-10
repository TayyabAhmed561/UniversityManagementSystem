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

public class StudentFacultyFeatures {

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> idColumn, nameColumn;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Sample data for testing
        Student student = new Student("123", "John Doe", "123 Main St", "123-456-7890", "johndoe@email.com");
        student.setStatus("Active");
        student.setSemester("Semester 1");

        studentList.add(student);
        studentTable.setItems(studentList);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
