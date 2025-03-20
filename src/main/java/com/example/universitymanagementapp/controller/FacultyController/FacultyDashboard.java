package com.example.universitymanagementapp.controller.FacultyController;

import com.example.universitymanagementapp.UniversityManagementApp;
import com.example.universitymanagementapp.controller.CourseController.CourseFacultyController;
import com.example.universitymanagementapp.controller.EventController.EventFacultyController;
import com.example.universitymanagementapp.controller.StudentController.FacultyStudentController;
import com.example.universitymanagementapp.controller.SubjectController.FacultySubjectController;
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

public class FacultyDashboard {

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
    private AnchorPane contentPane; // This is where pages will load dynamically

    // New fields for the dashboard UI elements
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

    private String currentPage = "faculty-dashboard.fxml"; // Track the currently loaded page
    private String facultyName; // Store faculty name
    private String facultyUsername; // Store faculty username
    private Node initialDashboardContent; // Store the initial dashboard content

    // Data for the tables
    private ObservableList<Activity> recentActivities = FXCollections.observableArrayList();
    private ObservableList<Registration> recentRegistrations = FXCollections.observableArrayList();
    private ObservableList<Event> upcomingEvents = FXCollections.observableArrayList();
    private ObservableList<Notification> notifications = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        contentPane.setPickOnBounds(false); // Ensures it does not block clicks

        // Store the initial content of the contentPane (the dashboard UI)
        if (!contentPane.getChildren().isEmpty()) {
            initialDashboardContent = contentPane.getChildren().get(0);
        }

        // Configure table columns
        if (activityTypeColumn != null) { // Check if the dashboard is loaded
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

            // Load data for the dashboard
            loadSummaryData();
            loadRecentActivities();
            loadRecentRegistrations();
            loadUpcomingEvents();
            loadNotifications();
        }

