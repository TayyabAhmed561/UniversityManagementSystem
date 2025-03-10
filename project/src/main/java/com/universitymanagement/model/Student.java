package com.universitymanagement.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty phone;
    private final StringProperty email;
    private final StringProperty status;
    private final StringProperty semester;
    public List<String> enrolledCourses = new ArrayList<>();

    // Full Constructor with all parameters
    public Student(String id, String name, String address, String phone, String email, String status, String semester) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.status = new SimpleStringProperty(status);
        this.semester = new SimpleStringProperty(semester);
    }

    // Constructor with five parameters
    public Student(String id, String name, String address, String phone, String email) {
        this(id, name, address, phone, email, "N/A", "N/A");
    }

    // Simplified constructor for event registration
    public Student(String id, String name) {
        this(id, name, "N/A", "N/A", "N/A", "N/A", "N/A");
    }

    // Getters
    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getSemester() {
        return semester.get();
    }

    // Setters
    public void setStatus(String status) {
        this.status.set(status);
    }

    public void setSemester(String semester) {
        this.semester.set(semester);
    }

    // Property methods for JavaFX TableView
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty semesterProperty() {
        return semester;
    }
}
