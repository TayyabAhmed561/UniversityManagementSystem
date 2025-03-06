package com.example.universitymanagementapp.controller.FacultyController;

import com.example.universitymanagementapp.model.Faculty;
import com.example.universitymanagementapp.service.FacultyService;

import java.util.List;

public class FacultyAdminFeatures {
    private FacultyService facultyService = new FacultyService();

    // Add a new faculty member
    public void addFaculty(Faculty faculty) {
        facultyService.registerFaculty(faculty);
    }

    // Update faculty profile
    public void updateFaculty(Faculty faculty) {
        facultyService.updateFacultyProfile(faculty);
    }

    // Remove a faculty member
    public void deleteFaculty(String email) {
        facultyService.removeFaculty(email);
    }

    // View all faculty members
    public void listFaculty() {
        List<Faculty> facultyList = facultyService.listAllFaculty();
        for (Faculty faculty : facultyList) {
            System.out.println(faculty);
        }
    }
}
