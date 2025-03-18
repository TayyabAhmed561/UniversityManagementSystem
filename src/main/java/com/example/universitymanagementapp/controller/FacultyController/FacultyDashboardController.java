package com.example.universitymanagementapp.controller.FacultyController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class FacultyDashboardController {

    @FXML
    public MenuButton toggleMenuButton;
    @FXML
    public MenuItem dashboard;
    @FXML
    public MenuItem subjectSelection;
    @FXML
    public MenuItem courseSelection;
    @FXML
    public MenuItem studentSelection;
    @FXML
    public MenuItem facultySelection;
    @FXML
    public MenuItem eventSelection;


    @FXML
    private AnchorPane contentPane; // This is where pages will load dynamically

    @FXML
    public void initialize() {
        contentPane.setPickOnBounds(false); // Ensures it does not block clicks
    }

    private String currentPage = "faculty-dashboard.fxml"; // Track the currently loaded page

    // Method to load FXML content into contentPane
    private void loadPage(String fxmlFile) {
        // Prevent reloading the same page
        if (fxmlFile.equals(currentPage)) {
            return; // Do nothing if already on the same page
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapplication/" + fxmlFile));
            Parent newPage = loader.load();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(newPage);

            AnchorPane.setTopAnchor(newPage, 0.0);
            AnchorPane.setBottomAnchor(newPage, 0.0);
            AnchorPane.setLeftAnchor(newPage, 0.0);
            AnchorPane.setRightAnchor(newPage, 0.0);

            currentPage = fxmlFile; // Update current page
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML: " + fxmlFile);
        }
    }

    @FXML
    public void handleDashboardAction(ActionEvent actionEvent) {
        // Load the dashboard only if the user is not already on it
        loadPage("faculty-dashboard.fxml");
    }

    @FXML
    public void handleSubjectSelection(ActionEvent actionEvent) {
        loadPage("faculty-subject-selection.fxml");
    }

    @FXML
    public void handleCourseSelection(ActionEvent actionEvent) {
        loadPage("faculty-course-selection.fxml");
    }

    @FXML
    public void handleStudentSelection(ActionEvent actionEvent) {
        loadPage("faculty-student-selection.fxml");
    }

    @FXML
    public void handleFacultySelection(ActionEvent actionEvent) {
        loadPage("faculty-faculty-selection.fxml");
    }

    @FXML
    public void handleEventSelection(ActionEvent actionEvent) {
        loadPage("faculty-event-selection.fxml");
    }
}
