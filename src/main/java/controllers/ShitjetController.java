package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.dto.Shitjet.CreateShitjetDto;
import models.dto.Shitjet.UpdateShitjeDto;
import models.dto.Shitjet.Shitjet;
import services.LanguageManager;
import services.ShitjetService;

import java.util.List;

public class ShitjetController {

    @FXML public Button btn_perditeso;
    @FXML public Button btn_fshij;
    @FXML public Button btn_shto;

    @FXML private TextField txtKlientId;
    @FXML private TextField txtVeturaId;
    @FXML private TextField txtPunetorId;
    @FXML private TextField txtCmimiFinal;
    @FXML private TextField searchField;

    @FXML private Label messageLabel;

    @FXML private TableView<Shitjet> shitjetTableView;
    @FXML private TableColumn<Shitjet, String> colKlientId;
    @FXML private TableColumn<Shitjet, String> colVeturaId;
    @FXML private TableColumn<Shitjet, String> colPunetorId;
    @FXML private TableColumn<Shitjet, String> colDataShitjes;
    @FXML private TableColumn<Shitjet, String> colCmimiFinal;

    private final ShitjetService shitjetService;
    private final LanguageManager languageManager;

    public ShitjetController() {
        this.shitjetService = new ShitjetService();
        this.languageManager = LanguageManager.getInstance();
    }

    @FXML
    public void initialize() {
        // Kolonat e tabelës
        colKlientId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getKid())));
        colVeturaId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getVetura_id())));
        colPunetorId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getPunetor_id())));
        colDataShitjes.setCellValueFactory(data -> {
            if (data.getValue().getData_shitjes() == null) return new SimpleStringProperty("");
            return new SimpleStringProperty(data.getValue().getData_shitjes().toString());
        });
        colCmimiFinal.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCmimi_final())));

        loadShitjet();

        // Kërkimi me klientId në fushën e kërkimit
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                loadShitjet();
                messageLabel.setText("");
            } else {
                try {
                    int klientId = Integer.parseInt(newVal.trim());
                    List<Shitjet> filtruar = shitjetService.kerkoShitjetMeKlientId(klientId);
                    if (filtruar.isEmpty()) {
                        messageLabel.setText("Nuk u gjet asnjë shitje për këtë klient.");
                    } else {
                        messageLabel.setText("");
                    }
                    shitjetTableView.setItems(FXCollections.observableArrayList(filtruar));
                } catch (NumberFormatException e) {
                    messageLabel.setText("Gabim: ID e klientit duhet të jetë numër.");
                }
            }
        });
    }

    private void loadShitjet() {
        List<Shitjet> shitjet = shitjetService.getAll();
        shitjetTableView.getItems().setAll(shitjet);
    }

    @FXML
    private void handleCreateShitjet() {
        try {
            CreateShitjetDto dto = new CreateShitjetDto(
                    Integer.parseInt(txtKlientId.getText()),
                    Integer.parseInt(txtVeturaId.getText()),
                    Integer.parseInt(txtPunetorId.getText()),
                    Double.parseDouble(txtCmimiFinal.getText())
            );

            shitjetService.create(dto);
            messageLabel.setText("Shitja u shtua me sukses.");
            loadShitjet();
            clearForm();
        } catch (NumberFormatException e) {
            messageLabel.setText("Gabim në formatimin e numrave. Kontrolloni fushat numerike.");
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdateShitjet() {
        try {
            Shitjet selected = shitjetTableView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një shitje nga tabela!");
                return;
            }

            UpdateShitjeDto dto = new UpdateShitjeDto();
            dto.setShitjet_id(selected.getShitje_id());
            dto.setKid(Integer.parseInt(txtKlientId.getText()));
            dto.setVetura_id(Integer.parseInt(txtVeturaId.getText()));
            dto.setPunetor_id(Integer.parseInt(txtPunetorId.getText()));
            dto.setCmimi_final(Double.parseDouble(txtCmimiFinal.getText()));

            shitjetService.update(dto);
            messageLabel.setText("Shitja u përditësua me sukses.");
            loadShitjet();
            clearForm();
        } catch (NumberFormatException e) {
            messageLabel.setText("Gabim në formatimin e numrave.");
        } catch (Exception e) {
            messageLabel.setText("Gabim gjatë përditësimit: " + e.getMessage());
        }
    }

    @FXML
    private void handleDeleteShitjet() {
        try {
            Shitjet selected = shitjetTableView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një shitje nga tabela!");
                return;
            }

            shitjetService.delete(selected.getShitje_id());
            messageLabel.setText("Shitja u fshi me sukses.");
            loadShitjet();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private void clearForm() {
        txtKlientId.clear();
        txtVeturaId.clear();
        txtPunetorId.clear();
        txtCmimiFinal.clear();
    }
}