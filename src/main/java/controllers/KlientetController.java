package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import services.KlientetService;

import java.util.List;

public class KlientetController {
    @FXML
    private TextField txtEmri;
    @FXML
    private TextField txtMbiemri;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNrTelefonit;
    @FXML
    private TextField txtAdresa;
    @FXML
    private ListView<String> txtKlientetList;

    @FXML
    private Label messageLabel;
    private KlientetService klientetService;
    public KlientetController(){
        this.klientetService=new KlientetService();
    }
    @FXML
    public void initialize() {
        loadKlientet();
    }

    private void loadKlientet() {
        txtKlientetList.getItems().clear();
        List<Klientet> klientet = klientetService.getAll();
        for (Klientet k : klientet) {
            txtKlientetList.getItems().add(k.getKid() + " - " + k.getEmri() + " " + k.getMbiemri());
        }
    }

    @FXML
    private void handleCreate(MouseEvent event) {
        try {
            CreateKlientetDto dto = new CreateKlientetDto(
                    txtEmri.getText(),
                    txtMbiemri.getText(),
                    txtEmail.getText(),
                    txtNrTelefonit.getText(),
                    txtAdresa.getText()
            );
            klientetService.create(dto);
            messageLabel.setText("Klienti u shtua me sukses.");
            loadKlientet();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(MouseEvent event) {
        try {
            int selectedId = getSelectedKlientId();
            if (selectedId == -1) return;

            UpdateKlientiDto dto = new UpdateKlientiDto();
            dto.setId(selectedId);
            dto.setEmail(txtEmail.getText());
            dto.setNrtelefonit(txtNrTelefonit.getText());
            dto.setAdresa(txtAdresa.getText());

            klientetService.update(dto);
            messageLabel.setText("Klienti u përditësua me sukses.");
            loadKlientet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(MouseEvent event) {
        try {
            int selectedId = getSelectedKlientId();
            if (selectedId == -1) return;

            klientetService.delete(selectedId);
            messageLabel.setText("Klienti u fshi me sukses.");
            loadKlientet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private int getSelectedKlientId() {
        String selected = txtKlientetList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një klient në listë.");
            return -1;
        }
        return Integer.parseInt(selected.split(" - ")[0]);
    }

    private void clearForm() {
        txtEmri.clear();
        txtMbiemri.clear();
        txtAdresa.clear();
        txtNrTelefonit.clear();
        txtAdresa.clear();
    }
}

