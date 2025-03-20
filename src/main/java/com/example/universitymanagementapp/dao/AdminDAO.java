package com.example.universitymanagementapp.dao;

import com.example.universitymanagementapp.model.Admins;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO{

    public AdminDAO() {}

    private static List<Admins> admins = new ArrayList<>();

    //add admin
    public void addAdmin(Admins admin){
        admins.add(admin);
    }

    public List<Admins> getAllAdmins(){
        return admins;
    }

    public void clearAdmins(){
        admins.clear();
    }




}

