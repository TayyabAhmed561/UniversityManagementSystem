package com.example.universitymanagementapp.dao;

import com.example.universitymanagementapp.model.Faculty;

import java.util.List;
import java.util.ArrayList;

public class FacultyDAO {
    private static List<Faculty> facultyList = new ArrayList<>();

    // add faculty member to the list
    public void addFaculty(Faculty faculty) {
        facultyList.add(faculty);
        System.out.println("Faculty added successfully!" + faculty.getUsername());
    }

    // updates info of existing faculty member
    public void updateFaculty(Faculty updatedFaculty) {
        for (Faculty faculty : facultyList) {
            if (faculty.getEmail().equals(updatedFaculty.getEmail())) {
                faculty.setName(updatedFaculty.getName());
                faculty.setDegree(updatedFaculty.getDegree());
                faculty.setResearchInterest(updatedFaculty.getResearchInterest());
                faculty.setCoursesOffered(updatedFaculty.getCoursesOffered());
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

    //finds faculty by username
    public Faculty getFacultyByUsername(String username) {
        return facultyList.stream()
                .filter(faculty -> faculty.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    //get logged in faculty
    public static String getLoggedInFaculty(String username) {
        for (Faculty faculty : facultyList) {
            if (faculty.getUsername().equals(username)) {
                return faculty.getName();
            }
        }
        return null;
    }

    // returns all faculty list
    public List<Faculty> getAllFaculty() {
        return facultyList;
    }

    public void clearFaculty(){
        facultyList.clear();
    }
}

