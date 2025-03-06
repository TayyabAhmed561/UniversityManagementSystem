package com.universitymanagement.controller.StudentController;

import com.universitymanagement.model.Student;
import java.util.Scanner;

public class StudentStudentFeatures {
    private Student student;
    private static final Scanner scanner = new Scanner(System.in);

    public StudentStudentFeatures(Student student) {
        this.student = student;
    }

    public void viewProfile() {
        student.viewProfile();
    }

    public void editProfile() {
        System.out.println("Enter new address: ");
        String address = scanner.nextLine();
        System.out.println("Enter new phone: ");
        String phone = scanner.nextLine();
        System.out.println("Enter new email: ");
        String email = scanner.nextLine();
        student.setAddress(address);
        student.setPhone(phone);
        student.setEmail(email);
        System.out.println("Profile updated successfully.");
    }

    public void changePassword() {
        System.out.println("Enter new password: ");
        String password = scanner.nextLine();
        student.changePassword(password);
    }

    public void viewGrades() {
        System.out.println("Grades viewing not implemented yet.");
        // Future implementation could iterate over grades list if made accessible
    }

    public void viewTuitionStatus() {
        int tuition = student.getStatus().equalsIgnoreCase("Graduate") ? 4000 : 5000;
        System.out.println("Tuition fee: $" + tuition);
        System.out.println("Payment status: " + (student.isTuitionPaid() ? "Paid" : "Unpaid"));
        if (!student.isTuitionPaid()) {
            System.out.println("Pay now? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                student.payTuition();
            }
        }
    }
}