package com.universitymanagement.controller;

import com.universitymanagement.auth.UserRoleAuth;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BackButtonController {

    public void handleBackAction(ActionEvent event) {
        String fxmlFile = "";
        String title = "";

        switch (UserRoleAuth.getUserRole()) {
            case ADMIN:
                fxmlFile = "/com/universitymanagement/roleViews/admin-dashboard-view.fxml";
                title = "Admin Dashboard";
                break;
            case STUDENT:
                fxmlFile = "/com/universitymanagement/roleViews/student-dashboard-view.fxml";
                title = "Student Dashboard";
                break;
            case FACULTY:
                fxmlFile = "/com/universitymanagement/roleViews/faculty-dashboard-view.fxml";
                title = "Faculty Dashboard";
                break;
            default:
                fxmlFile = "/com/universitymanagement/auth/login-view.fxml";
                title = "Login";
                break;
        }

        try {
            Parent viewRoot = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(viewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
