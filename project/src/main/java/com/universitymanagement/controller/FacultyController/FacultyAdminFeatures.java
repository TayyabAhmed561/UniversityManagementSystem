package com.universitymanagement.controller.FacultyController;

import com.universitymanagement.model.Faculty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Collections;

public class FacultyAdminFeatures {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField degreeField;
    @FXML
    private TextField researchField;
    @FXML
    private TextField officeField;
    @FXML
    private TextField contactNumberField; // New field
    @FXML
    private TextField departmentField; // New field

    @FXML
    private TableView<Faculty> facultyTable;
    @FXML
    private TableColumn<Faculty, String> nameColumn;
    @FXML
    private TableColumn<Faculty, String> emailColumn;

    private final ObservableList<Faculty> facultyList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        emailColumn.setCellValueFactory(data -> data.getValue().emailProperty());
        facultyTable.setItems(facultyList);
    }

    @FXML
    public void handleAddFaculty(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        String degree = degreeField.getText();
        String researchInterest = researchField.getText();
        String officeLocation = officeField.getText();
        String contactNumber = contactNumberField.getText(); // New field
        String department = departmentField.getText(); // New field

        if (name.isEmpty() || email.isEmpty()) {
            showAlert("Input Error", "Name and Email cannot be empty.");
            return;
        }

        Faculty faculty = new Faculty(
                name,
                email,
                degree,
                researchInterest,
                Collections.emptyList(), // Assuming no courses offered initially
                "", // Placeholder for profile photo
                officeLocation,
                contactNumber,
                department
        );

        facultyList.add(faculty);
        clearFields();
        showAlert("Success", "Faculty added successfully.");
    }

    @FXML
    public void handleEditFaculty(ActionEvent event) {
        Faculty selectedFaculty = facultyTable.getSelectionModel().getSelectedItem();
        if (selectedFaculty == null) {
            showAlert("Selection Error", "No faculty selected.");
            return;
        }

        selectedFaculty.setName(nameField.getText());
        selectedFaculty.setEmail(emailField.getText());
        selectedFaculty.setDegree(degreeField.getText());
        selectedFaculty.setResearchInterest(researchField.getText());
        selectedFaculty.setOfficeLocation(officeField.getText());
        selectedFaculty.setContactNumber(contactNumberField.getText()); // Update contact number
        selectedFaculty.setDepartment(departmentField.getText()); // Update department

        facultyTable.refresh();
        clearFields();
        showAlert("Success", "Faculty updated successfully.");
    }

    @FXML
    public void handleDeleteFaculty(ActionEvent event) {
        Faculty selectedFaculty = facultyTable.getSelectionModel().getSelectedItem();
        if (selectedFaculty != null) {
            facultyList.remove(selectedFaculty);
            clearFields();
            showAlert("Success", "Faculty deleted successfully.");
        } else {
            showAlert("Selection Error", "No faculty selected.");
        }
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        try {
            Parent viewRoot = FXMLLoader.load(getClass().getResource("/com/universitymanagement/roleViews/admin-dashboard-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(viewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Admin Dashboard.");
        }
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        degreeField.clear();
        researchField.clear();
        officeField.clear();
        contactNumberField.clear(); // Clear new field
        departmentField.clear(); // Clear new field
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
