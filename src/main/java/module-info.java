module com.example.knk_2025_autosallon {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.knk_2025_autosallon to javafx.fxml;
    exports com.example.knk_2025_autosallon;
}