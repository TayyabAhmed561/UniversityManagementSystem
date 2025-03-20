package com.example.universitymanagementapp.model;

public class Admins extends User{
    private String name;

    public Admins(String username, String password, String name) {
        super(username, password, "admin");
        this.name = name;
    }

    public Admins() {};

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Admins{" +
                "name='" + name + '\'' +
                '}';
    }


}



