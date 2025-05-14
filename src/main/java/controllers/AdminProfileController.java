package controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;
import models.dto.Shitjet.Shitjet;
import repository.KlientetRepository;
import repository.PerdoruesitRepository;
import repository.ShitjetRepository;
import repository.VeturatRepository;
import services.PerdoruesitService;
import services.SceneManager;
import services.SessionManager;
import utils.SceneLocator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class AdminProfileController {
    @FXML
    private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtNrTelefonit;
    @FXML private TextField txtAdresa;
    @FXML private ImageView imgProfilePhoto;
    @FXML private Button buttonEdit;
    @FXML private Button buttonSave;
    @FXML public Label totalKlientetLabel;
    @FXML public Label totalPerdoruesitLabel;
    @FXML public Label totalVeturatLabel;
    @FXML public BarChart<String, Number> shitjetChart;
    private  PerdoruesitService perdoruesitService;
    private Perdoruesit currentUser;
    private KlientetRepository klientetRepository;
    private VeturatRepository veturatRepository;
    private ShitjetRepository shitjetRepository;
    private PerdoruesitRepository perdoruesitRepository;
    public AdminProfileController(){
        this.perdoruesitService = new PerdoruesitService();
        this.klientetRepository=new KlientetRepository();
        this.shitjetRepository=new ShitjetRepository();
        this.veturatRepository=new VeturatRepository();
        this.perdoruesitRepository=new PerdoruesitRepository();
    }
    @FXML public void initialize(){
        currentUser = SessionManager.getInstance().getcurrentUser();
        if (currentUser != null){
            loadUserProfile();
            setEditable(false);
        }
        try {
            totalKlientetLabel.setText(klientetRepository.getTotalKlientet() + "\n Klientë");
            totalPerdoruesitLabel.setText(perdoruesitRepository.getTotalPerdoruesit() + "\n Përdorues");
            totalVeturatLabel.setText(veturatRepository.getTotalVeturat() + "\n Vetura");
            Map<String, Double> shitjet = shitjetRepository.getShitjetMujore(LocalDate.now().getYear());
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Shitjet " + LocalDate.now().getYear());
            for(Map.Entry<String, Double> entry : shitjet.entrySet()){
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
            shitjetChart.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
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
    @FXML private void handleEdit(){
        setEditable(true);
    }
    @FXML private void handleSave(){
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
    @FXML private void handleChangePassword()throws Exception{
        SceneManager.getInstance().setCenterPanePath(SceneLocator.CHANGE_PASSWORD);
        SceneManager.reload();
    }
}
