package com.universitymanagement.utils;

public class Validator {

    // Validates subject codes with a unique identifier format (e.g., MATH001)
    public static boolean isValidSubjectCode(String code) {
        return code != null && code.matches("^[A-Z]{4}[0-9]{3}$");
    }

    // Validates that a subject name is not empty and follows proper naming conventions
    public static boolean isValidSubjectName(String name) {
        return name != null && name.matches("^[A-Za-z\\s]{2,50}$");
    }

    // Validates an email address using a standard regex pattern
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Validates phone numbers in common formats (e.g., 123-456-7890, (123) 456-7890, 1234567890)
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\(?\\d{3}\\)?[-\\s]?\\d{3}[-\\s]?\\d{4}$");
    }

    // Validates that a given string is not null or empty (general purpose)
    public static boolean isValidInformation(String info) {
        return info != null && !info.trim().isEmpty();
    }

    // Validates numerical inputs (e.g., capacity, age)
    public static boolean isValidNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validates that the input is a positive integer
    public static boolean isValidPositiveNumber(String number) {
        try {
            int value = Integer.parseInt(number);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validates date format (e.g., 2025-12-31)
    public static boolean isValidDate(String date) {
        return date != null && date.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }
}
