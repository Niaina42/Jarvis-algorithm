module com.gafi.jarvis {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;


    opens com.gafi.jarvis to javafx.fxml;
    exports com.gafi.jarvis;
}