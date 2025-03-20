package com.example.universitymanagementapp.controller.FacultyController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AdminFacultyController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<Object> allFacultyTable;

    @FXML
    private TableColumn<Object, String> nameColumn;

    @FXML
    private TableColumn<Object, String> facultyIdColumn;

    @FXML
    private TableColumn<Object, String> researchInterestColumn;

    @FXML
    private TextField facultySearch;

    @FXML
    private TableView<Object> searchResultsTable;

    @FXML
    private TableColumn<Object, String> searchNameColumn;

    @FXML
    private TableColumn<Object, String> searchFacultyIdColumn;

    @FXML
    private TableColumn<Object, String> searchResearchInterestColumn;

    @FXML
    private AnchorPane manageFacultyPane;

    @FXML
    private TextField nameField;

    @FXML
    private TextField facultyIdField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField degreeField;

    @FXML
    private TextField researchInterestField;

    @FXML
    private TextField officeLocationField;

    @FXML
    private ComboBox<String> courseComboBox;

    @FXML
    private TableView<Object> assignedCoursesTable;

    @FXML
    private TableColumn<Object, String> assignedCourseCodeColumn;

    @FXML
    private TableColumn<Object, String> assignedCourseNameColumn;

    @FXML
    public void initialize() {
        // Set up double-click handlers for All Faculty table
        allFacultyTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) { // Double-click
                handleEditFacultyFromTable(allFacultyTable);
            }
        });

        // Set up double-click handlers for Search Results table
        searchResultsTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) { // Double-click
                handleEditFacultyFromTable(searchResultsTable);
            }
        });

        // Placeholder items for ComboBox (to make it visible)
        courseComboBox.getItems().addAll("MATH101 - Calculus 1", "PHYS101 - Physics 101");
    }

    private void handleEditFacultyFromTable(TableView<Object> table) {
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Switch to Manage Faculty tab
            tabPane.getSelectionModel().select(2); // Index 2 is "Manage Faculty" tab

            // Populate the form with dummy data for now
            nameField.setText("Dr. Alan Turing");
            facultyIdField.setText("F0001");
            passwordField.setText("password123");
            emailField.setText("alan.turing@university.edu");
            degreeField.setText("PhD in Computer Science");
            researchInterestField.setText("Artificial Intelligence");
            officeLocationField.setText("Room 305");
        }
    }

    @FXML
    private void handleAddFaculty() {
        // Clear the form and switch to Manage Faculty tab
        handleClearForm();
        tabPane.getSelectionModel().select(2); // Index 2 is "Manage Faculty" tab
    }

    @FXML
    private void handleEditFaculty() {
        // Simulate selecting a faculty member from the All Faculty table
        if (!allFacultyTable.getSelectionModel().isEmpty()) {
            handleEditFacultyFromTable(allFacultyTable);
        }
    }

    @FXML
    private void handleDeleteFaculty() {
        // Placeholder for delete action
        System.out.println("Delete Faculty button clicked");
    }

    @FXML
    private void handleSaveFaculty() {
        // Placeholder for save action
        System.out.println("Save Faculty button clicked");
        System.out.println("Name: " + nameField.getText());
        System.out.println("Faculty ID: " + facultyIdField.getText());
        System.out.println("Password: " + passwordField.getText());
        System.out.println("Email: " + emailField.getText());
        System.out.println("Degree: " + degreeField.getText());
        System.out.println("Research Interest: " + researchInterestField.getText());
        System.out.println("Office Location: " + officeLocationField.getText());
    }

    @FXML
    private void handleClearForm() {
        // Clear all fields in the form
        nameField.clear();
        facultyIdField.clear();
        passwordField.clear();
        emailField.clear();
        degreeField.clear();
        researchInterestField.clear();
        officeLocationField.clear();
        courseComboBox.getSelectionModel().clearSelection();
        assignedCoursesTable.getItems().clear();
    }

    @FXML
    private void handleAssignCourse() {
        // Placeholder for assign course action
        String selectedCourse = courseComboBox.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            System.out.println("Assigned course: " + selectedCourse);
        }
    }

    @FXML
    private void handleUnassignCourse() {
        // Placeholder for unassign course action
        System.out.println("Unassign course button clicked");
    }
}