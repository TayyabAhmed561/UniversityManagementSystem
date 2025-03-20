package com.example.universitymanagementapp.dao;

import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDAO {

    private static List<Student> students = new ArrayList<>();
    private CourseDAO courseDAO;

    public StudentDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudentById(String studentId) {
        if (studentId == null) return null;
        String normalizedStudentId = studentId.trim();
        System.out.println("Looking up student with normalized ID: '" + normalizedStudentId + "' (length: " + normalizedStudentId.length() + ")");
        Student student = students.stream()
                .filter(s -> s.getUsername() != null && s.getUsername().trim().equalsIgnoreCase(normalizedStudentId))
                .findFirst()
                .orElse(null);
        if (student == null) {
            System.out.println("No student found with normalized ID: '" + normalizedStudentId + "'");
        } else {
            System.out.println("Found student: " + student.getStudentId() + " (" + student.getName() + ")");
        }
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Student> getStudentsEnrolledInCourse(int courseCode) {
        return students.stream()
                .filter(s -> s.getRegisteredCourses() != null && s.getRegisteredCourses().stream()
                        .anyMatch(c -> c.getCourseCode() == courseCode))
                .collect(Collectors.toList());
    }

    public void enrollStudentInCourse(Student student, Course course) {
        if (student.getRegisteredCourses() == null) {
            student.setRegisteredCourses(new ArrayList<>());
        }
        // Fetch the Course instance from CourseDAO to ensure consistency
        Course courseFromDAO = courseDAO.getCourseByCode(course.getCourseCode()).stream().findFirst().orElse(null);
        if (courseFromDAO == null) {
            System.err.println("Course with code " + course.getCourseCode() + " not found in CourseDAO.");
            return;
        }
        // Check if the student is already enrolled in this course
        if (!student.getRegisteredCourses().stream().anyMatch(c -> c.getCourseCode() == courseFromDAO.getCourseCode())) {
            // Add the Course instance from CourseDAO to the student's registered courses
            student.getRegisteredCourses().add(courseFromDAO);
            // Sync Course.enrolledStudents
            if (courseFromDAO.getEnrolledStudents() == null) {
                courseFromDAO.setEnrolledStudents(new ArrayList<>());
            }
            if (!courseFromDAO.getEnrolledStudents().contains(student.getStudentId())) {
                courseFromDAO.getEnrolledStudents().add(student.getStudentId());
                System.out.println("Added student " + student.getStudentId() + " to course " + courseFromDAO.getCourseCode() + " enrolledStudents: " + courseFromDAO.getEnrolledStudents());
            }
            // Update currentEnrollment based on Course.enrolledStudents
            int previousEnrollment = courseFromDAO.getCurrentEnrollment();
            courseFromDAO.setCurrentEnrollment(courseFromDAO.getEnrolledStudents().size());
            System.out.println("Enrolled student " + student.getStudentId() + " in course " + courseFromDAO.getCourseCode() +
                    ". Previous enrollment: " + previousEnrollment + ", New enrollment count: " + courseFromDAO.getCurrentEnrollment());
            // Persist the updated Course back to CourseDAO
            courseDAO.updateCourse(courseFromDAO);
        }
    }

    public void removeStudentFromCourse(Student student, Course course) {
        if (student.getRegisteredCourses() != null &&
                student.getRegisteredCourses().removeIf(c -> c.getCourseCode() == course.getCourseCode())) {
            // Fetch the Course instance from CourseDAO
            Course courseFromDAO = courseDAO.getCourseByCode(course.getCourseCode()).stream().findFirst().orElse(null);
            if (courseFromDAO != null) {
                // Sync Course.enrolledStudents
                if (courseFromDAO.getEnrolledStudents() != null) {
                    courseFromDAO.getEnrolledStudents().remove(student.getStudentId());
                    System.out.println("Removed student " + student.getStudentId() + " from course " + courseFromDAO.getCourseCode() + " enrolledStudents: " + courseFromDAO.getEnrolledStudents());
                }
                // Update currentEnrollment
                int previousEnrollment = courseFromDAO.getCurrentEnrollment();
                courseFromDAO.setCurrentEnrollment(courseFromDAO.getEnrolledStudents() != null ? courseFromDAO.getEnrolledStudents().size() : 0);
                System.out.println("Removed student " + student.getStudentId() + " from course " + courseFromDAO.getCourseCode() +
                        ". Previous enrollment: " + previousEnrollment + ", New enrollment count: " + courseFromDAO.getCurrentEnrollment());
                // Persist the updated Course back to CourseDAO
                courseDAO.updateCourse(courseFromDAO);
            }
        }
    }

    public void updateStudent(Student updated) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(updated.getStudentId())) {
                students.set(i, updated);
                return;
            }
        }
    }

    public void removeStudent(String studentId) {
        students.removeIf(s -> s.getStudentId().equals(studentId));
    }

    public void clearStudents() {
        students.clear();
    }
}