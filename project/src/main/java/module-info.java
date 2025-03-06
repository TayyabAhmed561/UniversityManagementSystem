module com.universitymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    // Open all packages to FXML
    opens com.universitymanagement to javafx.fxml;
    opens com.universitymanagement.auth to javafx.fxml;
    opens com.universitymanagement.auth.AuthenticationManager to javafx.fxml;

    opens com.universitymanagement.controller to javafx.fxml;

    opens com.universitymanagement.controller.CourseController to javafx.fxml;
    opens com.universitymanagement.controller.EventController to javafx.fxml;
    opens com.universitymanagement.controller.FacultyController to javafx.fxml;
    opens com.universitymanagement.controller.StudentController to javafx.fxml;
    opens com.universitymanagement.controller.SubjectController to javafx.fxml;

    // Export all packages for application use
    exports com.universitymanagement;
    exports com.universitymanagement.auth;
    exports com.universitymanagement.auth.AuthenticationManager;

    exports com.universitymanagement.controller;
    exports com.universitymanagement.controller.CourseController;
    exports com.universitymanagement.controller.EventController;
    exports com.universitymanagement.controller.FacultyController;
    exports com.universitymanagement.controller.StudentController;
    exports com.universitymanagement.controller.SubjectController;
}
