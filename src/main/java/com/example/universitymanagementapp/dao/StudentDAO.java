package com.example.universitymanagementapp.dao;

import com.example.universitymanagementapp.model.*;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends Student {
    private CourseDAO courseDAO = new CourseDAO();
    private List<Course> courses;
    private List<Subject> subjects;
    private static List<Student> students = new ArrayList<>();

    // Add student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("✅ Student added: " + student.getName() + " (" + student.getStudentId() + ")");
    }

    // Remove student
    public void removeStudent(Student student) {
        students.remove(student);
    }

    // Edit student details
    public void editStudent(String name, String studentId, Image profilePicture,
                            String address, String phoneNumber, int tuitionFees, List<Course> registeredCourses,
                            String email, List<Grade> grades, String currentSemester, List<String> registeredSubjects,
                            String academicLevel, String thesisTitle, double progress) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                student.setName(name);
                student.setStudentId(studentId);
                student.setProfilePicture(profilePicture);
                student.setAddress(address);
                student.setPhoneNumber(phoneNumber);
                student.setTuitionFees(tuitionFees);
                student.setRegisteredCourses(registeredCourses);
                student.setEmail(email);
                student.setGrades(grades);
                student.setCurrentSemester(currentSemester);
                student.setRegisteredSubjects(registeredSubjects);
                student.setAcademicLevel(academicLevel);
                student.setThesisTitle(thesisTitle);
                student.setProgress(progress);
            }
        }
    }


    //get all students
    public List<Student> getAllStudents(){
        return students;
    }


}
