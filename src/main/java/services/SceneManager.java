package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SceneManager {
    private static SceneManager sceneManager;
    private Scene scene;

    private SceneManager(Scene scene){
        this.scene=scene;
    }
    public static void initialize(Scene scene){
        sceneManager=new SceneManager(scene);
    }

    public static void load(String scenePath) throws Exception{
        if(sceneManager==null){
            throw new Exception("Scene manager is not initialized yet");
        }
        sceneManager.loadScene(scenePath);
    }

    public static void load(String scenePath, Pane pane) throws Exception{
        if(sceneManager==null){
            throw new Exception("Scene manager is not initialize yet!");
        }
        sceneManager.loadScene(scenePath,pane);
    }

    private void loadScene(String scenePath) throws Exception{
        Parent parent=getParent(scenePath);
        this.scene.setRoot(parent);
    }

    private void loadScene(String scenePath,Pane pane) throws Exception{
        pane.getChildren().clear();
        Parent parent=getParent(scenePath);
        pane.getChildren().add(parent);
    }
    private Parent getParent(String path)throws Exception{
        FXMLLoader loader=new FXMLLoader(
                this.getClass().getResource(path)
        );
        return loader.load();
    }

}
