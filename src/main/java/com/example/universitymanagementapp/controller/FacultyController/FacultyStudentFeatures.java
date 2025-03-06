package com.example.universitymanagementapp.controller.FacultyController;

import com.example.universitymanagementapp.model.Faculty;
import com.example.universitymanagementapp.service.FacultyService;

import java.util.List;

public class FacultyStudentFeatures {
    private FacultyService facultyService = new FacultyService();

    // View faculty profiles (students can see faculty details)
    public void viewFacultyProfiles() {
        List<Faculty> facultyList = facultyService.listAllFaculty();
        for (Faculty faculty : facultyList) {
            System.out.println("Faculty Name: " + faculty.getName());
            System.out.println("Email: " + faculty.getEmail());
            System.out.println("Office: " + faculty.getOfficeLocation());
            System.out.println("Research Interest: " + faculty.getResearchInterest());
            System.out.println("-----------------------------------");
        }
    }
}
