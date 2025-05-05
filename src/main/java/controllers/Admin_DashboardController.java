package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import services.SceneManager;
import utils.SceneLocator;

public class Admin_DashboardController {
    @FXML private Button btn_klientet;
    @FXML private AnchorPane centerPane;

   @FXML private void handleLoadKlientet()throws Exception{
        SceneManager.load(SceneLocator.KLIENTET,centerPane);
    }
}
