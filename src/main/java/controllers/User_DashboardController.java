package controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import services.LanguageManager;
import services.SceneManager;
import services.SessionManager;
import utils.SceneLocator;

import java.util.Locale;

public class User_DashboardController {
    @FXML private AnchorPane centerPane;
    @FXML private VBox sideMenu;
    @FXML private Button btn_vleresimetemia;
    @FXML private Button btn_vleresimet;
    @FXML private Button languageToggleButton;
    private boolean isEnglish = true;
    private LanguageManager languageManager;
    public User_DashboardController(){
        this.languageManager=LanguageManager.getInstance();
    }
    @FXML public void initialize() {
        if(!SessionManager.getInstance().isLoggedIn()){
            btn_vleresimetemia.setVisible(false);
        }else {
            btn_vleresimetemia.setVisible(true);
        }
        if (languageManager.getLocale().equals(new Locale("en"))) {
            setLanguageIcon("/Images/language-en.png");
            isEnglish = true;
        } else {
            setLanguageIcon("/Images/language-sq.png");
            isEnglish = false;
        }
    }

    @FXML private void handleLoadDashboard_Home()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.DASHBOARD_HOME);
        SceneManager.load(SceneLocator.DASHBOARD_HOME,centerPane);
    }
    @FXML private void handleLoadUserVleresimet()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.LOGEDUSER_VLERESIMET);
        SceneManager.load(SceneLocator.LOGEDUSER_VLERESIMET,centerPane);
    }
    @FXML private void handleLoadVleresimet()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.USER_VLERESIMET);
        SceneManager.load(SceneLocator.USER_VLERESIMET,centerPane);
    }
    @FXML private void handleLoadUserSherbimet()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.USER_SHERBIMET);
        SceneManager.load(SceneLocator.USER_SHERBIMET,centerPane);
    }
    @FXML private void handleLoadUserProfili()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.USER_PROFILE);
        SceneManager.load(SceneLocator.USER_PROFILE,centerPane);
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
        SessionManager.getInstance().logout();
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
