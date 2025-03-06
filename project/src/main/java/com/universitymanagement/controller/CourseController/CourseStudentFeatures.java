package com.universitymanagement.controller.CourseController;

import com.universitymanagement.model.Course;
import com.universitymanagement.dao.CourseDAO;
import com.universitymanagement.model.Subject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class CourseStudentFeatures implements Initializable {

    @FXML private TableView<Course> availableCoursesTable;
    @FXML private TableColumn<Course, String> courseNameColumn;
    @FXML private TableColumn<Course, Integer> courseCodeColumn;

    private CourseDAO courseDAO = new CourseDAO();
    private ObservableList<Course> courseList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize table columns
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));

        // Load available courses
        courseList = FXCollections.observableArrayList(courseDAO.getAllCourses());
        availableCoursesTable.setItems(courseList);

        // Add registration logic (e.g., button action) here if needed
    }
    //View Courses: Access comprehensive information about courses.
    //- View Enrollments: Faculty can view courses they teach; students can view
    //courses they are enrolled in
}
