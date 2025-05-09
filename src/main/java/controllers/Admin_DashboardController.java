package controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Locale;

public class Admin_DashboardController {
    @FXML private AnchorPane centerPane;
    @FXML private VBox sideMenu;
    @FXML private Button menuToggleButton;
    @FXML private Button btnLogOut;
    private boolean menuVisible = false;
    @FXML private Button languageToggleButton;
    private boolean isEnglish = true;
    private LanguageManager languageManager;
    public Admin_DashboardController(){
        this.languageManager=LanguageManager.getInstance();
    }
    @FXML public void initialize() {
        sideMenu.setTranslateX(-200);
        sideMenu.setVisible(false);
        sideMenu.setManaged(false);
        menuToggleButton.setOnAction(event -> toggleMenu());
        if (languageManager.getLocale().equals(new Locale("en"))) {
            setLanguageIcon("/Images/language-en.png");
            isEnglish = true;
        } else {
            setLanguageIcon("/Images/language-sq.png");
            isEnglish = false;
        }
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
        menuVisible =!menuVisible;
    }
   @FXML private void handleLoadKlientet()throws Exception{
       SceneManager.getInstance().setCenterPanePath(SceneLocator.KLIENTET);
       SceneManager.load(SceneLocator.KLIENTET, centerPane);
   }
    @FXML private void handleLoadServices()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.SHERBIMET);
        SceneManager.load(SceneLocator.SHERBIMET, centerPane);
    }

    @FXML
    private void handleLoadVeturat() throws Exception {
        SceneManager.getInstance().setCenterPanePath(SceneLocator.VETURAT);
        SceneManager.load(SceneLocator.VETURAT, centerPane);
    }
    @FXML private void handleLoadSherbimet()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.SHERBIMET);
        SceneManager.load(SceneLocator.SHERBIMET, centerPane);
    }
    @FXML private void handleLoadVleresimet()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.VLERESIMET);
        SceneManager.load(SceneLocator.VLERESIMET,centerPane);
    }
    @FXML private void handleLoadDashboard_Home()throws Exception{
        SceneManager.load(SceneLocator.DASHBOARD_HOME,centerPane);
    }

@FXML private void handleLanguageToggle() throws Exception{
    if (isEnglish) {
        loadLanguage(new Locale("sq"));
        setLanguageIcon("/Images/language-sq.png");
    } else {
        loadLanguage(Locale.ENGLISH);
        setLanguageIcon("/Images/language-en.png");
    }
    isEnglish =!isEnglish;
}
    @FXML private void handleLogOut()throws Exception{
        SceneManager.load(SceneLocator.OVERALL_DASHBOARD);
    }
    private void setLanguageIcon(String imagePath) {
        ImageView imageView = new ImageView(new javafx.scene.image.Image(getClass().getResourceAsStream(imagePath)));
        imageView.setFitHeight(20);
        imageView.setFitWidth(30);
        languageToggleButton.setGraphic(imageView);
    }
    private void loadLanguage(Locale locale) throws Exception{
        languageManager.setLocale(locale);
        SceneManager.reload();
    }


}
