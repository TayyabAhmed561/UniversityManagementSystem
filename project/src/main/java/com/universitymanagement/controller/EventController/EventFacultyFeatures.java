package com.universitymanagement.controller.EventController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class EventFacultyFeatures {

    @FXML
    private ImageView eventImage;
    @FXML
    private Label eventDescriptionLabel;
    @FXML
    private Label eventDateLabel;
    @FXML
    private Label eventTimeLabel;
    @FXML
    private Label eventLocationLabel;

    @FXML
    public void initialize() {
        eventDescriptionLabel.setText("Sample Event: Faculty Workshop");
        eventDateLabel.setText("Date: 2025-05-10");
        eventTimeLabel.setText("Time: 10:00 AM");
        eventLocationLabel.setText("Location: Conference Room B");

        // Load a placeholder image from an external URL
        String placeholderImageUrl = "https://www.gel.uoguelph.ca/files/2020/09/rXaqDhbl_400x400.jpg"; // Replace with your image URL
        try {
            Image image = new Image(placeholderImageUrl, true); // 'true' enables background loading
            eventImage.setImage(image);
        } catch (Exception e) {
            System.out.println("Failed to load the image from the URL: " + placeholderImageUrl);
            e.printStackTrace();
        }
    }


    @FXML
    public void registerForEvent() {
        System.out.println("Registering for event...");
        // Add logic to handle event registration
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        try {
            Parent viewRoot = FXMLLoader.load(getClass().getResource("/com/universitymanagement/roleViews/faculty-dashboard-view.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("Faculty Dashboard");
            stage.setScene(new Scene(viewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the Faculty Dashboard.");
        }
    }
}
