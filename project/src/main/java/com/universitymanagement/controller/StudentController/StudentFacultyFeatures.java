//package com.universitymanagement.controller.StudentController;
//import java.util.List;
//
//// Manages faculty operations
//public class Faculty {
//    public void viewStudentInformation(List<Student> students) {
//        for (Student student : students) {
//            System.out.println("Student ID: " + student.id + " | Name: " + student.name);
//        }
//    }
//
//    // View students enrolled in a specific course
//    public void viewStudentsInCourse(List<Student> students, String course) {
//        System.out.println("Students enrolled in " + course + ":");
//        for (Student student : students) {
//            if (student.enrolledCourses.contains(course)) {
//                System.out.println(student.id + " | " + student.name);
//            }
//        }
//    }
//}