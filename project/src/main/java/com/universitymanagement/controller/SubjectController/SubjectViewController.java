package com.universitymanagement.controller.SubjectController;

import com.universitymanagement.auth.UserRoleAuth;
import com.universitymanagement.model.Subject;
import com.universitymanagement.controller.SubjectController.SubjectManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class SubjectViewController {

    @FXML
    private ListView<String> subjectListView;

    @FXML
    public void initialize() {
        refreshSubjectList();
    }

    public void refreshSubjectList() {
        ObservableList<String> subjectNames = FXCollections.observableArrayList();
        for (Subject subject : SubjectManager.getSubjects()) {
            subjectNames.add(subject.getSubjectName());
        }
        subjectListView.setItems(subjectNames);
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        String fxmlFile = "";
        String title = "";

        // Determine the correct dashboard based on the user's role
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
            showAlert("Error", "Failed to load the correct dashboard.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
