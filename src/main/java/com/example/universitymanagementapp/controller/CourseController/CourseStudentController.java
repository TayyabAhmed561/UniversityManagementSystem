package com.example.universitymanagementapp.controller.CourseController;

import com.example.universitymanagementapp.UniversityManagementApp;
import com.example.universitymanagementapp.controller.StudentController.StudentDashboard;
import com.example.universitymanagementapp.dao.CourseDAO;
import com.example.universitymanagementapp.dao.StudentDAO;
import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseStudentController implements Initializable {

    // FXML components for "Enrolled Courses" tab
    @FXML
    private TableView<Course> enrolledCoursesTable;
    @FXML
    private TableColumn<Course, Integer> courseCodeColumn;
    @FXML
    private TableColumn<Course, String> courseNameColumn;
    @FXML
    private TableColumn<Course, String> subjectNameColumn;
    @FXML
    private TableColumn<Course, String> instructorColumn;
    @FXML
    private TableColumn<Course, Integer> capacityColumn;
    @FXML
    private TableColumn<Course, Integer> enrollmentColumn;

    // FXML components for "Search Courses" tab
    @FXML
    private TextField courseSearch;
    @FXML
    private TableView<Course> searchResultsTable;
    @FXML
    private TableColumn<Course, Integer> searchCourseCodeColumn;
    @FXML
    private TableColumn<Course, String> searchCourseNameColumn;
    @FXML
    private TableColumn<Course, String> searchSubjectNameColumn;
    @FXML
    private TableColumn<Course, String> searchInstructorColumn;
    @FXML
    private TableColumn<Course, Integer> searchCapacityColumn;
    @FXML
    private TableColumn<Course, Integer> searchEnrollmentColumn;
    @FXML
    private Button viewDetailsButton;

    // FXML components for "Course Details" tab
    @FXML
    private Label courseCodeLabel;
    @FXML
    private Label courseNameLabel;
    @FXML
    private Label subjectNameLabel;
    @FXML
    private Label instructorLabel;
    @FXML
    private Label capacityLabel;
    @FXML
    private Label enrollmentLabel;
    @FXML
    private Label meetingDaysLabel;
    @FXML
    private Label finalExamDateLabel;

    // FXML component for the TabPane
    @FXML
    private TabPane tabPane;

    private StudentDashboard parentController;

    public void setParentController(StudentDashboard parentController) {
        this.parentController = parentController;
    }

    // Data access objects
    private CourseDAO courseDAO = UniversityManagementApp.courseDAO;
    private StudentDAO studentDAO = UniversityManagementApp.studentDAO;

    // Observable lists to hold course data
    private ObservableList<Course> enrolledCoursesList = FXCollections.observableArrayList();
    private ObservableList<Course> allCoursesList = FXCollections.observableArrayList();
    private ObservableList<Course> searchResultsList = FXCollections.observableArrayList();

    // Current student's ID (to be set by the parent controller)
    private String studentId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configure columns for "Enrolled Courses" table
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        instructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        enrollmentColumn.setCellValueFactory(new PropertyValueFactory<>("currentEnrollment"));

        // Configure columns for "Search Courses" table
        searchCourseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        searchCourseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        searchSubjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        searchInstructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        searchCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        searchEnrollmentColumn.setCellValueFactory(new PropertyValueFactory<>("currentEnrollment"));

        // Set table resize policies
        enrolledCoursesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        searchResultsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Load all courses (for search functionality)
        loadAllCourses();

        // Load enrolled courses (requires studentId to be set)
        if (studentId != null) {
            loadEnrolledCourses();
        } else {
            System.out.println("Student ID not set. Cannot load enrolled courses.");
        }

        // Add listener to search TextField for real-time search
        courseSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterCourses(newValue);
        });

        // Add double-click event handler to open course details tab from "Search Courses" tab
        searchResultsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && searchResultsTable.getSelectionModel().getSelectedItem() != null) {
                handleViewDetails(searchResultsTable.getSelectionModel().getSelectedItem());
            }
        });

        // Add double-click event handler to open course details tab from "Enrolled Courses" tab
        enrolledCoursesTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && enrolledCoursesTable.getSelectionModel().getSelectedItem() != null) {
                handleViewDetails(enrolledCoursesTable.getSelectionModel().getSelectedItem());
            }
        });

        // Refresh enrolled courses when returning to the "Enrolled Courses" tab
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab != null && "Enrolled Courses".equals(newTab.getText()) && studentId != null) {
                loadEnrolledCourses();
            }
        });
    }

    // Method to set the current student's ID
    public void setStudentId(String studentId) {
        this.studentId = studentId;
        System.out.println("Student ID set in CourseStudentController: " + studentId);
        // Load enrolled courses once the student ID is set
        loadEnrolledCourses();
    }

    // Load all courses into the allCoursesList (for search functionality)
    private void loadAllCourses() {
        allCoursesList.clear();
        allCoursesList.addAll(courseDAO.getAllCourses());
        System.out.println("Loaded all courses: " + allCoursesList);
    }

    // Load the student's enrolled courses into the enrolledCoursesTable
    private void loadEnrolledCourses() {
        if (studentId == null) {
            System.out.println("Cannot load enrolled courses: studentId is null.");
            return;
        }
        enrolledCoursesList.clear();
        // Fetch the student by ID
        Student student = studentDAO.getStudentById(studentId);
        if (student != null && student.getRegisteredCourses() != null) {
            enrolledCoursesList.addAll(student.getRegisteredCourses());
        } else {
            System.out.println("Student " + studentId + " not found or has no registered courses.");
        }
        enrolledCoursesTable.setItems(enrolledCoursesList);
        System.out.println("Loaded enrolled courses for student " + studentId + ": " + enrolledCoursesList);
    }

    // Filter courses based on search input and display in the searchResultsTable
    private void filterCourses(String searchText) {
        searchResultsList.clear();
        if (searchText == null || searchText.trim().isEmpty()) {
            searchResultsTable.setItems(searchResultsList);
            return;
        }

        String lowerCaseSearch = searchText.trim().toLowerCase();
        for (Course course : allCoursesList) {
            if (String.valueOf(course.getCourseCode()).toLowerCase().contains(lowerCaseSearch) ||
                    course.getCourseName().toLowerCase().contains(lowerCaseSearch) ||
                    course.getSubjectCode().toLowerCase().contains(lowerCaseSearch)) {
                searchResultsList.add(course);
            }
        }
        searchResultsTable.setItems(searchResultsList);
        System.out.println("Search results for '" + searchText + "': " + searchResultsList);
    }

    // Handle the "View Details" button or double-click to display the selected course's details
    @FXML
    private void handleViewDetails() {
        Course selectedCourse = searchResultsTable.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            handleViewDetails(selectedCourse);
        }
    }

    // Helper method to populate the "Course Details" tab with the selected course's information
    private void handleViewDetails(Course selectedCourse) {
        if (selectedCourse != null) {
            // Populate the "Course Details" tab with the selected course's information
            courseCodeLabel.setText(String.valueOf(selectedCourse.getCourseCode()));
            courseNameLabel.setText(selectedCourse.getCourseName());
            subjectNameLabel.setText(selectedCourse.getSubjectCode());
            instructorLabel.setText(selectedCourse.getInstructor());
            capacityLabel.setText(String.valueOf(selectedCourse.getCapacity()));
            enrollmentLabel.setText(String.valueOf(selectedCourse.getCurrentEnrollment()));
            meetingDaysLabel.setText(selectedCourse.getMeetingDaysTime() != null ? selectedCourse.getMeetingDaysTime() : "Not set");
            finalExamDateLabel.setText(selectedCourse.getFinalExamDateTime() != null ? selectedCourse.getFinalExamDateTime() : "Not set");

            // Switch to the "Course Details" tab
            tabPane.getSelectionModel().select(2); // Index 2 corresponds to the "Course Details" tab
        }
    }
}


