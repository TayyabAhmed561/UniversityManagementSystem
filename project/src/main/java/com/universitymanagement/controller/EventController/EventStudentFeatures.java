package com.universitymanagement.controller.EventController;

import com.universitymanagement.model.Event;
import com.universitymanagement.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EventStudentFeatures {

    @FXML
    private ListView<String> eventsListView;
    @FXML
    private TextField studentNameField;

    private final ObservableList<Event> eventList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Sample data for testing
        eventList.add(new Event("Science Fair", "EV001", "A fair showcasing scientific projects",
                "default.jpg", "Main Hall", "2025-04-10", "10:00 AM", 100, 0));
        eventList.add(new Event("Art Workshop", "EV002", "Workshop on modern art techniques",
                "default.jpg", "Room 102", "2025-05-05", "1:00 PM", 50, 0));
        displayEvents();
    }

    // Method to display events
    @FXML
    public void displayEvents() {
        eventsListView.getItems().clear();
        for (Event event : eventList) {
            eventsListView.getItems().add(event.getEventCode() + " - " + event.getEventName());
        }
    }

    // Method for user to register for an event
    // Method for user to register for an event
    @FXML
    public void registerForEvent() {
        String studentName = studentNameField.getText();
        String selectedEvent = eventsListView.getSelectionModel().getSelectedItem();

        if (studentName.isEmpty()) {
            showAlert("Error", "Please enter your name.");
            return;
        }

        if (selectedEvent != null) {
            String eventCode = selectedEvent.split(" - ")[0]; // Extract the event code
            Event event = getEventByCode(eventCode);

            if (event != null) {
                if (event.isFull()) {
                    showAlert("Error", "Event is full. Registration not possible.");
                } else {
                    Student student = new Student(studentName, studentName); // Corrected constructor
                    event.addStudent(student);
                    showAlert("Success", studentName + " successfully registered for " + event.getEventName() + "!");
                }
            } else {
                showAlert("Error", "Event not found.");
            }
        } else {
            showAlert("Error", "Please select an event to register for.");
        }
    }


    // Method to handle the Back button action
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

    // Helper method to find an event by its event code
    private Event getEventByCode(String eventCode) {
        for (Event event : eventList) {
            if (event.getEventCode().equals(eventCode)) {
                return event;
            }
        }
        return null;
    }

    // Helper method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
