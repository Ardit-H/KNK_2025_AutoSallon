package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ResourceBundle;

public class SceneManager {
    private static SceneManager sceneManager;
    private LanguageManager languageManager;
    private Scene scene;
    private String currentScenePath;
    private SceneManager(Scene scene,String path){
        this.scene=scene;
        this.languageManager=LanguageManager.getInstance();
        this.currentScenePath=path;
    }
    public static void initialize(Scene scene,String path){
        sceneManager=new SceneManager(scene,path);
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
        this.currentScenePath=scenePath;
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
        ResourceBundle resourceBundle=this.languageManager.getResourceBundle();
        loader.setResources(resourceBundle);
        return loader.load();
    }
    public static void reloadScene()throws Exception{
        load(SceneManager.sceneManager.currentScenePath);
    }

}
