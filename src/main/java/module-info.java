module com.example.knk_2025_autosallon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;


    opens com.example.knk_2025_autosallon to javafx.fxml;
    exports com.example.knk_2025_autosallon;

    opens models.dto.Porosite to javafx.base;
    exports models.dto.Porosite;
    opens models.dto.Ofertat to javafx.base;
    exports models.dto.Ofertat;

    opens models.dto.Rezervimet to javafx.base;
    exports models.dto.Rezervimet;

    opens  controllers to javafx.fxml;
    exports  controllers;
    opens App to javafx.fxml;
    exports App;

}