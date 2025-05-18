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


import java.awt.event.ActionEvent;
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
        for(Punetoret p : punetoret) {
            txtPunetoretList.getItems().add(p.getPunetor_id() + "-" + p.getEmri() + " " + p.getMbiemri());
        }}
    @FXML
    private void handleSelect() {
        String selected = txtPunetoretList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }

        int id = Integer.parseInt(selected.split("-")[0].trim());

        try {
            Punetoret p = punetoretService.getById(id);
            if (p != null) {
                txtEmri.setText(p.getEmri());
                txtMbiemri.setText(p.getMbiemri());
                txtPozita.setText(p.getPozita());
                txtTelefoni.setText(p.getTelefoni());
                txtEmail.setText(p.getEmail());
                txtPaga.setText(String.valueOf(p.getPaga()));
                txtDataPunesimit.setText(p.getData_punesimit().toString());
            }
        } catch (Exception e) {
            messageLabel.setText("Gabim gjatë përzgjedhjes së punëtorit: " + e.getMessage());
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
    private void handleUpdate(MouseEvent event) {
        try {
            int selectedId = getSelectedPunetorId();
            if (selectedId == -1) return;

            UpdatePunetoretDto dto = new UpdatePunetoretDto();
            dto.setId(selectedId);

            // Vetëm fusha që nuk janë bosh i vendosim në DTO, në mënyrë që të përditësohen
            if (!txtEmri.getText().trim().isEmpty())
                dto.setEmri(txtEmri.getText().trim());

            if (!txtMbiemri.getText().trim().isEmpty())
                dto.setMbiemri(txtMbiemri.getText().trim());

            if (!txtEmail.getText().trim().isEmpty())
                dto.setEmail(txtEmail.getText().trim());

            if (!txtPozita.getText().trim().isEmpty())
                dto.setPozita(txtPozita.getText().trim());

            if (!txtPaga.getText().trim().isEmpty())
                dto.setPaga(Double.parseDouble(txtPaga.getText().trim()));

            Punetoret updated = punetoretService.update(dto);
            if (updated != null) {
                messageLabel.setText("Punëtori u përditësua me sukses.");
                loadPunetoret();
                clearForm();
            } else {
                messageLabel.setText("Gabim gjatë përditësimit të punëtorit.");
            }
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }


    @FXML
    private void handleDelete(MouseEvent event) {
        String selected = txtPunetoretList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjedh një punëtor për ta fshirë.");
            return;
        }

        int id = Integer.parseInt(selected.split("-")[0].trim());

        try {
            boolean success = punetoretService.delete(id);

            if (success) {
                messageLabel.setText("Punëtori u fshi me sukses.");
                clearForm();
                loadPunetoret();
            } else {
                messageLabel.setText("Fshirja dështoi. Ky punetor lidhet me shitjet!!");
            }
        } catch (Exception e) {
            messageLabel.setText("Gabim gjatë fshirjes: " + e.getMessage());

        }
    }


    private int getSelectedPunetorId() {
        String selected = txtPunetoretList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një punëtor në listë.");
            return -1;
        }
        return Integer.parseInt(selected.split("-")[0]);
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
