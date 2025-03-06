package com.universitymanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String subjectName;
    private String courseName;
    private int courseCode;
    private String instructor;
    private int capacity;
    private int currentEnrollment;
    private int sectionID;
    private String meetingDays;
    private String meetingTime;
    private String meetingLocation;
    private String finalExamDate;
    private String finalExamTime;

    // Course constructor
    public Course(String subjectName, String courseName, int courseCode,String instructor, int capacity,
                  int currentEnrollment, int sectionID, String meetingDays, String meetingTime, String meetingLocation,
                  String finalExamDate, String finalExamTime)
    {
        this.subjectName = subjectName;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.capacity = capacity;
        this.currentEnrollment = currentEnrollment;
        this.sectionID = sectionID;
        this.meetingDays = meetingDays;
        this.meetingTime = meetingTime;
        this.meetingLocation = meetingLocation;
        this.finalExamDate = finalExamDate;
        this.finalExamTime = finalExamTime;
    }

    // Getter & Setter methods
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    public int getSectionID() {
        return sectionID;
    }
    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public String getMeetingDays() {
        return meetingDays;
    }
    public void setMeetingDays(String meetingDays) {
        this.meetingDays = meetingDays;
    }

    public String getMeetingTime() {
        return meetingTime;
    }
    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingLocation() {
        return meetingLocation;
    }
    public void setMeetingLocation(String meetingLocation) {
        this.meetingLocation = meetingLocation;
    }

    public String getFinalExamDate() { return finalExamDate; }
    public void setFinalExamDate(String finalExamDate) { this.finalExamDate = finalExamDate; }

    public String getFinalExamTime() { return finalExamTime; }
    public void setFinalExamTime(String finalExamTime) { this.finalExamTime = finalExamTime; }


    //consider using toString method for debugging/logging

    public static void main(String[] args){

    }



    //Contains course details (e.g., courseId, courseName, subject, capacity).
    //Course Name: A descriptive name of the course.
    //- Course Code: A unique identifier for the course (e.g., 001).
    //- Subject Name: The subject to which the course belongs.
    //- Section Number: The specific section identifier (e.g., Section 1).
    //- Teacher Name: The faculty assigned to teach the course.
    //- Capacity: The maximum number of students allowed.
    //- Lecture Time: Scheduled days and times for the course lectures.
    //- Final Exam Date/Time: The date and time of the final examination.
    //- Location: The venue for lectures (e.g., Room 101)
}














