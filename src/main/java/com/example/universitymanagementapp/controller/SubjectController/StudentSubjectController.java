package com.example.universitymanagementapp.controller.SubjectController;

import com.example.universitymanagementapp.dao.SubjectDAO;
import com.example.universitymanagementapp.UniversityManagementApp;
import com.example.universitymanagementapp.model.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentSubjectController implements Initializable {

    // FXML components
    @FXML
    private TableView<Subject> allSubjectsTable;
    @FXML
    private TableColumn<Subject, String> subjectNameColumn;
    @FXML
    private TableColumn<Subject, String> subjectCodeColumn;

    @FXML
    private TextField subjectSearch;
    @FXML
    private TableView<Subject> searchResultsTable;
    @FXML
    private TableColumn<Subject, String> searchSubjectNameColumn;
    @FXML
    private TableColumn<Subject, String> searchSubjectCodeColumn;



    // Access SubjectDAO from HelloApplication
    private SubjectDAO subjectDAO = UniversityManagementApp.subjectDAO;

    // Observable lists to hold subjects
    private ObservableList<Subject> allSubjectsList = FXCollections.observableArrayList();
    private ObservableList<Subject> searchResultsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configure columns for All Subjects table
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        subjectCodeColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));

        // Configure columns for Search Results table
        searchSubjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        searchSubjectCodeColumn.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));

        // Set table resize policies
        allSubjectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        searchResultsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Load all subjects
        loadAllSubjects();

        // Add listener to subjectSearch TextField for real-time search
        subjectSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterSubjects(newValue);
        });
    }

    // Load all subjects into the "All Subjects" tab
    private void loadAllSubjects() {
        allSubjectsList.clear();
        allSubjectsList.addAll(subjectDAO.getAllSubjects());
        allSubjectsTable.setItems(allSubjectsList);
        System.out.println("Loaded all subjects: " + allSubjectsList);
    }

    // Filter subjects based on search input and display in the "Search" tab
    private void filterSubjects(String searchText) {
        searchResultsList.clear();
        if (searchText == null || searchText.trim().isEmpty()) {
            searchResultsTable.setItems(searchResultsList);
            return;
        }

        String lowerCaseSearch = searchText.trim().toLowerCase();
        for (Subject subject : subjectDAO.getAllSubjects()) {
            if (subject.getSubjectName().toLowerCase().contains(lowerCaseSearch) ||
                    subject.getSubjectCode().toLowerCase().contains(lowerCaseSearch)) {
                searchResultsList.add(subject);
            }
        }
        searchResultsTable.setItems(searchResultsList);
        System.out.println("Search results for '" + searchText + "': " + searchResultsList);
    }
}

