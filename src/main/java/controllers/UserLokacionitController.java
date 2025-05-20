package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.dto.Lokacionet.CreateLokacionetDto;
import models.dto.Lokacionet.Lokacionet;
import models.dto.Lokacionet.UpdateLokacionetDto;
import services.LanguageManager;
import services.LokacionetService;
import services.SceneManager;

import java.util.List;
import java.util.Locale;

public class UserLokacionitController {
    @FXML private TextField searchField;

    @FXML private TableView<Lokacionet> lokacionetTableView;
    @FXML private TableColumn<Lokacionet, String> colEmriLokacionit;
    @FXML private TableColumn<Lokacionet, String> colAdresa;
    @FXML private TableColumn<Lokacionet, String> colQyteti;
    @FXML private TableColumn<Lokacionet, String> colNrTelefonit;

    @FXML private Label messageLabel;

    private LokacionetService lokacionetService;
    private LanguageManager languageManager;

    public UserLokacionitController() {
        this.lokacionetService = new LokacionetService();
        this.languageManager = LanguageManager.getInstance();
    }

    @FXML
    public void initialize() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                lokacionetTableView.setItems(FXCollections.observableArrayList(lokacionetService.getAll()));
            } else {
                List<Lokacionet> filtruar = lokacionetService.kerkoLokacionMeEmrin(newValue);
                lokacionetTableView.setItems(FXCollections.observableArrayList(filtruar));
            }
        });

        colEmriLokacionit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmri_lokacionit()));
        colAdresa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresa()));
        colQyteti.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQyteti()));
        colNrTelefonit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNrtelefonit()));

        loadLokacionet();
    }

    private void loadLokacionet() {
        List<Lokacionet> lokacionet = lokacionetService.getAll();
        lokacionetTableView.getItems().setAll(lokacionet);
    }


    private int getSelectedLokacionId() {
        Lokacionet selected = lokacionetTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një lokacion në tabelë.");
            return -1;
        }
        return selected.getLokacionet_id();
    }
}
