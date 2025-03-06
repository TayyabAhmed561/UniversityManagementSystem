package com.universitymanagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student {
    public String id;
    public String name;
    private String address;
    private String phone;
    private String email;
    public String status; // Undergraduate, Graduate, PhD
    public String semester;
    public String username;
    private String password;
    private boolean isTuitionPaid;
    public List<String> enrolledCourses = new ArrayList<>(); // Course codes
    private List<CourseGrade> grades = new ArrayList<>();
    public String thesisTitle; // For PhD students
    public double progress; // Percentage of program completed

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

    // Getter for Student ID
    public String getStudentID() {
        return id;
    }

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

    public void viewGrades() {
        if (grades.isEmpty()) {
            System.out.println("No grades available.");
        } else {
            System.out.println("Grades for " + name + ":");
            for (CourseGrade courseGrade : grades) {
                System.out.println(courseGrade.courseCode + ": " + courseGrade.grade);
            }
        }
    }

    public void viewProfile() {
        displayStudentDetails();
        System.out.println("Enrolled Courses: " + String.join(", ", enrolledCourses));
    }

    public void displayStudentDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Status: " + status);
        System.out.println("Semester: " + semester);
        System.out.println("Tuition Paid: " + (isTuitionPaid ? "Yes" : "No"));
        if (status.equalsIgnoreCase("PhD")) {
            System.out.println("Thesis Title: " + thesisTitle);
        }
        System.out.println("Progress: " + progress + "%");
        System.out.println("----------------------------");
    }

    public void updateStudentDetails(String newAddress, String newPhone, String newEmail) {
        this.address = newAddress;
        this.phone = newPhone;
        this.email = newEmail;
        System.out.println("Details updated.");
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password updated.");
    }

    public void payTuition() {
        int tuition = status.equalsIgnoreCase("Graduate") ? 4000 : 5000;
        System.out.println("Tuition fee: $" + tuition);
        isTuitionPaid = true;
        System.out.println("Payment successful.");
    }

    public boolean isTuitionPaid() {
        return isTuitionPaid;
    }

    private void updateProgress() {
        progress = Math.min(100, grades.size() * 10.0); // 10% per course completed
    }

    public void setThesisTitle(String title) {
        if (status.equalsIgnoreCase("PhD")) {
            this.thesisTitle = title;
            System.out.println("Thesis title updated.");
        }
    }
}
