module com.example.universitymanagementapp {
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
    requires jdk.jfr;
    requires org.apache.poi.ooxml;

    opens com.example.universitymanagementapp to javafx.fxml;
    exports com.example.universitymanagementapp;
    exports com.example.universitymanagementapp.auth to javafx.fxml;
    opens com.example.universitymanagementapp.auth to javafx.fxml;
    exports com.example.universitymanagementapp.controller.FacultyController to javafx.fxml;
    opens com.example.universitymanagementapp.controller.FacultyController to javafx.fxml;
    exports com.example.universitymanagementapp.controller.StudentController to javafx.fxml;
    opens com.example.universitymanagementapp.controller.StudentController to javafx.fxml;
    exports com.example.universitymanagementapp.controller.CourseController to javafx.fxml;
    opens com.example.universitymanagementapp.controller.CourseController to javafx.fxml;
    exports com.example.universitymanagementapp.controller.SubjectController to javafx.fxml;
    opens com.example.universitymanagementapp.controller.SubjectController to javafx.fxml;
    exports com.example.universitymanagementapp.controller.EventController to javafx.fxml;
    opens com.example.universitymanagementapp.controller.EventController to javafx.fxml;
    exports com.example.universitymanagementapp.controller.AdminController to javafx.fxml;
    opens com.example.universitymanagementapp.controller.AdminController to javafx.fxml;

    opens com.example.universitymanagementapp.model to javafx.base;

}