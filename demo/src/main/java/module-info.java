module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens Code.backEnd to javafx.fxml;
    exports Code.backEnd;

    opens com.example to javafx.fxml;
    exports com.example;
}
