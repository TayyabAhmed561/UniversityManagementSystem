package com.example.universitymanagementapp.ui.CourseManagementUI;

import com.example.universitymanagementapp.dao.CourseDAO;
import com.example.universitymanagementapp.model.Course;

import com.example.universitymanagementapp.model.Course;
import com.example.universitymanagementapp.dao.CourseDAO;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CourseManagementAdminUI {
    @FXML
    private TableView<Course> adminCourseTable;

    private CourseDAO courseDAO = new CourseDAO();

    @FXML
    public void initialize() {
        adminCourseTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        adminCourseTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("capacity"));
    }

    @FXML
    public void handleAddCourse() {
        Course newCourse = new Course("Physics", "Intro to Physics", 7, "Dr. Newton", 40, 35, 1, "Mon/Wed", "2:00-3:30 PM", "Room 303", "12/18/2025", "2:00 PM");
        courseDAO.addCourse(newCourse);
        refreshTable();
    }

    @FXML
    public void handleDeleteCourse() {
        courseDAO.deleteCourse(1); // Example: Deleting course with code 1
        refreshTable();
    }

    private void refreshTable() {
        adminCourseTable.getItems().setAll(courseDAO.getAllCourses());
    }
}

