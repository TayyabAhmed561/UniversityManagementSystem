package com.example.universitymanagementapp.controller.EventController;

import com.example.universitymanagementapp.controller.FacultyController.FacultyDashboard;
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

public class EventFacultyController {

    @FXML
    private TabPane tabPane;

    @FXML
    private ToolBar toolBar;

    @FXML
    private Button backButton;

    @FXML
    private TableView<?> myEventsTable;

    @FXML
    private TableColumn<?, String> myEventCodeColumn;

    @FXML
    private TableColumn<?, String> myEventNameColumn;

    @FXML
    private TableColumn<?, String> myEventDescriptionColumn;

    @FXML
    private TableColumn<?, String> myEventLocationColumn;

    @FXML
    private TableColumn<?, String> myEventDateColumn;

    @FXML
    private TableView<?> availableEventsTable;

    @FXML
    private TableColumn<?, String> availableEventCodeColumn;

    @FXML
    private TableColumn<?, String> availableEventNameColumn;

    @FXML
    private TableColumn<?, String> availableEventDescriptionColumn;

    @FXML
    private TableColumn<?, String> availableEventLocationColumn;

    @FXML
    private TableColumn<?, String> availableEventDateColumn;

    private String facultyUsername;
    private String facultyName;

    private FacultyDashboard parentController;

    public void setParentController(FacultyDashboard parentController) {
        this.parentController = parentController;
    }

    @FXML
    public void initialize() {
        // No data population needed for now, just ensuring the UI loads
        System.out.println("EventFacultyController initialized");
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        try {
            // Navigate back to the FacultyDashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapplication/faculty-dashboard.fxml"));
            Parent root = loader.load();
            FacultyDashboard dashboardController = loader.getController();
            dashboardController.setFacultyName(facultyName);
            dashboardController.setFacultyUsername(facultyUsername);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setTitle("Faculty Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error navigating back to FacultyDashboard: " + e.getMessage());
        }
    }

    // Setter for faculty username
    public void setFacultyUsername(String username) {
        this.facultyUsername = username;
        System.out.println("EventFacultyController: Set facultyUsername to: " + facultyUsername);
    }

    // Setter for faculty name
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
        System.out.println("EventFacultyController: Set facultyName to: " + facultyName);
    }
}
