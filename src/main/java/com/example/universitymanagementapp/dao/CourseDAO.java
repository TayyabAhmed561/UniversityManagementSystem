package com.example.universitymanagementapp.dao;

import com.example.universitymanagementapp.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends Course {

    List<Course> courses = new ArrayList<>();

    //method to get data from service layer

    //add course
    public void addCourse(Course course){
        courses.add(course);
    }

    //delete course
    public void deleteCourse(Course course){
        for(Course c : courses) {
            if (c.getCourseName().equals(course.getCourseName())) {
                courses.remove(c);
            }
        }
    }

    //edit course
    public void editCourse(String subjectName, String courseName, int courseCode, String instructor, int capacity,
                           int currentEnrollment, int sectionID, String meetingDays, String meetingTime, String meetingLocation,
                           String finalExamDate, String finalExamTime){
        for(Course course : courses) {
            if(course.getCourseName().equals(courseName)){
                course.setSubjectName(subjectName);
                course.setCourseName(courseName);
                course.setCourseCode(courseCode);
                course.setInstructor(instructor);
                course.setCapacity(capacity);
                course.setCurrentEnrollment(currentEnrollment);
                course.setSectionID(sectionID);
                course.setMeetingDays(meetingDays);
                course.setMeetingTime(meetingTime);
                course.setMeetingLocation(meetingLocation);
                course.setFinalExamDate(finalExamDate);
                course.setFinalExamTime(finalExamTime);
            }

        }
    }


    // Search course by Course Code
    public List<Course> getCourseByCode(int Code){
        if(Code < 1){
            throw new IllegalArgumentException("Invalid course code.");
        }
        List<Course> matchingCodes = new ArrayList<>();
        for(Course course : courses){
            if(course.getCourseCode() == Code){
                matchingCodes.add(course);
            }
        }
        if (matchingCodes.isEmpty()){
            System.out.println("No course found with code " + Code);
        }
        System.out.println("Matching codes: ");
        return matchingCodes;
    }

    // Search course by name
    public List<Course> getCourseByName(String Name){
        if(Name == null || Name.trim().isEmpty()){
            System.out.println("Enter a course name.");
        }
        List<Course> matchingCourses = new ArrayList<>();
        for(Course course : courses){
            if(course.getCourseName().equals(Name)){
                matchingCourses.add(course);
            }
        }
        if(matchingCourses.isEmpty()){
            System.out.println("No course found with name " + Name);
        }
        return matchingCourses;
    }

    // Get courses taught
    public List<Course> getCoursesTaught(String instructorName) {
        if (instructorName == null || instructorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid instructor name.");
        }
        List<Course> instructorCourses = new ArrayList<>();
        for (Course course : courses) {
            if(course.getInstructor().equalsIgnoreCase(instructorName)){
                instructorCourses.add(course);
            }
        }
        if (instructorCourses.isEmpty()) {
            System.out.println("No courses found for instructor: " + instructorName);
        }
        return instructorCourses;
    }

    // Get enrolled students
    public List<Course> getEnrolledStudents(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid course name.");
        }
        List<Course> courseStudents = new ArrayList<>();
        for (Course course : courses) {
            if(course.getCourseName().equalsIgnoreCase(courseName)){
                courseStudents.add(course);
            }
        }
        if (courseStudents.isEmpty()) {
            System.out.println("No students found for course: " + courseName);
        }
        return courseStudents;
    }

    //get all courses
    public List<Course> getAllCourses(){
        return new ArrayList<>(courses);
    }

    @Override
    public String toString(){
        return "Course{ Courses: " +courses + "}";
    }

}
