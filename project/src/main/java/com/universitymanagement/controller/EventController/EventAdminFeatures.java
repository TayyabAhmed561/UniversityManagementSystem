package com.universitymanagement.controller.EventController;

import com.universitymanagement.model.Event;
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

public class EventAdminFeatures {

    @FXML
    private TextField eventNameField;
    @FXML
    private TextField eventCodeField;
    @FXML
    private ListView<String> eventsListView;

    private final ObservableList<Event> eventList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Sample data for testing
        eventList.add(new Event("Science Fair", "EV001", "A fair showcasing scientific projects",
                "default.jpg", "Main Hall", "2025-04-10", "10:00 AM", 100, 0));
        eventList.add(new Event("Art Workshop", "EV002", "Workshop on modern art techniques",
                "default.jpg", "Room 102", "2025-05-05", "1:00 PM", 50, 0));
        updateEventListView();
    }

    @FXML
    public void handleAddEvent(ActionEvent event) {
        String eventName = eventNameField.getText();
        String eventCode = eventCodeField.getText();

        if (eventName.isEmpty() || eventCode.isEmpty()) {
            showAlert("Error", "Event Name and Event Code cannot be empty.");
            return;
        }

        Event newEvent = new Event(eventName, eventCode, "Description", "default.jpg",
                "Location", "2025-01-01", "10:00 AM", 100, 0);
        eventList.add(newEvent);
        updateEventListView();
        showAlert("Success", "Event added successfully!");
    }

    @FXML
    public void handleDeleteEvent(ActionEvent event) {
        String selectedEvent = eventsListView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            String eventCode = selectedEvent.split(" - ")[0];
            eventList.removeIf(eventItem -> eventItem.getEventCode().equals(eventCode));
            updateEventListView();
            showAlert("Success", "Event deleted successfully!");
        } else {
            showAlert("Error", "Please select an event to delete.");
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

    private void updateEventListView() {
        eventsListView.getItems().clear();
        for (Event event : eventList) {
            eventsListView.getItems().add(event.getEventCode() + " - " + event.getEventName());
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
