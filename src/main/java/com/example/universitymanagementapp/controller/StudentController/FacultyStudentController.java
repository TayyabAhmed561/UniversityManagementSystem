package com.example.universitymanagementapp.controller.StudentController;

import com.example.universitymanagementapp.UniversityManagementApp;
import com.example.universitymanagementapp.controller.FacultyController.FacultyDashboard;
import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.model.Student;
import com.example.universitymanagementapp.model.Grade;
import com.example.universitymanagementapp.dao.StudentDAO;
import com.example.universitymanagementapp.dao.CourseDAO;
import com.example.universitymanagementapp.dao.FacultyDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FacultyStudentController {

    @FXML
    private TabPane tabPane;

    @FXML
    private ToolBar toolBar;

    @FXML
    private Button backButton;

    @FXML
    private TableView<StudentInfo> studentsTable;

    @FXML
    private TableColumn<StudentInfo, String> studentIdColumn;

    @FXML
    private TableColumn<StudentInfo, String> nameColumn;

    @FXML
    private TableColumn<StudentInfo, String> emailColumn;

    @FXML
    private TableColumn<StudentInfo, String> courseCodeColumn;

    @FXML
    private TableColumn<StudentInfo, String> courseNameColumn;

    @FXML
    private TableView<GradeInfo> gradesTable;

    @FXML
    private TableColumn<GradeInfo, String> gradeStudentIdColumn;

    @FXML
    private TableColumn<GradeInfo, String> gradeNameColumn;

    @FXML
    private TableColumn<GradeInfo, String> gradeCourseCodeColumn;

    @FXML
    private TableColumn<GradeInfo, String> gradeCourseNameColumn;

    @FXML
    private TableColumn<GradeInfo, String> finalGradeColumn;

    @FXML
    private TableColumn<GradeInfo, String> midtermGradeColumn;

    @FXML
    private TableColumn<GradeInfo, String> assignmentGradeColumn;

    // DAOs for data access (using static instances from HelloApplication)
    private StudentDAO studentDAO = UniversityManagementApp.studentDAO;
    private CourseDAO courseDAO = UniversityManagementApp.courseDAO;
    private FacultyDAO facultyDAO = UniversityManagementApp.facultyDAO;

    // Faculty username and name (passed from FacultyDashboard)
    private String facultyUsername;
    private String facultyName;
    private FacultyDashboard parentController;


    // Flag to track if initialize has run before setters
    private boolean isInitialized = false;

    // Constructor
    public FacultyStudentController() {
        // DAOs are now initialized using the static instances from HelloApplication
    }

    public void setParentController(FacultyDashboard parentController) {
        this.parentController = parentController;
    }

    @FXML
    public void initialize() {
        // Set up the Students table columns
        studentIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentId()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        courseCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseCode()));
        courseNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseName()));

        // Set up the Grades table columns
        gradeStudentIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentId()));
        gradeNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        gradeCourseCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseCode()));
        gradeCourseNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseName()));
        finalGradeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFinalGrade()));
        midtermGradeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMidtermGrade()));
        assignmentGradeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAssignmentGrade()));

        // Mark that initialize has run
        isInitialized = true;

        // Try to populate tables if facultyName and facultyUsername are already set
        if (facultyName != null && facultyUsername != null) {
            populateTables();
        } else {
            System.err.println("Faculty name or username not set during initialize. Waiting for setters to be called.");
        }
    }

    private void populateTables() {
        // Get all courses taught by the faculty (including multiple sections)
        List<Course> facultyCourses = courseDAO.getCoursesTaught(facultyName);
        System.out.println("Courses taught by " + facultyName + ": " + facultyCourses);

        // Fetch students enrolled in these courses using Course.enrolledStudents
        ObservableList<StudentInfo> studentData = FXCollections.observableArrayList();
        ObservableList<GradeInfo> gradeData = FXCollections.observableArrayList();

        for (Course course : facultyCourses) {
            // Log course details
            System.out.println("Processing course: " + course.getCourseCode() + " (" + course.getCourseName() + "), Enrolled Students: " + course.getEnrolledStudents());

            // Get the list of enrolled student IDs from the Course object
            List<String> enrolledStudentIds = course.getEnrolledStudents();
            if (enrolledStudentIds == null || enrolledStudentIds.isEmpty()) {
                System.out.println("No enrolled students for course " + course.getCourseCode());
                continue;
            }

            // Fetch the Student objects for each enrolled student ID
            for (String studentId : enrolledStudentIds) {
                System.out.println("Looking up student with ID: '" + studentId + "' (length: " + studentId.length() + ")");
                Student student = studentDAO.getStudentById(studentId);
                if (student == null) {
                    System.err.println("Student with ID '" + studentId + "' not found in StudentDAO.");
                    continue;
                }
                System.out.println("Found student: " + student.getStudentId() + " (" + student.getName() + ")");
                studentData.add(new StudentInfo(
                        student.getStudentId(),
                        student.getName(),
                        student.getEmail(),
                        String.valueOf(course.getCourseCode()) + " (" + course.getSectionID() + ")",
                        course.getCourseName()
                ));

                // Fetch the student's grades for this course
                Map<Integer, Grade> studentGrades = student.getGrades();
                Grade grade = studentGrades.get(course.getCourseCode());
                if (grade != null) {
                    gradeData.add(new GradeInfo(
                            student.getStudentId(),
                            student.getName(),
                            String.valueOf(course.getCourseCode()) + " (" + course.getSectionID() + ")",
                            course.getCourseName(),
                            String.valueOf(grade.getFinalGrade()),
                            String.valueOf(grade.getMidtermGrade()),
                            String.valueOf(grade.getAssignmentGrade())
                    ));
                }
            }
        }

        studentsTable.setItems(studentData);
        gradesTable.setItems(gradeData);

        // Debug output
        System.out.println("Populated students table for faculty " + facultyName + ": " + studentData);
        System.out.println("Populated grades table for faculty " + facultyName + ": " + gradeData);
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        try {
            // Navigate back to the faculty dashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapplication/faculty-dashboard.fxml"));
            Parent root = loader.load();
            FacultyDashboard dashboardController = loader.getController();
            dashboardController.setFacultyName(facultyName); // Pass the name back
            dashboardController.setFacultyUsername(facultyUsername); // Pass the username back
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setTitle("Faculty Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Data model for Students table
    public static class StudentInfo {
        private final String studentId;
        private final String name;
        private final String email;
        private final String courseCode;
        private final String courseName;

        public StudentInfo(String studentId, String name, String email, String courseCode, String courseName) {
            this.studentId = studentId;
            this.name = name;
            this.email = email;
            this.courseCode = courseCode;
            this.courseName = courseName;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getCourseName() {
            return courseName;
        }
    }

    // Data model for Grades table
    public static class GradeInfo {
        private final String studentId;
        private final String name;
        private final String courseCode;
        private final String courseName;
        private final String finalGrade;
        private final String midtermGrade;
        private final String assignmentGrade;

        public GradeInfo(String studentId, String name, String courseCode, String courseName, String finalGrade, String midtermGrade, String assignmentGrade) {
            this.studentId = studentId;
            this.name = name;
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.finalGrade = finalGrade != null ? finalGrade : "N/A";
            this.midtermGrade = midtermGrade != null ? midtermGrade : "N/A";
            this.assignmentGrade = assignmentGrade != null ? assignmentGrade : "N/A";
        }

        public String getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public String getFinalGrade() {
            return finalGrade;
        }

        public String getMidtermGrade() {
            return midtermGrade;
        }

        public String getAssignmentGrade() {
            return assignmentGrade;
        }
    }

    // Setter for DAOs (for dependency injection, if needed later)
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void setFacultyDAO(FacultyDAO facultyDAO) {
        this.facultyDAO = facultyDAO;
    }

    // Setter for faculty username
    public void setFacultyUsername(String username) {
        this.facultyUsername = username;
        System.out.println("FacultyStudentController: Set facultyUsername to: " + facultyUsername);
        // If initialize has already run, try to populate tables now
        if (isInitialized && facultyName != null && facultyUsername != null) {
            populateTables();
        }
    }

    // Setter for faculty name
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
        System.out.println("FacultyStudentController: Set facultyName to: " + facultyName);
        // If initialize has already run, try to populate tables now
        if (isInitialized && facultyName != null && facultyUsername != null) {
            populateTables();
        }
    }
}