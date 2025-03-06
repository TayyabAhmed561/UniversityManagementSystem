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

    // Open packages to FXML for reflection (necessary for FXMLLoader to access controllers)
    opens com.universitymanagement to javafx.fxml;
    opens com.universitymanagement.auth to javafx.fxml;
    opens com.universitymanagement.auth.AuthenticationManager to javafx.fxml;

    opens com.universitymanagement.controller to javafx.fxml;
    opens com.universitymanagement.controller.AdminController to javafx.fxml;
    opens com.universitymanagement.controller.CourseController to javafx.fxml;
    opens com.universitymanagement.controller.EventController to javafx.fxml;
    opens com.universitymanagement.controller.FacultyController to javafx.fxml;
    opens com.universitymanagement.controller.StudentController to javafx.fxml;
    opens com.universitymanagement.controller.SubjectController to javafx.fxml;

    opens com.universitymanagement.model to javafx.base;
    opens com.universitymanagement.dao to javafx.fxml;

    // Add new FXML views related to subjects
    opens com.universitymanagement.roleViews to javafx.fxml;

    // Export packages for external access
    exports com.universitymanagement;
    exports com.universitymanagement.auth;
    exports com.universitymanagement.auth.AuthenticationManager;

    exports com.universitymanagement.controller;
    exports com.universitymanagement.controller.AdminController;
    exports com.universitymanagement.controller.CourseController;
    exports com.universitymanagement.controller.EventController;
    exports com.universitymanagement.controller.FacultyController;
    exports com.universitymanagement.controller.StudentController;
    exports com.universitymanagement.controller.SubjectController;

    exports com.universitymanagement.model;
    exports com.universitymanagement.dao;
}
