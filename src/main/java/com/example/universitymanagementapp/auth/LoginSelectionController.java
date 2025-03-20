package com.example.universitymanagementapp.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSelectionController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private StackPane loginPaneContainer;

    @FXML
    private Pane loginPane;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ImageView logoImage;

    @FXML
    private Button userLoginButton;

    @FXML
    private Button adminLoginButton;

    @FXML
    public void initialize() {
        // Bind the background image's fitWidth and fitHeight to the AnchorPane's size
        backgroundImage.fitWidthProperty().bind(anchorPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(anchorPane.heightProperty());

        // Debug the logo image loading
        try {
            Image logo = new Image(getClass().getResourceAsStream("/images/university_of_guelph_logo.png"));
            logoImage.setImage(logo);
        } catch (Exception e) {
            System.err.println("Failed to load logo image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleUserLoginAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/user-login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) userLoginButton.getScene().getWindow();
            stage.setTitle("User Login");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load user login page: " + e.getMessage());
        }
    }

    @FXML
    public void handleAdminLoginAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/admin-login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) adminLoginButton.getScene().getWindow();
            stage.setTitle("Admin Login");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load admin login page: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
