package com.universitymanagement.controller.FacultyController;
import com.universitymanagement.model.Faculty;
import com.universitymanagement.service.FacultyService;

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
