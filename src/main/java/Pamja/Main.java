package Pamja;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader(
                Main.class.getResource("klientet.fxml")
        );
        Scene scene=new Scene(fxmlLoader.load(),500,500);
        stage.setScene(scene);
        stage.show();
    }
}
