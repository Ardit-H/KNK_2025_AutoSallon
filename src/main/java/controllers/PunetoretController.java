package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.dto.Punetoret.CreatePunetoretDto;
import models.dto.Punetoret.UpdatePunetoretDto;
import models.dto.Punetoret.Punetoret;
import services.LanguageManager;
import services.PerdoruesitService;
import services.PunetoretService;

import java.util.List;

public class PunetoretController {
    @FXML
    private TextField txtEmri;
    @FXML
    private TextField txtMbiemri;
    @FXML
    private TextField txtPozita;
    @FXML
    private TextField txtTelefoni;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPaga;
    @FXML
    private TextField txtDataPunesimit;

    @FXML
    private ListView<String> txtPunetoretList;

    @FXML
    private Label messageLabel;

    private final PunetoretService punetoretService;
    private LanguageManager languageManager;
    public PunetoretController(){
        this.punetoretService=new PunetoretService();
        this.languageManager= LanguageManager.getInstance();
    }



    @FXML
    public void initialize(){
        loadPunetoret();
    }

    private void loadPunetoret(){
        txtPunetoretList.getItems().clear();
        List<Punetoret> punetoret = punetoretService.getAll();
        for(Punetoret p : punetoret){
            txtPunetoretList.getItems().add(p.getPunetor_id() + "-" + p.getEmri() + " " + p.getMbiemri());
        }
    }


    @FXML
    private void handleCreate(MouseEvent event){
        try{
            CreatePunetoretDto dto = new CreatePunetoretDto(txtEmri.getText(),
                    txtMbiemri.getText(),
                    txtPozita.getText(),
                    txtTelefoni.getText(),
                    txtEmail.getText(),
                    Double.parseDouble(txtPaga.getText()),
                    txtDataPunesimit.getText());

            punetoretService.create(dto);
            messageLabel.setText("Punetori u shtua me sukses!");
            loadPunetoret();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }
    @FXML
    private void handleUpdate(MouseEvent event){
        try {
            int selectedId = getSelectedPunetorId();
            if (selectedId == -1) return;

            UpdatePunetoretDto dto = new UpdatePunetoretDto();
            dto.setId(selectedId);
            if (!txtEmail.getText().trim().isEmpty())
                dto.setEmail(txtEmail.getText().trim());
            if (!txtPozita.getText().trim().isEmpty())
                dto.setPozita(txtPozita.getText().trim());
            if (!txtPaga.getText().trim().isEmpty())
                dto.setPaga(Double.parseDouble(txtPaga.getText().trim()));

            punetoretService.update(dto);
            messageLabel.setText("Punëtori u përditësua me sukses.");
            loadPunetoret();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(MouseEvent event){
        try{
            int selectedId = getSelectedPunetorId();
            if(selectedId == -1) return;

            punetoretService.delete(selectedId);
            messageLabel.setText("Punetori u fshi me sukses!");
            loadPunetoret();
        }catch (Exception e){
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private int getSelectedPunetorId() {
        String selected = txtPunetoretList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një punëtor në listë.");
            return -1;
        }
        return Integer.parseInt(selected.split(" - ")[0]);
    }

    private void clearForm() {
        txtEmri.clear();
        txtMbiemri.clear();
        txtPozita.clear();
        txtTelefoni.clear();
        txtEmail.clear();
        txtPaga.clear();
        txtDataPunesimit.clear();
    }

}
