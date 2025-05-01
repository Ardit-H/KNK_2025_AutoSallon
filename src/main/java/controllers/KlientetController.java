package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import services.KlientetService;
import services.LanguageManager;
import services.SceneManager;

import java.util.List;
import java.util.Locale;

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

    @FXML private TableView<Klientet> KlientetTableView;
    @FXML private TableColumn<Klientet, String> colEmri;
    @FXML private TableColumn<Klientet, String> colMbiemri;
    @FXML private TableColumn<Klientet, String> colEmail;
    @FXML private TableColumn<Klientet, String> colNrTelefonit;
    @FXML private TableColumn<Klientet, String> colAdresa;
    @FXML private TableColumn<Klientet, String> colDataRegjistrimit;

    @FXML
    private Label messageLabel;
    private KlientetService klientetService;
    private LanguageManager languageManager;
    public KlientetController(){
        this.klientetService=new KlientetService();
        this.languageManager=LanguageManager.getInstance();
    }
    @FXML

    public void initialize() {
        colEmri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmri()));
        colMbiemri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMbiemri()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colNrTelefonit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNrtelefonit()));
        colAdresa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresa()));
        colDataRegjistrimit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getData_regjistrimit()));
        loadKlientet();
    }

private void loadKlientet() {
    List<Klientet> klientet = klientetService.getAll();
    KlientetTableView.getItems().setAll(klientet);
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
            if (!txtEmail.getText().trim().isEmpty())
                dto.setEmail(txtEmail.getText().trim());
            if (!txtNrTelefonit.getText().trim().isEmpty())
                dto.setNrtelefonit(txtNrTelefonit.getText().trim());
            if (!txtAdresa.getText().trim().isEmpty())
                dto.setAdresa(txtAdresa.getText().trim());
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
        Klientet selected = KlientetTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një klient në tabelë.");
            return -1;
        }
        return selected.getKid();
    }
    private void clearForm() {
        txtEmri.clear();
        txtMbiemri.clear();
        txtEmail.clear();
        txtNrTelefonit.clear();
        txtAdresa.clear();
    }
    @FXML
    private void handleLanguageEnglishClick()throws Exception{
        this.languageManager.setLocale(Locale.ENGLISH);
        SceneManager.reloadScene();
    }
    @FXML
    private void handleLanguageAlbanianClick()throws Exception{
        this.languageManager.setLocale(new Locale("sq","Kosova"));
        SceneManager.reloadScene();
    }
}

