package com.universitymanagement.dao;

import com.universitymanagement.model.Course;
import com.universitymanagement.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    // List of courses
    private List<Course> courses = new ArrayList<>();

    //initialize courses
    public CourseDAO(){
        initializeCourses();
    }

    private void initializeCourses(){
        courses.add(new Course("Mathematics", "Calculus I", "001",
                "Dr. Alan Turing", 30, 25, 1, "Mon/Wed",
                "9:00-11:00 AM", "Room 100"));
        courses.add(new Course("English", "Literature Basics", "002",
                "Prof. Emily Bronte", 25, 25, 1, "Tue/Thu",
                "9:00-12:00 PM", "Room 101"));
        courses.add(new Course("English", "Literature Basics", "002",
                "Prof. Emily Bronte", 25, 25, 2, "Mon/Wed",
                "10:00-12:00 PM", "Room 101"));
        courses.add(new Course("Computer Science", "Introduction to Programming", "003",
                "Dr. Grace Hopper", 42, 40, 1, "Tue/Thu",
                "12:00-2:00 PM", "Room 103"));
        courses.add(new Course("Chemistry", "Introduction to Chemistry", "004",
                "Albozr Gharabaghi", 50, 48, 1, "Mon/Thu",
                "3:00-4:00 PM", "Room 104"));
        courses.add(new Course("Chemistry", "Introduction to Chemistry", "004",
                "Albozr Gharabaghi", 50, 48, 2, "Mon/Tue",
                "5:00-6:00 PM", "Room 104"));
        courses.add(new Course("Chemistry", "Introduction to Chemistry", "004",
                "Albozr Gharabaghi", 50, 48, 3, "Fri/Thur",
                "2:00-3:00 PM", "Room 104"));
        courses.add(new Course("English", "Introduction to French", "005",
                "Dr. Lakyn Copeland", 25, 25, 1, "Tue/Thu",
                "4:30-5:30 PM", "Room 101"));
        courses.add(new Course("English", "Introduction to French", "005",
                "Dr. Lakyn Copeland", 25, 25, 2, "Tue/Thu",
                "5:30-6:30 PM", "Room 101"));
        courses.add(new Course("Engineering", "Water Resources", "006",
                "Albozr Gharabaghi", 50, 50, 1, "Mon/Fri",
                "9:00-10:30 AM", "Room 104"));
    }

    // Search course by Course Code
    public List<Course> getCourseByCode(String Code){
        if(Code == null || Code.trim().equals("")){
            throw new IllegalArgumentException("Please enter a course code");
        }
        List<Course> matchingCodes = new ArrayList<>();
        for(Course course : courses){
            if(course.getCourseCode().equals(Code)){
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