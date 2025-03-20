package com.example.universitymanagementapp.controller.AdminController;

import com.example.universitymanagementapp.UniversityManagementApp;
import com.example.universitymanagementapp.model.Activity;
import com.example.universitymanagementapp.model.Event;
import com.example.universitymanagementapp.model.Notification;
import com.example.universitymanagementapp.model.Registration;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdminDashboard {

    @FXML
    public MenuButton toggleMenuButton;
    @FXML
    public MenuItem dashboard;
    @FXML
    public MenuItem subjectSelection;
    @FXML
    public MenuItem courseSelection;
    @FXML
    public MenuItem studentSelection;
    @FXML
    public MenuItem facultySelection;
    @FXML
    public MenuItem eventSelection;
    @FXML
    public MenuItem logout;

    @FXML
    public AnchorPane contentPane;

    @FXML
    private Button addStudentButton;
    @FXML
    private Button addCourseButton;
    @FXML
    private Button addFacultyButton;
    @FXML
    private Button addSubjectButton;
    @FXML
    private Button addEventButton;

    @FXML
    private Text totalStudentsText;
    @FXML
    private Text totalCoursesText;
    @FXML
    private Text totalFacultyText;
    @FXML
    private Text totalSubjectsText;
    @FXML
    private Text totalEventsText;

    @FXML
    private TableView<Activity> recentActivitiesTable;
    @FXML
    private TableColumn<Activity, String> activityTypeColumn;
    @FXML
    private TableColumn<Activity, String> activityDescriptionColumn;
    @FXML
    private TableColumn<Activity, String> activityDateColumn;

    @FXML
    private TableView<Registration> recentRegistrationsTable;
    @FXML
    private TableColumn<Registration, String> registrationStudentColumn;
    @FXML
    private TableColumn<Registration, Integer> registrationCourseColumn;
    @FXML
    private TableColumn<Registration, String> registrationDateColumn;

    @FXML
    private TableView<Event> upcomingEventsTable;
    @FXML
    private TableColumn<Event, String> eventCodeColumn;
    @FXML
    private TableColumn<Event, String> eventNameColumn;
    @FXML
    private TableColumn<Event, String> eventDateColumn;
    @FXML
    private TableColumn<Event, String> eventLocationColumn;

    @FXML
    private TableView<Notification> notificationsTable;
    @FXML
    private TableColumn<Notification, String> notificationTypeColumn;
    @FXML
    private TableColumn<Notification, String> notificationMessageColumn;
    @FXML
    private TableColumn<Notification, String> notificationDateColumn;

    private String currentPage = "admin-dashboard.fxml";
    private ObservableList<Activity> recentActivities = FXCollections.observableArrayList();
    private ObservableList<Registration> recentRegistrations = FXCollections.observableArrayList();
    private ObservableList<Event> upcomingEvents = FXCollections.observableArrayList();
    private ObservableList<Notification> notifications = FXCollections.observableArrayList();
    private Node initialDashboardContent; // Store the initial dashboard content

    @FXML
    public void initialize() {
        contentPane.setPickOnBounds(false);

        // Store the initial content of the contentPane (the dashboard UI)
        if (!contentPane.getChildren().isEmpty()) {
            initialDashboardContent = contentPane.getChildren().get(0);
        }

        // Configure table columns
        activityTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        activityDescriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        activityDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"))));
        recentActivitiesTable.setItems(recentActivities);

        registrationStudentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentId()));
        registrationCourseColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCourseCode()).asObject());
        registrationDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"))));
        recentRegistrationsTable.setItems(recentRegistrations);

        eventCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEventCode()));
        eventNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEventName()));
        eventDateColumn.setCellValueFactory(cellData -> {
            Date date = cellData.getValue().getEventDateTime();
            if (date != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                return new SimpleStringProperty(sdf.format(date));
            }
            return new SimpleStringProperty("N/A");
        });
        eventLocationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEventLocation()));
        upcomingEventsTable.setItems(upcomingEvents);

        notificationTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        notificationMessageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMessage()));
        notificationDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"))));
        notificationsTable.setItems(notifications);

        // Load data
        loadSummaryData();
        loadRecentActivities();
        loadRecentRegistrations();
        loadUpcomingEvents();
        loadNotifications();
    }

    private void loadSummaryData() {
        totalStudentsText.setText(String.valueOf(UniversityManagementApp.studentDAO.getAllStudents().size()));
        totalCoursesText.setText(String.valueOf(UniversityManagementApp.courseDAO.getAllCourses().size()));
        totalFacultyText.setText(String.valueOf(UniversityManagementApp.facultyDAO.getAllFaculty().size()));
        totalSubjectsText.setText(String.valueOf(UniversityManagementApp.subjectDAO.getAllSubjects().size()));
        totalEventsText.setText(String.valueOf(UniversityManagementApp.eventDAO.getAllEvents().size()));
    }

    private void loadRecentActivities() {
        recentActivities.add(new Activity("Course Added", "Added course CS101", LocalDateTime.now().minusDays(1)));
        recentActivities.add(new Activity("Student Added", "Added student S20250001", LocalDateTime.now().minusDays(2)));
        recentActivities.add(new Activity("Subject Deleted", "Deleted subject MATH201", LocalDateTime.now().minusDays(3)));
        recentActivities.sort(Comparator.comparing(Activity::getDate).reversed());
    }

    private void loadRecentRegistrations() {
        recentRegistrations.add(new Registration("S20250001", 101, LocalDateTime.now().minusHours(5)));
        recentRegistrations.add(new Registration("S20250002", 102, LocalDateTime.now().minusDays(1)));
        recentRegistrations.sort(Comparator.comparing(Registration::getDate).reversed());
    }

    private void loadUpcomingEvents() {
        List<Event> events = UniversityManagementApp.eventDAO.getAllEvents().stream()
                .filter(event -> {
                    LocalDateTime eventDateTime = event.getEventDateTime()
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();
                    return eventDateTime.isAfter(LocalDateTime.now());
                })
                .sorted(Comparator.comparing(event -> event.getEventDateTime()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()))
                .limit(5)
                .collect(Collectors.toList());
        upcomingEvents.setAll(events);
    }

    private void loadNotifications() {
        notifications.add(new Notification("Alert", "Course CS101 is at full capacity", LocalDateTime.now().minusHours(2)));
        notifications.add(new Notification("Warning", "Event E001 is tomorrow", LocalDateTime.now().minusDays(1)));
        notifications.sort(Comparator.comparing(Notification::getDate).reversed());
    }

    private void loadPage(String fxmlFile) {
        // If loading the dashboard, restore the initial content
        if (fxmlFile.equals("admin-dashboard.fxml")) {
            if (initialDashboardContent != null) {
                contentPane.getChildren().clear();
                contentPane.getChildren().add(initialDashboardContent);
                currentPage = "admin-dashboard.fxml";
                // Refresh the dashboard data
                recentActivities.clear();
                recentRegistrations.clear();
                upcomingEvents.clear();
                notifications.clear();
                loadSummaryData();
                loadRecentActivities();
                loadRecentRegistrations();
                loadUpcomingEvents();
                loadNotifications();
            }
            return;
        }

        // Prevent loading the same page again
        if (fxmlFile.equals(currentPage)) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/" + fxmlFile));
            Parent newPage = loader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newPage);

            AnchorPane.setTopAnchor(newPage, 0.0);
            AnchorPane.setBottomAnchor(newPage, 0.0);
            AnchorPane.setLeftAnchor(newPage, 0.0);
            AnchorPane.setRightAnchor(newPage, 0.0);

            currentPage = fxmlFile;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML: " + fxmlFile);
        }
    }

    // Add this method to refresh the Courses tab
    public void refreshCoursesTab() {
        if (currentPage.equals("admin-course-selection.fxml")) {
            // If the current page is the courses tab, reload it
            loadPage("admin-course-selection.fxml");
        }
        // Optionally, you can also refresh the recent activities and registrations on the dashboard
        recentActivities.clear();
        recentRegistrations.clear();
        loadRecentActivities();
        loadRecentRegistrations();
    }

    @FXML
    public void handleDashboardAction(ActionEvent actionEvent) {
        System.out.println("Dashboard menu item clicked. Restoring dashboard content.");
        loadPage("admin-dashboard.fxml"); // Restores the initial dashboard content and refreshes data
    }

    @FXML
    public void handleSubjectSelection(ActionEvent actionEvent) {
        loadPage("admin-subject-selection.fxml");
    }

    @FXML
    public void handleCourseSelection(ActionEvent actionEvent) {
        loadPage("admin-course-selection.fxml");
    }

    @FXML
    public void handleStudentSelection(ActionEvent actionEvent) {
        loadPage("admin-student-selection.fxml");
    }

    @FXML
    public void handleFacultySelection(ActionEvent actionEvent) {
        loadPage("admin-faculty-selection.fxml");
    }

    @FXML
    public void handleEventSelection(ActionEvent actionEvent) {
        loadPage("admin-events-selection.fxml");
    }

    @FXML
    public void handleLogoutAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/login-page.fxml"));
            Parent loginPage = loader.load();
            Stage stage = (Stage) toggleMenuButton.getScene().getWindow();
            stage.setScene(new Scene(loginPage));
            stage.setTitle("Login Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading login page: " + e.getMessage());
        }
    }

    @FXML
    public void handleAddStudent(ActionEvent actionEvent) {
        loadPage("admin-student-selection.fxml");
    }

    @FXML
    public void handleAddCourse(ActionEvent actionEvent) {
        loadPage("admin-course-selection.fxml");
    }

    @FXML
    public void handleAddFaculty(ActionEvent actionEvent) {
        loadPage("admin-faculty-selection.fxml");
    }

    @FXML
    public void handleAddSubject(ActionEvent actionEvent) {
        loadPage("admin-subject-selection.fxml");
    }

    @FXML
    public void handleAddEvent(ActionEvent actionEvent) {
        loadPage("admin-events-selection.fxml");
    }
}
