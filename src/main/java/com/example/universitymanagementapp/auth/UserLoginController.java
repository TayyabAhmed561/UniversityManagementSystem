package com.example.universitymanagementapp.auth;

//controls login page

import com.example.universitymanagementapp.UniversityManagementApp;
import com.example.universitymanagementapp.auth.authenticator.UserAuthentication;
import com.example.universitymanagementapp.controller.FacultyController.FacultyDashboard;
import com.example.universitymanagementapp.controller.StudentController.StudentDashboard;
import com.example.universitymanagementapp.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class UserLoginController {

    @FXML
    public Button userLoginButton;

    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField userPasswordField;


    @FXML
    public void handleUserLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = userPasswordField.getText();

        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().equals("Login")) {
            User user = UserAuthentication.authenticate(username, password);

            if (user == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid ID or password.");
                alert.showAndWait();
            } else {
                try {
                    if (redirectToAdminDash(user)) {
                        loadDashboard("/com/example/universitymanagementapp/admin-dashboard.fxml", "Admin Dashboard");
                    } else if (redirectToFacultyDash(user)) {
                        String facultyName = UniversityManagementApp.facultyDAO.getLoggedInFaculty(username);
                        if (facultyName == null) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Login Failed");
                            alert.setHeaderText(null);
                            alert.setContentText("Faculty name not found for username: " + username);
                            alert.showAndWait();
                            return;
                        }
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementapp/faculty-dashboard.fxml"));
                        Parent root = loader.load();
                        FacultyDashboard dashboardController = loader.getController();
                        dashboardController.setFacultyName(facultyName); // Pass faculty name
                        dashboardController.setFacultyUsername(username); // Optionally pass faculty ID for other uses

                        Stage stage = (Stage) usernameField.getScene().getWindow();
                        stage.setTitle("Faculty Dashboard");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } else if (redirectToStudentDash(user)) {
                        loadDashboard("/com/example/universitymanagementapp/student-dashboard.fxml", "Student Dashboard");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to load the dashboard view: " + e.getMessage());
                    alert.showAndWait();
                }
            }
        }
    }

    private void loadDashboard(String fxmlPath, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        if (fxmlPath.equals("/com/example/universitymanagementapp/student-dashboard.fxml")) {
            StudentDashboard controller = loader.getController();
            String studentId = usernameField.getText();
            System.out.println("Setting studentId in StudentDashboard: " + studentId);
            controller.setStudentId(studentId);
        }
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private boolean redirectToAdminDash(User user) {
        return user.getRole().equalsIgnoreCase("admin");
    }

    private boolean redirectToFacultyDash(User user) {
        return user.getRole().equalsIgnoreCase("faculty");
    }

    private boolean redirectToStudentDash(User user) {
        return user.getRole().equalsIgnoreCase("student");
    }
}