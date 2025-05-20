package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.dto.Partneret.CreatePartneretDto;
import models.dto.Partneret.Partneret;
import models.dto.Partneret.UpdatePartneretDto;
import services.PartneretService;

import java.util.List;

public class PartneretController {

    @FXML private TextField txtEmriKompanise;
    @FXML private TextField txtLlojiPartnerit;
    @FXML private TextField txtPersonKontakti;
    @FXML private TextField txtTelefoni;
    @FXML private TextField txtAdresa;
    @FXML private TextField txtEmail;
    @FXML private TextField searchField;

    @FXML private TableView<Partneret> partneretTableView;
    @FXML private TableColumn<Partneret, String> colId;
    @FXML private TableColumn<Partneret, String> colEmriKompanise;
    @FXML private TableColumn<Partneret, String> colLlojiPartnerit;
    @FXML private TableColumn<Partneret, String> colPersonKontakti;
    @FXML private TableColumn<Partneret, String> colTelefoni;
    @FXML private TableColumn<Partneret, String> colAdresa;
    @FXML private TableColumn<Partneret, String> colEmail;

    @FXML private Label messageLabel;

    private final PartneretService partneretService = new PartneretService();
    private final ObservableList<Partneret> partneretList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicijalizimi i kolonave të tabelës
        colId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        colEmriKompanise.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmriKompanise()));
        colLlojiPartnerit.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLlojiPartnerit()));
        colPersonKontakti.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPersonKontakti()));
        colTelefoni.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefoni()));
        colAdresa.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAdresa()));
        colEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

        partneretTableView.setItems(partneretList);

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                partneretTableView.setItems(partneretList);
            } else {
                String searchTerm = newVal.toLowerCase();
                ObservableList<Partneret> filtered = partneretList.filtered(p ->
                        p.getEmriKompanise().toLowerCase().contains(searchTerm) ||
                                p.getLlojiPartnerit().toLowerCase().contains(searchTerm)
                );
                partneretTableView.setItems(filtered);
            }
        });

        loadPartneret();
    }

    private void loadPartneret() {
        List<Partneret> partneret = partneretService.getAll();
        partneretTableView.getItems().setAll(partneret);
    }

    @FXML
    private void handleCreat() {
        try {
            CreatePartneretDto dto = new CreatePartneretDto(
                    txtEmriKompanise.getText(),
                    txtLlojiPartnerit.getText(),
                    txtPersonKontakti.getText(),
                    txtEmail.getText(),
                    txtTelefoni.getText(),
                    txtAdresa.getText()
            );
            partneretService.create(dto);
            messageLabel.setText("Partneri u shtua me sukses.");
            loadPartneret();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdat() {
        try {
            Partneret selected = partneretTableView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një partner.");
                return;
            }

            UpdatePartneretDto dto = new UpdatePartneretDto();
            dto.setId(selected.getId());

            if (!txtTelefoni.getText().trim().isEmpty())
                dto.setTelefoni(txtTelefoni.getText().trim());

            if (!txtEmail.getText().trim().isEmpty())
                dto.setEmail(txtEmail.getText().trim());

            if (!txtAdresa.getText().trim().isEmpty())
                dto.setAdresa(txtAdresa.getText().trim());

            if (!txtPersonKontakti.getText().trim().isEmpty())
                dto.setPersonKontakti(txtPersonKontakti.getText().trim());
            
            partneretService.update(dto);
            messageLabel.setText("Partneri u përditësua me sukses.");
            loadPartneret();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }


    @FXML
    private void handleDelet() {
        try {
            Partneret selected = partneretTableView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një partner.");
                return;
            }

            partneretService.delete(selected.getId());
            messageLabel.setText("Partneri u fshi me sukses.");
            loadPartneret();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private void clearForm() {
        txtEmriKompanise.clear();
        txtLlojiPartnerit.clear();
        txtPersonKontakti.clear();
        txtTelefoni.clear();
        txtAdresa.clear();
        txtEmail.clear();
    }
}
