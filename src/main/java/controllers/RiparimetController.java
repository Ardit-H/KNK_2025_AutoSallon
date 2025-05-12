package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.dto.Riparimet.CreateRiparimetDto;
import models.dto.Riparimet.Riparimet;
import models.dto.Riparimet.UpdateRiparimetDto;
import services.RiparimetService;
import services.LanguageManager;
import services.SceneManager;

import java.util.List;
import java.util.Locale;

public class RiparimetController {
    @FXML private TextField txtVeturaId;
    @FXML private TextField txtSherbimiId;
    @FXML private TextField txtStatusi;
    @FXML private TextField txtKostoRiparimit;
    @FXML private TextField txtDataRiparimit;
    @FXML private TextField searchField;

    @FXML private TableView<Riparimet> riparimetTableView;
    @FXML private TableColumn<Riparimet, String> colVeturaId;
    @FXML private TableColumn<Riparimet, String> colSherbimiId;
    @FXML private TableColumn<Riparimet, String> colStatusi;
    @FXML private TableColumn<Riparimet, String> colKostoRiparimit;
    @FXML private TableColumn<Riparimet, String> colDataRiparimit;
    @FXML private Label messageLabel;

    private RiparimetService riparimetService;
    private LanguageManager languageManager;

    public RiparimetController() {
        this.riparimetService = new RiparimetService();
        this.languageManager = LanguageManager.getInstance();
    }

    @FXML
    public void initialize() {
        searchField.textProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                riparimetTableView.setItems(FXCollections.observableArrayList(riparimetService.getAll()));
            } else {
                List<Riparimet> filtruar = riparimetService.kerkoSipasStatusit(newVal);
                riparimetTableView.setItems(FXCollections.observableArrayList(filtruar));
            }
        });

        colVeturaId.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getVeturaId())));
        colSherbimiId.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getSherbimiId())));
        colStatusi.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getStatusi()));
        colKostoRiparimit.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getKostoRiparimit())));
        colDataRiparimit.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDataRiparimit()));

        loadRiparimet();
    }

    private void loadRiparimet() {
        List<Riparimet> lista = riparimetService.getAll();
        riparimetTableView.getItems().setAll(lista);
    }

    @FXML
    private void handleCreate(MouseEvent event) {
        try {
            Integer veturaId = Integer.parseInt(txtVeturaId.getText());
            Integer sherbimiId = Integer.parseInt(txtSherbimiId.getText());
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
    private void handleUpdate(MouseEvent event) {
        try {
            int selectedId = getSelectedRiparimiId();
            if (selectedId == -1) return;

            UpdateRiparimetDto dto = new UpdateRiparimetDto();
            dto.setId(selectedId);
            dto.setStatusi(txtStatusi.getText());
            dto.setKostoRiparimit(Double.parseDouble(txtKostoRiparimit.getText()));

            riparimetService.update(dto);
            messageLabel.setText("Riparimi u përditësua me sukses.");
            loadRiparimet();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(MouseEvent event) {
        try {
            int selectedId = getSelectedRiparimiId();
            if (selectedId == -1) return;

            riparimetService.delete(selectedId);
            messageLabel.setText("Riparimi u fshi me sukses.");
            loadRiparimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private int getSelectedRiparimiId() {
        Riparimet selected = riparimetTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një riparim në tabelë.");
            return -1;
        }
        return selected.getId();
    }

    private void clearForm() {
        txtVeturaId.clear();
        txtSherbimiId.clear();
        txtStatusi.clear();
        txtKostoRiparimit.clear();
        txtDataRiparimit.clear();
    }

    @FXML
    private void handleLanguageEnglishClick() throws Exception {
        loadLanguage(Locale.ENGLISH);
    }

    @FXML
    private void handleLanguageAlbanianClick() throws Exception {
        loadLanguage(new Locale("sq"));
    }

    private void loadLanguage(Locale locale) throws Exception {
        languageManager.setLocale(locale);
        SceneManager.reload();
    }
}
