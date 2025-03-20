package com.example.universitymanagementapp.model;

import java.time.LocalDateTime;

public class Registration {
    private String studentId;
    private int courseCode;
    private LocalDateTime date;

    public Registration(String studentId, int courseCode, LocalDateTime date) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.date = date;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
