package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;
import services.LanguageManager;
import services.PerdoruesitService;
import services.SceneManager;
import services.SessionManager;
import utils.PasswordUtil;
import utils.SceneLocator;

public class ChangePasswordController{
    @FXML private PasswordField pwdCurrentPassword;
    @FXML private PasswordField pwdNewPassword;
    @FXML private PasswordField pwdConfirmPassword;
    private PerdoruesitService perdoruesitService;
    private LanguageManager languageManager;
    public ChangePasswordController(){
        this.perdoruesitService=new PerdoruesitService();
        languageManager=LanguageManager.getInstance();
    }

    @FXML private void handleChangePassword() {
        Perdoruesit currentUser=SessionManager.getInstance().getcurrentUser();
        String currentPassword = pwdCurrentPassword.getText();
        String newPassword = pwdNewPassword.getText();
        String confirmPassword = pwdConfirmPassword.getText();

        if (currentPassword.isEmpty()||newPassword.isEmpty()||confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Ju lutem plotesoni te gjitha fushat!");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Fjalëkalimi i ri nuk përputhet me konfirmimin.");
            return;
        }
        // Hashimi i fjalëkalimit aktual për verifikim
        String hashedInput = PasswordUtil.hashPassword(currentPassword, currentUser.getSalt());

        if (!hashedInput.equals(currentUser.getPasswordHash())) {
            showAlert(Alert.AlertType.ERROR, "Fjalëkalimi aktual është i pasaktë.");
            return;
        }
        // Gjenerimi i saltit të ri dhe hash për fjalëkalimin e ri
        String newSalt = PasswordUtil.generateSalt();
        String newPasswordHash = PasswordUtil.hashPassword(newPassword, newSalt);

        UpdatePerdoruesitDto dto = new UpdatePerdoruesitDto();
        dto.setId(currentUser.getPid());
        dto.setPasswordhash(newPasswordHash);
        dto.setSalt(newSalt);
        try {
            Perdoruesit updatedUser = perdoruesitService.update(dto);
            SessionManager.getInstance().loginUser(updatedUser);
            showAlert(Alert.AlertType.INFORMATION, "Fjalëkalimi u ndryshua me sukses.");
            clearPasswordFields();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Gabim: " + e.getMessage());
        }
    }
   @FXML private void handleCloseChangePassword()throws Exception{
       SceneManager.getInstance().setCenterPanePath(SceneLocator.USER_PROFILE);
       SceneManager.reload();
    }
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Ndryshimi i fjalëkalimit");
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void clearPasswordFields() {
        pwdCurrentPassword.clear();
        pwdNewPassword.clear();
        pwdConfirmPassword.clear();
    }
}