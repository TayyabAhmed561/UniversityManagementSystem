package com.example.universitymanagementapp.controller.EventController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;

public class EventAdminController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TabPane tabPane;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private TableView<Object> allEventsTable;

    @FXML
    private TableColumn<Object, String> codeColumn;

    @FXML
    private TableColumn<Object, String> nameColumn;

    @FXML
    private TableColumn<Object, String> dateColumn;

    @FXML
    private TableColumn<Object, String> locationColumn;

    @FXML
    private TableColumn<Object, Integer> capacityColumn;

    @FXML
    private TableColumn<Object, String> costColumn;

    @FXML
    private TextField eventSearch;

    @FXML
    private TableView<Object> searchResultsTable;

    @FXML
    private TableColumn<Object, String> searchCodeColumn;

    @FXML
    private TableColumn<Object, String> searchNameColumn;

    @FXML
    private TableColumn<Object, String> searchDateColumn;

    @FXML
    private TableColumn<Object, String> searchLocationColumn;

    @FXML
    private TableColumn<Object, Integer> searchCapacityColumn;

    @FXML
    private TableColumn<Object, String> searchCostColumn;

    @FXML
    private AnchorPane manageEventsPane;

    @FXML
    private TextField codeField;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField locationField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField timeField;

    @FXML
    private TextField capacityField;

    @FXML
    private TextField costField;

    @FXML
    private ComboBox<String> studentComboBox;

    @FXML
    private TableView<Object> registeredStudentsTable;

    @FXML
    private TableColumn<Object, String> studentIdColumn;

    @FXML
    private TableColumn<Object, String> studentNameColumn;

    @FXML
    public void initialize() {
        // Populate the calendar for March 2025
        populateCalendar();

        // Enable sorting for All Events table
        codeColumn.setSortable(true);
        nameColumn.setSortable(true);
        dateColumn.setSortable(true);
        locationColumn.setSortable(true);
        capacityColumn.setSortable(true);
        costColumn.setSortable(true);

        // Enable sorting for Search Results table
        searchCodeColumn.setSortable(true);
        searchNameColumn.setSortable(true);
        searchDateColumn.setSortable(true);
        searchLocationColumn.setSortable(true);
        searchCapacityColumn.setSortable(true);
        searchCostColumn.setSortable(true);

        // Set up double-click handlers for All Events table
        allEventsTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) { // Double-click
                handleEditEventFromTable(allEventsTable);
            }
        });

        // Set up double-click handlers for Search Results table
        searchResultsTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) { // Double-click
                handleEditEventFromTable(searchResultsTable);
            }
        });

        // Placeholder items for ComboBox (to make it visible)
        studentComboBox.getItems().addAll("S0001 - John Doe", "S0002 - Jane Smith");
    }

    private void populateCalendar() {
        // Use March 2025 as the current month
        YearMonth yearMonth = YearMonth.of(2025, 3);
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7; // Sunday = 0, Monday = 1, ..., Saturday = 6
        int daysInMonth = yearMonth.lengthOfMonth();

        int row = 1;
        int col = dayOfWeek;
        for (int day = 1; day <= daysInMonth; day++) {
            Label dayLabel = new Label(String.valueOf(day));
            dayLabel.setStyle("-fx-font-size: 12px;");

            // Simulate an event on March 19, 2025
            if (day == 19) {
                Label eventLabel = new Label("Workshop");
                eventLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: blue;");
                GridPane.setRowIndex(dayLabel, row);
                GridPane.setColumnIndex(dayLabel, col);
                GridPane.setRowIndex(eventLabel, row + 1);
                GridPane.setColumnIndex(eventLabel, col);
                calendarGrid.getChildren().addAll(dayLabel, eventLabel);
            } else {
                GridPane.setRowIndex(dayLabel, row);
                GridPane.setColumnIndex(dayLabel, col);
                calendarGrid.getChildren().add(dayLabel);
            }

            col++;
            if (col > 6) { // Move to the next row after Saturday
                col = 0;
                row += 2; // Increment by 2 to leave space for event labels
            }
        }
    }

    private void handleEditEventFromTable(TableView<Object> table) {
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Switch to Manage Events tab
            tabPane.getSelectionModel().select(3); // Index 3 is "Manage Events" tab

            // Populate the form with dummy data for now
            codeField.setText("EVT001");
            nameField.setText("AI Workshop");
            descriptionField.setText("A workshop on artificial intelligence.");
            locationField.setText("Room 101");
            datePicker.setValue(LocalDate.of(2025, 3, 19));
            timeField.setText("14:00");
            capacityField.setText("50");
            costField.setText("$20");
        }
    }

    @FXML
    private void handleAddEvent() {
        // Clear the form and switch to Manage Events tab
        handleClearForm();
        tabPane.getSelectionModel().select(3); // Index 3 is "Manage Events" tab
    }

    @FXML
    private void handleEditEvent() {
        // Simulate selecting an event from the All Events table
        if (!allEventsTable.getSelectionModel().isEmpty()) {
            handleEditEventFromTable(allEventsTable);
        }
    }

    @FXML
    private void handleDeleteEvent() {
        // Placeholder for delete action
        System.out.println("Delete Event button clicked");
    }

    @FXML
    private void handleSaveEvent() {
        // Placeholder for save action
        System.out.println("Save Event button clicked");
        System.out.println("Code: " + codeField.getText());
        System.out.println("Name: " + nameField.getText());
        System.out.println("Description: " + descriptionField.getText());
        System.out.println("Location: " + locationField.getText());
        System.out.println("Date: " + datePicker.getValue());
        System.out.println("Time: " + timeField.getText());
        System.out.println("Capacity: " + capacityField.getText());
        System.out.println("Cost: " + costField.getText());
    }

    @FXML
    private void handleClearForm() {
        // Clear all fields in the form
        codeField.clear();
        nameField.clear();
        descriptionField.clear();
        locationField.clear();
        datePicker.setValue(null);
        timeField.clear();
        capacityField.clear();
        costField.clear();
        studentComboBox.getSelectionModel().clearSelection();
        registeredStudentsTable.getItems().clear();
    }

    @FXML
    private void handleRegisterStudent() {
        // Placeholder for register student action
        String selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            System.out.println("Registered student: " + selectedStudent);
        }
    }

    @FXML
    private void handleUnregisterStudent() {
        // Placeholder for unregister student action
        System.out.println("Unregister student button clicked");
    }
}