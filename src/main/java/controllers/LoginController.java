package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.dto.Perdoruesit.Perdoruesit;
import services.LanguageManager;
import services.LoginService;
import services.SceneManager;
import services.SessionManager;
import utils.SceneLocator;

import java.util.Locale;


public class LoginController {

    @FXML private AnchorPane anchorPane;
    @FXML private ImageView imgLlogoAutoSallon;
    @FXML private ImageView loginLogo;
    @FXML private ImageView usernameLogo;
    @FXML private ImageView passwordLogo;
    @FXML private ImageView eyeIcon;
    @FXML private Button loginBtn;

    @FXML private TextField userEmail;
    @FXML private PasswordField userPassword;
    @FXML private CheckBox showHidePasswordCheckbox;
    @FXML private TextField userPasswordVisible;
    @FXML private Label errorMessageLabel;
    @FXML private Button languageToggleButton;
    private boolean isEnglish = true;
    private LoginService loginService ;
    private SessionManager sessionManager;
    private LanguageManager languageManager;

    public LoginController(){
        this.loginService=new LoginService();
        this.sessionManager=SessionManager.getInstance();
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
        userPasswordVisible.setVisible(false);
        eyeIcon.setOnMouseClicked(event -> {
            showHidePasswordCheckbox.setSelected(!showHidePasswordCheckbox.isSelected());
            handleShowHidePassword();
        });
    }
    @FXML
    private void handleLogin() {
        String email = userEmail.getText();
        String password = userPassword.getText();

        try {
            Perdoruesit loggedUser = loginService.login(email, password);
            sessionManager.loginUser(loggedUser);

            String roli = loggedUser.getRoli().toLowerCase();

            if (roli.equals("admin")) {
                SceneManager.load(SceneLocator.ADMIN_DASHBOARD);
            } else if (roli.equals("user")) {
                SceneManager.load(SceneLocator.USER_DASHBOARD);

            } else {
                throw new IllegalStateException("Roli i panjohur: " + roli);
            }

        } catch (Exception e) {
            errorMessageLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML private void handleGuestLogin()throws Exception{
        SceneManager.load(SceneLocator.USER_DASHBOARD);
    }
    @FXML private void  handleLoadSignUp()throws Exception{
        SceneManager.load(SceneLocator.SIGNUP);
    }
    @FXML private void handleShowHidePassword(){
        if(showHidePasswordCheckbox.isSelected()){
            userPasswordVisible.setText(userPassword.getText());
            userPasswordVisible.setVisible(true);
            userPassword.setVisible(false);
        }else{
            userPassword.setText(userPasswordVisible.getText());
            userPassword.setVisible(true);
            userPasswordVisible.setVisible(false);
        }
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
