package com.example.universitymanagementapp.controller.FacultyController;

import com.example.universitymanagementapp.model.Faculty;
import com.example.universitymanagementapp.service.FacultyService;

public class FacultyFacultyFeatures {
    private FacultyService facultyService = new FacultyService();

    // View own profile
    public void viewProfile(String email) {
        Faculty faculty = facultyService.listAllFaculty().stream()
                .filter(f -> f.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (faculty != null) {
            System.out.println(faculty);
        } else {
            System.out.println("Faculty not found!");
        }
    }

    // Update profile (Faculty can edit their own details)
    public void updateProfile(Faculty faculty) {
        facultyService.updateFacultyProfile(faculty);
    }
}