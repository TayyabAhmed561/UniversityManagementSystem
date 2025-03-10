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

public class CourseFacultyFeatures {

    @FXML
    private TableView<Course> assignedCoursesTable;
    @FXML
    private TableColumn<Course, String> courseNameColumn;
    @FXML
    private TableColumn<Course, String> instructorColumn;

    private ObservableList<Course> courseList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        courseNameColumn.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        instructorColumn.setCellValueFactory(cellData -> cellData.getValue().instructorProperty());

        String facultyName = "Dr. Alan Turing"; // This should be dynamically set to logged-in faculty
        courseList.add(new Course("Subject101", "Algorithms", 123, facultyName, 30, 20, 1,
                "Tue/Thu", "10:00-12:00 PM", "Room 102", "12/15/2025", "10:00 AM"));

        assignedCoursesTable.setItems(courseList);
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/faculty-dashboard-view.fxml", "Faculty Dashboard", event);
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
