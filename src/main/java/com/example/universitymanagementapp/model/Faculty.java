package com.example.universitymanagementapp.model;

import java.util.List;

// attributes
public class Faculty extends User{
    private String name;
    private String email;
    private String degree;
    private String researchInterest;
    private List<String> coursesOffered;
    private String officeLocation;

    // constructor
    public Faculty(String username, String password, String name, String email, String degree, String researchInterest,
                   List<String> coursesOffered, String officeLocation) {
        super(username, password, "Faculty");
        this.name = name;
        this.email = email;
        this.degree = degree;
        this.researchInterest = researchInterest;
        this.coursesOffered = coursesOffered;
        this.officeLocation = officeLocation;
    }

    // getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getResearchInterest() {
        return researchInterest;
    }

    public void setResearchInterest(String researchInterest) {
        this.researchInterest = researchInterest;
    }

    public List<String> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<String> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    @Override
    public String toString() {
        return "Faculty\n" +
                "username='" + getUsername() + "\n" +
                "password='" + getPassword() + "\n" +
                "name='" + name + "\n" +
                "email='" + email + "\n" +
                "degree='" + degree + "\n" +
                "researchInterest='" + researchInterest + "\n" +
                "coursesOffered=" + coursesOffered + "\n" +
                "officeLocation='" + officeLocation + "\n";
    }

}

