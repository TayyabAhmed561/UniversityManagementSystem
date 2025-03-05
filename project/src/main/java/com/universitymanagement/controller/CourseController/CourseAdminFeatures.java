package com.universitymanagement.controller.CourseController;

import com.universitymanagement.model.Course;
import com.universitymanagement.model.Subject;
import com.universitymanagement.service.CourseService;

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


public class CourseAdminFeatures implements Initializable {
    @FXML
    private TextField subjectName;
    @FXML
    private TextField courseName;
    @FXML
    private TextField courseCode;
    @FXML
    private TextField instructor;
    @FXML
    private TextField capacity;
    @FXML
    private TextField currentEnrollment;
    @FXML
    private TextField sectionID;
    @FXML
    private TextField meetingDays;
    @FXML
    private TextField meetingTime;
    @FXML
    private TextField meetingLocation;
    @Override
    public void initialize(URL url, ResourceBundle resources) {

    }
    private void loadInitialCourseData(){

    }
    //Add Course: Create new courses with detailed information.
    //- Edit Course: Update course details such as timing or location.
    //- Delete Course: Remove courses from the system.
    //- View Courses: Display a list of courses with essential details.
    //- Assign Faculty: Assign a teacher to the course.
    //- Manage Enrollments: View and manage students enrolled in the course.
}
