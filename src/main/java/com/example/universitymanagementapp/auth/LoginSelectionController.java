package com.example.universitymanagementapp.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSelectionController {
    @FXML
    private Button userLoginButton;

    @FXML
    private Button adminLoginButton;

    // action events
    @FXML
    public void handleUserLoginAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().equals("User Login")||clickedButton.getText().equals("Admin Login")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapplication/user-login.fxml"));
            try {
                Parent root = loader.load();
                Stage stage = (Stage) userLoginButton.getScene().getWindow();
                stage.setTitle("Login Page");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
            }
        }
    }

}
