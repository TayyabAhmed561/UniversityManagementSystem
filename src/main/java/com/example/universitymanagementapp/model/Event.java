package com.example.universitymanagementapp.model;

import javafx.scene.image.Image;
import java.util.Date;
import java.util.List;

public class Event {
    private String eventName;
    private String eventCode;
    private String eventDescription;
    private Image eventHeaderImage;
    private String eventLocation;
    private Date eventDate;
    private String eventTime;
    private double eventCost;
    private List<Student> registeredStudents;

    public Event(){};

    public Event(String eventName, String eventCode, String eventDescription
            , Image eventHeaderImage, String eventLocation, Date eventDate
            , String eventTime, double eventCost, List<Student> registeredStudents){
        this.eventName = eventName;
        this.eventCode = eventCode;
        this.eventDescription = eventDescription;
        this.eventHeaderImage = eventHeaderImage;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
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
    public Date getEventDate() {
        return eventDate;
    }
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
    public String getEventTime() {
        return eventTime;
    }
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
    public double getEventCost() {
        return eventCost;
    }
    public void setEventCost(double eventCost) {
        this.eventCost = eventCost;
    }
    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }
    public void setRegisteredStudents(List<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    @Override
    public String toString() {
        return "Event\neventName=" + eventName + ", eventCode=" + eventCode + ", eventDescription=" + eventDescription + ", eventHeaderImage=" + eventHeaderImage + ", eventLocation=" + eventLocation + ", eventDate=" + eventDate + ", eventTime=" + eventTime + ", eventCost=" + eventCost + ", registeredStudents=" + registeredStudents;
    }


}
