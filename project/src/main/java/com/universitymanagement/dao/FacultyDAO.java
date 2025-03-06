package com.universitymanagement.dao;

import com.universitymanagement.model.Faculty;

import java.util.ArrayList;
import java.util.List;


public class FacultyDAO {
    // Arraylist of fauclty of type faculty
    private static List<Faculty> facultyList = new ArrayList<>();

    // add faculty member to the list
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