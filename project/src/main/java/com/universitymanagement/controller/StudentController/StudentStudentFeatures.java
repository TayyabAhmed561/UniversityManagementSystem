package com.universitymanagement.controller.StudentController;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Manages student details and operations
public class Student {
    public String id;
    public String name;
    private String address;
    private String phone;
    private String email;
    public String status;
    public String semester;
    public String username;
    private String password;
    private boolean isTuitionPaid;
    public List<String> enrolledCourses = new ArrayList<>();
    private List<CourseGrade> grades = new ArrayList<>(); // Stores course grades
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random rand = new Random();

    // Inner class to represent a course and its grade
    private class CourseGrade {
        String courseName;
        double grade;

        public CourseGrade(String courseName, double grade) {
            this.courseName = courseName;
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
        this.isTuitionPaid = false; // Default tuition status is unpaid
    }

    // Generate a random password
    private static String generateRandomPassword() {
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append(CHARACTERS.charAt(rand.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    // Generate a username from the student's name
    private static String generateUsername(String fullName) {
        String[] nameParts = fullName.trim().split("\\s+");
        if (nameParts.length < 2) return "unknown";
        char firstInitial = Character.toUpperCase(nameParts[0].charAt(0));
        String lastName = nameParts[1].toLowerCase();
        return firstInitial + lastName;
    }

    // Display student details
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
        System.out.println("----------------------------");
    }

    // Update student details
    public void updateStudentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new address: ");
        this.address = scanner.nextLine();
        System.out.println("Enter new phone number: ");
        this.phone = scanner.nextLine();
        System.out.println("Enter new email: ");
        this.email = scanner.nextLine();
        System.out.println("Details updated.");
    }

    // Change password
    public void changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new password: ");
        this.password = scanner.nextLine();
        System.out.println("Password updated.");
    }

    // Enroll in a course
    public void enrollInCourse(String course) {
        enrolledCourses.add(course);
        System.out.println(course + " enrolled.");
    }

    // Drop a course
    public void dropCourse(String course) {
        if (enrolledCourses.remove(course)) {
            System.out.println(course + " dropped.");
        } else {
            System.out.println("Course not found.");
        }
    }

    // View profile
    public void viewProfile() {
        displayStudentDetails();
        System.out.println("Enrolled Courses: " + String.join(", ", enrolledCourses));
    }

    // View grades
    public void viewGrades() {
        if (grades.isEmpty()) {
            System.out.println("No grades available.");
        } else {
            System.out.println("Grades for " + name + ":");
            for (CourseGrade courseGrade : grades) {
                System.out.println(courseGrade.courseName + ": " + courseGrade.grade);
            }
        }
    }

    // Assign a grade for a course
    public void assignGrade(String course, double grade) {
        grades.add(new CourseGrade(course, grade));
        System.out.println("Grade assigned for " + course);
    }

    // Tuition management
    public void tuitionManagement() {
        int tuition = status.equalsIgnoreCase("Graduate") ? 4000 : 5000;
        System.out.println("Your tuition fee for this semester: $" + tuition);
        System.out.println("Payment status: " + (isTuitionPaid ? "Paid" : "Unpaid"));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pay tuition fee now? (yes/no)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            isTuitionPaid = true;
            System.out.println("Payment successful. Tuition fee paid.");
        }
    }
}