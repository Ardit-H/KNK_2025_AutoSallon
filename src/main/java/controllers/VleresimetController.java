
package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.dto.KomentetDto;
import models.dto.Vleresimet.CreateVleresimetDto;
import models.dto.Vleresimet.UpdateVleresimetDto;
import models.dto.Vleresimet.Vleresimet;
import services.LanguageManager;
import services.SceneManager;
import services.VleresimetService;

import java.util.List;
import java.util.Locale;

public class VleresimetController {

    @FXML private TextField veturaId;
    @FXML private TextField perdoruesiId;
    @FXML private Spinner vleresimi;
    @FXML private TextField komenti;
    @FXML private Label messageLabel;
    @FXML private TextField searchField;
    @FXML private TableView<Vleresimet> VleresimetTableView;
    @FXML private TableColumn<Vleresimet, String> colPerdoruesi;
    @FXML private TableColumn<Vleresimet, String> colVetura;
    @FXML private TableColumn<Vleresimet, String> colVleresimi;
    @FXML private TableColumn<Vleresimet, String> colKomenti;
    @FXML private TableColumn<Vleresimet, String> colDataVleresimit;
    @FXML private VleresimetService vleresimetService;
    private LanguageManager languageManager;

    public VleresimetController(){
        this.vleresimetService=new VleresimetService();
        this.languageManager=LanguageManager.getInstance();
    }
    @FXML
    public void initialize() {
        colPerdoruesi.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPerdoruesiEmriPlote())));
        colVetura.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVeturaEmri())));
        colVleresimi.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVleresimi())));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        vleresimi.setValueFactory(valueFactory);
        colKomenti.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKomenti()));
        colDataVleresimit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataVleresimit()));
        loadVleresimet();

    }

    private void loadVleresimet() {
        List<Vleresimet> vleresimet = vleresimetService.getVleresimetWithJoins();
        VleresimetTableView.getItems().setAll(vleresimet);
    }

    @FXML
    private void handleUpdateVleresim(MouseEvent event) {
        try {
            int selectedId = getSelectedVleresimId();
            if (selectedId == -1) return;
            Integer vleresimiInt = null;
            if (vleresimi.getValue() != null) {
                vleresimiInt = (Integer) vleresimi.getValue();
            }
            UpdateVleresimetDto dto = new UpdateVleresimetDto();
            dto.setVleresimiId(selectedId);
            if (vleresimiInt != null) {
                dto.setVleresimi(vleresimiInt);
            }
            if (!komenti.getText().trim().isEmpty())
                dto.setKomenti(komenti.getText().trim());
            vleresimetService.update(dto);
            messageLabel.setText("Vlerësimi u përditësua me sukses.");
            loadVleresimet();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }


    @FXML
    private void handleDeleteVleresim(MouseEvent event) {
        try {
            int selectedId = getSelectedVleresimId();
            if (selectedId == -1) return;

            vleresimetService.delete(selectedId);
            messageLabel.setText("Vleresimi u fshi me sukses.");
            loadVleresimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }
    @FXML
    private void onSearchClicked(){
        String keyword = searchField.getText();
        if (keyword == null || keyword.isBlank()) {
            loadVleresimet();
        } else {
            List<Vleresimet> filtered = vleresimetService.searchByVeturaOrPerdoruesOrDate(keyword);
            VleresimetTableView.setItems(FXCollections.observableArrayList(filtered));
        }
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

    private int getSelectedVleresimId() {
        Vleresimet selected = VleresimetTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një vlerësim në listë.");
            return -1;
        }
        return selected.getVleresimiId();
    }

    private void clearForm() {
        vleresimi.getValueFactory().setValue(1);
        komenti.clear();
    }
}

