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
import services.SessionManager;
import utils.SceneLocator;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Locale;

public class Admin_DashboardController {
    public Button btn_dashboard;
    public Button btn_klientet;
    public Button btn_veturat;
    public Button btn_sherbimet;
    public Button btn_rezervimet;
    public Button btn_testdrives;
    public Button btn_vleresimet;
    public Button btn_faturat;
    public Button btn_statistikat;
    public Button btn_perdoruesit;
    public Button btn_garancia;
    public Button btn_profili;
    @FXML private AnchorPane centerPane;
    @FXML private VBox sideMenu;
    @FXML private Button btnLogOut;
    @FXML private Button languageToggleButton;
    private boolean isEnglish = true;
    private LanguageManager languageManager;
    public Admin_DashboardController(){
        this.languageManager=LanguageManager.getInstance();
    }
    @FXML public void initialize() {
        if (languageManager.getLocale().equals(new Locale("en"))) {
            setLanguageIcon("/Images/language-en.png");
            isEnglish = true;
        } else {
            setLanguageIcon("/Images/language-sq.png");
            isEnglish = false;
        }
    }
   @FXML private void handleLoadKlientet()throws Exception{
       SceneManager.getInstance().setCenterPanePath(SceneLocator.KLIENTET);
       SceneManager.load(SceneLocator.KLIENTET, centerPane);
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
    @FXML private void handleLoadFaturat()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.FATURAT);
        SceneManager.load(SceneLocator.FATURAT, centerPane);
    }
    @FXML private void handleLoadDashboard_Home()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.ADMIN_DASHBOARD_HOME);
        SceneManager.load(SceneLocator.ADMIN_DASHBOARD_HOME,centerPane);
    }
    @FXML private void handleLoadAdminProfile()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.ADMIN_PROFILE);
        SceneManager.load(SceneLocator.ADMIN_PROFILE,centerPane);
    }
    @FXML private void handleLoadtestDrives()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.TESTDRIVES);
        SceneManager.load(SceneLocator.TESTDRIVES,centerPane);
    }
    @FXML private void handleLoadGarancia()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.GARANCIA);
        SceneManager.load(SceneLocator.GARANCIA,centerPane);
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
