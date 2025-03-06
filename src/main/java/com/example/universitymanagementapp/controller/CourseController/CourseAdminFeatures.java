package com.example.universitymanagementapp.controller.CourseController;

import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.dao.CourseDAO;

import java.util.List;

public class CourseAdminFeatures {

    private CourseDAO courseDAO = new CourseDAO();

    public boolean addCourse(Course course){
        return courseDAO.addCourse(course);
    }

    public boolean deleteCourse(int courseCode){
        return courseDAO.deleteCourse(courseCode);
    }

    public List<Course> searchCourseByName(String courseName){
        return courseDAO.getCourseByName(courseName);
    }

    public List<Course> getAllCourses(){
        return courseDAO.getAllCourses();
    }

}
