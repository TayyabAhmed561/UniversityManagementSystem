module com.example.universitymanagementapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics; // Added for Application, Stage, Scene, etc.

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.universitymanagementapp to javafx.graphics; // For launching Application
    opens com.example.universitymanagementapp.ui.CourseManagementUI to javafx.fxml; // For FXML loading
}