package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML private TableColumn<Riparimet, String> colId;
    @FXML private TableColumn<Riparimet, String> colVeturaId;
    @FXML private TableColumn<Riparimet, String> colSherbimiId;
    @FXML private TableColumn<Riparimet, String> colStatusi;
    @FXML private TableColumn<Riparimet, String> colKostoRiparimit;
    @FXML private TableColumn<Riparimet, String> colDataRiparimit;

    @FXML private Label messageLabel;

    private LanguageManager languageManager;
    private final RiparimetService riparimetService = new RiparimetService();
    private final ObservableList<Riparimet> riparimetList = FXCollections.observableArrayList();

    public RiparimetController() {
        this.languageManager = LanguageManager.getInstance();
    }

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
                riparimetTableView.setItems(riparimetList);
            } else {
                String searchTerm = newVal.toLowerCase();
                ObservableList<Riparimet> filtered = riparimetList.filtered(r ->
                        String.valueOf(r.getId()).contains(searchTerm) ||
                                String.valueOf(r.getVeturaId()).contains(searchTerm) ||
                                r.getStatusi().toLowerCase().contains(searchTerm)
                );
                riparimetTableView.setItems(filtered);
            }
        });

        loadRiparimet();
    }

    private void loadRiparimet() {
        List<Riparimet> lista = riparimetService.getAll();
        riparimetList.setAll(lista);
    }

    @FXML
    private void handleCreate() {
        try {
            Integer veturaId = Integer.parseInt(txtVeturaId.getText());
            Integer sherbimiId = Integer.parseInt(txtSherbimiId.getText());
            String statusi = txtStatusi.getText();
            Double kostoRiparimit = Double.parseDouble(txtKostoRiparimit.getText());
            String dataRiparimit = txtDataRiparimit.getText();

            CreateRiparimetDto dto = new CreateRiparimetDto(
                    veturaId,
                    sherbimiId,
                    statusi,
                    kostoRiparimit,
                    dataRiparimit
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
    private void handleUpdate() {
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

            riparimetService.update(dto);
            messageLabel.setText("Riparimi u përditësua me sukses.");
            loadRiparimet();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
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
