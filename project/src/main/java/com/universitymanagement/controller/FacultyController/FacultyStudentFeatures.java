package com.universitymanagement.controller.FacultyController;

import com.universitymanagement.model.Faculty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Collections;

public class FacultyStudentFeatures {

    @FXML
    private TableView<Faculty> facultyTable;
    @FXML
    private TableColumn<Faculty, String> nameColumn;
    @FXML
    private TableColumn<Faculty, String> emailColumn;
    @FXML
    private TableColumn<Faculty, String> researchColumn;
    @FXML
    private TableColumn<Faculty, String> officeColumn;

    @FXML
    public void initialize() {
        // Initialize the table columns with the Faculty model properties
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        researchColumn.setCellValueFactory(new PropertyValueFactory<>("researchInterest"));
        officeColumn.setCellValueFactory(new PropertyValueFactory<>("officeLocation"));

        // Populate the table with mock data for presentation
        facultyTable.setItems(getMockFacultyData());
    }

    private ObservableList<Faculty> getMockFacultyData() {
        ObservableList<Faculty> facultyList = FXCollections.observableArrayList();
        facultyList.add(new Faculty("Dr. Jane Smith", "jsmith@university.edu", "PhD in Biology",
                "Molecular Biology", Collections.emptyList(), "", "Room 101", "123-456-7890", "Biology"));
        facultyList.add(new Faculty("Dr. Alan Brown", "abrown@university.edu", "PhD in Physics",
                "Physics", Collections.emptyList(), "", "Room 203", "234-567-8901", "Physics"));
        facultyList.add(new Faculty("Dr. Maria Lee", "mlee@university.edu", "PhD in Chemistry",
                "Organic Chemistry", Collections.emptyList(), "", "Room 305", "345-678-9012", "Chemistry"));
        facultyList.add(new Faculty("Dr. Emily Davis", "edavis@university.edu", "PhD in Computer Science",
                "Artificial Intelligence", Collections.emptyList(), "", "Room 410", "456-789-0123", "Computer Science"));
        facultyList.add(new Faculty("Dr. Robert Johnson", "rjohnson@university.edu", "PhD in Mathematics",
                "Topology", Collections.emptyList(), "", "Room 512", "567-890-1234", "Mathematics"));
        return facultyList;
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        try {
            Parent viewRoot = FXMLLoader.load(getClass().getResource("/com/universitymanagement/roleViews/student-dashboard-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Student Dashboard");
            stage.setScene(new Scene(viewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Student Dashboard.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
