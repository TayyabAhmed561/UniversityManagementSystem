package com.example.universitymanagementapp.model;

public class Grade {
    private int finalGrade;
    private int midtermGrade;
    private int assignmentGrade;
    private int quizGrade;
    private int labGrade;

    //constructors

    public Grade() {};
    public Grade(int finalGrade, int midtermGrade, int assignmentGrade, int quizGrade, int labGrade) {
        this.finalGrade = finalGrade;
        this.midtermGrade = midtermGrade;
        this.assignmentGrade = assignmentGrade;
        this.quizGrade = quizGrade;
        this.labGrade = labGrade;
    }

    //getters & setters

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
                "finalGrade=" + finalGrade +
                ", midtermGrade=" + midtermGrade +
                ", assignmentGrade=" + assignmentGrade +
                ", quizGrade=" + quizGrade +
                ", labGrade=" + labGrade +
                '}';
    }




}
