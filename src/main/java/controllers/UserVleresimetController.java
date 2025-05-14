package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.dto.Klientet.Klientet;
import models.dto.KomentetDto;
import models.dto.Vleresimet.Vleresimet;
import services.LanguageManager;
import services.VleresimetService;

import java.util.List;

public class UserVleresimetController {
    @FXML private TextField searchField;
    @FXML private TableView<Vleresimet> VleresimetTableView;
    @FXML private TableColumn<Vleresimet, String> colPerdoruesi;
    @FXML private TableColumn<Vleresimet, String> colVetura;
    @FXML private TableColumn<Vleresimet, String> colVleresimi;
    @FXML private TableColumn<Vleresimet, String> colKomenti;
    @FXML private TableColumn<Vleresimet, String> colDataVleresimit;
    @FXML private VleresimetService vleresimetService;
    private LanguageManager languageManager;

    public UserVleresimetController(){
        this.vleresimetService=new VleresimetService();
        this.languageManager=LanguageManager.getInstance();
    }
    @FXML
    public void initialize() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                VleresimetTableView.setItems(FXCollections.observableArrayList(vleresimetService.getVleresimetWithJoins()));
            } else {
                List<Vleresimet> filtruar = vleresimetService.searchByVeturaOrPerdoruesOrDate(newValue);
                VleresimetTableView.setItems(FXCollections.observableArrayList(filtruar));
            }
        });
        colPerdoruesi.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPerdoruesiEmriPlote())));
        colVetura.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVeturaEmri())));
        colVleresimi.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVleresimi())));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        colKomenti.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKomenti()));
        colDataVleresimit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataVleresimit()));
        loadVleresimet();

    }

    private void loadVleresimet() {
        List<Vleresimet> vleresimet = vleresimetService.getVleresimetWithJoins();
        VleresimetTableView.getItems().setAll(vleresimet);
    }
    @FXML private void handleShowAverage() {
        Vleresimet selected = VleresimetTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Gabim", "Ju lutem selektoni një vlerësim!", Alert.AlertType.ERROR);
            return;
        }
        int veturaId = selected.getVeturaId();
        double mesatarja = vleresimetService.getMesatarjaEVleresimevePerVeture(veturaId);
        KomentetDto komentetDto = vleresimetService.getPositiveAndNegativeVleresimet(veturaId);
        StringBuilder content = new StringBuilder();
        content.append("Mesatarja e vlerësimeve për veturën \"")
                .append(selected.getVeturaEmri())
                .append("\" është: ")
                .append(String.format("%.2f", mesatarja))
                .append("\n\n");

        content.append("💬 Komente Pozitive:\n");
        if (komentetDto.getKomentetPozitive().isEmpty()) {
            content.append("- Asnjë koment pozitiv.\n");
        } else {
            for (String kom : komentetDto.getKomentetPozitive()) {
                content.append("- ").append(kom).append("\n");
            }
        }

        content.append("\n💬 Komente Negative:\n");
        if (komentetDto.getKomentetNegative().isEmpty()) {
            content.append("- Asnjë koment negativ.\n");
        } else {
            for (String kom : komentetDto.getKomentetNegative()) {
                content.append("- ").append(kom).append("\n");
            }
        }
        showAlert("Detaje të Vlerësimeve", content.toString(), Alert.AlertType.INFORMATION);
    }
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
