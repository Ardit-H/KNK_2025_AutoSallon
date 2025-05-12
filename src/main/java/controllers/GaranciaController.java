package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.util.List;
import models.dto.Garancia.CreateGaranciaDto;
import models.dto.Garancia.UpdateGaranciaDto;
import models.dto.Garancia.Garancia;
import services.GaranciaService;

public class GaranciaController {

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
    @FXML private Button btnShto;
    @FXML private Button btnPerditeso;
    @FXML private Button btnFshij;

    private GaranciaService garanciaService = new GaranciaService();
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
        String lloji = txtLlojiGarancise.getText();
        String fillimi = dpDataFillimit.getValue() != null ? dpDataFillimit.getValue().toString() : "";
        String mbarimi = dpDataMbarimit.getValue() != null ? dpDataMbarimit.getValue().toString() : "";
        int kid = Integer.parseInt(txtKlientId.getText());
        int vid = Integer.parseInt(txtVeturaId.getText());

        CreateGaranciaDto dto = new CreateGaranciaDto(lloji, fillimi, mbarimi, kid, vid);
        garanciaService.create(dto);
        loadTable();
        clearFields();
    }

    @FXML
    private void onPerditesoClicked() {
        Garancia selected = garanciaTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        UpdateGaranciaDto dto = new UpdateGaranciaDto();
        dto.setId(selected.getId());
        dto.setDataFillimit(dpDataFillimit.getValue().toString());
        dto.setDataMbarimit(dpDataMbarimit.getValue().toString());

        try {
            garanciaService.update(dto);
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

    @FXML
    private void onTableClicked(MouseEvent event) {
        Garancia selected = garanciaTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtLlojiGarancise.setText(selected.getLlojiGarancise());
            dpDataFillimit.setValue(java.time.LocalDate.parse(selected.getDataFillimit()));
            dpDataMbarimit.setValue(java.time.LocalDate.parse(selected.getDataMbarimit()));
        }
    }

    private void clearFields() {
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
}
