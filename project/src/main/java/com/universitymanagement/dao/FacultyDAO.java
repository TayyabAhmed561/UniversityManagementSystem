package com.universitymanagement.dao;

import com.universitymanagement.model.Faculty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FacultyDAO {
    // Arraylist of fauclty of type faculty
    private static List<Faculty> facultyList = new ArrayList<>();
    public FacultyDAO() {
        loadInitialFaculty();
    }

    private void loadInitialFaculty() {
        facultyList.add(new Faculty("Dr. JEWHIB", "alan.turing@university.edu", "Ph.D.",
                "Money", Arrays.asList("Econ", "Banker"),
                "profile1.jpg", "Room 101"));

        facultyList.add(new Faculty("Prof. GOONHIB", "ada.lovelace@university.edu", "Ph.D.",
                "Computational Mathematics", Arrays.asList("Discrete Math", "Programming Theory"),
                "profile2.jpg", "Room 102"));

        facultyList.add(new Faculty("Dr. MEWHIB", "john.neumann@university.edu", "Ph.D.",
                "Game Theory", Arrays.asList("Probability Theory", "Quantum Mechanics"),
                "profile3.jpg", "Room 201"));

        System.out.println("✅ Initial Faculty Data Loaded!");
    }

    // add faculty member to to the list
    public void addFaculty(Faculty faculty) {
        facultyList.add(faculty);
        System.out.println("Faculty added successfully!");
    }

    // updates info of existing faculty member
    public void updateFaculty(Faculty updatedFaculty) {
        for (Faculty faculty : facultyList) {
            if (faculty.getEmail().equals(updatedFaculty.getEmail())) {
                faculty.setName(updatedFaculty.getName());
                faculty.setDegree(updatedFaculty.getDegree());
                faculty.setResearchInterest(updatedFaculty.getResearchInterest());
                faculty.setCoursesOffered(updatedFaculty.getCoursesOffered());
                faculty.setProfilePhoto(updatedFaculty.getProfilePhoto());
                faculty.setOfficeLocation(updatedFaculty.getOfficeLocation());
                System.out.println("Faculty updated successfully!");
                break;
            }
        }
    }

    // deletes faculty memeber from faculty list
    public void deleteFaculty(String email) {
        facultyList.removeIf(faculty -> faculty.getEmail().equals(email));
        System.out.println("Faculty removed successfully!");
    }

    // finds fauclty member based on email
    public Faculty getFacultyByEmail(String email) {
        return facultyList.stream()
                .filter(faculty -> faculty.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    // returns all faculty list
    public List<Faculty> getAllFaculty() {
        return facultyList;
    }
}


