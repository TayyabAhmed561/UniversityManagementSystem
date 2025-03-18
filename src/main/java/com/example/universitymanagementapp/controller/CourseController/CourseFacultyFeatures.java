package com.example.universitymanagementapp.controller.CourseController;

import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.dao.CourseDAO;
import com.example.universitymanagementapp.dao.FacultyDAO;
import com.example.universitymanagementapp.model.Faculty;
import com.example.universitymanagementapp.UniversityManagementApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CourseFacultyFeatures {

    @FXML
    private TableView<Course> allCoursesTable;

    @FXML
    private TableColumn<Course, Integer> courseCodeColumn;

    @FXML
    private TableColumn<Course, String> subjectNameColumn;

    @FXML
    private TableColumn<Course, String> courseNameColumn;

    @FXML
    private TableColumn<Course, String> instructorColumn;

    @FXML
    private TableColumn<Course, Integer> capacityColumn;

    @FXML
    private TableColumn<Course, Integer> enrollmentColumn;

    @FXML
    private TableView<Course> myCoursesTable;

    @FXML
    private TableColumn<Course, Integer> myCourseCodeColumn;

    @FXML
    private TableColumn<Course, String> mySubjectNameColumn;

    @FXML
    private TableColumn<Course, String> myCourseNameColumn;

    @FXML
    private TableColumn<Course, Integer> myCapacityColumn;

    @FXML
    private TableColumn<Course, Integer> myEnrollmentColumn;

    @FXML
    private TableColumn<Course, String> myMeetingDaysColumn;

    // Use the static DAO instances from HelloApplication
    private CourseDAO courseDAO = UniversityManagementApplication.courseDAO;
    private FacultyDAO facultyDAO = UniversityManagementApplication.facultyDAO;

    private String facultyName;

    public CourseFacultyFeatures() {
    }

    @FXML
    public void initialize() {
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        instructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        enrollmentColumn.setCellValueFactory(new PropertyValueFactory<>("currentEnrollment"));

        myCourseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        mySubjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        myCourseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        myCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        myEnrollmentColumn.setCellValueFactory(new PropertyValueFactory<>("currentEnrollment"));
        myMeetingDaysColumn.setCellValueFactory(new PropertyValueFactory<>("meetingDaysTime"));


        loadAllCourses();
        loadMyCourses();

    }

    public void setLoggedInUsername(String username) {
        System.out.println("Setting facultyName for username: " + username);
        Faculty faculty = facultyDAO.getFacultyByUsername(username);
        if (faculty != null) {
            this.facultyName = faculty.getName();
            System.out.println("Faculty name set to: " + facultyName);
        } else {
            this.facultyName = "Unknown Faculty";
            System.out.println("No faculty found for username: " + username);
        }
        loadMyCourses();
    }

    private void loadAllCourses() {
        System.out.println("Loading all courses...");
        ObservableList<Course> allCourses = FXCollections.observableArrayList(courseDAO.getAllCourses());
        allCoursesTable.setItems(allCourses);
        System.out.println("All Courses Table: " + allCourses);
    }

    private void loadMyCourses() {
        if (facultyName != null) {
            System.out.println("Loading courses for faculty: " + facultyName);
            ObservableList<Course> myCourses = FXCollections.observableArrayList(courseDAO.getCoursesTaught(facultyName));
            myCoursesTable.setItems(myCourses);
            System.out.println("My Courses Table: " + myCourses);
        } else {
            System.out.println("facultyName is null, my courses not loaded.");
        }
    }
}
