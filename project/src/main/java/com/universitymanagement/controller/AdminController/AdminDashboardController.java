package com.universitymanagement.controller.AdminController;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private Button menuToggleButton;
    @FXML
    private VBox sidebarMenu;
    @FXML
    private StackPane contentArea;
    private boolean menuExpanded = false;

    // VBoxes for different module content
    private VBox dashboardContent;
    private VBox subjectContent;
    private VBox courseContent;
    private VBox studentContent;
    private VBox facultyContent;
    private VBox eventContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the sidebar in collapsed state
        sidebarMenu.setPrefWidth(0);
        sidebarMenu.setOpacity(0);

        // Initialize all content modules
        initializeContentModules();

        // Show dashboard by default
        loadDashboard();
    }

    private void initializeContentModules() {
        // Initialize Dashboard Content
        dashboardContent = createContentVBox("Dashboard");
        dashboardContent.getChildren().addAll(
                createStyledButton("Manage Users", "#4682B4", e -> manageUsers(e)),
                createStyledButton("View Reports", "#4682B4", e -> viewReports(e)),
                createStyledButton("Manage Subjects", "#4682B4", e -> handleManageSubjects()),
                createStyledButton("Manage Students", "#4682B4", e -> handleManageStudents()),
                createStyledButton("Manage Faculty", "#4682B4", e -> handleManageFaculty()),
                createStyledButton("Manage Events", "#4682B4", e -> handleManageEvents()),
                createStyledButton("Manage Courses", "#4682B4", e -> handleManageCourses()),
                createStyledButton("Logout", "#B22222", e -> handleLogoutAction(e))
        );

        // Initialize Subject Content
        subjectContent = createContentVBox("Subject Management");
        subjectContent.getChildren().addAll(
                createStyledButton("View Subjects", "#32CD32", e -> handleViewSubjects(e)),
                createStyledButton("Add Subject", "#32CD32", e -> handleAddSubject(e)),
                createStyledButton("Edit Subject", "#FFA500", e -> handleEditSubject(e)),
                createStyledButton("Back to Dashboard", "#4682B4", e -> loadDashboard())
        );

        // Initialize Course Content
        courseContent = createContentVBox("Course Management");
        courseContent.getChildren().addAll(
                createStyledButton("View Courses", "#32CD32", e -> handleViewCourses(e)),
                createStyledButton("Add Course", "#32CD32", e -> handleAddCourse(e)),
                createStyledButton("Edit Course", "#FFA500", e -> handleEditCourse(e)),
                createStyledButton("Assign Faculty", "#4682B4", e -> handleAssignFaculty(e)),
                createStyledButton("Back to Dashboard", "#4682B4", e -> loadDashboard())
        );

        // Initialize Student Content
        studentContent = createContentVBox("Student Management");
        studentContent.getChildren().addAll(
                createStyledButton("View Students", "#32CD32", e -> handleViewStudents(e)),
                createStyledButton("Add Student", "#32CD32", e -> handleAddStudent(e)),
                createStyledButton("Edit Student", "#FFA500", e -> handleEditStudent(e)),
                createStyledButton("Enroll Student", "#4682B4", e -> handleEnrollStudent(e)),
                createStyledButton("Student Grades", "#4682B4", e -> handleStudentGrades(e)),
                createStyledButton("Back to Dashboard", "#4682B4", e -> loadDashboard())
        );

        // Initialize Faculty Content
        facultyContent = createContentVBox("Faculty Management");
        facultyContent.getChildren().addAll(
                createStyledButton("View Faculty", "#32CD32", e -> handleViewFaculty(e)),
                createStyledButton("Add Faculty", "#32CD32", e -> handleAddFaculty(e)),
                createStyledButton("Edit Faculty", "#FFA500", e -> handleEditFaculty(e)),
                createStyledButton("Assign Courses", "#4682B4", e -> handleAssignCourses(e)),
                createStyledButton("Back to Dashboard", "#4682B4", e -> loadDashboard())
        );

        // Initialize Event Content
        eventContent = createContentVBox("Event Management");
        eventContent.getChildren().addAll(
                createStyledButton("View Events", "#32CD32", e -> handleViewEvents(e)),
                createStyledButton("Add Event", "#32CD32", e -> handleAddEvent(e)),
                createStyledButton("Edit Event", "#FFA500", e -> handleEditEvent(e)),
                createStyledButton("Schedule Event", "#4682B4", e -> handleScheduleEvent(e)),
                createStyledButton("Back to Dashboard", "#4682B4", e -> loadDashboard())
        );
    }

    private VBox createContentVBox(String title) {
        VBox contentVBox = new VBox();
        contentVBox.setAlignment(javafx.geometry.Pos.CENTER);
        contentVBox.setSpacing(15);
        contentVBox.setStyle("-fx-padding: 40;");

        // Add title label
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 0 0 20 0;");
        contentVBox.getChildren().add(titleLabel);

        return contentVBox;
    }

    private Button createStyledButton(String text, String color, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-size: 20px; -fx-min-width: 350px;");
        button.setOnAction(action);
        return button;
    }

    private void setContent(VBox content) {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(content);
    }

    @FXML
    public void toggleMenu() {
        Timeline timeline = new Timeline();

        if (menuExpanded) {
            // Collapse menu
            KeyValue kvWidth = new KeyValue(sidebarMenu.prefWidthProperty(), 0);
            KeyValue kvOpacity = new KeyValue(sidebarMenu.opacityProperty(), 0);

            KeyFrame kf = new KeyFrame(Duration.millis(250), kvWidth, kvOpacity);
            timeline.getKeyFrames().add(kf);

            menuToggleButton.setText("☰");
            menuExpanded = false;
        } else {
            // Expand menu
            KeyValue kvWidth = new KeyValue(sidebarMenu.prefWidthProperty(), 250);
            KeyValue kvOpacity = new KeyValue(sidebarMenu.opacityProperty(), 1);

            KeyFrame kf = new KeyFrame(Duration.millis(250), kvWidth, kvOpacity);
            timeline.getKeyFrames().add(kf);

            menuToggleButton.setText("✕");
            menuExpanded = true;
        }

        timeline.play();
    }

    // Dashboard methods
    @FXML
    public void loadDashboard() {
        System.out.println("Loading Dashboard");
        setContent(dashboardContent);
    }

    @FXML
    public void manageUsers(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/user-management-view.fxml", "User Management", event);
    }

    @FXML
    public void viewReports(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/reports-view.fxml", "Reports", event);
    }

    // Fixed method - removed ActionEvent parameter to match FXML usage
    @FXML
    public void handleManageSubjects() {
        System.out.println("Managing Subjects");
        setContent(subjectContent);
    }

    @FXML
    public void handleViewSubjects(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/subjects-view.fxml", "View Subjects", event);
    }

    @FXML
    public void handleAddSubject(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/add-subject-view.fxml", "Add Subject", event);
    }

    @FXML
    public void handleEditSubject(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/edit-subject-view.fxml", "Edit Subject", event);
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/admin-dashboard-view.fxml", "Admin Dashboard", event);
    }

    @FXML
    public void handleLogoutAction(ActionEvent event) {
        loadView("/com/universitymanagement/auth/login-view.fxml", "Login", event);
        System.out.println("Logged out successfully.");
    }

    // Student management methods - no ActionEvent needed for sidebar navigation
    @FXML
    public void handleManageStudents() {
        System.out.println("Managing Students");
        setContent(studentContent);
    }

    @FXML
    public void handleViewStudents(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/students-view.fxml", "View Students", event);
    }

    @FXML
    public void handleAddStudent(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/add-student-view.fxml", "Add Student", event);
    }

    @FXML
    public void handleEditStudent(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/edit-student-view.fxml", "Edit Student", event);
    }

    @FXML
    public void handleEnrollStudent(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/enroll-student-view.fxml", "Enroll Student", event);
    }

    @FXML
    public void handleStudentGrades(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/student-grades-view.fxml", "Student Grades", event);
    }

    // Faculty management methods - no ActionEvent needed for sidebar navigation
    @FXML
    public void handleManageFaculty() {
        System.out.println("Managing Faculty");
        setContent(facultyContent);
    }

    @FXML
    public void handleViewFaculty(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/faculty-view.fxml", "View Faculty", event);
    }

    @FXML
    public void handleAddFaculty(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/add-faculty-view.fxml", "Add Faculty", event);
    }

    @FXML
    public void handleEditFaculty(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/edit-faculty-view.fxml", "Edit Faculty", event);
    }

    @FXML
    public void handleAssignCourses(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/assign-courses-view.fxml", "Assign Courses", event);
    }

    // Event management methods - no ActionEvent needed for sidebar navigation
    @FXML
    public void handleManageEvents() {
        System.out.println("Managing Events");
        setContent(eventContent);
    }

    @FXML
    public void handleViewEvents(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/events-view.fxml", "View Events", event);
    }

    @FXML
    public void handleAddEvent(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/add-event-view.fxml", "Add Event", event);
    }

    @FXML
    public void handleEditEvent(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/edit-event-view.fxml", "Edit Event", event);
    }

    @FXML
    public void handleScheduleEvent(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/schedule-event-view.fxml", "Schedule Event", event);
    }

    // Course management methods - no ActionEvent needed for sidebar navigation
    @FXML
    public void handleManageCourses() {
        System.out.println("Managing Courses");
        setContent(courseContent);
    }

    @FXML
    public void handleViewCourses(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/courses-view.fxml", "View Courses", event);
    }

    @FXML
    public void handleAddCourse(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/add-course-view.fxml", "Add Course", event);
    }

    @FXML
    public void handleEditCourse(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/edit-course-view.fxml", "Edit Course", event);
    }

    @FXML
    public void handleAssignFaculty(ActionEvent event) {
        loadView("/com/universitymanagement/roleViews/assign-faculty-view.fxml", "Assign Faculty", event);
    }

    private void loadView(String fxmlFile, String title, ActionEvent event) {
        try {
            URL resource = getClass().getResource(fxmlFile);
            if (resource == null) {
                System.out.println("FXML file not found: " + fxmlFile);
                showAlert("Error", "FXML file not found: " + fxmlFile);
                return;
            }

            Parent viewRoot = FXMLLoader.load(resource);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(viewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the view: " + fxmlFile);
        }
    }

    private void loadView(String fxmlPath, String title) {
        try {
            System.out.println("Loading FXML file: " + fxmlPath);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));

            if (loader.getLocation() == null) {
                System.out.println("Error: FXML file not found at " + fxmlPath);
                showAlert("Error", "FXML file not found: " + fxmlPath);
                return;
            }

            Parent root = loader.load();
            Stage stage = (Stage) contentArea.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the view: " + fxmlPath);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}