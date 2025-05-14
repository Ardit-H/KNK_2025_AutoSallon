package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.dto.Veturat.CreateVeturatDto;
import models.dto.Veturat.UpdateVeturatDto;
import models.dto.Veturat.Veturat;
import services.LanguageManager;
import services.SceneManager;
import services.VeturatService;

import java.util.List;
import java.util.Locale;

public class VeturatController {

    @FXML public Button btn_perditeso;
    @FXML public Button btn_fshij;
    @FXML public Button btn_shto;
    @FXML private TextField txtProdhuesi;
    @FXML private TextField txtModeli;
    @FXML private TextField txtVitiProdhimit;
    @FXML private TextField txtNgjyra;
    @FXML private TextField txtCmimi;
    @FXML private TextField txtGjendja;
    @FXML private TextField txtKilometrazha;
    @FXML private TextField txtTipiKarburant;
    @FXML private TextField searchField;
    @FXML private Label messageLabel;

    @FXML private TableView<Veturat> veturatTableView;
    @FXML private TableColumn<Veturat, String> colProdhuesi;
    @FXML private TableColumn<Veturat, String> colModeli;
    @FXML private TableColumn<Veturat, String> colVitiProdhimit;
    @FXML private TableColumn<Veturat, String> colNgjyra;
    @FXML private TableColumn<Veturat, String> colCmimi;
    @FXML private TableColumn<Veturat, String> colGjendja;
    @FXML private TableColumn<Veturat, String> colKilometrazha;
    @FXML private TableColumn<Veturat, String> colTipiKarburant;

    private final VeturatService veturatService;
    private final LanguageManager languageManager;

    public VeturatController() {
        this.veturatService = new VeturatService();
        this.languageManager = LanguageManager.getInstance();
    }

    @FXML public void initialize() {
        colProdhuesi.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProdhuesi()));
        colModeli.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModeli()));
        colVitiProdhimit.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getVitiProdhimit())));
        colNgjyra.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNgjyra()));
        colCmimi.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCmimi())));
        colGjendja.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGjendja()));
        colKilometrazha.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getKilometrazha())));
        colTipiKarburant.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipiKarburant()));

        loadVeturat();

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                loadVeturat();
                messageLabel.setText("");
            } else {
                List<Veturat> filtered = veturatService.kerkoVeturat(newVal.trim());
                if (filtered.isEmpty()) {
                    messageLabel.setText("Nuk u gjet asnjë veturë për këtë prodhues.");
                } else {
                    messageLabel.setText("");
                }
                veturatTableView.setItems(FXCollections.observableArrayList(filtered));
            }
        });
    }

    private void loadVeturat() {
        List<Veturat> lista = veturatService.getAll();
        veturatTableView.getItems().setAll(lista);
    }

    @FXML private void handleCreate() {
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
        } catch (NumberFormatException e) {
            messageLabel.setText("Gabim në formatimin e numrave. Ju lutem, kontrolloni fushat numerike.");
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        try {
            Veturat selected = veturatTableView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një veturë nga tabela!");
                return;
            }

            UpdateVeturatDto dto = new UpdateVeturatDto();
            dto.setId(selected.getId());

            String gjendja = txtGjendja.getText().trim();
            String ngjyra = txtNgjyra.getText().trim();
            String kmText = txtKilometrazha.getText().trim();

            if (!gjendja.isEmpty()) {
                dto.setGjendja(gjendja);
            }

            if (!ngjyra.isEmpty()) {
                dto.setNgjyra(ngjyra);
            }

            if (!kmText.isEmpty()) {
                try {
                    int km = Integer.parseInt(kmText);
                    if (km >= 0) {
                        dto.setKilometrazha(km);
                    } else {
                        messageLabel.setText("Kilometrazha nuk mund të jetë negative.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    messageLabel.setText("Kilometrazha duhet të jetë numër.");
                    return;
                }
            }

            veturatService.update(dto);
            messageLabel.setText("Vetura u përditësua me sukses.");
            loadVeturat();

        } catch (IllegalArgumentException e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        } catch (Exception e) {
            messageLabel.setText("Gabim gjatë përditësimit: " + e.getMessage());
        }
    }

    @FXML private void handleDelete() {
        try {
            Veturat selected = veturatTableView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një veturë!");
                return;
            }

            veturatService.delete(selected.getId());
            messageLabel.setText("Vetura u fshi me sukses.");
            loadVeturat();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
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

}
