package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.dto.Shitjet.CreateShitjetDto;
import models.dto.Shitjet.UpdateShitjeDto;
import models.dto.Shitjet.Shitjet;
import services.LanguageManager;
import services.SceneManager;
import services.ShitjetService;

import java.util.List;
import java.util.Locale;

public class ShitjetController {
    @FXML private TextField txtShitjeId;
    @FXML private TextField txtKlientId;
    @FXML private TextField txtVeturaId;
    @FXML private TextField txtPunetorId;
    @FXML private TextField txtCmimiFinal;
    @FXML private TextField searchField;

    @FXML private TableView<Shitjet> shitjetTableView;
    @FXML private TableColumn<Shitjet, String> colShitjeId;
    @FXML private TableColumn<Shitjet, String> colKlientId;
    @FXML private TableColumn<Shitjet, String> colVeturaId;
    @FXML private TableColumn<Shitjet, String> colPunetorId;
    @FXML private TableColumn<Shitjet, String> colDataShitjes;
    @FXML private TableColumn<Shitjet, String> colCmimiFinal;

    @FXML private Label messageLabel;

    private ShitjetService shitjetService;
    private LanguageManager languageManager;

    public ShitjetController() {
        this.shitjetService = new ShitjetService();
        this.languageManager = LanguageManager.getInstance();
    }

    @FXML
    public void initialize() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                shitjetTableView.setItems(FXCollections.observableArrayList(shitjetService.getAll()));
            } else {
                try {
                    int klientiId = Integer.parseInt(newValue.trim());
                    List<Shitjet> filtruar = shitjetService.kerkoShitjetMeKlientId(klientiId);
                    shitjetTableView.setItems(FXCollections.observableArrayList(filtruar));
                } catch (NumberFormatException e) {
                    messageLabel.setText("Gabim: ID e klientit duhet te jete numer.");
                }
            }
        });

        colShitjeId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getShitje_id())));
        colKlientId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getKid())));
        colVeturaId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVetura_id())));
        colPunetorId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPunetor_id())));
        colDataShitjes.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getData_shitjes()));
        colCmimiFinal.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCmimi_final())));

        loadShitjet();
    }

    private void loadShitjet() {
        List<Shitjet> shitjet = shitjetService.getAll();
        shitjetTableView.getItems().setAll(shitjet);
    }

    @FXML
    private void handleCreate(MouseEvent event) {
        try {
            Integer klientId = Integer.parseInt(txtKlientId.getText());
            Integer veturaId = Integer.parseInt(txtVeturaId.getText());
            Integer punetorId = Integer.parseInt(txtPunetorId.getText());
            Double cmimiFinal = Double.parseDouble(txtCmimiFinal.getText());

            CreateShitjetDto dto = new CreateShitjetDto(
                    klientId,
                    veturaId,
                    punetorId,
                    cmimiFinal
            );

            shitjetService.create(dto);
            messageLabel.setText("Shitja u shtua me sukses.");
            loadShitjet();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(MouseEvent event) {
        try {
            int selectedId = getSelectedShitjeId();
            if (selectedId == -1) return;

            UpdateShitjeDto dto = new UpdateShitjeDto();
            dto.setShitjet_id(selectedId);
            dto.setKid(Integer.parseInt(txtKlientId.getText()));
            dto.setVetura_id(Integer.parseInt(txtVeturaId.getText()));
            dto.setPunetor_id(Integer.parseInt(txtPunetorId.getText()));
            dto.setCmimi_final(Double.parseDouble(txtCmimiFinal.getText()));

            shitjetService.update(dto);
            messageLabel.setText("Shitja u përditësua me sukses.");
            loadShitjet();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(MouseEvent event) {
        try {
            int selectedId = getSelectedShitjeId();
            if (selectedId == -1) return;

            shitjetService.delete(selectedId);
            messageLabel.setText("Shitja u fshi me sukses.");
            loadShitjet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private int getSelectedShitjeId() {
        Shitjet selected = shitjetTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një shitje në tabelë.");
            return -1;
        }
        return selected.getShitje_id();
    }

    private void clearForm() {
        txtKlientId.clear();
        txtVeturaId.clear();
        txtPunetorId.clear();
        txtCmimiFinal.clear();
    }
}


