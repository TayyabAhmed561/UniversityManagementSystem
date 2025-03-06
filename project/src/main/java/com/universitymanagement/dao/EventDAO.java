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
            System.out.println("Event with code " + event.getEventCode() + " has been added.");
        } else {
            System.out.println("Failure: Event with code " + event.getEventCode() + " already exists.");
        }
    }

    // Method to delete and event from the list
    public void deleteEvent(String eventCode) {
        boolean eventFound = false;
        for (Event event : events) {
            if (event.getEventCode().equals(eventCode)) {
                events.remove(event);
                System.out.println("Event with code " + eventCode + " has been deleted.");
                eventFound = true;
                break;
            }
        }
        if (!eventFound) {
            System.out.println("Failure: Event with code " + eventCode + " not found.");
        }
    }

    // Method to get all events
    public ArrayList<Event> getAllEvents() {
        return events;
    }
}
