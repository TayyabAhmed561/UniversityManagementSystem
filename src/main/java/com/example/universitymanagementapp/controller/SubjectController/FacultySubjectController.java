package com.example.universitymanagementapp.controller.SubjectController;

import com.example.universitymanagementapp.controller.FacultyController.FacultyDashboard;
import com.example.universitymanagementapp.dao.SubjectDAO;
import com.example.universitymanagementapp.UniversityManagementApp;
import com.example.universitymanagementapp.model.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class FacultySubjectController {

    @FXML
    public TableView<Subject> allSubjectsTable;

    @FXML
    public TextField subjectSearch;

    @FXML
    public TableColumn<Subject, String> subjectNameColumn;

    @FXML
    public TableColumn<Subject, String> subjectCodeColumn;

    @FXML
    public TableView<Subject> searchResultsTable;

    @FXML
    public TableColumn<Subject, String> searchSubjectNameColumn;

    @FXML
    public TableColumn<Subject, String> searchSubjectCodeColumn;

    private SubjectDAO subjectDAO = UniversityManagementApp.subjectDAO; // Access SubjectDAO from HelloApplication
    private ObservableList<Subject> allSubjectsList = FXCollections.observableArrayList();
    private ObservableList<Subject> searchResultsList = FXCollections.observableArrayList();

    private FacultyDashboard parentController;


    public void setParentController(FacultyDashboard parentController) {
        this.parentController = parentController;
    }

    @FXML
    public void initialize() {
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

    // Load all subjects into the first tab
    private void loadAllSubjects() {
        allSubjectsList.clear();
        allSubjectsList.addAll(subjectDAO.getAllSubjects());
        allSubjectsTable.setItems(allSubjectsList);
        System.out.println("Loaded all subjects: " + allSubjectsList);
    }

    // Filter subjects based on search input and display in the second tab
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
