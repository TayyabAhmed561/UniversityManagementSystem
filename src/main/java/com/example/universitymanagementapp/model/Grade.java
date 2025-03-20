package com.example.universitymanagementapp.model;

public class Grade {
    private int courseCode; // Added to link the grade to a course
    private int finalGrade;
    private int midtermGrade;
    private int assignmentGrade;
    private int quizGrade;
    private int labGrade;

    // Constructors
    public Grade() {}

    public Grade(int courseCode, int finalGrade, int midtermGrade, int assignmentGrade, int quizGrade, int labGrade) {
        this.courseCode = courseCode;
        this.finalGrade = finalGrade;
        this.midtermGrade = midtermGrade;
        this.assignmentGrade = assignmentGrade;
        this.quizGrade = quizGrade;
        this.labGrade = labGrade;
    }

    // Getters & setters
    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
    }

    public int getMidtermGrade() {
        return midtermGrade;
    }

    public void setMidtermGrade(int midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public int getAssignmentGrade() {
        return assignmentGrade;
    }

    public void setAssignmentGrade(int assignmentGrade) {
        this.assignmentGrade = assignmentGrade;
    }

    public int getQuizGrade() {
        return quizGrade;
    }

    public void setQuizGrade(int quizGrade) {
        this.quizGrade = quizGrade;
    }

    public int getLabGrade() {
        return labGrade;
    }

    public void setLabGrade(int labGrade) {
        this.labGrade = labGrade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "courseCode=" + courseCode +
                ", finalGrade=" + finalGrade +
                ", midtermGrade=" + midtermGrade +
                ", assignmentGrade=" + assignmentGrade +
                ", quizGrade=" + quizGrade +
                ", labGrade=" + labGrade +
                '}';
    }
}