        // No need to call loadPage("faculty-dashboard.fxml") here since the dashboard is already loaded by FXML
        currentPage = "faculty-dashboard.fxml";
    }

    // Method to set faculty name from UserLoginController
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
        System.out.println("Faculty name set in dashboard: " + facultyName);
    }

    // Method to set faculty username from UserLoginController
    public void setFacultyUsername(String facultyUsername) {
        this.facultyUsername = facultyUsername;
        System.out.println("Faculty username set in dashboard: " + facultyUsername);
    }

    // Method to load FXML content into contentPane
    private void loadPage(String fxmlFile) {
        // If loading the dashboard, restore the initial content
        if (fxmlFile.equals("faculty-dashboard.fxml")) {
            if (initialDashboardContent != null) {
                contentPane.getChildren().clear();
                contentPane.getChildren().add(initialDashboardContent);
                currentPage = "faculty-dashboard.fxml";
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

        // Prevent reloading the same page
        if (fxmlFile.equals(currentPage)) {
            return; // Do nothing if already on the same page
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/" + fxmlFile));
            Parent newPage = loader.load();

            // If loading CourseFacultyController, pass the faculty name
            if (fxmlFile.equals("faculty-course-selection.fxml")) {
                CourseFacultyController controller = loader.getController();
                controller.setLoggedInFacultyName(facultyName);
                controller.setParentController(this); // Set parent controller
            }
            // If loading FacultyStudentController, pass the faculty name and username
            else if (fxmlFile.equals("faculty-student-selection.fxml")) {
                FacultyStudentController controller = loader.getController();
                controller.setFacultyName(facultyName);
                controller.setFacultyUsername(facultyUsername);
                controller.setParentController(this); // Set parent controller
            }
            // Set parent controller for other subpages
            else if (fxmlFile.equals("faculty-subject-selection.fxml")) {
                FacultySubjectController controller = loader.getController(); // Replace with actual controller class
                controller.setParentController(this);
            }
            else if (fxmlFile.equals("faculty-faculty-selection.fxml")) {
                FacultyFacultyController controller = loader.getController(); // Replace with actual controller class
                controller.setParentController(this);
            }
            else if (fxmlFile.equals("faculty-event-selection.fxml")) {
                EventFacultyController controller = loader.getController(); // Replace with actual controller class
                controller.setParentController(this);
            }

            contentPane.getChildren().clear();
            contentPane.getChildren().add(newPage);

            AnchorPane.setTopAnchor(newPage, 0.0);
            AnchorPane.setBottomAnchor(newPage, 0.0);
            AnchorPane.setLeftAnchor(newPage, 0.0);
            AnchorPane.setRightAnchor(newPage, 0.0);

            currentPage = fxmlFile; // Update current page
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML: " + fxmlFile);
        }
    }

    private void loadSummaryData() {
        totalStudentsText.setText(String.valueOf(UniversityManagementApp.studentDAO.getAllStudents().size()));
        totalCoursesText.setText(String.valueOf(UniversityManagementApp.courseDAO.getAllCourses().size()));
        totalFacultyText.setText(String.valueOf(UniversityManagementApp.facultyDAO.getAllFaculty().size()));
        totalSubjectsText.setText(String.valueOf(UniversityManagementApp.subjectDAO.getAllSubjects().size()));
        totalEventsText.setText(String.valueOf(UniversityManagementApp.eventDAO.getAllEvents().size()));
    }

    private void loadRecentActivities() {
        // Simulate recent activities (in a real app, this would come from a log or database)
        recentActivities.add(new Activity("Course Added", "Added course CS101", LocalDateTime.now().minusDays(1)));
        recentActivities.add(new Activity("Student Added", "Added student S20250001", LocalDateTime.now().minusDays(2)));
        recentActivities.add(new Activity("Subject Deleted", "Deleted subject MATH201", LocalDateTime.now().minusDays(3)));
        recentActivities.sort(Comparator.comparing(Activity::getDate).reversed());
    }

    private void loadRecentRegistrations() {
        // Simulate recent registrations (in a real app, this would come from a log or database)
        recentRegistrations.add(new Registration("S20250001", 101, LocalDateTime.now().minusHours(5)));
        recentRegistrations.add(new Registration("S20250002", 102, LocalDateTime.now().minusDays(1)));
        recentRegistrations.sort(Comparator.comparing(Registration::getDate).reversed());
    }

    private void loadUpcomingEvents() {
        // Load upcoming events from EventDAO
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
        // Simulate notifications (in a real app, this would come from a notification system)
        notifications.add(new Notification("Alert", "Course CS101 is at full capacity", LocalDateTime.now().minusHours(2)));
        notifications.add(new Notification("Warning", "Event E001 is tomorrow", LocalDateTime.now().minusDays(1)));
        notifications.sort(Comparator.comparing(Notification::getDate).reversed());
    }

    @FXML
    public void handleDashboardAction(ActionEvent actionEvent) {
        System.out.println("Dashboard menu item clicked. Restoring dashboard content.");
        loadPage("faculty-dashboard.fxml"); // Restores the initial dashboard content and refreshes data
    }

    @FXML
    public void handleSubjectSelection(ActionEvent actionEvent) {
        loadPage("faculty-subject-selection.fxml");
    }

    @FXML
    public void handleCourseSelection(ActionEvent actionEvent) {
        loadPage("faculty-course-selection.fxml"); // This will trigger setLoggedInFacultyName
    }

    @FXML
    public void handleStudentSelection(ActionEvent actionEvent) {
        loadPage("faculty-student-selection.fxml"); // This will trigger setFacultyName and setFacultyUsername
    }

    @FXML
    public void handleFacultySelection(ActionEvent actionEvent) {
        loadPage("faculty-faculty-selection.fxml");
    }

    @FXML
    public void handleEventSelection(ActionEvent actionEvent) {
        loadPage("faculty-event-selection.fxml");
    }

    @FXML
    public void handleLogoutAction(ActionEvent actionEvent) {
        try {
            // Load the login page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/login-page.fxml"));
            Parent loginPage = loader.load();

            // Get the current stage (window)
            Stage stage = (Stage) toggleMenuButton.getScene().getWindow();

            // Create a new scene with the login page
            Scene scene = new Scene(loginPage, 601, 498);
            stage.setScene(scene);
            stage.setTitle("Login Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading login page: " + e.getMessage());
        }
    }
}