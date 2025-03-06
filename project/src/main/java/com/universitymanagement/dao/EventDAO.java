package com.universitymanagement.dao;
// Imports ArrayList and Event class from model
import com.universitymanagement.model.Event; 
import java.util.ArrayList;

public class EventDAO {
    //Controls event-related database operations (e.g., add event, register students).

    // Initialize ArrayList to store all events
    private final ArrayList<Event> events;

    // Constructor
    public EventDAO() {
        events = new ArrayList<>();
    }

    // Method to check if the event already exists
    public boolean eventExists(String eventCode) {
        for (Event event : events) {
            if (event.getEventCode().equals(eventCode)) {
                return true;
            }
        }
        return false;
    }

    // Method to add event to the list
    public void addEvent(Event event) {
        if (!eventExists(event.getEventCode())) {
            events.add(event);
        } 
    }

    // Method to delete and event from the list
    public void deleteEvent(String eventCode) {
        for (Event event : events) {
            if (event.getEventCode().equals(eventCode)) {
                events.remove(event);
            }
        }
    }

    // Method to get all events
    public ArrayList<Event> getAllEvents() {
        return events;
    }
}
