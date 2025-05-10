package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.dto.Perdoruesit.CreatePerdoruesitDto;
import services.PerdoruesitService;
import services.SceneManager;
import utils.SceneLocator;

public class SignUpController {
    @FXML private TextField txtFistName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private TextField txtAddress;
    @FXML private TextField pwdPassword;
    @FXML private Label errorMessageLabel;
    @FXML private ImageView imgIcon;

    private PerdoruesitService perdoruesitService;
    public SignUpController(){
        this.perdoruesitService=new PerdoruesitService();
    }
    @FXML
    private void handleSignUp() {
        try {
            CreatePerdoruesitDto dto = new CreatePerdoruesitDto(
                    txtFistName.getText(),
                    txtLastName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    pwdPassword.getText()
            );
            perdoruesitService.create(dto); // Këtu brenda bëhet hashing dhe salt
            errorMessageLabel.setText("Perdoruesi u shtua me sukses!");
            clearForm();
        } catch (Exception e) {
            errorMessageLabel.setText("Gabim: " + e.getMessage());
        }
    }
    @FXML private void handleGoToOverallDashboard()throws Exception{
        SceneManager.load(SceneLocator.OVERALL_DASHBOARD);
    }
    private void clearForm() {
        txtFistName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtPhone.clear();
        txtEmail.clear();
        pwdPassword.clear();
    }
}
