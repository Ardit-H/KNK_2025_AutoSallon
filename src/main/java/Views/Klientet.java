package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Klientet extends Application {
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader(
                Klientet.class.getResource("/Views/klientet.fxml")
        );
        Scene scene=new Scene(fxmlLoader.load(),700,500);
        stage.setScene(scene);
        stage.show();
    }
}
