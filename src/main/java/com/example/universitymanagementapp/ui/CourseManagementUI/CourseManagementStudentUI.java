package com.example.universitymanagementapp.ui.CourseManagementUI;

import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.controller.CourseController.CourseStudentController;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CourseManagementStudentUI extends CourseStudentController {

    @FXML
    private TableView<Course> studentCourseTable;

    @FXML
    private TableColumn<Course, String> courseNameColumn;  // Ensure these match FXML
    @FXML
    private TableColumn<Course, String> instructorColumn;

    private final CourseStudentController courseStudentFeatures = new CourseStudentController();

    @FXML
    public void initialize() {
        // Set up column mappings
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        instructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));
    }

    @FXML
    public void handleViewEnrolledCourses() {
        String studentName = "Alice Smith";
        List<Course> courses = courseStudentFeatures.getEnrolledCourses(studentName);

        // Update TableView
        studentCourseTable.getItems().setAll(courses);
    }
}
