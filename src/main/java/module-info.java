module com.mycompany.np {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.base;

    opens com.mycompany.np to javafx.fxml;
    exports com.mycompany.np;
    exports controller; // Add this line to export the controller package
    opens controller to javafx.fxml;
}
