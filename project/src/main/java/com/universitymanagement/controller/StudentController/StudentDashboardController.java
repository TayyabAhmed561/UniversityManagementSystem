package com.universitymanagement.controller.StudentController;

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

public class StudentDashboardController implements Initializable {

    @FXML
    private Button menuToggleButton;
    @FXML
    private VBox sidebarMenu;
    @FXML
    private StackPane contentArea;
    private boolean menuExpanded = false;

    // VBoxes for different module content
    private VBox dashboardContent;
    private VBox coursesContent;
    private VBox facultyInfoContent;
    private VBox eventsContent;
    private VBox profileContent;

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
        dashboardContent = createContentVBox("Student Dashboard");
        dashboardContent.getChildren().addAll(
                createStyledButton("View My Profile", "#4682B4", this::handleViewProfile),
                createStyledButton("View Faculty Profiles", "#4682B4", this::handleViewFacultyProfiles),
                createStyledButton("View Available Events", "#4682B4", this::handleViewEvents),
                createStyledButton("View My Courses", "#4682B4", this::handleViewCourses),
                createStyledButton("Logout", "#B22222", this::handleLogoutAction)
        );

        // Initialize Courses Content
        coursesContent = createContentVBox("My Courses");
        coursesContent.getChildren().addAll(
                createStyledButton("View Enrolled Courses", "#32CD32", this::handleViewEnrolledCourses),
                createStyledButton("Course Materials", "#32CD32", this::handleViewCourseMaterials),
                createStyledButton("Assignments", "#FFA500", this::handleViewAssignments),
                createStyledButton("View Grades", "#4682B4", this::handleViewGrades),
                createStyledButton("Back to Dashboard", "#4682B4", this::loadDashboard)
        );

        // Initialize Faculty Information Content
        facultyInfoContent = createContentVBox("Faculty Profiles");
        facultyInfoContent.getChildren().addAll(
                createStyledButton("View All Faculty", "#32CD32", this::handleViewAllFaculty),
                createStyledButton("My Course Instructors", "#32CD32", this::handleViewMyCourseInstructors),
                createStyledButton("Department Faculty", "#FFA500", this::handleViewDepartmentFaculty),
                createStyledButton("Contact Faculty", "#4682B4", this::handleContactFaculty),
                createStyledButton("Back to Dashboard", "#4682B4", this::loadDashboard)
        );

        // Initialize Events Content
        eventsContent = createContentVBox("Events");
        eventsContent.getChildren().addAll(
                createStyledButton("Upcoming Events", "#32CD32", this::handleUpcomingEvents),
                createStyledButton("Register for Events", "#32CD32", this::handleRegisterEvents),
                createStyledButton("My Registered Events", "#FFA500", this::handleMyRegisteredEvents),
                createStyledButton("Event Calendar", "#4682B4", this::handleEventCalendar),
                createStyledButton("Back to Dashboard", "#4682B4", this::loadDashboard)
        );

        // Initialize Profile Content
        profileContent = createContentVBox("My Profile");
        profileContent.getChildren().addAll(
                createStyledButton("View Profile Details", "#32CD32", this::handleViewProfileDetails),
                createStyledButton("Edit Personal Information", "#FFA500", this::handleEditProfile),
                createStyledButton("Change Password", "#4682B4", this::handleChangePassword),
                createStyledButton("Notification Settings", "#4682B4", this::handleNotificationSettings),
                createStyledButton("Back to Dashboard", "#4682B4", this::loadDashboard)
        );
    }

    private VBox createContentVBox(String title) {
        VBox contentVBox = new VBox();
        contentVBox.setAlignment(javafx.geometry.Pos.CENTER);
        contentVBox.setSpacing(30);
        contentVBox.setStyle("-fx-padding: 40;");

        // Add title label
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-padding: 0 0 20 0;");
        contentVBox.getChildren().add(titleLabel);

        return contentVBox;
    }

    private Button createStyledButton(String text, String color, Runnable action) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-size: 20px; -fx-min-width: 350px;");
        button.setOnAction(e -> action.run());
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

    @FXML
    public void loadDashboard() {
        setContent(dashboardContent);
    }

    // Original method implementations with navigation to separate views
    private void loadView(String fxmlPath, String title) {
        try {
            System.out.println("Loading FXML file: " + fxmlPath);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));

            if (loader.getLocation() == null) {
                System.out.println("Error: FXML file not found at " + fxmlPath);
                showAlert("Error", "FXML file not found: " + fxmlPath);
                return;
            }

            Parent viewRoot = loader.load();
            Stage stage = (Stage) contentArea.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(viewRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the view: " + title);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Main handlers - modified to use the new content-swapping approach
    @FXML
    public void handleViewProfile() {
        System.out.println("Viewing Profile");
        setContent(profileContent);
    }

    @FXML
    public void handleViewFacultyProfiles() {
        System.out.println("Viewing Faculty Profiles");
        setContent(facultyInfoContent);
        // For full page navigation uncomment:
        // loadView("/com/universitymanagement/roleViews/faculty/faculty-student-view.fxml", "View Faculty Profiles");
    }

    @FXML
    public void handleViewEvents() {
        System.out.println("Viewing Events");
        setContent(eventsContent);
        // For full page navigation uncomment:
        // loadView("/com/universitymanagement/roleViews/events/event-user-view.fxml", "View Available Events");
    }

    @FXML
    public void handleViewCourses() {
        System.out.println("Viewing Courses");
        setContent(coursesContent);
        // For full page navigation uncomment:
        // loadView("/com/universitymanagement/roleViews/course/student-course-view.fxml", "View My Courses");
    }

    @FXML
    public void handleLogoutAction() {
        System.out.println("Logging out");
        loadView("/com/universitymanagement/auth/login-view.fxml", "Login");
    }

    // Methods for course management section
    @FXML
    public void handleViewEnrolledCourses() {
        System.out.println("Viewing Enrolled Courses");
    }

    @FXML
    public void handleViewCourseMaterials() {
        System.out.println("Viewing Course Materials");
    }

    @FXML
    public void handleViewAssignments() {
        System.out.println("Viewing Assignments");
    }

    @FXML
    public void handleViewGrades() {
        System.out.println("Viewing Grades");
    }

    // Methods for faculty information section
    @FXML
    public void handleViewAllFaculty() {
        System.out.println("Viewing All Faculty");
    }

    @FXML
    public void handleViewMyCourseInstructors() {
        System.out.println("Viewing My Course Instructors");
    }

    @FXML
    public void handleViewDepartmentFaculty() {
        System.out.println("Viewing Department Faculty");
    }

    @FXML
    public void handleContactFaculty() {
        System.out.println("Contacting Faculty");
    }

    // Methods for events section
    @FXML
    public void handleUpcomingEvents() {
        System.out.println("Viewing Upcoming Events");
    }

    @FXML
    public void handleRegisterEvents() {
        System.out.println("Registering for Events");
    }

    @FXML
    public void handleMyRegisteredEvents() {
        System.out.println("Viewing My Registered Events");
    }

    @FXML
    public void handleEventCalendar() {
        System.out.println("Viewing Event Calendar");
    }

    // Methods for profile section
    @FXML
    public void handleViewProfileDetails() {
        System.out.println("Viewing Profile Details");
    }

    @FXML
    public void handleEditProfile() {
        System.out.println("Editing Profile");
    }

    @FXML
    public void handleChangePassword() {
        System.out.println("Changing Password");
    }

    @FXML
    public void handleNotificationSettings() {
        System.out.println("Managing Notification Settings");
    }
}