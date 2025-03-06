package com.universitymanagement.test;

import com.universitymanagement.controller.FacultyController.FacultyAdminFeatures;
import com.universitymanagement.model.Faculty;

import java.util.Arrays;

public class Facultyc {
    public static void main(String[] args) {
        // Create FacultyAdminFeatures instance (Admin controls faculty)
        FacultyAdminFeatures facultyAdmin = new FacultyAdminFeatures();

        // Create faculty members
        Faculty faculty1 = new Faculty("Dr. Alan Turing", "alan.turing@university.edu", "Ph.D.",
                "Artificial Intelligence", Arrays.asList("Machine Learning", "Algorithms"),
                "profile1.jpg", "Room 101");

        Faculty faculty2 = new Faculty("Prof. Ada Lovelace", "ada.lovelace@university.edu", "Ph.D.",
                "Computational Mathematics", Arrays.asList("Discrete Math", "Programming Theory"),
                "profile2.jpg", "Room 102");

        // Admin adds faculty members
        System.out.println("\n--- Admin Adding Faculty ---");
        facultyAdmin.addFaculty(faculty1);
        facultyAdmin.addFaculty(faculty2);
        facultyAdmin.listFaculty();

        // Admin updates faculty profile
        System.out.println("\n--- Admin Updating Faculty (Alan Turing) ---");
        faculty1.setOfficeLocation("Room 303");
        facultyAdmin.updateFaculty(faculty1);
        facultyAdmin.listFaculty();

        // Admin deletes faculty
        System.out.println("\n--- Admin Removing Faculty (Ada Lovelace) ---");
        facultyAdmin.deleteFaculty("ada.lovelace@university.edu");
        facultyAdmin.listFaculty();
    }
}
