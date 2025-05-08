package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.dto.Perdoruesit.CreatePerdoruesitDto;
import services.PerdoruesitService;

public class SignUpController {
    @FXML private TextField txtName;
    @FXML private TextField txtEmail;
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
                    txtName.getText(),
                    txtEmail.getText(),
                    pwdPassword.getText()
            );
            perdoruesitService.create(dto); // Këtu brenda bëhet hashing dhe salt
            errorMessageLabel.setText("Perdoruesi u shtua me sukses!");
            clearForm();
        } catch (Exception e) {
            errorMessageLabel.setText("Gabim: " + e.getMessage());
        }
    }
    private void clearForm() {
        txtName.clear();
        txtEmail.clear();
        pwdPassword.clear();
    }
}
