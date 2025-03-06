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

public class CourseFacultyFeatures implements Initializable {

    @FXML private TableView<Course> assignedCoursesTable;
    @FXML private TableColumn<Course, String> courseNameColumn;
    @FXML private TableColumn<Course, String> instructorColumn;

    private CourseDAO courseDAO = new CourseDAO();
    private ObservableList<Course> courseList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize table columns
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        instructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));

        // Load courses assigned to the faculty (filter by instructor name, e.g., logged-in user)
        String facultyName = "Dr. Alan Turing"; // Replace with actual logged-in faculty
        courseList = FXCollections.observableArrayList();
        for (Course course : courseDAO.getAllCourses()) {
            if (course.getInstructor().equals(facultyName)) {
                courseList.add(course);
            }
        }
        assignedCoursesTable.setItems(courseList);

        // Add update logic here if needed
    }


}
