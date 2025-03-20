package com.example.universitymanagementapp.model;

import java.time.LocalDateTime;

public class Activity {
    private String type;
    private String description;
    private LocalDateTime date;

    public Activity(String type, String description, LocalDateTime date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
