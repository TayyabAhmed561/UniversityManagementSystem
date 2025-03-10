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

public class CourseStudentFeatures {

    @FXML
    private TableView<Course> availableCoursesTable;
    @FXML
    private TableColumn<Course, String> courseNameColumn;
    @FXML
    private TableColumn<Course, Integer> courseCodeColumn;

    private ObservableList<Course> courseList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        courseNameColumn.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        courseCodeColumn.setCellValueFactory(cellData -> cellData.getValue().courseCodeProperty().asObject());

        courseList.add(new Course("Subject102", "Data Structures", 456, "Dr. Grace Hopper", 30, 25, 1,
                "Mon/Wed", "1:00-3:00 PM", "Room 103", "12/15/2025", "1:00 PM"));

        availableCoursesTable.setItems(courseList);
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/student-dashboard-view.fxml", "Student Dashboard", event);
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
