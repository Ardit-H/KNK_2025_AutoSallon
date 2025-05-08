package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.dto.Perdoruesit.Perdoruesit;
import services.LoginService;
import services.SceneManager;
import services.SessionManager;
import utils.SceneLocator;


public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    private LoginService loginService ;
    private SessionManager sessionManager;

    public LoginController(){
        this.loginService=new LoginService();
        this.sessionManager=SessionManager.getInstance();
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            Perdoruesit loggedUser = loginService.login(email, password);
            sessionManager.loginUser(loggedUser);

            String roli = loggedUser.getRoli().toLowerCase();

            if (roli.equals("admin")) {
                SceneManager.load(SceneLocator.ADMIN_DASHBOARD_HOME);
            } else if (roli.equals("user")) {
                SceneManager.load(SceneLocator.USER_DASHBOARD_HOME);

            } else {
                throw new IllegalStateException("Roli i panjohur: " + roli);
            }

        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }
    @FXML private void  handleLoadSignUp(){

    }
}
