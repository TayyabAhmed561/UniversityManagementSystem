package com.universitymanagement.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Course {
    private SimpleStringProperty department;
    private SimpleStringProperty courseName;
    private SimpleIntegerProperty courseCode;
    private SimpleStringProperty instructor;
    private SimpleIntegerProperty capacity;
    private SimpleIntegerProperty enrolled;
    private SimpleIntegerProperty section;
    private SimpleStringProperty days;
    private SimpleStringProperty time;
    private SimpleStringProperty room;
    private SimpleStringProperty examDate;
    private SimpleStringProperty examTime;

    // Constructor
    public Course(String department, String courseName, int courseCode,
                  String instructor, int capacity, int enrolled, int section,
                  String days, String time, String room, String examDate, String examTime) {
        this.department = new SimpleStringProperty(department);
        this.courseName = new SimpleStringProperty(courseName);
        this.courseCode = new SimpleIntegerProperty(courseCode);
        this.instructor = new SimpleStringProperty(instructor);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.enrolled = new SimpleIntegerProperty(enrolled);
        this.section = new SimpleIntegerProperty(section);
        this.days = new SimpleStringProperty(days);
        this.time = new SimpleStringProperty(time);
        this.room = new SimpleStringProperty(room);
        this.examDate = new SimpleStringProperty(examDate);
        this.examTime = new SimpleStringProperty(examTime);
    }

    // Default constructor
    public Course() {}

    // Property getters for JavaFX binding
    public SimpleStringProperty departmentProperty() { return department; }
    public SimpleStringProperty courseNameProperty() { return courseName; }
    public SimpleIntegerProperty courseCodeProperty() { return courseCode; }
    public SimpleStringProperty instructorProperty() { return instructor; }
    public SimpleIntegerProperty capacityProperty() { return capacity; }
    public SimpleIntegerProperty enrolledProperty() { return enrolled; }
    public SimpleIntegerProperty sectionProperty() { return section; }
    public SimpleStringProperty daysProperty() { return days; }
    public SimpleStringProperty timeProperty() { return time; }
    public SimpleStringProperty roomProperty() { return room; }
    public SimpleStringProperty examDateProperty() { return examDate; }
    public SimpleStringProperty examTimeProperty() { return examTime; }

    // Standard Getters
    public String getDepartment() { return department.get(); }
    public String getCourseName() { return courseName.get(); }
    public int getCourseCode() { return courseCode.get(); }
    public String getInstructor() { return instructor.get(); }
    public int getCapacity() { return capacity.get(); }
    public int getEnrolled() { return enrolled.get(); }
    public int getSection() { return section.get(); }
    public String getDays() { return days.get(); }
    public String getTime() { return time.get(); }
    public String getRoom() { return room.get(); }
    public String getExamDate() { return examDate.get(); }
    public String getExamTime() { return examTime.get(); }

    // Standard Setters
    public void setDepartment(String department) { this.department.set(department); }
    public void setCourseName(String courseName) { this.courseName.set(courseName); }
    public void setCourseCode(int courseCode) { this.courseCode.set(courseCode); }
    public void setInstructor(String instructor) { this.instructor.set(instructor); }
    public void setCapacity(int capacity) { this.capacity.set(capacity); }
    public void setEnrolled(int enrolled) { this.enrolled.set(enrolled); }
    public void setSection(int section) { this.section.set(section); }
    public void setDays(String days) { this.days.set(days); }
    public void setTime(String time) { this.time.set(time); }
    public void setRoom(String room) { this.room.set(room); }
    public void setExamDate(String examDate) { this.examDate.set(examDate); }
    public void setExamTime(String examTime) { this.examTime.set(examTime); }
}
