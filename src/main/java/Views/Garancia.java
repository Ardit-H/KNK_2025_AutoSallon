package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;

public class Garancia {
    public static Scene scene;
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader(
                getClass().getResource(SceneLocator.GARANCIA)
        );
        LanguageManager languageManager=LanguageManager.getInstance();
        fxmlLoader.setResources(languageManager.getResourceBundle());
        Parent parent=fxmlLoader.load();
        scene=new Scene(parent);
        SceneManager.initialize(scene,SceneLocator.GARANCIA);
        stage.setScene(scene);
        stage.show();
    }
}
