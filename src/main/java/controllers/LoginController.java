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
                SceneManager.load("/Views/admin_dashboard.fxml");
            } else if (roli.equals("user")) {
                SceneManager.load("/Views/user_dashboard.fxml");

            } else {
                throw new IllegalStateException("Roli i panjohur: " + roli);
            }

        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }
}
