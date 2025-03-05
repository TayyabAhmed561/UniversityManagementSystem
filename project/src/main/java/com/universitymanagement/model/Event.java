package com.universitymanagement.model;
import java.util.ArrayList;

public class Event {
    //Represents events (eventId, name, location, capacity).
    //Event Name: The title of the event.
    //- Event Code: A unique identifier for the event (e.g., EV001).
    //- Description: Detailed information about the event.
    //- Header Image: An image representing the event. At First, the default photo is shown.
    //- Location: Venue of the event.
    //- Date and Time: Scheduled date and time.
    //- Capacity: Maximum number of participants.
    //- Cost: Indicates if the event is free or paid; includes the amount if paid.
    //- Registered Students: List of students registered for the event.

    // Event attributes
    private String eventName;
    private String eventCode;
    private String description;
    private String headerImageLocation;
    private String location;
    private String date;
    private String time;
    private int capacity;
    private int cost; // Cost is stored in cents and converted to dollars (/100)
    private ArrayList<Student> registeredStudents;

    // Constructor
    public Event(String eventName, String eventCode, String description,
                 String headerImageLocation, String location, String date, 
                 String time, int capacity, int cost) {
        this.eventName = eventName;
        this.eventCode = eventCode;
        this.description = description;
        this.headerImageLocation = headerImageLocation;
        this.location = location;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
        this.cost = cost;
        this.registeredStudents = new ArrayList<>();
    }

    // Getters
    public String getEventName() {
        return eventName;
    }
    public String getEventCode() {
        return eventCode;
    }
    public String getDescription() {
        return description;
    }
    public String getHeaderImageLocation() {
        return headerImageLocation;
    }
    public String getLocation() {
        return location;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public int getCapacity() {
        return capacity;
    }
    public int getCost() {
        return cost;
    }
    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    } 

    // Setters
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setHeaderImageLocation(String headerImageLocation) {
        this.headerImageLocation = headerImageLocation;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    // Methods to add and remove students from registered students list
    public void addStudent(Student student) {
        registeredStudents.add(student);
    }
    public void removeStudent(Student student) {
        registeredStudents.remove(student);
    }

    // Method to check if event is at capacity
    public boolean isFull() {
        return registeredStudents.size() >= capacity;
    }

    // Method to check if a student is registered
    public boolean isStudentRegistered(String studentID) {
        for (Student student : registeredStudents) {
            if (student.getStudentID().equals(studentID)) {
                return true;
            }
        }
        return false;
    }

    // Method to display info for debugging 
    @Override
    public String toString() {
        return "Event: " + eventName + " (" + eventCode + "), " + 
               "Location: " + location + ", Date: " + date + ", Time: " + time +
               ", Capacity: " + capacity + ", Cost: $" + (cost / 100.0);
    }
}
