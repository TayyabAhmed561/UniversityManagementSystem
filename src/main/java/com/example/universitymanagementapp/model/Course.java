package com.example.universitymanagementapp.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String subjectCode;
    private String courseName;
    private int courseCode;
    private String instructor;
    private int capacity;
    private int currentEnrollment;
    private String sectionID;
    private String meetingDaysTime;
    private String meetingLocation;
    private String finalExamDateTime;
    private List<String> enrolledStudents;

    // Course constructors
    public Course() {};

    public Course(String subjectName, String courseName, int courseCode, String instructor, int capacity,
                  int currentEnrollment, String sectionID, String meetingDaysTime, String meetingLocation,
                  String finalExamDateTime) {
        this.subjectCode = subjectName;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.capacity = capacity;
        this.currentEnrollment = currentEnrollment;
        this.sectionID = sectionID;
        this.meetingDaysTime = meetingDaysTime;
        this.meetingLocation = meetingLocation;
        this.finalExamDateTime = finalExamDateTime;
        this.enrolledStudents = new ArrayList<>();
    }

    // Getter & Setter methods
    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    public void setCurrentEnrollment(int currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getMeetingDaysTime() {
        return meetingDaysTime;
    }

    public void setMeetingDaysTime(String meetingDays) {
        this.meetingDaysTime = meetingDaysTime;
    }

    public String getMeetingLocation() {
        return meetingLocation;
    }

    public void setMeetingLocation(String meetingLocation) {
        this.meetingLocation = meetingLocation;
    }

    public String getFinalExamDateTime() {
        return finalExamDateTime;
    }

    public void setFinalExamDateTime(String finalExamDateTime) {
        this.finalExamDateTime = finalExamDateTime;
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<String> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }


    //enroll a student
    public void enrollStudent(String studentName) {
        enrolledStudents.add(studentName);
        int studentCount = enrolledStudents.size();
        setCurrentEnrollment(studentCount);
    }

    //create a function to enroll students in a course with the paramenter of type student.
    //use a search by student name to pull up student details and then enroll student.

    @Override
    public String toString(){
        return "Course\nSubject: " + subjectCode + "\nCourse Name: " +courseName
                + "\nCourse Code: " +courseCode + "\nInstructor: " +instructor
                + "\nCapacity: " +capacity + "\nCurrent Enrollment: " +currentEnrollment
                + "\nSection ID: " +sectionID + "\nMeeting Days: " +meetingDaysTime
                + "\nMeeting Location: " +meetingLocation
                + "\nFinal Exam Date: " +finalExamDateTime
                + "\nEnrolled Students: " +enrolledStudents;
    }

}
