package com.example.universitymanagementapp.model;

import javafx.scene.image.Image;
import jdk.jfr.Percentage;

import java.util.List;

public class Student {
    private String name;
    private String studentId;
    private Image profilePicture;
    private String address;
    private String phoneNumber;
    private int tuitionFees; //consider making this a class to store tuition info
    private List<Course> registeredCourses;
    private String email;
    private List<Grade> grades;
    private String currentSemester; //consider making it an int and switching print out statement based on number
    private List<Subject> registeredSubjects;
    private String academicLevel;
    private String thesisTitle;
    private Percentage progress;

    public Student(){};

    public Student(String name, String studentId, Image profilePicture, String address, String phoneNumber,
                   int tuitionFees, List<Course> registeredCourses, String email, List<Grade> grades,
                   String currentSemester, List<Subject> registeredSubjects, String academicLevel, String thesisTitle,
                   Percentage progress){
        this.name = name;
        this.studentId = studentId;
        this.profilePicture = profilePicture;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.tuitionFees = tuitionFees;
        this.registeredCourses = registeredCourses;
        this.email = email;
        this.grades = grades;
        this.currentSemester = currentSemester;
        this.registeredSubjects = registeredSubjects;
        this.academicLevel = academicLevel;
        this.thesisTitle = thesisTitle;
        this.progress = progress;
    }

    //getters & setters
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
    public int getTuitionFees() {
        return tuitionFees;
    }
    public void setTuitionFees(int tuitionFees) {
        this.tuitionFees = tuitionFees;
    }
    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }
    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Grade> getGrades() {
        return grades;
    }
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
    public String getCurrentSemester() {
        return currentSemester;
    }
    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }
    public List<Subject> getRegisteredSubjects() {
        return registeredSubjects;
    }
    public void setRegisteredSubjects(List<Subject> registeredSubjects) {
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
    public Percentage getProgress() {
        return progress;
    }
    public void setProgress(Percentage progress) {
        this.progress = progress;
    }

    @Override
    public String toString(){
        return "Student\nName: " +name + "\nStudent ID: " +studentId + "\nProfile Picture: " +profilePicture
                + "\nAddress: " +address + "\nPhone Number: " +phoneNumber + "\nTuition Fees: " +tuitionFees
                + "\nRegistered Courses: " +registeredCourses + "\nEmail: " +email + "\nGrades: " +grades
                + "\nCurrent Semester: " +currentSemester + "\nRegistered Subjects: " +registeredSubjects
                + "\nAcademic Level: " +academicLevel + "\nThesis Title: " +thesisTitle + "\nProgress: " +progress;
    }

}
