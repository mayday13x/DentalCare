module com.example.dentalcare {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.dentalcare to javafx.fxml;
    exports com.example.dentalcare;
    exports com.example.dentalcare.controllers;
    opens com.example.dentalcare.controllers to javafx.fxml;
}