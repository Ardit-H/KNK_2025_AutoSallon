package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;
import services.KlientetService;
import services.LanguageManager;
import services.PerdoruesitService;
import java.util.List;
import models.dto.Perdoruesit.CreatePerdoruesitDto;

public class PerdoruesitController {
    @FXML private AnchorPane anchor;
    @FXML private TableView<Perdoruesit> perdoruesitTable;
    @FXML private TableColumn<Perdoruesit, String> colEmri;
    @FXML private TableColumn<Perdoruesit, String> colMbiemri;
    @FXML private TableColumn<Perdoruesit, String> colEmail;
    @FXML private TableColumn<Perdoruesit, String> colNrTelefonit;
    @FXML private TableColumn<Perdoruesit, String> colRoli;

    @FXML private TextField txtEmri;
    @FXML private TextField txtMbiemri;
    @FXML private TextField txtEmail;
    @FXML private TextField txtNrTelefonit;
    @FXML private TextField txtAdresa;
    @FXML private ComboBox<String> cmbRoli;
    @FXML private PasswordField txtFjalekalimi;
    @FXML private Button btnShto;
    @FXML private Button btnPerditeso;
    @FXML private Button btnFshij;

    private PerdoruesitService perdoruesitService;
    private LanguageManager languageManager;
    public PerdoruesitController(){
        this.perdoruesitService=new PerdoruesitService();
        this.languageManager= LanguageManager.getInstance();
    }

    private int selectedUserId = -1;
    @FXML
    public void initialize() {
        colEmri.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmri()));
        colMbiemri.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getMbiemri()));
        colEmail.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmail()));
        colNrTelefonit.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNrtelefonit()));
        colRoli.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getRoli()));

        loadUsers();

        perdoruesitTable.setOnMouseClicked(this::populateFields);
    }

    private void loadUsers() {
        List<Perdoruesit> perdoruesitList = perdoruesitService.getAll();
        ObservableList<Perdoruesit> observableList = FXCollections.observableArrayList(perdoruesitList);
        perdoruesitTable.setItems(observableList);
    }

    private void populateFields(MouseEvent event) {
        Perdoruesit p = perdoruesitTable.getSelectionModel().getSelectedItem();
        if (p != null) {
            selectedUserId = p.getPid();
            txtEmri.setText(p.getEmri());
            txtMbiemri.setText(p.getMbiemri());
            txtEmail.setText(p.getEmail());
            txtNrTelefonit.setText(p.getNrtelefonit());
            txtAdresa.setText(p.getAdresa());
            cmbRoli.setValue(p.getRoli());
        }
    }

    @FXML
    private void handleShto() {
        try {
            String roli = cmbRoli.getValue();
            if (!roli.equals("Admin") && !roli.equals("User")) {
                throw new IllegalArgumentException("Roli duhet të jetë 'admin' ose 'user'.");
            }

            // Krijimi i objektit CreatePerdoruesitDto
            CreatePerdoruesitDto dto = new CreatePerdoruesitDto(
                    txtEmri.getText(),
                    txtMbiemri.getText(),
                    txtEmail.getText(),
                    txtNrTelefonit.getText(),
                    txtAdresa.getText(),
                    txtFjalekalimi.getText()
            );

            // Thirrja e metodës create në PerdoruesitService
            Perdoruesit perdoruesi = perdoruesitService.create(dto);

            // Rifreskimi i tabelës pas krijimit
            loadUsers();
            clearFields();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private UpdatePerdoruesitDto updateDto;
    @FXML
    private void handlePerditeso() {
        try {



            if (selectedUserId == -1) {
                throw new IllegalArgumentException("Përditësimi nuk mund të bëhet pa zgjedhur një përdorues.");
            }
            updateDto.setId(selectedUserId);

            UpdatePerdoruesitDto updateDto = new UpdatePerdoruesitDto();
            updateDto.setEmail(txtEmail.getText());
            updateDto.setNrtelefonit(txtNrTelefonit.getText());
            updateDto.setAdresa(txtAdresa.getText());
            updateDto.setRoli(cmbRoli.getValue());
            updateDto.setFjalekalimi(txtFjalekalimi.getText());

            // Thirrja e metodës update në PerdoruesitService
            Perdoruesit updated = perdoruesitService.update(updateDto);

            // Rifreskimi i tabelës pas përditësimit
            loadUsers();
            clearFields();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }



    @FXML
    private void handleFshij() {
        try {
            loadUsers();
            clearFields();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }


    private void clearFields() {

        txtEmri.clear();
        txtMbiemri.clear();
        txtEmail.clear();
        txtNrTelefonit.clear();
        txtAdresa.clear();
        cmbRoli.setValue(null);
        txtFjalekalimi.clear();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
