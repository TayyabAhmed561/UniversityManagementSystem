package com.universitymanagement.test;

import com.universitymanagement.model.Faculty;
import com.universitymanagement.service.FacultyService;

import java.util.Arrays;
import java.util.List;

public class FacultyTest {
    public static void main(String[] args) {
        // Step 1: Create FacultyService instance
        FacultyService facultyService = new FacultyService();



        // Step 4: Display All Faculty Members
        System.out.println("\n--- Faculty List After Adding ---");
        printFacultyList(facultyService.listAllFaculty());

        // Step 5: Update Faculty Profile (Change Office)

        // Step 6: Display Updated Faculty List



        // Step 7: Try to Update a Non-Existent Faculty

        // Step 8: Delete a Faculty Member


        // Step 9: Try to Delete a Non-Existent Faculty

    }

    // Helper Method to Print Faculty List
    private static void printFacultyList(List<Faculty> facultyList) {
        if (facultyList.isEmpty()) {
            System.out.println("❌ No faculty members found.");
        } else {
            System.out.println("-------------------------------------------------------------");
            for (Faculty faculty : facultyList) {
                System.out.println("👤 Name       : " + faculty.getName());
                System.out.println("📧 Email      : " + faculty.getEmail());
                System.out.println("🎓 Degree     : " + faculty.getDegree());
                System.out.println("🔬 Research   : " + faculty.getResearchInterest());
                System.out.println("📚 Courses    : " + String.join(", ", faculty.getCoursesOffered()));
                System.out.println("🏢 Office     : " + faculty.getOfficeLocation());
                System.out.println("-------------------------------------------------------------");
            }
        }
    }
}