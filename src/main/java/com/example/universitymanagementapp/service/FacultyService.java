package com.example.universitymanagementapp.service;

import com.example.universitymanagementapp.model.Faculty;
import com.example.universitymanagementapp.dao.FacultyDAO;

import java.util.List;

// creates an instnace of faculty doa so that we can use its mehtods whout intertacion wiht datasbase direclty
public class FacultyService
{
    private FacultyDAO facultyDAO = new FacultyDAO();

    // invokes fauculty doa method to add faculty to arraylist if no duplicate is found
    public void registerFaculty(Faculty faculty)
    {
        if (facultyDAO.getFacultyByEmail(faculty.getEmail()) == null)
        {
            facultyDAO.addFaculty(faculty);
            System.out.println("Faculty added successfully!");
        }
        else
        {
            System.out.println("Error: Faculty with this email already exists!");
        }
    }

    public void updateFacultyProfile(Faculty faculty)
    {
        Faculty existingFaculty = facultyDAO.getFacultyByEmail(faculty.getEmail());
        if (existingFaculty != null) {
            facultyDAO.updateFaculty(faculty);
            System.out.println("Faculty updated successfully!");
        }
        else
        {
            System.out.println("Error: Faculty not found. Update failed!");
        }
    }

    public void removeFaculty(String email) {
        Faculty existingFaculty = facultyDAO.getFacultyByEmail(email);

        if (existingFaculty != null)
        {
            facultyDAO.deleteFaculty(email);
            System.out.println("Faculty removed successfully!");
        }
        else
        {
            System.out.println("Error: Faculty not found. Deletion failed!");
        }
    }

    public List<Faculty> listAllFaculty()
    {
        return facultyDAO.getAllFaculty();
    }
}
