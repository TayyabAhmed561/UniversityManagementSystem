package com.example.universitymanagementapp.controller.FacultyController;

import com.example.universitymanagementapp.dao.FacultyDAO;
import com.example.universitymanagementapp.model.Faculty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FacultyFacultyController {

    // TableView for all faculty members
    @FXML
    private TableView<Faculty> allFacultyTable;
    @FXML
    private TableColumn<Faculty, String> facultyIdColumn; // We'll display username as the ID
    @FXML
    private TableColumn<Faculty, String> nameColumn;
    @FXML
    private TableColumn<Faculty, String> departmentColumn; // Using degree as a substitute for department
    @FXML
    private TableColumn<Faculty, String> emailColumn;

    // TableView for selected faculty members
    @FXML
    private TableView<Faculty> selectedFacultyTable;
    @FXML
    private TableColumn<Faculty, String> selectedFacultyIdColumn;
    @FXML
    private TableColumn<Faculty, String> selectedNameColumn;
    @FXML
    private TableColumn<Faculty, String> selectedDepartmentColumn;
    @FXML
    private TableColumn<Faculty, String> selectedEmailColumn;

    // FacultyDAO instance for data access
    private FacultyDAO facultyDAO;

    private FacultyDashboard parentController;

    public void setParentController(FacultyDashboard parentController) {
        this.parentController = parentController;
    }

    @FXML
    public void initialize() {
        // Instantiate the DAO (this assumes that faculty data is already imported)
        facultyDAO = new FacultyDAO();

        // Set up cell value factories for "All Faculty" table.
        // Note: We're using "username" as the unique identifier for faculty.
        facultyIdColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        // Using "degree" to populate the department column for display purposes.
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Set up cell value factories for "Selected Faculty" table.
        selectedFacultyIdColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        selectedNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        selectedDepartmentColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
        selectedEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Load all faculty data from the DAO into an observable list.
        ObservableList<Faculty> allFaculty = FXCollections.observableArrayList(facultyDAO.getAllFaculty());
        allFacultyTable.setItems(allFaculty);

        // Initialize the selected faculty list (empty at start).
        ObservableList<Faculty> selectedFaculty = FXCollections.observableArrayList();
        selectedFacultyTable.setItems(selectedFaculty);

        // Optional: Add a listener to add a faculty member to the selected table when clicked.
        allFacultyTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !selectedFaculty.contains(newValue)) {
                selectedFaculty.add(newValue);
            }
        });
    }

    // You can add additional methods here to remove items from the selected table,
    // refresh the data, or handle other UI events.
}