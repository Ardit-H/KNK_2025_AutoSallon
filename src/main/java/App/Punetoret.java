package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Punetoret extends Application{
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(
                Punetoret.class.getResource("punetoret.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setScene(scene);
        stage.show();
    }
}
