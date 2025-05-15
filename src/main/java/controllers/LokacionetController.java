package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.dto.Lokacionet.CreateLokacionetDto;
import models.dto.Lokacionet.Lokacionet;
import models.dto.Lokacionet.UpdateLokacionetDto;
import services.LokacionetService;
import services.LanguageManager;
import services.SceneManager;

import java.util.List;
import java.util.Locale;

public class LokacionetController {
    @FXML private TextField txtEmriLokacionit;
    @FXML private TextField txtAdresa;
    @FXML private TextField txtQyteti;
    @FXML private TextField txtNrTelefonit;
    @FXML private TextField searchField;

    @FXML private TableView<Lokacionet> lokacionetTableView;
    @FXML private TableColumn<Lokacionet, String> colEmriLokacionit;
    @FXML private TableColumn<Lokacionet, String> colAdresa;
    @FXML private TableColumn<Lokacionet, String> colQyteti;
    @FXML private TableColumn<Lokacionet, String> colNrTelefonit;

    @FXML private Label messageLabel;

    private LokacionetService lokacionetService;
    private LanguageManager languageManager;

    public LokacionetController() {
        this.lokacionetService = new LokacionetService();
        this.languageManager = LanguageManager.getInstance();
    }

    @FXML
    public void initialize() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                lokacionetTableView.setItems(FXCollections.observableArrayList(lokacionetService.getAll()));
            } else {
                List<Lokacionet> filtruar = lokacionetService.kerkoLokacionMeEmrin(newValue);
                lokacionetTableView.setItems(FXCollections.observableArrayList(filtruar));
            }
        });

        colEmriLokacionit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmri_lokacionit()));
        colAdresa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresa()));
        colQyteti.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQyteti()));
        colNrTelefonit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNrtelefonit()));

        loadLokacionet();
    }

    private void loadLokacionet() {
        List<Lokacionet> lokacionet = lokacionetService.getAll();
        lokacionetTableView.getItems().setAll(lokacionet);
    }

    @FXML
    private void handleCreateLokacionet() {
        try {
            String emriLokacionit = txtEmriLokacionit.getText();
            String adresa = txtAdresa.getText();
            String qyteti = txtQyteti.getText();
            String nrTelefonit = txtNrTelefonit.getText();

            CreateLokacionetDto dto = new CreateLokacionetDto(
                    emriLokacionit,
                    adresa,
                    qyteti,
                    nrTelefonit
            );

            lokacionetService.create(dto);

            messageLabel.setText("Lokacioni u shtua me sukses.");
            loadLokacionet();

            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdateLokacionet() {
        try {
            int selectedId = getSelectedLokacionId();
            if (selectedId == -1) return;

            UpdateLokacionetDto dto = new UpdateLokacionetDto();
            dto.setId(selectedId);
            if (!txtAdresa.getText().trim().isEmpty())
                dto.setAdresa(txtAdresa.getText().trim());
            if (!txtQyteti.getText().trim().isEmpty())
                dto.setQyteti(txtQyteti.getText().trim());
            if (!txtNrTelefonit.getText().trim().isEmpty())
                dto.setNrtelefonit(txtNrTelefonit.getText().trim());
            lokacionetService.update(dto);
            messageLabel.setText("Lokacioni u përditësua me sukses.");
            loadLokacionet();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDeleteLokacionet() {
        try {
            int selectedId = getSelectedLokacionId();
            if (selectedId == -1) return;

            lokacionetService.delete(selectedId);
            messageLabel.setText("Lokacioni u fshi me sukses.");
            loadLokacionet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private int getSelectedLokacionId() {
        Lokacionet selected = lokacionetTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një lokacion në tabelë.");
            return -1;
        }
        return selected.getLokacionet_id();
    }

    private void clearForm() {
        txtEmriLokacionit.clear();
        txtAdresa.clear();
        txtQyteti.clear();
        txtNrTelefonit.clear();
    }
}
