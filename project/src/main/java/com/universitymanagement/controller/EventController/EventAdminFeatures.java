package com.universitymanagement.controller.EventController;

import com.universitymanagement.dao.EventDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class EventAdminFeatures {
    //Add Event: Create new events with comprehensive details.
    //- Edit Event: Update event information. Also be able to upload an image as a new
    //header image.
    //- Delete Event: Remove events from the schedule.
    //- View Events: Display upcoming events with details.
    //- Manage Registrations: Track and manage student registrations for events.
    
    private final EventDAO eventDAO;

    @FXML
    private TextField eventNameField;
    @FXML
    private TextField eventCodeField;
    @FXML
    private ListView<String> eventsListView;

    // Constructor
    public EventAdminFeatures() {
        this.eventDAO = new EventDAO(); // Initialize DAO
    }

    // Method to add event
    @FXML
    public void addEvent() {
        String eventName = eventNameField.getText();
        String eventCode = eventCodeField.getText();

        if (eventName.isEmpty() || eventCode.isEmpty()) {
            showAlert("Error", "Event Name and Event Code cannot be empty.");
            return;
        }

        // Create Event object (other attributes can be added similarly)
        Event event = new Event(eventName, eventCode, "Description", "default.jpg", 
                                "Location", "2025-01-01", "10:00 AM", 100, 0);
        eventDAO.addEvent(event);
        updateEventListView();  // Refresh the event list
        showAlert("Success", "Event added successfully!");
    }

    // Method to delete event
    @FXML
    public void deleteEvent() {
        String selectedEvent = eventsListView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            String eventCode = selectedEvent.split(" - ")[0]; // Extract the event code from the list item
            eventDAO.deleteEvent(eventCode);
            updateEventListView();  // Refresh the event list
        } else {
            showAlert("Error", "Please select an event to delete.");
        }
    }

    // Helper method to update the ListView
    private void updateEventListView() {
        eventsListView.getItems().clear();
        ArrayList<Event> events = eventDAO.getAllEvents();
        for (Event event : events) {
            eventsListView.getItems().add(event.getEventCode() + " - " + event.getEventName());
        }
    }
    
     // Helper method to show alerts
     private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
