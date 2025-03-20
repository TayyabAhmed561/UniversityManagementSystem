package com.example.universitymanagementapp.controller.EventController;

import com.example.universitymanagementapp.controller.StudentController.StudentDashboard;
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

public class EventStudentController {

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

    private String studentUsername;
    private String studentName;

    @FXML
    public void initialize() {
        System.out.println("EventStudentController initialized");
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        try {
            // Navigate back to the StudentDashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapplication/student-dashboard.fxml"));
            Parent root = loader.load();
            StudentDashboard dashboardController = loader.getController();
            dashboardController.setStudentName(studentName);
            dashboardController.setStudentId(studentUsername);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setTitle("Student Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error navigating back to StudentDashboard: " + e.getMessage());
        }
    }

    // Setter for student username
    public void setStudentUsername(String username) {
        this.studentUsername = username;
        System.out.println("EventStudentController: Set studentUsername to: " + studentUsername);
    }

    // Setter for student name
    public void setStudentName(String studentName) {
        this.studentName = studentName;
        System.out.println("EventStudentController: Set studentName to: " + studentName);
    }
}