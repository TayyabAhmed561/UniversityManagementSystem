package com.universitymanagement.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Faculty {
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty degree;
    private final StringProperty researchInterest;
    private final StringProperty officeLocation;
    private List<String> coursesOffered;
    private String profilePhoto;
    private String contactNumber; // Added new field
    private String department; // Added new field

    // Constructor
    public Faculty(String name, String email, String degree, String researchInterest,
                   List<String> coursesOffered, String profilePhoto, String officeLocation,
                   String contactNumber, String department) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.degree = new SimpleStringProperty(degree);
        this.researchInterest = new SimpleStringProperty(researchInterest);
        this.officeLocation = new SimpleStringProperty(officeLocation);
        this.coursesOffered = coursesOffered;
        this.profilePhoto = profilePhoto;
        this.contactNumber = contactNumber;
        this.department = department;
    }

    // Name Property
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    // Email Property
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    // Degree Property
    public String getDegree() {
        return degree.get();
    }

    public void setDegree(String degree) {
        this.degree.set(degree);
    }

    public StringProperty degreeProperty() {
        return degree;
    }

    // Research Interest Property
    public String getResearchInterest() {
        return researchInterest.get();
    }

    public void setResearchInterest(String researchInterest) {
        this.researchInterest.set(researchInterest);
    }

    public StringProperty researchInterestProperty() {
        return researchInterest;
    }

    // Office Location Property
    public String getOfficeLocation() {
        return officeLocation.get();
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation.set(officeLocation);
    }

    public StringProperty officeLocationProperty() {
        return officeLocation;
    }

    // Courses Offered
    public List<String> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<String> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    // Profile Photo
    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    // Contact Number Property (New)
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // Department Property (New)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", degree='" + getDegree() + '\'' +
                ", researchInterest='" + getResearchInterest() + '\'' +
                ", officeLocation='" + getOfficeLocation() + '\'' +
                ", coursesOffered=" + coursesOffered +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
