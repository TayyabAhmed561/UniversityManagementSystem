package com.universitymanagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String status; // Undergraduate, Graduate, PhD
    private String semester;
    private String username;
    private String password;
    private boolean isTuitionPaid;
    private List<String> enrolledCourses = new ArrayList<>(); // Course codes
    private List<CourseGrade> grades = new ArrayList<>();
    private String thesisTitle; // For PhD students
    private double progress; // Percentage of program completed

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random rand = new Random();

    private class CourseGrade {
        String courseCode;
        double grade;

        public CourseGrade(String courseCode, double grade) {
            this.courseCode = courseCode;
            this.grade = grade;
        }
    }

    public Student(String id, String name, String address, String phone, String email, String status, String semester) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.semester = semester;
        this.username = generateUsername(name);
        this.password = generateRandomPassword();
        this.isTuitionPaid = false;
        this.progress = 0.0;
        if (status.equalsIgnoreCase("PhD")) this.thesisTitle = "TBD";
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isTuitionPaid() { return isTuitionPaid; }
    public void setTuitionPaid(boolean isTuitionPaid) { this.isTuitionPaid = isTuitionPaid; }

    public List<String> getEnrolledCourses() { return enrolledCourses; }

    public double getProgress() { return progress; }

    public String getThesisTitle() { return thesisTitle; }
    public void setThesisTitle(String thesisTitle) { this.thesisTitle = thesisTitle; }

    private static String generateRandomPassword() {
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append(CHARACTERS.charAt(rand.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    private static String generateUsername(String fullName) {
        String[] nameParts = fullName.trim().split("\\s+");
        if (nameParts.length < 2) return "unknown";
        char firstInitial = Character.toUpperCase(nameParts[0].charAt(0));
        String lastName = nameParts[1].toLowerCase();
        return firstInitial + lastName;
    }

    public void enrollInCourse(String courseCode) {
        enrolledCourses.add(courseCode);
        System.out.println("Enrolled in " + courseCode);
    }

    public void dropCourse(String courseCode) {
        if (enrolledCourses.remove(courseCode)) {
            System.out.println(courseCode + " dropped.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void assignGrade(String courseCode, double grade) {
        grades.add(new CourseGrade(courseCode, grade));
        System.out.println("Grade assigned for " + courseCode);
        updateProgress();
    }

    public void displayStudentId() {
        System.out.println("Student ID: " + id);
    }

    private void updateProgress() {
        progress = Math.min(100, grades.size() * 10.0); // 10% per course completed
    }
}
