package com.universitymanagement.dao;

import com.universitymanagement.model.Course;
import com.universitymanagement.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDAO {

    //arrayList to store courses and course information
    private final List<Course> courseList = new ArrayList<>();

    //create course method
    //automatically checks for duplicates
    public Course createCourse(Course course) {
        Optional<Course> resultName = courseList.stream()
                .filter(existingCourse->existingCourse.getCourseName()
                        .equalsIgnoreCase(course.getCourseName())).findFirst();
        Optional<Course> resultCode = courseList.stream()
                .filter(existingCourse->existingCourse.getCourseCode()
                        .equalsIgnoreCase(course.getCourseCode())).findFirst();
        if(resultName.isPresent() || resultCode.isPresent()) {
            return null;
        }
        //either throw exception here or throw exception in service based on the condition of null or other determinator


        /* public Course createCourse(Course course) throws DuplicateCourseException {
    // Check if duplicate
    Optional<Course> result = courseList.stream()
        .filter(existingCourse -> existingCourse.getCourseName()
                .equalsIgnoreCase(course.getCourseName()))
        .findFirst();
    if (result.isPresent()) {
        throw new DuplicateCourseException("Course name already exists.");
    }
    courseList.add(course);
    return course;
}
*/

        courseList.add(course);
        return course;
    }

    //search by courseID
    public Course getCourseById(String courseID){
        Optional<Course> result = courseList.stream().filter(course -> course.getCourseCode()
                .equalsIgnoreCase(courseID)).findFirst();
        return result.orElse(null);
    }

    //course get list


    //generate section id

    //Handles CRUD operations for courses.
}