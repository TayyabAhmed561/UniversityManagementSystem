package com.universitymanagement.controller.StudentController;

import com.universitymanagement.dao.StudentDAO;
import com.universitymanagement.dao.StudentDAO.CourseRecord;
import com.universitymanagement.model.Student;
//5
public class StudentAdminFeatures {
    private StudentDAO dao;

    public StudentAdminFeatures(StudentDAO dao) {
        this.dao = dao;
    }

    // Student Management
    public void addStudent(String id, String name, String address, String phone, String email, String status, String semester) {
        Student student = new Student(id, name, address, phone, email, status, semester);
        dao.addStudent(student);
        System.out.println("Student added successfully.");
    }

    public void deleteStudent(String studentId) {
        dao.deleteStudent(studentId);
        System.out.println("Student deleted successfully.");
    }

    public void viewStudentProfile(String studentId) {
        Student student = dao.getStudentById(studentId);
        if (student != null) {
            student.viewProfile();
        } else {
            System.out.println("Student not found.");
        }
    }

    public void manageEnrollments(String studentId, String courseCode) {
        Student student = dao.getStudentById(studentId);
        if (student != null && dao.enrollStudentInCourse(studentId, courseCode)) {
            student.enrollInCourse(courseCode);
        } else {
            System.out.println("Enrollment failed: Student or course not found, or course is full.");
        }
    }

    public void assignGrade(String studentId, String courseCode, double grade) {
        Student student = dao.getStudentById(studentId);
        if (student != null) {
            student.assignGrade(courseCode, grade);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Course Management
    public void addCourse(String courseName, String courseCode, String subjectName, String sectionNumber, int capacity, String lectureTime, String finalExamDateTime, String location) {
        CourseRecord course = new CourseRecord(courseName, courseCode, subjectName, sectionNumber, capacity, lectureTime, finalExamDateTime, location);
        dao.addCourse(course);
        System.out.println("Course added successfully.");
    }

    public void editCourse(String courseCode, String newLectureTime, String newLocation) {
        CourseRecord course = dao.getCourseByCode(courseCode);
        if (course != null) {
            course.lectureTime = newLectureTime;
            course.location = newLocation;
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void deleteCourse(String courseCode) {
        dao.deleteCourse(courseCode);
        System.out.println("Course deleted successfully.");
    }

    public void viewCourses() {
        for (CourseRecord course : dao.getAllCourses()) {
            System.out.println("Course Name: " + course.courseName + " | Code: " + course.courseCode + " | Teacher: " + (course.teacherName != null ? course.teacherName : "Not assigned"));
            System.out.println("Capacity: " + course.capacity + " | Enrolled: " + course.enrolledStudents.size());
            System.out.println("Lecture Time: " + course.lectureTime + " | Location: " + course.location);
            System.out.println("----------------------------");
        }
    }

    public void assignFaculty(String courseCode, String teacherName) {
        CourseRecord course = dao.getCourseByCode(courseCode);
        if (course != null) {
            course.teacherName = teacherName;
            System.out.println("Teacher " + teacherName + " assigned to " + courseCode);
        } else {
            System.out.println("Course not found.");
        }
    }
}