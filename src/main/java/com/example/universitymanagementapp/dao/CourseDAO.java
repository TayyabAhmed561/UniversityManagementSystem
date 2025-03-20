package com.example.universitymanagementapp.dao;

import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.model.Faculty;

import java.util.ArrayList;
import java.util.List;

import static com.example.universitymanagementapp.UniversityManagementApp.courseDAO;

public class CourseDAO {

    private static List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(int courseCode) {
        courses.removeIf(c -> c.getCourseCode() == courseCode);
    }

    public CourseDAO() {
        synchronizeEnrollments(); // Fix any discrepancies on startup
    }

    // Synchronize currentEnrollment with enrolledStudents.size() for all courses
    public void synchronizeEnrollments() {
        for (Course course : courses) {
            int actualEnrollment = course.getEnrolledStudents() != null ? course.getEnrolledStudents().size() : 0;
            if (course.getCurrentEnrollment() != actualEnrollment) {
                System.out.println("Fixing enrollment for course " + course.getCourseCode() + " (" + course.getCourseName() + "): " +
                        "currentEnrollment=" + course.getCurrentEnrollment() + ", actual=" + actualEnrollment);
                course.setCurrentEnrollment(actualEnrollment);
            }
        }
    }

    public void updateCourse(Course updatedCourse) {
        for (Course course : courses) {
            if (course.getCourseCode() == updatedCourse.getCourseCode()) {
                course.setSubjectCode(updatedCourse.getSubjectCode());
                course.setCourseName(updatedCourse.getCourseName());
                course.setInstructor(updatedCourse.getInstructor());
                course.setCapacity(updatedCourse.getCapacity());
                course.setCurrentEnrollment(updatedCourse.getCurrentEnrollment());
                course.setSectionID(updatedCourse.getSectionID());
                course.setMeetingDaysTime(updatedCourse.getMeetingDaysTime());
                course.setMeetingLocation(updatedCourse.getMeetingLocation());
                course.setFinalExamDateTime(updatedCourse.getFinalExamDateTime());
                course.setEnrolledStudents(updatedCourse.getEnrolledStudents() != null ? new ArrayList<>(updatedCourse.getEnrolledStudents()) : new ArrayList<>());
                System.out.println("Updated course " + course.getCourseCode() + " in CourseDAO. Enrollment: " + course.getCurrentEnrollment());
                break;
            }
        }
    }

    public Course getCourseByInt(int courseCode) {
        return courses.get(courseCode);
    }

    public List<Course> getCourseByCode(int Code) {
        if (Code < 1) {
            throw new IllegalArgumentException("Invalid course code.");
        }
        List<Course> matchingCodes = new ArrayList<>();
        for (Course course : courses) {
            if (course.getCourseCode() == Code) {
                matchingCodes.add(course);
            }
        }
        if (matchingCodes.isEmpty()) {
            System.out.println("No course found with code " + Code);
        }
        System.out.println("Matching codes: ");
        return matchingCodes;
    }

    public List<Course> getCourseByName(String Name) {
        if (Name == null || Name.trim().isEmpty()) {
            System.out.println("Enter a course name.");
        }
        List<Course> matchingCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getCourseName().equals(Name)) {
                matchingCourses.add(course);
            }
        }
        if (matchingCourses.isEmpty()) {
            System.out.println("No course found with name " + Name);
        }
        return matchingCourses;
    }

    public List<Course> getCoursesTaught(String instructorName) {
        if (instructorName == null || instructorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid instructor name.");
        }
        List<Course> instructorCourses = new ArrayList<>();
        // Normalize by trimming and removing extra spaces, but keep periods and other characters
        String normalizedFacultyName = instructorName.trim().replaceAll("\\s+", " ");
        System.out.println("Normalized faculty name: '" + normalizedFacultyName + "' (length: " + normalizedFacultyName.length() + ")");
        for (Course course : courses) {
            if (course.getInstructor() != null) {
                String normalizedInstructor = course.getInstructor().trim().replaceAll("\\s+", " ");
                System.out.println("Comparing with course instructor: '" + normalizedInstructor + "' (length: " + normalizedInstructor.length() + ")");
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

    public List<Course> getAllCourses() {
        System.out.println("Total courses in CourseDAO: " + courses.size());
        return new ArrayList<>(courses);
    }

    public List<Course> getCoursesForLoggedInUser(String username) {
        Faculty faculty = new FacultyDAO().getFacultyByUsername(username);
        if (faculty == null) {
            System.out.println("No faculty found with username: " + username);
            return new ArrayList<>();
        }
        String facultyName = faculty.getName();
        System.out.println("Faculty name for username " + username + " is " + facultyName);
        return courseDAO.getCoursesTaught(facultyName);
    }

    public void removeCoursesBySubject(String subjectCode) {
        courses.removeIf(course -> course.getSubjectCode().equalsIgnoreCase(subjectCode));
    }

    public List<Course> getCoursesBySubject(String subjectCode) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getSubjectCode().equalsIgnoreCase(subjectCode)) {
                result.add(course);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "CourseDAO{ Courses: " + courses + "}";
    }

    public void clearCourses() {
        courses.clear();
    }
}
