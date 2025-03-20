package com.example.universitymanagementapp.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Student extends User {
    private String name;
    private String studentId;
    private Image profilePicture;
    private String address;
    private String phoneNumber;
    private int tuitionFees;
    private List<Course> registeredCourses;
    private String email;
    private Map<Integer, Grade> grades; // Changed to Map
    private String currentSemester;
    private List<String> registeredSubjects;
    private String academicLevel;
    private String thesisTitle;
    private double progress;

    public Student() {
        this.registeredCourses = new ArrayList<>();
        this.grades = new HashMap<>();
        this.registeredSubjects = new ArrayList<>();
    }

    public Student(String username, String hashedPassword, String name, String studentId, Image profilePicture, String address, String phoneNumber,
                   int tuitionFees, List<Course> registeredCourses, String email, Map<Integer, Grade> grades,
                   String currentSemester, List<String> registeredSubjects, String academicLevel, String thesisTitle,
                   double progress) {
        super(username, hashedPassword, "student");
        this.name = name;
        this.studentId = studentId;
        this.profilePicture = profilePicture;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.tuitionFees = tuitionFees;
        this.registeredCourses = registeredCourses != null ? new ArrayList<>(registeredCourses) : new ArrayList<>();
        this.email = email;
        this.grades = grades != null ? new HashMap<>(grades) : new HashMap<>();
        this.currentSemester = currentSemester;
        this.registeredSubjects = registeredSubjects != null ? new ArrayList<>(registeredSubjects) : new ArrayList<>();
        this.academicLevel = academicLevel;
        this.thesisTitle = thesisTitle;
        this.progress = progress;
    }

    public Student(String name, String studentId, String email, String phoneNumber) {
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registeredCourses = new ArrayList<>();
        this.grades = new HashMap<>();
        this.registeredSubjects = new ArrayList<>();
    }

    // Getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTuition() {
        return tuitionFees;
    }

    public void setTuition(int tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<Course> registeredCourses) {
        System.out.println("Setting registered courses for student " + studentId + ": " + registeredCourses);
        this.registeredCourses = new ArrayList<>(registeredCourses);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<Integer, Grade> getGrades() {
        return grades;
    }

    public void setGrades(Map<Integer, Grade> grades) {
        this.grades = grades;
    }

    public String getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }

    public List<String> getRegisteredSubjects() {
        return registeredSubjects;
    }

    public void setRegisteredSubjects(List<String> registeredSubjects) {
        this.registeredSubjects = registeredSubjects;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public int getNumberOfRegisteredCourses() {
        return registeredCourses != null ? registeredCourses.size() : 0;
    }

    public int getNumberOfRegisteredSubjects() {
        return registeredSubjects != null ? registeredSubjects.size() : 0;
    }

    @Override
    public String toString() {
        return "Student\nName: " + name + "\nStudent ID: " + studentId + "\nProfile Picture: " + profilePicture
                + "\nAddress: " + address + "\nPhone Number: " + phoneNumber + "\nTuition Fees: " + tuitionFees
                + "\nRegistered Courses: " + registeredCourses + "\nEmail: " + email + "\nGrades: " + grades
                + "\nCurrent Semester: " + currentSemester + "\nRegistered Subjects: " + registeredSubjects
                + "\nAcademic Level: " + academicLevel + "\nThesis Title: " + thesisTitle + "\nProgress: " + progress + "%";
    }
}


