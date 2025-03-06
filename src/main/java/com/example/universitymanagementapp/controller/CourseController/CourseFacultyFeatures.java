package com.example.universitymanagementapp.controller.CourseController;

import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.dao.CourseDAO;

import java.util.List;

public class CourseFacultyFeatures {
    public CourseDAO courseDAO = new CourseDAO();
    List<Course> instructorCourses = courseDAO.getCoursesTaught("Dr. Alan Turing");

    //get all courses
    public List<Course> getAllCourses(){
        return courseDAO.getAllCourses();
    }

    //search course by code
    public List<Course> getCourse(int courseCode){
        return courseDAO.getCourseByCode(courseCode);
    }

    //courses taught by instructor
    public List<Course> getCoursesTaught(String instructorName){
        instructorName = "Dr. Alan Turing";
        return courseDAO.getCoursesTaught(instructorName);
    }
}
