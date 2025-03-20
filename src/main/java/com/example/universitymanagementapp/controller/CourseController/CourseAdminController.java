package com.example.universitymanagementapp.controller.CourseController;

import com.example.universitymanagementapp.dao.CourseDAO;
import com.example.universitymanagementapp.dao.StudentDAO;
import com.example.universitymanagementapp.UniversityManagementApp;
import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.model.Student;
import com.example.universitymanagementapp.utils.ExExporter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CourseAdminController {

    @FXML public TableView<Course> allCoursesTable;
    @FXML public TableColumn<Course, Integer> courseCodeColumn;
    @FXML public TableColumn<Course, String> courseNameColumn;
    @FXML public TableColumn<Course, String> subjectNameColumn;
    @FXML public TableColumn<Course, String> instructorColumn;
    @FXML public TableColumn<Course, Integer> capacityColumn;
    @FXML public TableColumn<Course, Integer> enrollmentColumn;
    @FXML public Button addButton;
    @FXML public Button editButton;
    @FXML public Button deleteButton;
    @FXML public TextField courseCodeField;
    @FXML public TextField courseNameField;
    @FXML public TextField subjectNameField;
    @FXML public TextField instructorField;
    @FXML public TextField capacityField;
    @FXML public Button saveButton;
    @FXML public Button clearButton;
    @FXML public TableView<Student> enrolledStudentsTable;
    @FXML public TableColumn<Student, String> studentIdColumn;
    @FXML public TableColumn<Student, String> studentNameColumn;
    @FXML public Button addStudentButton;
    @FXML public Button removeStudentButton;
    @FXML public TextField meetingDaysField;
    @FXML public TextField finalExamDateField;
    @FXML public Button saveScheduleButton;
    @FXML public TabPane tabPane;

    private CourseDAO courseDAO = UniversityManagementApp.courseDAO;
    private StudentDAO studentDAO = UniversityManagementApp.studentDAO;
    private ExExporter exporter = new ExExporter(courseDAO, studentDAO, UniversityManagementApp.facultyDAO,
            UniversityManagementApp.subjectDAO, UniversityManagementApp.eventDAO);
    private ObservableList<Course> allCoursesList = FXCollections.observableArrayList();
    private ObservableList<Student> enrolledStudentsList = FXCollections.observableArrayList();
    private Course selectedCourse = null;

    @FXML
    public void initialize() {
        // Configure columns for All Courses table
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        instructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        enrollmentColumn.setCellValueFactory(new PropertyValueFactory<>("currentEnrollment"));

        // Configure columns for Enrolled Students table
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Set table resize policies
        allCoursesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        enrolledStudentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Load all courses
        loadAllCourses();
        allCoursesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            editButton.setDisable(newSelection == null);
            deleteButton.setDisable(newSelection == null);
        });
        enrolledStudentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            removeStudentButton.setDisable(newSelection == null);
        });

        // Add double-click event handler to open course details tab
        allCoursesTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Course course = allCoursesTable.getSelectionModel().getSelectedItem();
                if (course != null) {
                    openCourseDetailsTab(course);
                }
            }
        });
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab != null && "All Courses".equals(newTab.getText())) {
                loadAllCourses();
            }
        });

    }

    private void openCourseDetailsTab(Course course) {
        // Check if a tab for this course already exists
        for (Tab tab : tabPane.getTabs()) {
            if (tab.getText().equals("Course " + course.getCourseCode() + " Details")) {
                tabPane.getSelectionModel().select(tab);
                return;
            }
        }

        // Create a new tab
        Tab courseDetailsTab = new Tab("Course " + course.getCourseCode() + " Details");
        courseDetailsTab.setClosable(true);

        // Create content for the tab
        VBox content = new VBox(10);
        content.setStyle("-fx-padding: 10;");

        // Add course details
        content.getChildren().add(new Text("Course Code: " + course.getCourseCode()));
        content.getChildren().add(new Text("Course Name: " + course.getCourseName()));
        content.getChildren().add(new Text("Subject: " + course.getSubjectCode()));
        content.getChildren().add(new Text("Instructor: " + course.getInstructor()));
        content.getChildren().add(new Text("Capacity: " + course.getCapacity()));
        content.getChildren().add(new Text("Enrolled: " + course.getCurrentEnrollment()));
        content.getChildren().add(new Text("Section ID: " + (course.getSectionID() != null ? course.getSectionID() : "Not set")));
        content.getChildren().add(new Text("Meeting Location: " + (course.getMeetingLocation() != null ? course.getMeetingLocation() : "Not set")));
        content.getChildren().add(new Text("Meeting Times: " + (course.getMeetingDaysTime() != null ? course.getMeetingDaysTime() : "Not set")));
        content.getChildren().add(new Text("Final Exam Date: " + (course.getFinalExamDateTime() != null ? course.getFinalExamDateTime() : "Not set")));
        content.getChildren().add(new Text("Enrolled Students: " + (course.getEnrolledStudents() != null ? course.getEnrolledStudents() : "None")));

        courseDetailsTab.setContent(content);
        tabPane.getTabs().add(courseDetailsTab);
        tabPane.getSelectionModel().select(courseDetailsTab);
    }

    public void refreshCourses() {
        loadAllCourses();
        // If the Course Management tab is open, refresh the enrolled students list
        if (selectedCourse != null) {
            loadEnrolledStudents();
        }
        System.out.println("Refreshed courses in CourseAdminController.");
    }


    private void loadAllCourses() {
        allCoursesList.clear();
        allCoursesList.addAll(courseDAO.getAllCourses());
        allCoursesTable.setItems(allCoursesList);
        System.out.println("Loaded all courses: " + allCoursesList);
        for (Course course : allCoursesList) {
            System.out.println("Course " + course.getCourseCode() + " (" + course.getCourseName() + "): Enrolled = " + course.getCurrentEnrollment());
        }
    }

    private void loadEnrolledStudents() {
        enrolledStudentsList.clear();
        if (selectedCourse != null) {
            enrolledStudentsList.addAll(studentDAO.getStudentsEnrolledInCourse(selectedCourse.getCourseCode()));
            System.out.println("Loaded enrolled students for course " + selectedCourse.getCourseCode() + ": " + enrolledStudentsList);
        }
        enrolledStudentsTable.setItems(enrolledStudentsList);
    }

    @FXML
    private void handleAddCourse() {
        clearForm();
        selectedCourse = null;
        tabPane.getSelectionModel().select(1); // Switch to Course Management tab
    }

    @FXML
    private void handleEditCourse() {
        selectedCourse = allCoursesTable.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            courseCodeField.setText(String.valueOf(selectedCourse.getCourseCode()));
            courseNameField.setText(selectedCourse.getCourseName());
            subjectNameField.setText(selectedCourse.getSubjectCode());
            instructorField.setText(selectedCourse.getInstructor());
            capacityField.setText(String.valueOf(selectedCourse.getCapacity()));
            meetingDaysField.setText(selectedCourse.getMeetingDaysTime());
            finalExamDateField.setText(selectedCourse.getFinalExamDateTime());
            loadEnrolledStudents();
            tabPane.getSelectionModel().select(1); // Switch to Course Management tab
        }
    }

    @FXML
    private void handleDeleteCourse() {
        Course selected = allCoursesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + selected.getCourseName() + "?");
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Unenroll all students from the course
                    List<Student> enrolledStudents = studentDAO.getStudentsEnrolledInCourse(selected.getCourseCode());
                    for (Student student : enrolledStudents) {
                        studentDAO.removeStudentFromCourse(student, selected);
                        // Remove the course from the student's registered subjects if necessary
                        boolean hasOtherCoursesForSubject = student.getRegisteredCourses().stream()
                                .anyMatch(course -> course.getSubjectCode().equals(selected.getSubjectCode()));
                        if (!hasOtherCoursesForSubject) {
                            student.getRegisteredSubjects().remove(selected.getSubjectCode());
                        }
                    }
                    courseDAO.removeCourse(selected.getCourseCode());
                    loadAllCourses();
                    exporter.exportData(); // Correct: Export after deleting course and updating student enrollments
                }
            });
        }
    }

    @FXML
    private void handleSaveCourse() {
        try {
            int courseCode = Integer.parseInt(courseCodeField.getText().trim());
            String courseName = courseNameField.getText().trim();
            String subjectCode = subjectNameField.getText().trim();
            String instructor = instructorField.getText().trim();
            int capacity = Integer.parseInt(capacityField.getText().trim());

            if (courseName.isEmpty() || subjectCode.isEmpty() || instructor.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields are required.");
                return;
            }

            Course course = selectedCourse != null ? selectedCourse : new Course(subjectCode, courseName, courseCode, instructor, capacity, 0, "", "", "", "");
            course.setCourseCode(courseCode);
            course.setCourseName(courseName);
            course.setSubjectCode(subjectCode);
            course.setInstructor(instructor);
            course.setCapacity(capacity);
            // Preserve existing enrolled students and update currentEnrollment
            if (selectedCourse != null) {
                course.setEnrolledStudents(selectedCourse.getEnrolledStudents());
                course.setCurrentEnrollment(selectedCourse.getEnrolledStudents() != null ? selectedCourse.getEnrolledStudents().size() : 0);
                course.setMeetingDaysTime(selectedCourse.getMeetingDaysTime());
                course.setFinalExamDateTime(selectedCourse.getFinalExamDateTime());
            } else {
                // New course, initialize enrolledStudents
                course.setEnrolledStudents(new ArrayList<>());
                course.setCurrentEnrollment(0);
            }

            if (selectedCourse == null) {
                courseDAO.addCourse(course);
            } else {
                courseDAO.updateCourse(course);
            }

            loadAllCourses();
            clearForm();
            selectedCourse = null;
            enrolledStudentsList.clear();
            exporter.exportData(); // Export after adding or updating a course
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Course code and capacity must be numbers.");
        }
    }

    @FXML
    private void handleAddStudent() {
        if (selectedCourse == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Select a course first.");
            return;
        }
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Student");
        dialog.setHeaderText("Enter Student ID");
        dialog.showAndWait().ifPresent(studentId -> {
            Student student = studentDAO.getStudentById(studentId);
            if (student == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Student not found.");
                return;
            }
            if (selectedCourse.getCurrentEnrollment() >= selectedCourse.getCapacity()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Course is full.");
                return;
            }
            if (student.getRegisteredCourses().stream().anyMatch(c -> c.getCourseCode() == selectedCourse.getCourseCode())) {
                showAlert(Alert.AlertType.ERROR, "Error", "Student is already enrolled in this course.");
                return;
            }
            // Debug: Log all students' registered courses before enrolling
            System.out.println("Before enrolling student " + studentId + ":");
            for (Student s : studentDAO.getAllStudents()) {
                System.out.println("Student " + s.getStudentId() + " registered courses: " + s.getRegisteredCourses());
            }
            // Use the Course instance from CourseDAO
            Course courseFromDAO = courseDAO.getCourseByCode(selectedCourse.getCourseCode()).stream().findFirst().orElse(selectedCourse);
            studentDAO.enrollStudentInCourse(student, courseFromDAO);
            // Update the student's registered subjects
            String subjectCode = courseFromDAO.getSubjectCode();
            if (!student.getRegisteredSubjects().contains(subjectCode)) {
                student.getRegisteredSubjects().add(subjectCode);
            }
            selectedCourse = courseFromDAO;
            // Debug: Log after enrolling
            System.out.println("After enrolling student " + studentId + ":");
            for (Student s : studentDAO.getAllStudents()) {
                System.out.println("Student " + s.getStudentId() + " registered courses: " + s.getRegisteredCourses());
            }
            loadEnrolledStudents();
            loadAllCourses();
            exporter.exportData(); // Correct: Export after enrolling a student
        });
    }

    @FXML
    private void handleRemoveStudent() {
        Student selected = enrolledStudentsTable.getSelectionModel().getSelectedItem();
        if (selected != null && selectedCourse != null) {
            studentDAO.removeStudentFromCourse(selected, selectedCourse);
            // Update the student's registered subjects
            String subjectCode = selectedCourse.getSubjectCode();
            boolean hasOtherCoursesForSubject = selected.getRegisteredCourses().stream()
                    .anyMatch(course -> course.getSubjectCode().equals(subjectCode));
            if (!hasOtherCoursesForSubject) {
                selected.getRegisteredSubjects().remove(subjectCode);
            }
            loadEnrolledStudents();
            loadAllCourses();
            exporter.exportData(); // Correct: Export after unenrolling a student
        }
    }

    @FXML
    private void handleSaveSchedule() {
        if (selectedCourse == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Select a course first.");
            return;
        }
        String meetingDays = meetingDaysField.getText().trim();
        String finalExamDate = finalExamDateField.getText().trim();
        if (!meetingDays.isEmpty() || !finalExamDate.isEmpty()) {
            if (!meetingDays.isEmpty()) {
                selectedCourse.setMeetingDaysTime(meetingDays);
            }
            if (!finalExamDate.isEmpty()) {
                selectedCourse.setFinalExamDateTime(finalExamDate);
            }
            courseDAO.updateCourse(selectedCourse);
            loadAllCourses();
            exporter.exportData(); // Correct: Export after updating the course schedule
        }
    }

    @FXML
    public void handleClearForm() {
        clearForm();
        selectedCourse = null;
        enrolledStudentsList.clear();
    }

    @FXML
    private void clearForm() {
        courseCodeField.clear();
        courseNameField.clear();
        subjectNameField.clear();
        instructorField.clear();
        capacityField.clear();
        meetingDaysField.clear();
        finalExamDateField.clear();
    }



    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
