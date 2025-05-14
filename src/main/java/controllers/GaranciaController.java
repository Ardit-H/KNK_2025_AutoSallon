package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;
import models.dto.Garancia.CreateGaranciaDto;
import models.dto.Garancia.UpdateGaranciaDto;
import models.dto.Garancia.Garancia;
import services.GaranciaService;

public class GaranciaController {

    @FXML public Button btnShto;
    @FXML public Button btnPerditeso;
    @FXML public Button btnFshij;
    @FXML private Label messageLabel;

    @FXML private TableView<Garancia> garanciaTableView;
    @FXML private TableColumn<Garancia, String> colLlojiGarancise;
    @FXML private TableColumn<Garancia, String> colDataFillimit;
    @FXML private TableColumn<Garancia, String> colDataMbarimit;
    @FXML private TableColumn<Garancia, String> colKlientId;
    @FXML private TableColumn<Garancia, String> colVeturaId;

    @FXML private TextField txtLlojiGarancise;
    @FXML private DatePicker dpDataFillimit;
    @FXML private DatePicker dpDataMbarimit;
    @FXML private TextField txtKlientId;
    @FXML private TextField txtVeturaId;


    private final GaranciaService garanciaService = new GaranciaService();
    @FXML public void initialize() {
        colKlientId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getKid())));
        colVeturaId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getVid())));
        colLlojiGarancise.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLlojiGarancise()));
        colDataFillimit.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataFillimit()));
        colDataMbarimit.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataMbarimit()));
        loadTable();
    }

    private void loadTable() {
        List<Garancia> lista = garanciaService.getAll();
        garanciaTableView.getItems().setAll(lista);
    }

    @FXML
    private void onShtoClicked() {
        try {
            String lloji = txtLlojiGarancise.getText();
            String fillimi = dpDataFillimit.getValue() != null ? dpDataFillimit.getValue().toString() : "";
            String mbarimi = dpDataMbarimit.getValue() != null ? dpDataMbarimit.getValue().toString() : "";

            if (lloji == null || lloji.trim().isEmpty()) {
                messageLabel.setText("Lloji i garancisë është i detyrueshëm.");
                return;
            }

            if (fillimi == null) {
                messageLabel.setText("Data e fillimit është e detyrueshme.");
                return;
            }

            if (mbarimi == null) {
                messageLabel.setText("Data e mbarimit është e detyrueshme.");
                return;
            }

            int kid = Integer.parseInt(txtKlientId.getText());
            int vid = Integer.parseInt(txtVeturaId.getText());

            CreateGaranciaDto dto = new CreateGaranciaDto(
                    vid,
                    kid,
                    lloji,
                    fillimi,
                    mbarimi
            );

            garanciaService.create(dto);
            messageLabel.setText("Garancia u shtua me sukses.");
            loadTable();
            clearFields();
        } catch (NumberFormatException e) {
            messageLabel.setText("Gabim në formatimin e numrave. Ju lutem kontrolloni ID-të.");
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }


    @FXML
    private void onPerditesoClicked() {
        Garancia selected = garanciaTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        UpdateGaranciaDto dto = new UpdateGaranciaDto();
        dto.setId(selected.getId());

        if (dpDataFillimit.getValue() != null)
            dto.setDataFillimit(dpDataFillimit.getValue().toString());

        if (dpDataMbarimit.getValue() != null)
            dto.setDataMbarimit(dpDataMbarimit.getValue().toString());

        String lloji = txtLlojiGarancise.getText();

        if (lloji != null && !lloji.trim().isEmpty())
            dto.setLlojiGarancise(lloji);

        try {
            garanciaService.update(dto);
            messageLabel.setText("Garancia u përditësua me sukses.");
            loadTable();
            clearFields();
        } catch (Exception e) {
            showAlert("Gabim gjatë përditësimit: " + e.getMessage());
        }
    }


    @FXML
    private void onFshijClicked() {
        Garancia selected = garanciaTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try {
            garanciaService.delete(selected.getId());
            loadTable();
            clearFields();
        } catch (Exception e) {
            showAlert("Gabim gjatë fshirjes: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtKlientId.clear();
        txtVeturaId.clear();
        txtLlojiGarancise.clear();
        dpDataFillimit.setValue(null);
        dpDataMbarimit.setValue(null);
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML public void onTableClicked(MouseEvent mouseEvent) {
        Garancia selected = garanciaTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtLlojiGarancise.setText(selected.getLlojiGarancise());
            dpDataFillimit.setValue(java.time.LocalDate.parse(selected.getDataFillimit()));
            dpDataMbarimit.setValue(java.time.LocalDate.parse(selected.getDataMbarimit()));
        }
    }
}
