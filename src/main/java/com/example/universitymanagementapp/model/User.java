package com.example.universitymanagementapp.model;

import com.example.universitymanagementapp.utils.PasswordHasher;
import java.util.List;
import java.util.ArrayList;

public class User {
    private String username;
    private String hashedPassword;
    private String plaintextPassword;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.role = role;
        this.plaintextPassword = password;
        this.hashedPassword = PasswordHasher.hashPassword(password);
    }

    public User(){};

    //getter and setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return hashedPassword;
    }
    public void setPassword(String password) {
        this.plaintextPassword = password;
        this.hashedPassword = PasswordHasher.hashPassword(password);
    }
    public String getPlaintextPassword() {
        return plaintextPassword != null ? plaintextPassword : "";
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    private static List<User> users = new ArrayList<>();
    public static List<User> getUsers() {
        return users;
    }
    public static void setUsers(List<User> users) {
        User.users = users;
    }
    public static void addUser(User user) {
        users.add(user);
    }


    @Override
    public String toString() {
        return "User\nusername='" + username + "\npassword='" + hashedPassword + "\nrole='" + role;
    }
}

