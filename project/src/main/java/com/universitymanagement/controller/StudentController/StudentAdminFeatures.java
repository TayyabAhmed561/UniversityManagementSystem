package com.universitymanagement.controller.StudentController;
import java.util.Scanner;
import java.util.Random

public class StudentAdminFeatures {
    //Add Student: Register new students with all necessary details. (with default
    //password and unique student id as username)
 import java.util.ArrayList;
import java.util.List;
import java.util.Random;

    class StudentManagement {
        private String id;   // Creating specified variables for each new student in data base
        private String name; // *
        private String address; //*
        private String phone; //*
        private String email; //*
        private String status; //*
        private String semester;//*
        private String username;//*
        private String password;//*

        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        private static final Random rand = new Random();

        // Constructs the Student
        public Student(String id, String name, String address, String phone, String email, String status, String semester) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.phone = phone;
            this.email = email;
            this.status = status;
            this.semester = semester;
            this.username = generateUsername(name);
            this.password = generateRandomPassword();
        }

        // Generate a random 8-character password
        private static String generateRandomPassword() {
            StringBuilder sb = new StringBuilder(8);
            for (int i = 0; i < 8; i++) {
                sb.append(CHARACTERS.charAt(rand.nextInt(CHARACTERS.length())));
            }
            return sb.toString();
        }

        // Generate username based on first name & last name
        private static String generateUsername(String fullName) {
            String[] nameParts = fullName.trim().split("\\s+");
            if (nameParts.length < 2) return "unknown"; // Fallback if input is invalid
            char firstInitial = Character.toUpperCase(nameParts[0].charAt(0));
            String lastName = nameParts[1].toLowerCase();
            return firstInitial + lastName;
        }

        // Display student details
        public void displayStudentDetails() {
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Address: " + address);
            System.out.println("Phone: " + phone);
            System.out.println("Email: " + email);
            System.out.println("Status: " + status);
            System.out.println("Semester: " + semester);
            System.out.println("----------------------------");
        }
    }

    public class StudentManagement {
        public static void main(String[] args) {
            List<Student> students = new ArrayList<>();
            students.add(new Student("S20250001", "Alice Smith", "123 Maple St.", "555-1234", "alice@example.edu", "Undergraduate", "Fall 2025"));
            students.add(new Student("S20250002", "Bob Johnson", "456 Oak St.", "555-5678", "bob@example.edu", "Graduate", "Fall 2025"));
            students.add(new Student("S20250003", "Carol Williams", "789 Pine St.", "555-9012", "carol@example.edu", "Graduate", "Fall 2025"));
            students.add(new Student("S20250004", "Lucka Racki", "1767 Jane St.", "439-9966", "lucka@example.edu", "Undergraduate", "Fall 2025"));
            students.add(new Student("S20250005", "David Lee", "90 Elm St.", "555-3456", "lee@example.edu", "Undergraduate", "Fall 2025"));
            students.add(new Student("S20250006", "Emily Brown", "111 Oak Ave.", "555-7890", "brown@example.edu", "Graduate", "Fall 2025"));
            students.add(new Student("S20250007", "George Smith", "222 Pine Rd.", "555-2345", "smith@example.edu", "Undergraduate", "Fall 2025"));
            students.add(new Student("S20250008", "Helen Jones", "333 Maple Dr.", "555-4567", "jones@example.edu", "Graduate", "Fall 2025"));
            students.add(new Student("S20250009", "Isaac Clark", "444 Cedar Ln.", "555-8901", "clark@example.edu", "Undergraduate", "Fall 2025"));
            students.add(new Student("S20250010", "Jennifer Davis", "555 Oakwood Pl", "555-3456", "davis@example.edu", "Graduate", "Fall 2025"));

            System.out.println("Displaying Student Details with Usernames & Passwords:\n");
            for (Student student : students) {
                student.displayStudentDetails();
            }
        }
    }

    //- Edit Student: Update student information such as address or contact details.
    Public void UpdateStudent(){

    }
    //- Delete Student: Remove student records when necessary.
    //- View Student Profile: Access detailed information about a student.
    //- Manage Enrollments: Enroll students in courses and manage their schedules.
    public void Enrollment(){
        String courses[] = { "Calculus I", "Literature Basics", "Introduction to Programming",
                "Introduction to Chemistry", "Introduction to French" , "Water Resources";}

        System.out.println("Course Selection: ");

    }
    //- Academic Progress Tacking: Monitor grades and graduation progress.
    public void Progress_Tracking() {


    }
    //- Tuition Management: Record and manage tuition fees. Tuition fee for undergrad
    //students is $5000, graduate students $4000 per semester
    public void Tuition_Management(int Tution) {
        Scanner scanner = new Scanner(System.in);

        int Tution = 0;
        System.out.println("Are you a Grad or UnderGrad Student: ");
        String year = scanner.nextLine().toUpperCase();

        if (year.equals("GRAD")) {
            System.out.println("Your Tuition per semester is $4000.");
            Tution = 4000;
        } else if (year.equals("UNDERGRAD")) {
            System.out.println("Your Tuition per semester is $5000.");
            Tution = 5000;
        } else {
            System.out.println("Invalid input. Please enter 'Grad' or 'UnderGrad'.");
        }
        return Tution;

        scanner.close();
    }

    public void Student_details (){}
    // at the end make function with all updated info "for example Tution" where i can simply just print all the details of the student;

}
