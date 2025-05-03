package App;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.SceneManager;

public class Main extends Application {
    public static Scene scene;
    public void start(Stage stage) throws Exception{
        SceneManager sceneManager = SceneManager.getInstance();
        stage.setScene(sceneManager.getScene());
        stage.show();
    }
}
