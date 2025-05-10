package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.dto.Klientet.Klientet;
import models.dto.Perdoruesit.CreatePerdoruesitDto;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;
import services.PerdoruesitService;

import java.util.List;

public class PerdoruesitController {
    @FXML
    private TextField txtEmri;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFjalekalimi;

    @FXML
    private TextField txtRoli;

    @FXML
    private TableView<Perdoruesit> perdoruesitTable;

    @FXML
    private TableColumn<Perdoruesit, String> colFjalekalimi;

    @FXML
    private TableColumn<Perdoruesit, String> colEmri;

    @FXML
    private TableColumn<Perdoruesit, String> colEmail;

    @FXML
    private TableColumn<Perdoruesit, String> colRoli;

    @FXML
    private Label messageLabel;

    private PerdoruesitService perdoruesitService;

    public PerdoruesitController() {
        this.perdoruesitService = new PerdoruesitService();
    }

    @FXML
    public void initialize() {
        colEmri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmri()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colRoli.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoli()));
//        colFjalekalimi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFjalekalimi()));

        loadPerdoruesit();
    }

    private void loadPerdoruesit() {
        List<Perdoruesit> perdoruesit = perdoruesitService.getAll();
        perdoruesitTable.getItems().setAll(perdoruesit);
    }

//    @FXML
//    private void handleCreate(MouseEvent event) {
//        try {
//            CreatePerdoruesitDto dto = new CreatePerdoruesitDto(
//                    txtEmri.getText(),
//                    txtEmail.getText(),
//                    txtFjalekalimi.getText()
//            );
//            perdoruesitService.create(dto); // Këtu brenda bëhet hashing dhe salt
//            messageLabel.setText("Perdoruesi u shtua me sukses!");
//            loadPerdoruesit();
//            clearForm();
//        } catch (Exception e) {
//            messageLabel.setText("Gabim: " + e.getMessage());
//        }
//    }

    @FXML
    private void handleUpdate(MouseEvent event) {
        try {
            int selectedId = getSelectedPerdoruesitId();
            if (selectedId == -1) return;

            UpdatePerdoruesitDto dto = new UpdatePerdoruesitDto();
            dto.setId(selectedId);
            if (!txtEmail.getText().trim().isEmpty())
                dto.setEmail(txtEmail.getText().trim());
            if (!txtFjalekalimi.getText().trim().isEmpty())
                dto.setFjalekalimi(txtFjalekalimi.getText().trim());
            if (!txtRoli.getText().trim().isEmpty())
                dto.setRoli(txtRoli.getText().trim());

            perdoruesitService.update(dto);
            messageLabel.setText("Perdoruesi u perditesua me sukses!");
            loadPerdoruesit();

        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(MouseEvent event) {
        try {
            int selectedId = getSelectedPerdoruesitId();
            if (selectedId == -1) return;

            perdoruesitService.delete(selectedId);
            messageLabel.setText("Perdoruesi u fshi me sukses.");
            loadPerdoruesit();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private int getSelectedPerdoruesitId() {
        Perdoruesit selected = perdoruesitTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një përdorues në tabelë!");
            return -1;
        }
        return selected.getPid();
    }

    private void clearForm() {
        txtEmri.clear();
        txtEmail.clear();
        txtFjalekalimi.clear();
        txtRoli.clear();
    }
}
