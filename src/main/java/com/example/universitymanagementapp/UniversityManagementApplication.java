package com.example.universitymanagementapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UniversityManagementApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            String fxmlPath = "/com/example/universitymanagementapp/controller/course-management.fxml";
            System.out.println("Loading FXML: " + fxmlPath);

            FXMLLoader fxmlLoader = new FXMLLoader(UniversityManagementApplication.class.getResource(fxmlPath));

            if (fxmlLoader.getLocation() == null) {
                System.out.println("FXML file not found! Check the path.");
            }

            Scene scene = new Scene(fxmlLoader.load(), 320, 240); // Consider 800, 600 for visibility
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
