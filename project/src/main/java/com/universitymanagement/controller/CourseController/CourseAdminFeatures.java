package com.universitymanagement.controller.CourseController;

import com.universitymanagement.model.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseAdminFeatures {

    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableColumn<Course, String> courseName;
    @FXML
    private TableColumn<Course, Integer> courseCode;
    @FXML
    private TextField courseNameField;
    @FXML
    private TextField courseCodeField;

    private ObservableList<Course> courseList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        courseName.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        courseCode.setCellValueFactory(cellData -> cellData.getValue().courseCodeProperty().asObject());

        courseTable.setItems(courseList);
    }

    @FXML
    public void handleAddCourse(ActionEvent event) {
        String name = courseNameField.getText().trim();
        String codeText = courseCodeField.getText().trim();

        if (name.isEmpty() || codeText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Course name and code cannot be empty.");
            return;
        }

        try {
            int code = Integer.parseInt(codeText);
            Course newCourse = new Course("SampleSubject", name, code, "SampleInstructor", 30, 0, 1,
                    "Mon/Wed", "9:00-11:00 AM", "Room 101", "12/15/2025", "9:00 AM");
            courseList.add(newCourse);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Course added successfully!");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid course code. Please enter a number.");
        }
    }

    @FXML
    public void handleDeleteCourse(ActionEvent event) {
        Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            courseList.remove(selectedCourse);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Course deleted successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a course to delete.");
        }
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/admin-dashboard-view.fxml", "Admin Dashboard", event);
    }

    private void clearFields() {
        courseNameField.clear();
        courseCodeField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadView(String fxmlFile, String title, ActionEvent event) {
        try {
            Parent viewRoot = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(viewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
