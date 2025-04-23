module com.example.knk_2025_autosallon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens com.example.knk_2025_autosallon to javafx.fxml;
    exports com.example.knk_2025_autosallon;

    opens  controllers to javafx.fxml;
    exports  controllers;
    opens Pamja to javafx.fxml;
    exports Pamja;

}