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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class CourseAdminFeatures implements Initializable {
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
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    private CourseDAO courseDAO = new CourseDAO();
    private ObservableList<Course> courseList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize table columns
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));

        // Load data
        courseList = FXCollections.observableArrayList(courseDAO.getAllCourses());
        courseTable.setItems(courseList);

        // Add button action
        addButton.setOnAction(event -> {
            try {
                Course newCourse = new Course(
                        "SampleSubject", courseNameField.getText(), Integer.parseInt(courseCodeField.getText()),
                        "SampleInstructor", 30, 0, 1, "Mon/Wed", "9:00-11:00 AM",
                        "Room 101", "12/15/2025", "9:00 AM"
                );
                if (courseDAO.addCourse(newCourse)) {
                    courseList.add(newCourse);
                    clearFields();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Course added successfully!");
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid input: " + e.getMessage());
            }
        });

        // Delete button action
        deleteButton.setOnAction(event -> {
            Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
            if (selectedCourse != null && courseDAO.deleteCourse(selectedCourse.getCourseCode())) {
                courseList.remove(selectedCourse);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Course deleted successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Course deletion failed or not selected.");
            }
        });
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
}




    //Add Course: Create new courses with detailed information.
    //- Edit Course: Update course details such as timing or location.
    //- Delete Course: Remove courses from the system.
    //- View Courses: Display a list of courses with essential details.
    //- Assign Faculty: Assign a teacher to the course.
    //- Manage Enrollments: View and manage students enrolled in the course.
