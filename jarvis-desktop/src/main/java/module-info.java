module com.gafi.jarvis {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gafi.jarvis to javafx.fxml;
    exports com.gafi.jarvis;
}