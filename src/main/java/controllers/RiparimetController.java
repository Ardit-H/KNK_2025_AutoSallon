package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.dto.Riparimet.CreateRiparimetDto;
import models.dto.Riparimet.Riparimet;
import models.dto.Riparimet.UpdateRiparimetDto;
import services.RiparimetService;

import java.util.List;

public class RiparimetController {
    @FXML private TextField txtVeturaId;
    @FXML private TextField txtSherbimiId;
    @FXML private TextField txtStatusi;
    @FXML private TextField txtKostoRiparimit;
    @FXML private TextField txtDataRiparimit;
    @FXML private TextField searchField;

    @FXML private TableView<Riparimet> riparimetTableView;
    @FXML private TableColumn<Riparimet, String> colId;
    @FXML private TableColumn<Riparimet, String> colVeturaId;
    @FXML private TableColumn<Riparimet, String> colSherbimiId;
    @FXML private TableColumn<Riparimet, String> colStatusi;
    @FXML private TableColumn<Riparimet, String> colKostoRiparimit;
    @FXML private TableColumn<Riparimet, String> colDataRiparimit;

    @FXML private Label messageLabel;

    private final RiparimetService riparimetService = new RiparimetService();
    private final ObservableList<Riparimet> riparimetList = FXCollections.observableArrayList();
    private final ObservableList<Riparimet> originalList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicializimi i kolonave të tabelës
        colId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        colVeturaId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getVeturaId())));
        colSherbimiId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getSherbimiId())));
        colStatusi.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatusi()));
        colKostoRiparimit.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getKostoRiparimit())));
        colDataRiparimit.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataRiparimit()));

        riparimetTableView.setItems(riparimetList);

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                riparimetList.setAll(originalList);
            } else {
                String keyword = newVal.toLowerCase();
                ObservableList<Riparimet> filtered = originalList.filtered(r ->
                        String.valueOf(r.getId()).toLowerCase().contains(keyword) ||
                                String.valueOf(r.getVeturaId()).toLowerCase().contains(keyword) ||
                                String.valueOf(r.getSherbimiId()).toLowerCase().contains(keyword) ||
                                r.getStatusi().toLowerCase().contains(keyword) ||
                                String.valueOf(r.getKostoRiparimit()).toLowerCase().contains(keyword) ||
                                r.getDataRiparimit().toLowerCase().contains(keyword)
                );
                riparimetList.setAll(filtered);
            }
        });

        loadRiparimet();
    }

    private void loadRiparimet() {
        List<Riparimet> lista = riparimetService.getAll();
        originalList.setAll(lista);
        riparimetList.setAll(lista);
    }

    @FXML
    private void handleCreate() {
        try {
            int veturaId = Integer.parseInt(txtVeturaId.getText());
            int sherbimiId = Integer.parseInt(txtSherbimiId.getText());
            String statusi = txtStatusi.getText();
            Double kostoRiparimit = Double.parseDouble(txtKostoRiparimit.getText());

            CreateRiparimetDto dto = new CreateRiparimetDto(
                    veturaId,
                    sherbimiId,
                    statusi,
                    kostoRiparimit
            );

            riparimetService.create(dto);
            messageLabel.setText("Riparimi u shtua me sukses.");
            loadRiparimet();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdat() {
        try {
            Riparimet selected = riparimetTableView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një riparim.");
                return;
            }

            UpdateRiparimetDto dto = new UpdateRiparimetDto();
            dto.setId(selected.getId());

            if (!txtStatusi.getText().trim().isEmpty())
                dto.setStatusi(txtStatusi.getText().trim());

            if (!txtKostoRiparimit.getText().trim().isEmpty())
                dto.setKostoRiparimit(Double.parseDouble(txtKostoRiparimit.getText().trim()));

            if (!txtDataRiparimit.getText().trim().isEmpty())
                dto.setDataRiparimit(txtDataRiparimit.getText().trim());

            riparimetService.update(dto);
            messageLabel.setText("Riparimi u përditësua me sukses.");
            loadRiparimet();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelet() {
        try {
            Riparimet selected = riparimetTableView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një riparim.");
                return;
            }

            riparimetService.delete(selected.getId());
            messageLabel.setText("Riparimi u fshi me sukses.");
            loadRiparimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private void clearForm() {
        txtVeturaId.clear();
        txtSherbimiId.clear();
        txtStatusi.clear();
        txtKostoRiparimit.clear();
        txtDataRiparimit.clear();
    }
}
