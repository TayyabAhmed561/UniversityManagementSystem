package com.universitymanagement.controller.FacultyController;

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

public class FacultyDashboardController implements Initializable {

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
    private VBox studentInfoContent;
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
        dashboardContent = createContentVBox("Faculty Dashboard");
        dashboardContent.getChildren().addAll(
                createStyledButton("View Student Information", "#4682B4", this::handleViewStudentInformation),
                createStyledButton("Manage My Courses", "#4682B4", this::handleManageCourses),
                createStyledButton("View Events", "#4682B4", this::handleViewEvents),
                createStyledButton("View Profile", "#4682B4", this::handleViewProfile),
                createStyledButton("Logout", "#B22222", this::handleLogoutAction)
        );

        // Initialize Courses Content
        coursesContent = createContentVBox("My Courses");
        coursesContent.getChildren().addAll(
                createStyledButton("View My Courses", "#32CD32", this::handleViewCourses),
                createStyledButton("Add Course Material", "#32CD32", this::handleAddCourseMaterial),
                createStyledButton("Manage Assignments", "#FFA500", this::handleManageAssignments),
                createStyledButton("Grade Students", "#4682B4", this::handleGradeStudents),
                createStyledButton("Back to Dashboard", "#4682B4", this::loadDashboard)
        );

        // Initialize Student Information Content
        studentInfoContent = createContentVBox("Student Information");
        studentInfoContent.getChildren().addAll(
                createStyledButton("View My Students", "#32CD32", this::handleViewMyStudents),
                createStyledButton("Student Performance", "#32CD32", this::handleStudentPerformance),
                createStyledButton("Attendance Records", "#FFA500", this::handleAttendanceRecords),
                createStyledButton("Send Notifications", "#4682B4", this::handleSendNotifications),
                createStyledButton("Back to Dashboard", "#4682B4", this::loadDashboard)
        );

        // Initialize Events Content
        eventsContent = createContentVBox("Events");
        eventsContent.getChildren().addAll(
                createStyledButton("Upcoming Events", "#32CD32", this::handleUpcomingEvents),
                createStyledButton("My Department Events", "#32CD32", this::handleDepartmentEvents),
                createStyledButton("University Events", "#FFA500", this::handleUniversityEvents),
                createStyledButton("Event Calendar", "#4682B4", this::handleEventCalendar),
                createStyledButton("Back to Dashboard", "#4682B4", this::loadDashboard)
        );

        // Initialize Profile Content
        profileContent = createContentVBox("My Profile");
        profileContent.getChildren().addAll(
                createStyledButton("View Profile", "#32CD32", this::handleViewProfileDetails),
                createStyledButton("Edit Information", "#FFA500", this::handleEditProfile),
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

    // Existing handlers - modified to use the new content-swapping approach
    @FXML
    public void handleViewStudentInformation() {
        System.out.println("Viewing Student Information");
        setContent(studentInfoContent);
        // For full page navigation uncomment:
        // loadView("/com/universitymanagement/roleViews/faculty/faculty-faculty-view.fxml", "View Student Information");
    }

    @FXML
    public void handleManageCourses() {
        System.out.println("Managing Courses");
        setContent(coursesContent);
        // For full page navigation uncomment:
        // loadView("/com/universitymanagement/roleViews/course/faculty-course-view.fxml", "Manage My Courses");
    }

    @FXML
    public void handleViewEvents() {
        System.out.println("Viewing Events");
        setContent(eventsContent);
        // For full page navigation uncomment:
        // loadView("/com/universitymanagement/roleViews/events/events-faculty-view.fxml", "View Events");
    }

    @FXML
    public void handleViewProfile() {
        System.out.println("Viewing Profile");
        setContent(profileContent);
    }

    @FXML
    public void handleLogoutAction() {
        System.out.println("Logging out");
        loadView("/com/universitymanagement/auth/login-view.fxml", "Login");
    }

    // New methods for course management section
    @FXML
    public void handleViewCourses() {
        System.out.println("Viewing Courses");
    }

    @FXML
    public void handleAddCourseMaterial() {
        System.out.println("Adding Course Material");
    }

    @FXML
    public void handleManageAssignments() {
        System.out.println("Managing Assignments");
    }

    @FXML
    public void handleGradeStudents() {
        System.out.println("Grading Students");
    }

    // New methods for student information section
    @FXML
    public void handleViewMyStudents() {
        System.out.println("Viewing My Students");
    }

    @FXML
    public void handleStudentPerformance() {
        System.out.println("Viewing Student Performance");
    }

    @FXML
    public void handleAttendanceRecords() {
        System.out.println("Managing Attendance Records");
    }

    @FXML
    public void handleSendNotifications() {
        System.out.println("Sending Notifications");
    }

    // New methods for events section
    @FXML
    public void handleUpcomingEvents() {
        System.out.println("Viewing Upcoming Events");
    }

    @FXML
    public void handleDepartmentEvents() {
        System.out.println("Viewing Department Events");
    }

    @FXML
    public void handleUniversityEvents() {
        System.out.println("Viewing University Events");
    }

    @FXML
    public void handleEventCalendar() {
        System.out.println("Viewing Event Calendar");
    }

    // New methods for profile section
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