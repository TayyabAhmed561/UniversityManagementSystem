package com.example.universitymanagementapp.controller.CourseController;

import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.dao.CourseDAO;

import java.util.ArrayList;
import java.util.List;

public class CourseStudentFeatures {
    public CourseDAO courseDAO = new CourseDAO();

    //get all courses
    public List<Course> getAllCourses(){
        return courseDAO.getAllCourses();
    }

    //view courses enrolled in
    public List<Course> getEnrolledCourses(String studentName){
        List<Course> enrolledCourses = new ArrayList<>();
        for (Course course : courseDAO.getAllCourses()) {
            if (course.getEnrolledStudents().contains(studentName)) {
                enrolledCourses.add(course);
            }
        }
        return enrolledCourses;
    }
}
