package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.dto.Perdoruesit.CreatePerdoruesitDto;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;
import services.PerdoruesitService;

import javafx.event.ActionEvent;


public class PerdoruesitController {

    @FXML private TableView<Perdoruesit> tableUsers;
    @FXML private TableColumn<Perdoruesit, Integer> colId;
    @FXML private TableColumn<Perdoruesit, String> colEmri;
    @FXML private TableColumn<Perdoruesit, String> colMbiemri;
    @FXML private TableColumn<Perdoruesit, String> colEmail;
    @FXML private TableColumn<Perdoruesit, String> colRoli;

    @FXML private TextField txtEmri;
    @FXML private TextField txtMbiemri;
    @FXML private TextField txtEmail;
    @FXML private TextField txtNrTelefonit;

    @FXML private PasswordField txtFjalekalimi;
    @FXML private ComboBox<String> cmbRoli;

    private final PerdoruesitService perdoruesitService = new PerdoruesitService();
    private int selectedUserId = -1;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Pid"));
        colEmri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        colMbiemri.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRoli.setCellValueFactory(new PropertyValueFactory<>("roli"));

        cmbRoli.setItems(FXCollections.observableArrayList("Admin", "User"));
        loadUsers();

        tableUsers.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectedUserId = newSel.getPid();
                txtEmri.setText(newSel.getEmri());
                txtMbiemri.setText(newSel.getMbiemri());
                txtEmail.setText(newSel.getEmail());
                txtNrTelefonit.setText(newSel.getNrtelefonit());

                cmbRoli.setValue(newSel.getRoli());
                txtFjalekalimi.clear(); // nuk ruhet fjalëkalimi origjinal
            }
        });
    }

    private void loadUsers() {
        tableUsers.setItems(FXCollections.observableArrayList(perdoruesitService.getAll()));
    }



    @FXML
    private void handleShto(MouseEvent event) {
        try {
            CreatePerdoruesitDto dto = new CreatePerdoruesitDto();
            dto.setEmri(txtEmri.getText());
            dto.setMbiemri(txtMbiemri.getText());
            dto.setEmail(txtEmail.getText());
            dto.setNrtelefonit(txtNrTelefonit.getText());


            String roli = cmbRoli.getValue();
            if (roli == null || (!roli.equals("Admin") && !roli.equals("User"))) {
                throw new IllegalArgumentException("Roli duhet të jetë 'Admin' ose 'User'.");
            }
            dto.setRoli(roli);

            dto.setFjalekalimi(txtFjalekalimi.getText());

            perdoruesitService.create(dto);
            loadUsers();
            clearFields();
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    @FXML
    private void handlePerditeso(MouseEvent event) {
        if (selectedUserId == -1) {
            showMessage("Ju lutem zgjidhni një përdorues për ta përditësuar.");
            return;
        }

        String email = txtEmail.getText();
        String nrTelefonit = txtNrTelefonit.getText();
        String fjalekalimi = txtFjalekalimi.getText();
        String roli = cmbRoli.getValue();

        UpdatePerdoruesitDto dto = new UpdatePerdoruesitDto();
        dto.setId(selectedUserId);
        dto.setEmail(email);
        dto.setNrtelefonit(nrTelefonit);
        dto.setRoli(roli);

        if (fjalekalimi != null && !fjalekalimi.trim().isEmpty()) {
            dto.setFjalekalimi(fjalekalimi); // do kriptohet në service
        }

        try {
            perdoruesitService.update(dto);
            showMessage("Përdoruesi u përditësua me sukses.");
            loadUsers();
            clearFields();
        } catch (Exception e) {
            showMessage("Gabim gjatë përditësimit: " + e.getMessage());
        }
    }

    @FXML
    private void handleFshij(MouseEvent event) {
        if (selectedUserId == -1) {
            showMessage("Ju lutem zgjidhni një përdorues për ta fshirë.");
            return;
        }

        try {
            boolean deleted = perdoruesitService.delete(selectedUserId);
            if (deleted) {
                showMessage("Përdoruesi u fshi me sukses.");
                loadUsers();
                clearFields();
            } else {
                showMessage("Përdoruesi nuk u fshi.");
            }
        } catch (Exception e) {
            showMessage("Gabim gjatë fshirjes: " + e.getMessage());
        }
    }

    private void clearFields() {
        selectedUserId = -1;
        txtEmri.clear();
        txtMbiemri.clear();
        txtEmail.clear();
        txtNrTelefonit.clear();
        txtFjalekalimi.clear();
        cmbRoli.setValue(null);
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
