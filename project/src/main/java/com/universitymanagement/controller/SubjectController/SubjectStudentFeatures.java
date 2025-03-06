package com.universitymanagement.controller.SubjectController;

import com.universitymanagement.model.Subject;
import com.universitymanagement.service.SubjectService;

import java.util.List;
import java.util.Scanner;

public class SubjectStudentFeatures {

    private SubjectService subjectService = new SubjectService();
    private Scanner scanner = new Scanner(System.in);

    // View Subjects: Access a list of all subjects and view their details
    public void viewAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();

        if (subjects.isEmpty()) {
            System.out.println("No subjects available.");
        } else {
            System.out.println("List of all subjects:");
            for (Subject subject : subjects) {
                System.out.println(subject);
            }
        }
    }

    // Search for a subject by keyword (subject code or name)
    public void searchSubjects() {
        System.out.println("Enter a keyword to search for subjects:");
        String keyword = scanner.nextLine();

        List<Subject> subjects = subjectService.searchSubjects(keyword);

        if (subjects.isEmpty()) {
            System.out.println("No subjects found with the given keyword.");
        } else {
            System.out.println("Search results:");
            for (Subject subject : subjects) {
                System.out.println(subject);
            }
        }
    }

    // Main method to demonstrate the SubjectStudentFeatures functionality
    public static void main(String[] args) {
        SubjectStudentFeatures studentFeatures = new SubjectStudentFeatures();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nSubject Management - Student Features");
            System.out.println("1. View All Subjects");
            System.out.println("2. Search Subjects");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    studentFeatures.viewAllSubjects();
                    break;
                case 2:
                    studentFeatures.searchSubjects();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting Subject Management.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }
}
