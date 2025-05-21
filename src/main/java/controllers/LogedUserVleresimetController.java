package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.dto.Vleresimet.UpdateVleresimetDto;
import models.dto.Vleresimet.Vleresimet;
import services.SessionManager;
import services.VleresimetService;

import java.util.List;


public class LogedUserVleresimetController {
    @FXML private TextField komenti;
    @FXML private TextField searchField;
    @FXML private Spinner vleresimi;
    @FXML private TableView<Vleresimet> vleresimetTable;
    @FXML private TableColumn<Vleresimet, String> colVetura;
    @FXML private TableColumn<Vleresimet, String> colVleresimi;
    @FXML private TableColumn<Vleresimet, String> colKomenti;
    @FXML private TableColumn<Vleresimet, String> colData;
    @FXML private Label messageLabel;
    @FXML private Label averageLabel;
    private  VleresimetService vleresimetService;

    public LogedUserVleresimetController(){
        this.vleresimetService=new VleresimetService();
    }

    @FXML
    public void initialize() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
               loadVleresimet();
            } else {
                List<Vleresimet> filtruar = vleresimetService.searchByVeturaOrDate(newValue);
                vleresimetTable.setItems(FXCollections.observableArrayList(filtruar));
            }
        });
        colVetura.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVeturaEmri()));
        colVleresimi.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVleresimi())));
        colKomenti.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKomenti()));
        colData.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataVleresimit()));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        vleresimi.setValueFactory(valueFactory);
      loadVleresimet();
    }
    private void loadVleresimet() {
        int userId = getCurrentUserId();
        List<Vleresimet> vleresimet = vleresimetService.getVleresimetByUserId(userId);
        vleresimetTable.getItems().setAll(vleresimet);
    }


    private int getCurrentUserId() {
        // Merr ID-në e përdoruesit të kyçur permes login
        return SessionManager.getInstance().getcurrentUser().getPid();
    }
    @FXML
    private void handleUpdate(MouseEvent event) {
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
    private void handleDelete(MouseEvent event) {
        try {
            int selectedId = getSelectedVleresimId();
            if (selectedId == -1) return;

            vleresimetService.delete(selectedId);
            messageLabel.setText("Vlerësimi u fshi me sukses.");
            loadVleresimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }
    private int getSelectedVleresimId() {
        Vleresimet selected = vleresimetTable.getSelectionModel().getSelectedItem();
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
