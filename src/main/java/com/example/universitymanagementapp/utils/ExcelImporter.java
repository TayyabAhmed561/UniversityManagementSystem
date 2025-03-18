package com.example.universitymanagementapp.utils;

import com.example.universitymanagementapp.model.*;
import com.example.universitymanagementapp.dao.*;
import javafx.scene.image.Image;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelImporter {
    private CourseDAO courseService;
    private StudentDAO studentService;
    private FacultyDAO facultyService;
    private SubjectDAO subjectService;
    private EventDAO eventService;

    public ExcelImporter() {}

    public ExcelImporter(CourseDAO courseService, StudentDAO studentService,
                         FacultyDAO facultyService, SubjectDAO subjectService,
                         EventDAO eventService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.facultyService = facultyService;
        this.subjectService = subjectService;
        this.eventService = eventService;
    }

    public void importData() {
        try (InputStream file = ExcelImporter.class.getResourceAsStream("/UMS_Data.xlsx")) {
            if (file == null) {
                throw new FileNotFoundException("Excel file 'UMS_Data.xlsx' not found in src/main/resources/");
            }
            try (Workbook workbook = new XSSFWorkbook(file)) {
                // Log the sheets for debugging
                for (Sheet sheet : workbook) {
                    System.out.println("Sheet Name: " + sheet.getSheetName());
                }

                // Read each sheet and populate respective service classes
                readCourses(workbook.getSheet("Courses"));
                readStudents(workbook.getSheet("Students"));
                readFaculty(workbook.getSheet("Faculties"));
                readSubjects(workbook.getSheet("Subjects"));
                readEvents(workbook.getSheet("Events"));

                System.out.println("✅ Data successfully imported from Excel!");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //read courses
    private void readCourses(Sheet sheet) {
        if (sheet == null) return;
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        //need to get subject code and translate to subject name?


        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (isRowEmpty(row)) break;
            Course course = new Course(
                    getStringValue(row.getCell(2)), // Subject Code
                    getStringValue(row.getCell(1)), // Course Name
                    (int) getNumericValue(row.getCell(0)), // Course Code
                    getStringValue(row.getCell(8)), // Instructor
                    (int) getNumericValue(row.getCell(4)), // Capacity
                    0,
                    getStringValue(row.getCell(3)), // Section ID
                    getStringValue(row.getCell(5)), // Meeting Days/Time
                    getStringValue(row.getCell(7)), // Location
                    getStringValue(row.getCell(6))  // Final Exam Date/Time
            );
            courseService.addCourse(course);
        }
    }

    //read students from sheet
    private void readStudents(Sheet sheet){
        if (sheet == null) return;
        System.out.println("Reading students from Excel...");
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row
        List<Course> registeredCourses = new ArrayList<>();
        List<Grade> grades = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (isRowEmpty(row)) continue;  // Skip empty rows

            String profilePicturePath = getStringValue(row.getCell(7));
            Image profilePicture;
            if ("default".equalsIgnoreCase(profilePicturePath)){
                profilePicture = new Image(getClass().getResourceAsStream("/images/default.jpg"));
            } else {
                profilePicture = new Image(profilePicturePath);
            }

            // Separate courses
            String subjectsString = getStringValue(row.getCell(8));
            List<String> registeredSubjects = new ArrayList<>();
            if (!subjectsString.isEmpty()) {
                registeredSubjects = Arrays.asList(subjectsString.split("\\s*,\\s*"));
            }

            Student student = new Student(
                    getStringValue(row.getCell(0)),  // StudentId, Username
                    getStringValue(row.getCell(11)), // Password
                    getStringValue(row.getCell(1)),  // Name
                    getStringValue(row.getCell(0)),  // ID
                    profilePicture,
                    getStringValue(row.getCell(2)),  // Address
                    getStringValue(row.getCell(3)),  // Phone
                    0,
                    registeredCourses,
                    getStringValue(row.getCell(4)),  // Email
                    grades,
                    getStringValue(row.getCell(6)),  // Current Semester
                    registeredSubjects,
                    getStringValue(row.getCell(5)),  // Academic Level
                    getStringValue(row.getCell(9)),  // Thesis Title
                    getNumericValue(row.getCell(10)) * 100 // Progress
            );
            studentService.addStudent(student);
            System.out.println("Importing student: "+ student.getStudentId() + " " + student.getName());
        }
    }



    //read faculty from sheet
    private void readFaculty(Sheet sheet){
        if (sheet == null) return;
        System.out.println("Reading faculty from Excel...");
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();// Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (isRowEmpty(row)) break;

            String coursesString = getStringValue(row.getCell(8));
            List<String> coursesOffered = new ArrayList<>();
            //split into a list
            if (coursesString != null && !coursesString.isEmpty()) {
                coursesOffered = Arrays.asList(coursesString.split("\\s*,\\s*"));
            }

            Faculty faculty = new Faculty(
                    getStringValue(row.getCell(0)), //username
                    getStringValue(row.getCell(7)), //password
                    getStringValue(row.getCell(1)), //name
                    getStringValue(row.getCell(4)), //email
                    getStringValue(row.getCell(2)), //degree
                    getStringValue(row.getCell(3)), //research interest
                    coursesOffered,
                    getStringValue(row.getCell(5)) //office location
            );
            facultyService.addFaculty(faculty);
        }
    }


    //read subjects from sheet
    private void readSubjects(Sheet sheet){
        if (sheet == null) return;
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();// Skip header row
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            if (isRowEmpty(row)) break;
            Subject subjects = new Subject(
                    row.getCell(1).getStringCellValue(), //name
                    row.getCell(0).getStringCellValue() //code
            );
            subjectService.addSubject(subjects);
            //add to subject dao
        }

    }

    //read events from sheet
    private void readEvents(Sheet sheet){
        if (sheet == null) return;
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();// Skip header row
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            if (isRowEmpty(row)) break;

            String headerPicturePath = row.getCell(7).getStringCellValue();
            Image headerPicture;
            if ("default".equalsIgnoreCase(headerPicturePath)){
                headerPicture = new Image(getClass().getResourceAsStream("/images/default.jpg"));
            }else{
                headerPicture = new Image(headerPicturePath);
            }

            String studentsString = row.getCell(8).getStringCellValue();
            List<String> registeredStudents = new ArrayList<>();
            //split into a list
            if (studentsString != null && !studentsString.isEmpty()) {
                registeredStudents = Arrays.asList(studentsString.split("\\s*,\\s*"));
            }

            Event event = new Event(
                    row.getCell(1).getStringCellValue(), //name
                    row.getCell(0).getStringCellValue(), //code
                    row.getCell(2).getStringCellValue(), //description
                    headerPicture,
                    row.getCell(3).getStringCellValue(), //location
                    row.getCell(4).getDateCellValue(), //date and time
                    (int)row.getCell(5).getNumericCellValue(), //capacity
                    row.getCell(6).getStringCellValue(), //cost
                    registeredStudents
            );
            eventService.addEvent(event);
            //add to event dao
        }


    }


    //helper methods
    private boolean isRowEmpty(Row row) {
        if (row == null) return true;
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private String getStringValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    private double getNumericValue(Cell cell) {
        if (cell == null) return 0;
        return (cell.getCellType() == CellType.NUMERIC) ? cell.getNumericCellValue() : 0;
    }

}
