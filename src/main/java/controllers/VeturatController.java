package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import services.LanguageManager;
import services.SceneManager;
import models.dto.Veturat.CreateVeturatDto;
import models.dto.Veturat.Veturat;
import models.dto.Veturat.UpdateVeturatDto;
import services.VeturatService;

import java.util.List;
import java.util.Locale;

public class VeturatController {
    @FXML public Button btn_shto;
    @FXML public Button btn_perditeso;
    @FXML public Button btn_fshij;
    @FXML public Button btn_shqip;
    @FXML public Button btn_anglisht;

    @FXML private TextField txtProdhuesi;
    @FXML private TextField txtModeli;
    @FXML private TextField txtVitiProdhimit;
    @FXML private TextField txtNgjyra;
    @FXML private TextField txtCmimi;
    @FXML private TextField txtGjendja;
    @FXML private TextField txtKilometrazha;
    @FXML private TextField txtTipiKarburant;

    @FXML private TableView<Veturat> veturatTableView;
    @FXML private TableColumn<Veturat, String> colProdhuesi;
    @FXML private TableColumn<Veturat, String> colModeli;
    @FXML private TableColumn<Veturat, String> colVitiProdhimit;
    @FXML private TableColumn<Veturat, String> colNgjyra;
    @FXML private TableColumn<Veturat, String> colCmimi;
    @FXML private TableColumn<Veturat, String> colGjendja;
    @FXML private TableColumn<Veturat, String> colKilometrazha;
    @FXML private TableColumn<Veturat, String> colTipiKarburant;

    @FXML private Label messageLabel;

    private final VeturatService veturatService;
    private final LanguageManager languageManager;

    public VeturatController() {
        this.veturatService = new VeturatService();
        this.languageManager = LanguageManager.getInstance();
    }
    @FXML
    public void initialize() {
        colProdhuesi.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProdhuesi()));
        colModeli.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModeli()));
        colVitiProdhimit.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getVitiProdhimit())));
        colNgjyra.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNgjyra()));
        colCmimi.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCmimi())));
        colGjendja.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGjendja()));
        colKilometrazha.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getKilometrazha())));
        colTipiKarburant.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipiKarburant()));
        loadVeturat();
    }

    private void loadVeturat() {
        List<Veturat> lista = veturatService.getAll();
        veturatTableView.getItems().setAll(lista);
    }

    @FXML
    private void handleCreate(ActionEvent event) {
        try {
            CreateVeturatDto dto = new CreateVeturatDto(
                    txtProdhuesi.getText(),
                    txtModeli.getText(),
                    Integer.parseInt(txtVitiProdhimit.getText()),
                    txtNgjyra.getText(),
                    Double.parseDouble(txtCmimi.getText()),
                    txtGjendja.getText(),
                    Integer.parseInt(txtKilometrazha.getText()),
                    txtTipiKarburant.getText()
            );

            veturatService.create(dto);
            messageLabel.setText("Vetura u shtua me sukses.");
            loadVeturat();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        try {
            int selectedId = getSelectedVeturaId();
            if (selectedId == -1) return;

            UpdateVeturatDto dto = new UpdateVeturatDto(selectedId);
            if (!txtGjendja.getText().trim().isEmpty())
                dto.setGjendja(txtGjendja.getText().trim());

            veturatService.update(dto);
            messageLabel.setText("Vetura u përditësua me sukses.");
            loadVeturat();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            int selectedId = getSelectedVeturaId();
            if (selectedId == -1) return;

            veturatService.delete(selectedId);
            messageLabel.setText("Vetura u fshi me sukses.");
            loadVeturat();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private int getSelectedVeturaId() {
        Veturat selected = veturatTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një veturë në tabelë.");
            return -1;
        }
        return selected.getId();
    }

    private void clearForm() {
        txtProdhuesi.clear();
        txtModeli.clear();
        txtVitiProdhimit.clear();
        txtNgjyra.clear();
        txtCmimi.clear();
        txtGjendja.clear();
        txtKilometrazha.clear();
        txtTipiKarburant.clear();
    }

    @FXML
    private void handleLanguageEnglishClick()throws Exception{
        loadLanguage(Locale.ENGLISH);
    }
    @FXML
    private void handleLanguageAlbanianClick()throws Exception{
        loadLanguage(new Locale("sq"));
    }
    private void loadLanguage(Locale locale) throws Exception{
        languageManager.setLocale(locale);
        SceneManager.reload();
    }
}
