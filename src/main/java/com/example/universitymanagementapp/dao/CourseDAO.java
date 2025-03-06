package com.example.universitymanagementapp.dao;

import com.example.universitymanagementapp.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    // Handles CRUD operations



    // List of courses
    private List<Course> courses = new ArrayList<>();

    //initialize courses
    public CourseDAO(){
        initializeCourses();
    }

    private void initializeCourses(){
        courses.add(new Course("Mathematics", "Calculus I", 1,
                "Dr. Alan Turing", 30, 25, 1, "Mon/Wed",
                "9:00-11:00 AM", "Room 101", "12/15/2025", "9:00 AM"));
        courses.add(new Course("English", "Literature Basics", 2,
                "Prof. Emily Bronte", 25, 25, 1, "Tue/Thu",
                "9:00-12:00 PM", "Room 102", "12/16/2025", "10:00 AM"));
        courses.add(new Course("English", "Literature Basics", 2,
                "Prof. Emily Bronte", 25, 25, 2, "Mon/Wed",
                "10:00-12:00 PM", "Room 102", "12/16/2025", "10:00 AM"));
        courses.add(new Course("Computer Science", "Introduction to Programming", 3,
                "Prof. Bahar Nozari", 42, 40, 1, "Tue/Thu",
                "12:00-2:00 PM", "Room 103", "12/16/2025", "12:30 PM"));
        courses.add(new Course("Chemistry", "Introduction to Chemistry", 4,
                "Dr. Lucka Lucku", 50, 48, 1, "Mon/Thu",
                "3:00-4:00 PM", "Room 201", "12/14/2025", "4:00 PM"));
        courses.add(new Course("Chemistry", "Introduction to Chemistry", 4,
                "Dr. Lucka Lucku", 50, 48, 2, "Mon/Tue",
                "5:00-6:00 PM", "Room 201", "12/14/2025", "4:00 PM"));
        courses.add(new Course("Chemistry", "Introduction to Chemistry", 4,
                "Dr. Lucka Lucku", 50, 48, 3, "Fri/Thur",
                "2:00-3:00 PM", "Room 201", "12/14/2025", "4:00 PM"));
        courses.add(new Course("English", "Introduction to French", 5,
                "Dr. Lakyn Copeland", 25, 25, 1, "Tue/Thu",
                "4:30-5:30 PM", "Room 202", "12/13/2025", "10:00 AM"));
        courses.add(new Course("English", "Introduction to French", 5,
                "Dr. Lakyn Copeland", 25, 25, 2, "Tue/Thu",
                "5:30-6:30 PM", "Room 202", "12/13/2025", "10:00 AM"));
        courses.add(new Course("Engineering", "Water Resources", 6,
                "Albozr Gharabaghi", 50, 50, 1, "Mon/Fri",
                "9:00-10:30 AM", "Room 203", "12/01/2025", "9:00 AM"));

        //enrolled student
        enrollStudent(1, "Alice Smith");
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

    //get all courses
    public List<Course> getAllCourses(){
        return new ArrayList<>(courses);
    }

    //add courses
    public boolean addCourse(Course course) {
        if (course == null || course.getCourseName().isEmpty() || course.getCourseCode() < 1) {
            System.out.println("Enter a course name.");
        }
        System.out.println("Course added successfully.");
        return courses.add(course);
    }

    // Delete course by course code
    public boolean deleteCourse(int courseCode) {
        System.out.println("Course deleted successfully.");
        return courses.removeIf(course -> course.getCourseCode() == courseCode);
    }

    // Update course
    public boolean updateCourse(Course updatedCourse) {
        if (updatedCourse == null || updatedCourse.getCourseCode() < 1) {
            System.out.println("Invalid course data.");
        }
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseCode() == updatedCourse.getCourseCode()) {
                courses.set(i, updatedCourse);
                return true;
            }
        }
        return false; // Course not found
    }

    // get courses taught
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

    //enroll a student in a course
    public boolean enrollStudent(int courseCode, String studentName) {
        for (Course course : courses) {
            if (course.getCourseCode() == courseCode) {
                course.enrollStudent(studentName);
                return true;
            }
        }
        return false;
    }




    @Override
    public String toString(){
        return courses.toString();
    }

}
