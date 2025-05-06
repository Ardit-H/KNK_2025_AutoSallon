package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Rezervimet extends Application {
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(
                Rezervimet.class.getResource("rezervimet.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setScene(scene);
        stage.show();
    }
}
