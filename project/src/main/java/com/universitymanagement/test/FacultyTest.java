package com.universitymanagement.test;

import com.universitymanagement.model.Faculty;
import com.universitymanagement.service.FacultyService;

import java.util.Arrays;
import java.util.List;

public class FacultyTest {
    public static void main(String[] args) {
        // Step 1: Create FacultyService instance
        FacultyService facultyService = new FacultyService();

        // Step 2: Create sample faculty members
        Faculty faculty1 = new Faculty("Dr. Alan Turing", "alan.turing@university.edu", "Ph.D.",
                "Artificial Intelligence", Arrays.asList("Machine Learning", "Algorithms"),
                "profile1.jpg", "Room 101");

        Faculty faculty2 = new Faculty("Prof. Ada Lovelace", "ada.lovelace@university.edu", "Ph.D.",
                "Computational Mathematics", Arrays.asList("Discrete Math", "Programming Theory"),
                "profile2.jpg", "Room 102");

        // Step 3: Register Faculty Members
        System.out.println("\n--- Adding Faculty Members ---");
        facultyService.registerFaculty(faculty1);
        facultyService.registerFaculty(faculty2);
        facultyService.registerFaculty(faculty1); // Attempt duplicate

        // Step 4: Display All Faculty Members
        System.out.println("\n--- Faculty List After Adding ---");
        printFacultyList(facultyService.listAllFaculty());

        // Step 5: Update Faculty Profile (Change Office)
        System.out.println("\n--- Updating Faculty Member (Alan Turing) ---");
        faculty1.setOfficeLocation("Room 303");
        facultyService.updateFacultyProfile(faculty1);

        // Step 6: Display Updated Faculty List
        System.out.println("\n--- Faculty List After Update ---");
        printFacultyList(facultyService.listAllFaculty());

        // Step 7: Try to Update a Non-Existent Faculty
        Faculty nonExistentFaculty = new Faculty("Dr. John Doe", "john.doe@university.edu", "Ph.D.",
                "Cybersecurity", Arrays.asList("Ethical Hacking"),
                "profile3.jpg", "Room 404");
        facultyService.updateFacultyProfile(nonExistentFaculty);

        // Step 8: Delete a Faculty Member
        System.out.println("\n--- Deleting Faculty Member (Ada Lovelace) ---");
        facultyService.removeFaculty(faculty2.getEmail());

        // Step 9: Try to Delete a Non-Existent Faculty
        facultyService.removeFaculty("fakeperson@university.edu");

        // Step 10: Display Final Faculty List
        System.out.println("\n--- Final Faculty List After Deletion ---");
        printFacultyList(facultyService.listAllFaculty());

        System.out.println("\n✅ Faculty Service Tests Completed Successfully!");
    }

    // Helper Method to Print Faculty List
    private static void printFacultyList(List<Faculty> facultyList) {
        if (facultyList.isEmpty()) {
            System.out.println("No faculty members found.");
        } else {
            for (Faculty faculty : facultyList) {
                System.out.println(faculty);
            }
        }
    }
}
