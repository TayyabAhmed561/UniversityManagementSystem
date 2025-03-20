package com.example.universitymanagementapp.controller.StudentController;

import com.example.universitymanagementapp.UniversityManagementApp;
import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.model.Grade;
import com.example.universitymanagementapp.model.Student;
import com.example.universitymanagementapp.dao.CourseDAO;
import com.example.universitymanagementapp.dao.StudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Map;

public class StudentStudentController {

    // Profile tab
    @FXML private Label studentIdLabel;
    @FXML private Label nameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label addressLabel;

    // Academic Information tab
    @FXML private Label academicLevelLabel;
    @FXML private Label currentSemesterLabel;
    @FXML private TableView<Course> registeredCoursesTable;
    @FXML private TableColumn<Course, Integer> courseCodeColumn;
    @FXML private TableColumn<Course, String> courseNameColumn;
    @FXML private TableColumn<Course, String> subjectNameColumn;
    @FXML private TableColumn<Course, String> instructorColumn;
    @FXML private Label registeredSubjectsLabel;

    // Tuition tab
    @FXML private Label tuitionFeesLabel;

    // Grades tab
    @FXML private TableView<Grade> gradesTable;
    @FXML private TableColumn<Grade, Integer> gradeCourseCodeColumn;
    @FXML private TableColumn<Grade, String> gradeCourseNameColumn;
    @FXML private TableColumn<Grade, String> gradeSubjectColumn;
    @FXML private TableColumn<Grade, Double> finalGradeColumn;
    @FXML private TableColumn<Grade, Double> midtermGradeColumn;
    @FXML private TableColumn<Grade, Double> assignmentGradeColumn;
    @FXML private TableColumn<Grade, Double> quizGradeColumn;
    @FXML private TableColumn<Grade, Double> labGradeColumn;

    // Progress tab
    @FXML private Label progressLabel;

    @FXML private TabPane tabPane;

    private String studentId;
    private StudentDAO studentDAO = UniversityManagementApp.studentDAO;
    private CourseDAO courseDAO = UniversityManagementApp.courseDAO; // Use CourseDAO for course lookups
    private ObservableList<Course> registeredCoursesList = FXCollections.observableArrayList();
    private ObservableList<Grade> gradesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configure Registered Courses table columns
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        instructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        registeredCoursesTable.setItems(registeredCoursesList);

        // Configure Grades table columns
        gradeCourseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        gradeCourseNameColumn.setCellValueFactory(cellData -> {
            List<Course> courses = courseDAO.getCourseByCode(cellData.getValue().getCourseCode());
            Course course = courses.isEmpty() ? null : courses.get(0);
            return course != null ? javafx.beans.binding.Bindings.createStringBinding(() -> course.getCourseName()) : null;
        });
        gradeSubjectColumn.setCellValueFactory(cellData -> {
            List<Course> courses = courseDAO.getCourseByCode(cellData.getValue().getCourseCode());
            Course course = courses.isEmpty() ? null : courses.get(0);
            return course != null ? javafx.beans.binding.Bindings.createStringBinding(() -> course.getSubjectCode()) : null;
        });
        finalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));
        midtermGradeColumn.setCellValueFactory(new PropertyValueFactory<>("midtermGrade"));
        assignmentGradeColumn.setCellValueFactory(new PropertyValueFactory<>("assignmentGrade"));
        quizGradeColumn.setCellValueFactory(new PropertyValueFactory<>("quizGrade"));
        labGradeColumn.setCellValueFactory(new PropertyValueFactory<>("labGrade"));
        gradesTable.setItems(gradesList);

        // Load data if studentId is set
        if (studentId != null) {
            loadStudentData();
        }
    }

    // Set the student ID and load data
    public void setStudentId(String studentId) {
        this.studentId = studentId;
        System.out.println("Student ID set in StudentStudentController: " + studentId);
        loadStudentData();
    }

    // Load all student data into the UI
    private void loadStudentData() {
        if (studentId == null) {
            System.out.println("Student ID is null. Cannot load data.");
            return;
        }

        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found for ID: " + studentId);
            return;
        }

        System.out.println("Student found: " + student.getUsername() + ", Name: " + student.getName() +
                ", Email: " + student.getEmail() + ", Phone: " + student.getPhoneNumber() +
                ", Address: " + student.getAddress());

        // Profile tab
        studentIdLabel.setText(student.getUsername() != null ? student.getUsername() : "N/A");
        nameLabel.setText(student.getName() != null ? student.getName() : "N/A");
        emailLabel.setText(student.getEmail() != null ? student.getEmail() : "N/A");
        phoneLabel.setText(student.getPhoneNumber() != null ? student.getPhoneNumber() : "N/A");
        addressLabel.setText(student.getAddress() != null ? student.getAddress() : "N/A");

        // Academic Information tab
        academicLevelLabel.setText(student.getAcademicLevel() != null ? student.getAcademicLevel() : "N/A");
        currentSemesterLabel.setText(student.getCurrentSemester() != null ? student.getCurrentSemester() : "N/A");
        List<Course> registeredCourses = student.getRegisteredCourses();
        if (registeredCourses != null) {
            registeredCoursesList.setAll(registeredCourses);
        } else {
            registeredCoursesList.clear();
        }
        List<String> registeredSubjects = student.getRegisteredSubjects();
        registeredSubjectsLabel.setText(registeredSubjects != null && !registeredSubjects.isEmpty() ? String.join(", ", registeredSubjects) : "None");

        // Tuition tab
        tuitionFeesLabel.setText(calculateTuitionFees(student));

        // Grades tab
        Map<Integer, Grade> grades = student.getGrades();
        if (grades != null) {
            gradesList.setAll(grades.values());
        } else {
            gradesList.clear();
        }

        // Progress tab
        progressLabel.setText(student.getProgress() >= 0 ? student.getProgress() + "%" : "N/A");
    }

    // Placeholder method to calculate tuition fees
    private String calculateTuitionFees(Student student) {
        List<Course> courses = student.getRegisteredCourses();
        if (courses != null && !courses.isEmpty()) {
            double fee = courses.size() * 500.0;
            return String.format("$%.2f", fee);
        }
        return "$0.00";
    }
}
