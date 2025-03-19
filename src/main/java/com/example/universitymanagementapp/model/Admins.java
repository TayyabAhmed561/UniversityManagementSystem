package com.example.universitymanagementapp.model;

import java.util.List;

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
    public boolean deleteUser(String usernameToDelete) {
        // Get the list of users (static field from User)
        List<User> users = User.getUsers();

        // Check if the list is empty or the username is null/invalid
        if (users == null || usernameToDelete == null || usernameToDelete.trim().isEmpty()) {
            System.out.println("Invalid username or user list is empty.");
            return false;
        }

        // Find and remove the user with the matching username
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(usernameToDelete)) {
                users.remove(i);
                System.out.println("User " + usernameToDelete + " has been deleted.");
                return true;
            }
        }

        // If no user is found
        System.out.println("User " + usernameToDelete + " not found.");
        return false;
    }
}


