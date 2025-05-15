package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;
import services.LanguageManager;
import services.SceneManager;
import services.SessionManager;
import services.PerdoruesitService;
import utils.SceneLocator;

public class UserProfileController {
    @FXML private AnchorPane anchorPane;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtNrTelefonit;
    @FXML private TextField txtAdresa;
    @FXML private ImageView imgProfilePhoto;
    @FXML private Button buttonEdit;
    @FXML private Button buttonSave;
    private LanguageManager languageManager;
    private Perdoruesit currentUser;
    private PerdoruesitService perdoruesitService;

    public UserProfileController(){
        this.perdoruesitService=new PerdoruesitService();
        this.languageManager=LanguageManager.getInstance();
    }

    @FXML
    public void initialize(){
        currentUser = SessionManager.getInstance().getcurrentUser();
        if (currentUser != null){
            loadUserProfile();
            setEditable(false);
        }
    }
    private void loadUserProfile(){
        txtFirstName.setText(currentUser.getEmri());
        txtLastName.setText(currentUser.getMbiemri());
        txtEmail.setText(currentUser.getEmail());
        txtNrTelefonit.setText(currentUser.getNrtelefonit());
        txtAdresa.setText(currentUser.getAdresa());
    }

    private void setEditable(boolean editable){
        txtFirstName.setEditable(false); // nuk lejohet ndryshimi
        txtLastName.setEditable(false);  // nuk lejohet ndryshimi
        txtEmail.setEditable(editable);
        txtNrTelefonit.setEditable(editable);
        txtAdresa.setEditable(editable);
        buttonSave.setDisable(!editable);
    }

    @FXML
    private void handleEdit(){
        setEditable(true);
    }

    @FXML
    private void handleSave(){
        if (currentUser != null){
            UpdatePerdoruesitDto dto=new UpdatePerdoruesitDto();
            dto.setId(currentUser.getPid());
            dto.setEmail(txtEmail.getText());
            dto.setNrtelefonit(txtNrTelefonit.getText());
            dto.setAdresa(txtAdresa.getText());
            dto.setRoli(currentUser.getRoli());

            try{
                perdoruesitService.update(dto);
                // Përditësojmë userin e ruajtur në SessionManager
                currentUser = new Perdoruesit(
                        currentUser.getPid(),
                        currentUser.getEmri(),
                        currentUser.getMbiemri(),
                        dto.getEmail(),
                        dto.getNrtelefonit(),
                        dto.getAdresa(),
                        currentUser.getDataRegjistrimit(),
                        currentUser.getRoli(),
                        currentUser.getPasswordHash(),
                        currentUser.getSalt()
                );
                SessionManager.getInstance().loginUser(currentUser);
                setEditable(false);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleChangePassword()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.CHANGE_PASSWORD);
        SceneManager.reload();
    }
}
