package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.dto.Rezervimet.CreateRezervimetDto;
import models.dto.Veturat.Veturat;
import services.RezervimetService;

import java.time.LocalDate;
import java.util.List;

public class UserRezervimetController {

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TableView<Veturat> tabelaVeturat;

    @FXML
    private TableColumn<Veturat, Integer> colId;

    @FXML
    private TableColumn<Veturat, String> colProdhuesi;

    @FXML
    private TableColumn<Veturat, String> colModeli;

    @FXML
    private Button btnRezervo;

    @FXML
    private Label lblStatus;

    private RezervimetService rezervimetService;

    public UserRezervimetController() {
        this.rezervimetService = new RezervimetService();
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProdhuesi.setCellValueFactory(new PropertyValueFactory<>("prodhuesi"));
        colModeli.setCellValueFactory(new PropertyValueFactory<>("modeli"));

        dataPicker.setOnAction(event -> {
            if (dataPicker.getValue() != null) {
                loadVeturatELira(dataPicker.getValue());
            }
        });
    }

    private void loadVeturatELira(LocalDate data) {
        try {
            String dataStr = data.toString();
            List<Veturat> veturaELira = rezervimetService.gjejVeturatELiraPerDate(dataStr);
            tabelaVeturat.setItems(FXCollections.observableList(veturaELira));
            lblStatus.setText("U gjetën " + veturaELira.size() + " vetura të lira.");
        } catch (Exception e) {
            lblStatus.setText("Gabim gjatë ngarkimit: " + e.getMessage());
        }
    }

    @FXML
    public void handleRezervo() {
        Veturat veturaZgjedhur = tabelaVeturat.getSelectionModel().getSelectedItem();
        LocalDate data = dataPicker.getValue();

        if (veturaZgjedhur == null || data == null) {
            lblStatus.setText("Zgjidh një veturë dhe një datë.");
            return;
        }

        try {
            // Shembull: klientiId është 1
            CreateRezervimetDto dto = new CreateRezervimetDto(
                    1, // klientiId
                    veturaZgjedhur.getId(),
                    data.toString(),
                    "aktiv"
            );
            rezervimetService.create(dto);
            lblStatus.setText("Rezervimi u bë me sukses!");
            loadVeturatELira(data); // rifresko listën
        } catch (Exception e) {
            lblStatus.setText("Gabim gjatë rezervimit: " + e.getMessage());
        }
    }
}
