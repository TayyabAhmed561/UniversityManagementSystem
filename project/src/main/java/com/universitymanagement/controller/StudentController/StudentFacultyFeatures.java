package com.universitymanagement.controller.StudentController;

import com.universitymanagement.dao.StudentDAO;
import com.universitymanagement.dao.StudentDAO.CourseRecord;
import com.universitymanagement.model.Student;
//5
public class StudentFacultyFeatures {
    private StudentDAO dao;

    public StudentFacultyFeatures(StudentDAO dao) {
        this.dao = dao;
    }

    public void viewStudentInformation() {
        for (Student student : dao.getAllStudents()) {
            System.out.println("Student ID: " + student.id + " | Name: " + student.name);
        }
    }

    public void viewStudentsInCourse(String courseCode) {
        CourseRecord course = dao.getCourseByCode(courseCode);
        if (course != null) {
            System.out.println("Students enrolled in " + courseCode + ":");
            for (String studentId : course.enrolledStudents) {
                Student student = dao.getStudentById(studentId);
                if (student != null) {
                    System.out.println(student.id + " | " + student.name);
                }
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    public void viewCoursesTaught(String teacherName) {
        System.out.println("Courses taught by " + teacherName + ":");
        for (CourseRecord course : dao.getAllCourses()) {
            if (teacherName.equals(course.teacherName)) {
                System.out.println("Course Name: " + course.courseName + " | Code: " + course.courseCode);
                System.out.println("Lecture Time: " + course.lectureTime + " | Location: " + course.location);
                System.out.println("----------------------------");
            }
        }
    }
}