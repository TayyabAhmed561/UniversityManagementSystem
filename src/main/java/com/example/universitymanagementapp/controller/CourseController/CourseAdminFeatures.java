package com.example.universitymanagementapp.controller.CourseController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CourseAdminFeatures {
    @FXML
    public Label welcomeText;

    @FXML
    public void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
