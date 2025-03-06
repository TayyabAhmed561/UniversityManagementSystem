package com.example.universitymanagementapp.ui.CourseManagementUI;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class CourseManagementUI {

    @FXML
    private AnchorPane studentPane;  // FXML-defined panes for each section

    @FXML
    private AnchorPane facultyPane;

    @FXML
    private AnchorPane adminPane;

    private CourseManagementStudentUI studentController;
    private CourseManagementFacultyUI facultyController;
    private CourseManagementAdminUI adminController;

    @FXML
    private void handleViewEnrolledCourses() {
        if (studentController != null) {
            studentController.handleViewEnrolledCourses();
        } else {
            System.out.println("Student controller is not initialized!");
        }
    }

    @FXML
    private void handleViewCoursesTaught(){
        if (facultyController != null) {
            facultyController.handleViewTaughtCourses();
        }else {
            System.out.println("Faculty controller is not initialized!");
        }
    }

    @FXML
    private void handleAddCourse() {
        if (adminController != null) {
            adminController.handleAddCourse();
        } else {
            System.out.println("Admin controller is not initialized!");
        }
    }

    @FXML
    private void handleDeleteCourse() {
        if (adminController != null) {
            adminController.handleDeleteCourse();
        } else {
            System.out.println("Admin controller is not initialized!");
        }
    }


    @FXML
    public void initialize() {
        try {
            // Load Student UI Controller
            FXMLLoader studentLoader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/controller/student-course.fxml"));
            AnchorPane studentView = studentLoader.load();
            studentController = studentLoader.getController();
            studentPane.getChildren().setAll(studentView);



            // Load Faculty UI Controller
            FXMLLoader facultyLoader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/controller/faculty-course.fxml"));
            AnchorPane facultyView = facultyLoader.load();
            facultyController = facultyLoader.getController();
            facultyPane.getChildren().setAll(facultyView);

            // Load Admin UI Controller
            FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/controller/admin-course.fxml"));
            AnchorPane adminView = adminLoader.load();
            adminController = adminLoader.getController();
            adminPane.getChildren().setAll(adminView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
