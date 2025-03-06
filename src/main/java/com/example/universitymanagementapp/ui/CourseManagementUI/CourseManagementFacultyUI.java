package com.example.universitymanagementapp.ui.CourseManagementUI;

import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.controller.CourseController.CourseFacultyFeatures;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CourseManagementFacultyUI {

    @FXML
    private TableView<Course> facultyCourseTable;

    @FXML
    private TableColumn<Course, String> courseNameColumn;

    @FXML
    private TableColumn<Course, String> sectionIDColumn;

    private final CourseFacultyFeatures courseFacultyFeatures = new CourseFacultyFeatures();

    @FXML
    public void initialize() {
        // Ensure columns are properly assigned
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        sectionIDColumn.setCellValueFactory(new PropertyValueFactory<>("sectionID"));
    }

    @FXML
    public void handleViewTaughtCourses() {
        String facultyName = "Dr. Alan Turing";  // Example hardcoded faculty name
        List<Course> courses = courseFacultyFeatures.getCoursesTaught(facultyName);

        // Ensure UI updates safely
        if (courses != null) {
            facultyCourseTable.getItems().setAll(courses);
        }
    }
}