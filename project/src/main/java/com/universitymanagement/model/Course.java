package com.universitymanagement.model;

public class Course {
    private String department;
    private String courseName;
    private int courseCode;
    private String instructor;
    private int capacity;
    private int enrolled;
    private int section;
    private String days;
    private String time;
    private String room;
    private String examDate;
    private String examTime;

    // Constructor
    public Course(String department, String courseName, int courseCode,
                  String instructor, int capacity, int enrolled, int section,
                  String days, String time, String room, String examDate, String examTime) {
        this.department = department;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.capacity = capacity;
        this.enrolled = enrolled;
        this.section = section;
        this.days = days;
        this.time = time;
        this.room = room;
        this.examDate = examDate;
        this.examTime = examTime;
    }

    // Default constructor
    public Course() {}

    // Getters
    public String getDepartment() { return department; }
    public String getCourseName() { return courseName; }
    public int getCourseCode() { return courseCode; }
    public String getInstructor() { return instructor; }
    public int getCapacity() { return capacity; }
    public int getEnrolled() { return enrolled; }
    public int getSection() { return section; }
    public String getDays() { return days; }
    public String getTime() { return time; }
    public String getRoom() { return room; }
    public String getExamDate() { return examDate; }
    public String getExamTime() { return examTime; }

    // Setters
    public void setDepartment(String department) { this.department = department; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCourseCode(int courseCode) { this.courseCode = courseCode; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setEnrolled(int enrolled) { this.enrolled = enrolled; }
    public void setSection(int section) { this.section = section; }
    public void setDays(String days) { this.days = days; }
    public void setTime(String time) { this.time = time; }
    public void setRoom(String room) { this.room = room; }
    public void setExamDate(String examDate) { this.examDate = examDate; }
    public void setExamTime(String examTime) { this.examTime = examTime; }
}
