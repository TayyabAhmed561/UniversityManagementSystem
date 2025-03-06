package com.universitymanagement.dao;

import com.universitymanagement.model.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDAO {
    private List<Student> students;
    private Map<String, CourseRecord> courses; // Course code -> CourseRecord

    // Inner class to represent course data
    public static class CourseRecord {
        public String courseName;
        public String courseCode;
        public String subjectName;
        public String sectionNumber;
        public String teacherName;
        public int capacity;
        public String lectureTime;
        public String finalExamDateTime;
        public String location;
        public List<String> enrolledStudents;

        public CourseRecord(String courseName, String courseCode, String subjectName, String sectionNumber, int capacity, String lectureTime, String finalExamDateTime, String location) {
            this.courseName = courseName;
            this.courseCode = courseCode;
            this.subjectName = subjectName;
            this.sectionNumber = sectionNumber;
            this.capacity = capacity;
            this.lectureTime = lectureTime;
            this.finalExamDateTime = finalExamDateTime;
            this.location = location;
            this.enrolledStudents = new ArrayList<>();
        }
    }

    public StudentDAO() {
        students = new ArrayList<>();
        courses = new HashMap<>();
        initializeData();
    }

    private void initializeData() {
        // Initialize students
        students.add(new Student("S20250001", "Alice Smith", "123 Maple St.", "555-1234", "alice@example.edu", "Undergraduate", "Fall 2025"));
        students.add(new Student("S20250002", "Bob Johnson", "456 Oak St.", "555-5678", "bob@example.edu", "Graduate", "Fall 2025"));
        students.add(new Student("S20250003", "Carol Williams", "789 Pine St.", "555-9012", "carol@example.edu", "PhD", "Fall 2025"));

        // Initialize courses with teacher names
        CourseRecord cs201 = new CourseRecord("Introduction to Programming", "CS201", "Computer Science", "Section 1", 30, "Mon 10:00-11:30", "2025-12-15 09:00", "Room 101");
        cs201.teacherName = "Dr. Smith";
        courses.put("CS201", cs201);

        CourseRecord hist101 = new CourseRecord("History 101", "HIST101", "History", "Section 1", 25, "Wed 14:00-15:30", "2025-12-16 13:00", "Room 202");
        hist101.teacherName = "Dr. Jones";
        courses.put("HIST101", hist101);
    }

    // Student operations
    public void addStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(String studentId) {
        students.removeIf(student -> student.getId().equals(studentId));
        // Remove student from all courses
        for (CourseRecord course : courses.values()) {
            course.enrolledStudents.remove(studentId);
        }
    }

    public Student getStudentById(String studentId) {
        return students.stream().filter(student -> student.getId().equals(studentId)).findFirst().orElse(null);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    // Course operations
    public void addCourse(CourseRecord course) {
        courses.put(course.courseCode, course);
    }

    public void deleteCourse(String courseCode) {
        CourseRecord course = courses.remove(courseCode);
        if (course != null) {
            for (Student student : students) {
                student.dropCourse(courseCode);
            }
        }
    }

    public CourseRecord getCourseByCode(String courseCode) {
        return courses.get(courseCode);
    }

    public List<CourseRecord> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    public boolean enrollStudentInCourse(String studentId, String courseCode) {
        CourseRecord course = courses.get(courseCode);
        if (course != null && course.enrolledStudents.size() < course.capacity) {
            course.enrolledStudents.add(studentId);
            return true;
        }
        return false;
    }

    public void removeStudentFromCourse(String studentId, String courseCode) {
        CourseRecord course = courses.get(courseCode);
        if (course != null) {
            course.enrolledStudents.remove(studentId);
        }
    }
}