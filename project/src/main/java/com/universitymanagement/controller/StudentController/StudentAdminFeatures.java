package com.universitymanagement.controller.StudentController;

import java.util.List;
import java.util.ArrayList;

// Manages admin operations for students
public class StudentAdminFeatures {
    public List<Student> students;

    public StudentAdminFeatures() {
        students = new ArrayList<>();
    }

    // Add a new student
    public void addStudent(String id, String name, String address, String phone, String email, String status, String semester) {
        students.add(new Student(id, name, address, phone, email, status, semester));
        System.out.println("Student added successfully.");
    }

    // Delete a student
    public void deleteStudent(String studentId) {
        students.removeIf(student -> student.id.equals(studentId));
        System.out.println("Student deleted successfully.");
    }

    // View student profile
    public void viewStudentProfile(String studentId) {
        for (Student student : students) {
            if (student.id.equals(studentId)) {
                student.viewProfile();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Manage enrollments
    public void manageEnrollments(String studentId, String course) {
        for (Student student : students) {
            if (student.id.equals(studentId)) {
                student.enrollInCourse(course);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Drop a course for a student
    public void dropCourse(String studentId, String course) {
        for (Student student : students) {
            if (student.id.equals(studentId)) {
                student.dropCourse(course);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Assign a grade to a student
    public void assignGrade(String studentId, String course, double grade) {
        for (Student student : students) {
            if (student.id.equals(studentId)) {
                student.assignGrade(course, grade);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Track academic progress
    public void progressTracking(String studentId) {
        for (Student student : students) {
            if (student.id.equals(studentId)) {
                student.viewGrades();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Manage tuition
    public void tuitionManagement(String studentId) {
        for (Student student : students) {
            if (student.id.equals(studentId)) {
                student.tuitionManagement();
                return;
            }
        }
        System.out.println("Student not found.");
    }
}