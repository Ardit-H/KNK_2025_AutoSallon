package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import models.dto.Perdoruesit.CreatePerdoruesitDto;
import models.dto.Perdoruesit.Perdoruesit;
import repository.KlientetRepository;
import services.KlientetService;
import services.PerdoruesitService;
import services.SceneManager;
import services.SessionManager;
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
    @FXML private Button btnContinue;
    private Perdoruesit perdoruesiIRegjistruar = null;
    private PerdoruesitService perdoruesitService;
    private KlientetService klientetService;
    private KlientetRepository klientetRepository;
    public SignUpController(){
        this.perdoruesitService=new PerdoruesitService();
        this.klientetService=new KlientetService();
        this.klientetRepository=new KlientetRepository();
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
           perdoruesiIRegjistruar = perdoruesitService.create(dto);
           if(perdoruesiIRegjistruar!=null) {
               errorMessageLabel.setText("Perdoruesi u shtua me sukses!Kliko Continue per te vazhduar.");
               btnContinue.setVisible(true);
               clearForm();
           }else{
               errorMessageLabel.setText("Gabim gjatë shtimit të përdoruesit.");
           }
        } catch (Exception e) {
            errorMessageLabel.setText("Gabim: " + e.getMessage());
        }
    }
    @FXML
    private void handleContinue() {
        try {
            if (perdoruesiIRegjistruar==null){
                errorMessageLabel.setText("Nuk ka përdorues të regjistruar.");
                return;
            }
            Klientet ekzistues=klientetService.findByAllFields(
                    perdoruesiIRegjistruar.getEmri(),
                    perdoruesiIRegjistruar.getMbiemri(),
                    perdoruesiIRegjistruar.getEmail(),
                    perdoruesiIRegjistruar.getNrtelefonit(),
                    perdoruesiIRegjistruar.getAdresa()
            );
            if(ekzistues!=null){
                UpdateKlientiDto update=new UpdateKlientiDto();
                update.setId(ekzistues.getKid());
                update.setPerdoruesiId(perdoruesiIRegjistruar.getPid());
                klientetRepository.update(update);
                errorMessageLabel.setText("Lidhja me klientin ekzistues u bë me sukses!");
            }else {
                CreateKlientetDto klientDto = new CreateKlientetDto(
                        perdoruesiIRegjistruar.getEmri(),
                        perdoruesiIRegjistruar.getMbiemri(),
                        perdoruesiIRegjistruar.getEmail(),
                        perdoruesiIRegjistruar.getNrtelefonit(),
                        perdoruesiIRegjistruar.getAdresa(),
                        perdoruesiIRegjistruar.getPid()
                );

                klientetService.create(klientDto);
                errorMessageLabel.setText("Klienti u shtua me sukses!");
            }
            SceneManager.load(SceneLocator.USER_DASHBOARD_HOME);
            SessionManager.getInstance().loginUser(perdoruesiIRegjistruar);
            btnContinue.setVisible(false);
            clearForm();
            perdoruesiIRegjistruar = null;
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
