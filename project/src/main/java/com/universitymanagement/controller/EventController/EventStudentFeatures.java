package com.universitymanagement.controller.EventController;

public class EventStudentFeatures {
    //View Events: Access the list of upcoming events and view details.
    //- Register for Events: Sign up for events they are interested in attending.
    //- View Registered Events: See the events they have registered for
    
    private final EventDAO eventDAO;

    @FXML
    private ListView<String> eventsListView;
    @FXML
    private TextField studentNameField;

    // Constructor
    public EventUserFeatures() {
        this.eventDAO = new EventDAO(); // Initialize DAO
    }

    // Method to display events
    @FXML
    public void displayEvents() {
        eventsListView.getItems().clear();
        for (Event event : eventDAO.getAllEvents()) {
            eventsListView.getItems().add(event.getEventCode() + " - " + event.getEventName());
        }
    }

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
                    Student student = new Student(studentName); // Assuming Student class has a constructor taking a name
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

    // Helper method to find an event by its event code
    private Event getEventByCode(String eventCode) {
        for (Event event : eventDAO.getAllEvents()) {
            if (event.getEventCode().equals(eventCode)) {
                return event;
            }
        }
        return null;
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