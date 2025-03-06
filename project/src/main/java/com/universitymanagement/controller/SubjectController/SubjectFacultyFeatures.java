package com.universitymanagement.controller.SubjectController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class SubjectFacultyFeatures {

    @FXML
    private ListView<String> subjectListView;

    @FXML
    public void initialize() {
        ObservableList<String> subjects = FXCollections.observableArrayList(
                "Physics - Mechanics",
                "Biology - Cellular Biology",
                "Engineering - Water Resources",
                "History - World History"
        );
        subjectListView.setItems(subjects);
    }

    @FXML
    public void handleBackAction(ActionEvent event) throws IOException {
        loadView("/com/universitymanagement/roleViews/faculty-dashboard-view.fxml", "Faculty Dashboard", event);
    }

    private void loadView(String fxmlFile, String title, ActionEvent event) throws IOException {
        Parent viewRoot = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(viewRoot));
        stage.show();
    }
}
