package com.universitymanagement.controller.FacultyController;


import com.universitymanagement.model.Faculty;
import com.universitymanagement.service.FacultyService;

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
