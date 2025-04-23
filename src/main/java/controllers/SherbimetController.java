package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import models.dto.Sherbimet.CreateSherbimetDto;
import models.dto.Sherbimet.Sherbimet;
import models.dto.Sherbimet.UpdateSherbimetDto;
import services.SherbimetService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SherbimetController {
    @FXML
    private TextField txtEmri;
    @FXML private TextField txtPershkrimi;
    @FXML private TextField txtCmimi;
    @FXML private ListView<String> txtSherbimetList;
    @FXML private Label messageLabel;

    private SherbimetService sherbimetService;
    public SherbimetController(){
        this.sherbimetService=new SherbimetService();
    }

    @FXML
    public void initialize() {
        loadSherbimet();
    }


    private void loadSherbimet() {
        txtSherbimetList.getItems().clear();
        List<Sherbimet> sherbimet = sherbimetService.getAll();
        for (Sherbimet s : sherbimet) {
            txtSherbimetList.getItems().add(s.getId() + " - " + s.getEmri() + " - " + s.getPershkrimi()+" - "+s.getÇmimi());
        }
    }

    @FXML
    private void handleCreate(MouseEvent event) {
        try {
            String emri = txtEmri.getText();
            String pershkrimi = txtPershkrimi.getText();
            double cmimi = Double.parseDouble(txtCmimi.getText());

            Sherbimet sherbimi = sherbimetService.create(new CreateSherbimetDto(emri, pershkrimi, cmimi));
            messageLabel.setText("Shërbimi u shtua me sukses.");
            loadSherbimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }
    @FXML
    private void handleUpdate(MouseEvent event) {
        try {
            int selectedId = getSelectedSherbimId();
            if (selectedId == -1) return;

            UpdateSherbimetDto dto = new UpdateSherbimetDto();
            dto.setId(selectedId);
            if (!txtEmri.getText().trim().isEmpty())
                dto.setEmri(txtEmri.getText().trim());
            if (!txtPershkrimi.getText().trim().isEmpty())
                dto.setPershkrimi(txtPershkrimi.getText().trim());
            if (!txtCmimi.getText().trim().isEmpty())
                dto.setÇmimi(Double.parseDouble(txtCmimi.getText().trim()));
            sherbimetService.update(dto);
            messageLabel.setText("Sherbimi u përditësua me sukses.");
            loadSherbimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(MouseEvent event) {
        try {
            int selectedId = getSelectedSherbimId();
            if (selectedId == -1) return;

            sherbimetService.delete(selectedId);
            messageLabel.setText("Sherbimi u fshi me sukses.");
            loadSherbimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private int getSelectedSherbimId() {
        String selected = txtSherbimetList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një sherbim në listë.");
            return -1;
        }
        return Integer.parseInt(selected.split(" - ")[0]);
    }

    private void clearForm() {
        txtEmri.clear();
        txtPershkrimi.clear();
        txtCmimi.clear();
    }

//    @FXML
//    private void handleUpdate(MouseEvent event) {
//        Sherbimet selected = tblSherbimet.getSelectionModel().getSelectedItem();
//        if (selected == null) {
//            messageLabel.setText("Zgjedh një shërbim për përditësim.");
//            return;
//        }
//
//        try {
//            UpdateSherbimetDto dto = new UpdateSherbimetDto();
//            dto.setId(selected.getId());
//            if (!txtEmri.getText().isEmpty()) dto.setEmri(txtEmri.getText());
//            if (!txtPershkrimi.getText().isEmpty()) dto.setPershkrimi(txtPershkrimi.getText());
//            if (!txtCmimi.getText().isEmpty()) dto.setÇmimi(Double.parseDouble(txtCmimi.getText()));
//
//            sherbimetService.update(dto);
//            messageLabel.setText("Shërbimi u përditësua.");
//            loadSherbimet();
//        } catch (Exception e) {
//            messageLabel.setText("Gabim: " + e.getMessage());
//        }
//    }
//
//    @FXML
//    private void handleDelete(MouseEvent event) {
//        Sherbimet selected = tblSherbimet.getSelectionModel().getSelectedItem();
//        if (selected == null) {
//            messageLabel.setText("Zgjedh një shërbim për fshirje.");
//            return;
//        }
//
//        try {
//            sherbimetService.delete(selected.getId());
//            messageLabel.setText("Shërbimi u fshi me sukses.");
//            loadSherbimet();
//        } catch (Exception e) {
//            messageLabel.setText("Gabim: " + e.getMessage());
//        }
//    }
}