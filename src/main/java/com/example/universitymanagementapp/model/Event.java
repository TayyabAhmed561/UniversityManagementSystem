package com.example.universitymanagementapp.model;

import javafx.scene.image.Image;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Event {
    private String eventName;
    private String eventCode;
    private String eventDescription;
    private Image eventHeaderImage;
    private String eventLocation;
    private Date eventDateTime;
    private int eventCapacity;
    private String eventCost;
    private List<String> registeredStudents;

    public Event(){};

    public Event(String eventName, String eventCode, String eventDescription
            , Image eventHeaderImage, String eventLocation, Date eventDateTime, int eventCapacity, String eventCost, List<String> registeredStudents){
        this.eventName = eventName;
        this.eventCode = eventCode;
        this.eventDescription = eventDescription;
        this.eventHeaderImage = eventHeaderImage;
        this.eventLocation = eventLocation;
        this.eventDateTime = eventDateTime;
        this.eventCapacity = eventCapacity;
        this.eventCost = eventCost;
        this.registeredStudents = registeredStudents;
    }

    //getters & setters
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getEventCode() {
        return eventCode;
    }
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }
    public String getEventDescription() {
        return eventDescription;
    }
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
    public Image getEventHeaderImage() {
        return eventHeaderImage;
    }
    public void setEventHeaderImage(Image eventHeaderImage) {
        this.eventHeaderImage = eventHeaderImage;
    }
    public String getEventLocation() {
        return eventLocation;
    }
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
    public Date getEventDateTime() {
        return eventDateTime;
    }
    public void setEventDateTime(Date eventDateTime) {
        this.eventDateTime = eventDateTime;
    }
    public String getEventCost() {
        return eventCost;
    }
    public void setEventCost(String eventCost) {
        this.eventCost = eventCost;
    }
    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }
    public void setRegisteredStudents(List<String> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }
    public int getEventCapacity() {
        return eventCapacity;
    }
    public void setEventCapacity(int eventCapacity) {
        this.eventCapacity = eventCapacity;
    }

    @Override
    public String toString() {
        return "Event{" + "eventName=" + eventName + ", eventCode=" + eventCode + ", eventDescription=" + eventDescription + ", eventHeaderImage=" + eventHeaderImage + ", eventLocation=" + eventLocation + ", eventDate=" + eventDateTime + ", eventCost=" + eventCost + ", registeredStudents=" + registeredStudents + '}';
    }


}

