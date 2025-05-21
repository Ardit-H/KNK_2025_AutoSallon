package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.dto.Partneret.Partneret;
import models.dto.Partneret.Partneret;
import services.LanguageManager;
import services.PartneretService;

import java.util.List;
import java.util.stream.Collectors;

public class Partneret_UserController {

    @FXML private TextField searchField;
    @FXML private TableView<Partneret> partneretTableView;
    @FXML private TableColumn<Partneret, Integer> colId;
    @FXML private TableColumn<Partneret, String> colEmriKompanise;
    @FXML private TableColumn<Partneret, String> colLlojiPartnerit;
    @FXML private TableColumn<Partneret, String> colPersonKontakti;
    @FXML private TableColumn<Partneret, String> colEmail;
    @FXML private TableColumn<Partneret, String> colTelefoni;
    @FXML private TableColumn<Partneret, String> colAdresa;

    private final PartneretService partneretService;
    private final ObservableList<Partneret> partneretList = FXCollections.observableArrayList();
    private final LanguageManager languageManager;

    public Partneret_UserController() {
        this.partneretService = new PartneretService();
        this.languageManager = LanguageManager.getInstance();
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colEmriKompanise.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmriKompanise()));
        colLlojiPartnerit.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLlojiPartnerit()));
        colPersonKontakti.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPersonKontakti()));
        colEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        colTelefoni.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefoni()));
        colAdresa.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAdresa()));

        partneretTableView.setItems(partneretList);
        loadPartneret();

        // Mund të aktivizosh ose çaktivizosh këtë nëse kërkon dinamikisht ose me buton
//        searchField.textProperty().addListener((obs, oldVal, newVal) -> onSearch());
    }

    private void loadPartneret() {
        partneretList.setAll(partneretService.getAll());
    }

    @FXML
    private void onSearch() {
        String query = searchField.getText();
        if (query == null || query.trim().isEmpty()) {
            partneretList.setAll(partneretService.getAll());
        } else {
            String lowerQuery = query.toLowerCase();
            List<Partneret> filtered = partneretService.getAll().stream()
                    .filter(p -> String.valueOf(p.getId()).contains(lowerQuery)
                            || p.getEmriKompanise().toLowerCase().contains(lowerQuery)
                            || p.getLlojiPartnerit().toLowerCase().contains(lowerQuery))
                    .collect(Collectors.toList());
            partneretList.setAll(filtered);
        }
    }
}
