package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.dto.Faturat.CreateFaturatDto;
import models.dto.Faturat.Faturat;
import models.dto.Faturat.UpdateFaturatDto;
import models.dto.Klientet.Klientet;
import services.FaturatService;
import services.LanguageManager;
import services.SceneManager;

import java.util.List;
import java.util.Locale;

public class FaturatController {
    @FXML private TextField txtShitjeId;
    @FXML private TextField txtDataFatures;
    @FXML private TextField txtShumaTotale;
    @FXML private ComboBox<String> cmbLlojiPageses;
    @FXML private TextField searchField;

    @FXML private TableView<Faturat> faturatTableView;
    @FXML private TableColumn<Faturat, String> colId;
    @FXML private TableColumn<Faturat, String> colShitjeId;
    @FXML private TableColumn<Faturat, String> colDataFatures;
    @FXML private TableColumn<Faturat, String> colShumaTotale;
    @FXML private TableColumn<Faturat, String> colLlojiPageses;

    @FXML private Label messageLabel;
    private LanguageManager languageManager;
    private final FaturatService faturatService = new FaturatService();
    private final ObservableList<Faturat> faturatList = FXCollections.observableArrayList();

    public FaturatController() {
        this.languageManager = LanguageManager.getInstance();
    }

    @FXML
    public void initialize() {
        cmbLlojiPageses.setItems(FXCollections.observableArrayList("CASH", "KARTE", "BANK", "TJETER"));

        // Inicializimi i kolonave të tabelës
        colId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        colShitjeId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getShitjeId())));
        colDataFatures.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataFatures()));
        colShumaTotale.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getShumaTotale())));
        colLlojiPageses.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLlojiPageses()));

        faturatTableView.setItems(faturatList);


        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                faturatTableView.setItems(faturatList);
            } else {
                String searchTerm = newVal.toLowerCase();
                ObservableList<Faturat> filtered = faturatList.filtered(f ->
                        String.valueOf(f.getId()).contains(searchTerm) ||
                                f.getLlojiPageses().toLowerCase().contains(searchTerm)
                );
                faturatTableView.setItems(filtered);
            }
        });

        loadFaturat();
    }

    private void loadFaturat() {
//        System.out.println(faturatService.getAll());
//        faturatList.setAll(faturatService.getAll());
        List<Faturat> faturat = faturatService.getAll();
        faturatTableView.getItems().setAll(faturat);
    }

    @FXML
    private void handleCreate() {
        try {
            Integer shitjeId = Integer.parseInt(txtShitjeId.getText());
            String dataFatures = txtDataFatures.getText();
            Double shumaTotale = Double.parseDouble(txtShumaTotale.getText());
            String llojiPageses = cmbLlojiPageses.getValue();

            CreateFaturatDto dto = new CreateFaturatDto(
                    shitjeId,
                    dataFatures,
                    shumaTotale,
                    llojiPageses
            );

            faturatService.create(dto);  // Ruani faturën në databazë
            messageLabel.setText("Fatura u shtua me sukses.");
            loadFaturat();  // Rifreskoni tabelën pas shtimit
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdat() {
        try {
            Faturat selected = faturatTableView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një faturë.");
                return;
            }

            UpdateFaturatDto dto = new UpdateFaturatDto();
            dto.setId(selected.getId());

            // Përditëso fushat që janë ndryshuar
            if (!txtShumaTotale.getText().trim().isEmpty())
                dto.setShumaTotale(Double.parseDouble(txtShumaTotale.getText().trim()));
            if (cmbLlojiPageses.getValue() != null)
                dto.setLlojiPageses(cmbLlojiPageses.getValue());

            faturatService.update(dto);  // Përditëso faturën në databazë
            messageLabel.setText("Fatura u përditësua me sukses.");
            loadFaturat();  // Rifreskoni tabelën pas përditësimit
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelet() {
        try {
            Faturat selected = faturatTableView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një faturë.");
                return;
            }

            faturatService.delete(selected.getId());  // Fshini faturën nga databaza
            messageLabel.setText("Fatura u fshi me sukses.");
            loadFaturat();  // Rifreskoni tabelën pas fshirjes
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }


    private void clearForm() {
        txtShitjeId.clear();
        txtDataFatures.clear();
        txtShumaTotale.clear();
        cmbLlojiPageses.setValue(null);
    }

}
