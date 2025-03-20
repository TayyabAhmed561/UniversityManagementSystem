package com.example.universitymanagementapp.model;

import java.time.LocalDateTime;

public class Notification {
    private String type;
    private String message;
    private LocalDateTime date;

    public Notification(String type, String message, LocalDateTime date) {
        this.type = type;
        this.message = message;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
