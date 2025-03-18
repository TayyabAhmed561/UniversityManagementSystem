package com.example.universitymanagementapp.dao;

import com.example.universitymanagementapp.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private static List<Course> courses = new ArrayList<>();


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
                           int currentEnrollment, String sectionID, String meetingDaysTime, String meetingLocation,
                           String finalExamDateTime){
        for(Course course : courses) {
            if(course.getCourseName().equals(courseName)){
                course.setSubjectName(subjectName);
                course.setCourseName(courseName);
                course.setCourseCode(courseCode);
                course.setInstructor(instructor);
                course.setCapacity(capacity);
                course.setCurrentEnrollment(currentEnrollment);
                course.setSectionID(sectionID);
                course.setMeetingDaysTime(meetingDaysTime);
                course.setMeetingLocation(meetingLocation);
                course.setFinalExamDateTime(finalExamDateTime);
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
        String normalizedFacultyName = instructorName.replaceAll("[^a-zA-Z\\s]", "").trim();
        for (Course course : courses) {
            if (course.getInstructor() != null) {
                String normalizedInstructor = course.getInstructor().replaceAll("[^a-zA-Z\\s]", "").trim();
                if (normalizedInstructor.equalsIgnoreCase(normalizedFacultyName)) {
                    instructorCourses.add(course);
                }
            }
        }
        if (instructorCourses.isEmpty()) {
            System.out.println("No courses found for instructor: " + instructorName + " (normalized: " + normalizedFacultyName + ")");
        } else {
            System.out.println("Found " + instructorCourses.size() + " courses for instructor: " + instructorName);
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
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                courseStudents.add(course);
            }
        }
        if (courseStudents.isEmpty()) {
            System.out.println("No students found for course: " + courseName);
        }
        return courseStudents;
    }

    // Get all courses
    public List<Course> getAllCourses() {
        System.out.println("Total courses in CourseDAO: " + courses.size());
        return new ArrayList<>(courses);
    }

    @Override
    public String toString() {
        return "CourseDAO\nCourses: " + courses + "}";
    }
}
