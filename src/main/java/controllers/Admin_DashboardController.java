package controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import services.SceneManager;
import utils.SceneLocator;

public class Admin_DashboardController {
    @FXML private Button btn_klientet;
    @FXML private AnchorPane centerPane;
    @FXML private VBox sideMenu;
    @FXML private Button menuToggleButton;
    private boolean menuVisible = false;

    @FXML public void initialize() {
        sideMenu.setTranslateX(-200);
        sideMenu.setVisible(false);
        sideMenu.setManaged(false);
        menuToggleButton.setOnAction(event -> toggleMenu());
    }

    private void toggleMenu() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), sideMenu);
        if (!menuVisible) {
            sideMenu.setVisible(true);
            sideMenu.setManaged(true);
            transition.setFromX(-200);
            transition.setToX(0);
        } else {
            transition.setFromX(0);
            transition.setToX(-200);
            transition.setOnFinished(event -> {
                sideMenu.setVisible(false);
                sideMenu.setManaged(false);
            });
        }
        transition.play();
        menuVisible = !menuVisible;
    }
   @FXML private void handleLoadKlientet()throws Exception{
        SceneManager.load(SceneLocator.KLIENTET,centerPane);
    }
    @FXML private void handleLoadDashboard_Home()throws Exception{
        SceneManager.load(SceneLocator.DASHBOARD_HOME,centerPane);
    }
}
