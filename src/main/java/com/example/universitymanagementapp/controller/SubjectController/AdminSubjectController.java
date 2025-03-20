package com.example.universitymanagementapp.controller.SubjectController;

import com.example.universitymanagementapp.dao.CourseDAO;
import com.example.universitymanagementapp.dao.SubjectDAO;
import com.example.universitymanagementapp.UniversityManagementApp;
import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.model.Subject;
import com.example.universitymanagementapp.utils.ExExporter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AdminSubjectController {

    @FXML
    public TableView<Subject> allSubjectsTable;

    @FXML
    public TableColumn<Subject, String> subjectNameColumn;

    @FXML
    public TableColumn<Subject, String> subjectCodeColumn;

    @FXML
    public Button addButton;

    @FXML
    public Button editButton;

    @FXML
    public Button deleteButton;

    @FXML
    public TextField subjectSearch;

    @FXML
    public TableView<Subject> searchResultsTable;

    @FXML
    public TableColumn<Subject, String> searchSubjectNameColumn;

    @FXML
    public TableColumn<Subject, String> searchSubjectCodeColumn;

    @FXML
    public TextField subjectNameField;

    @FXML
    public TextField subjectCodeField;

    @FXML
    public Button saveButton;

    @FXML
    public Button clearButton;

    @FXML
    public TabPane tabPane;

    private CourseDAO courseDAO = UniversityManagementApp.courseDAO;
    private SubjectDAO subjectDAO = UniversityManagementApp.subjectDAO;
    private ExExporter exporter = new ExExporter(courseDAO, UniversityManagementApp.studentDAO,
            UniversityManagementApp.facultyDAO, subjectDAO, UniversityManagementApp.eventDAO); // Added ExExporter instance
    private ObservableList<Subject> allSubjectsList = FXCollections.observableArrayList();
    private ObservableList<Subject> searchResultsList = FXCollections.observableArrayList();
    private Subject selectedSubject = null; // For editing

    @FXML
    public void initialize() {
        // Configure columns for All Subjects table
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        subjectCodeColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));

        // Configure columns for Search Results table
        searchSubjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        searchSubjectCodeColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));

        // Set table resize policies
        allSubjectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        searchResultsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Load all subjects
        loadAllSubjects();

        // Add listener to subjectSearch TextField for real-time search
        subjectSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterSubjects(newValue);
        });

        // Enable/disable edit/delete buttons based on selection
        allSubjectsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            editButton.setDisable(newSelection == null);
            deleteButton.setDisable(newSelection == null);
        });
    }

    private void loadAllSubjects() {
        allSubjectsList.clear();
        allSubjectsList.addAll(subjectDAO.getAllSubjects());
        allSubjectsTable.setItems(allSubjectsList);
        System.out.println("Loaded all subjects: " + allSubjectsList);
    }

    private void filterSubjects(String searchText) {
        searchResultsList.clear();
        if (searchText == null || searchText.trim().isEmpty()) {
            searchResultsTable.setItems(searchResultsList);
            return;
        }

        String lowerCaseSearch = searchText.trim().toLowerCase();
        for (Subject subject : subjectDAO.getAllSubjects()) {
            if (subject.getSubjectName().toLowerCase().contains(lowerCaseSearch) ||
                    subject.getSubjectCode().toLowerCase().contains(lowerCaseSearch)) {
                searchResultsList.add(subject);
            }
        }
        searchResultsTable.setItems(searchResultsList);
        System.out.println("Search results for '" + searchText + "': " + searchResultsList);
    }

    @FXML
    private void handleAddSubject() {
        clearForm(); // Clear form for new subject
        selectedSubject = null; // Reset selection
        // Switch to the "Manage Subjects" tab (index 2)
        tabPane.getSelectionModel().select(2); // This will now work
        // Set focus on the subjectNameField
        if (subjectNameField != null) {
            subjectNameField.requestFocus();
        } else {
            System.out.println("subjectNameField is null. Check FXML injection.");
        }
    }

    @FXML
    private void handleEditSubject() {
        selectedSubject = allSubjectsTable.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            subjectNameField.setText(selectedSubject.getSubjectName());
            subjectCodeField.setText(selectedSubject.getSubjectCode());
            // Switch to the "Manage Subjects" tab (index 2)
            tabPane.getSelectionModel().select(2);
        }
    }

    @FXML
    private void handleDeleteSubject() {
        Subject selected = allSubjectsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Get all courses associated with this subject
            List<Course> associatedCourses = courseDAO.getCoursesBySubject(selected.getSubjectCode());

            // Build the confirmation message
            StringBuilder message = new StringBuilder("Are you sure you want to delete the subject '")
                    .append(selected.getSubjectName())
                    .append("' (")
                    .append(selected.getSubjectCode())
                    .append(")?");

            if (!associatedCourses.isEmpty()) {
                message.append("\n\nThe following courses will also be deleted:\n");
                for (Course course : associatedCourses) {
                    message.append("- ")
                            .append(course.getCourseName())
                            .append(" (")
                            .append(course.getCourseCode())
                            .append(")\n");
                }
            } else {
                message.append("\n\nNo courses are associated with this subject.");
            }

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, message.toString(), ButtonType.OK, ButtonType.CANCEL);
            confirm.setTitle("Confirm Deletion");
            confirm.setHeaderText(null);
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Delete the subject and associated courses
                    courseDAO.removeCoursesBySubject(selected.getSubjectCode());
                    subjectDAO.removeSubject(selected.getSubjectName());
                    loadAllSubjects();
                    String searchText = subjectSearch.getText();
                    if (searchText != null && !searchText.trim().isEmpty()) {
                        filterSubjects(searchText);
                    } else {
                        searchResultsList.clear();
                        searchResultsTable.setItems(searchResultsList);
                    }
                    exporter.exportData(); // Export after deleting subject and associated courses
                }
            });
        }
    }

    @FXML
    private void handleSaveSubject() {
        String name = subjectNameField.getText().trim();
        String code = subjectCodeField.getText().trim();

        if (name.isEmpty() || code.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Subject name and code are required.");
            return;
        }

        if (selectedSubject == null) {
            // Add new subject
            Subject newSubject = new Subject(name, code);
            subjectDAO.addSubject(newSubject);
        } else {
            // Update existing subject
            subjectDAO.updateSubject(new Subject(name, code));
            selectedSubject = null;
        }

        loadAllSubjects();
        filterSubjects(subjectSearch.getText()); // Refresh search too
        clearForm();
        exporter.exportData(); // Export after adding or updating a subject
    }

    @FXML
    private void handleClearForm() {
        clearForm();
        selectedSubject = null;
    }

    private void clearForm() {
        subjectNameField.clear();
        subjectCodeField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
