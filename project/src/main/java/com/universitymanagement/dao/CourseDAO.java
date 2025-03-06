package com.universitymanagement.dao;

import com.universitymanagement.model.Course;
import com.universitymanagement.model.Subject;

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
    }

    // Search course by Course Code
    public List<Course> getCourseByCode(int Code){
        if(Code < 1){
            throw new IllegalArgumentException("Please enter a course code");
        }
        List<Course> matchingCodes = new ArrayList<>();
        for(Course course : courses){
            if(course.getCourseCode() == Code){
                matchingCodes.add(course);
            }
        }
        if (matchingCodes.isEmpty()){
            return null;
        }
        return matchingCodes;
    }

    // Search course by name
    public List<Course> getCourseByName(String Name){
        if(Name == null){
            throw new IllegalArgumentException("Please enter a name for the course");
        }
        List<Course> matchingCourses = new ArrayList<>();
        for(Course course : courses){
            if(course.getCourseName().equals(Name)){
                matchingCourses.add(course);
            }
        }
        if(matchingCourses.isEmpty()){
            return null;
        }
        return matchingCourses;
    }

    //get all courses
    public List<Course> getAllCourses(){
        return new ArrayList<>(courses);
    }

    //add courses
    public Course addCourse(Course course){
        if(course == null){
            throw new IllegalArgumentException("Please enter a course name");
        }
        courses.add(course);
        return course;
    }

    //delete courses
    public Course deleteCourse(Course course){
        if(course == null){
            throw new IllegalArgumentException("Please enter a course");
        }
        courses.remove(course);
        return course;
    }

    //update courses
    public Course updateCourse(Course course){
        int index = courses.indexOf(course);
        courses.set(index, course);
        return course;
    }

    public String toString(){
        return courses.toString();
    }

}