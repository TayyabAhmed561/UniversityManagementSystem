package com.universitymanagement.controller.FacultyController;


import com.universitymanagement.model.Faculty;
import com.universitymanagement.service.FacultyService;

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
