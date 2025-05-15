package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import models.dto.Rezervimet.CreateRezervimetDto;
import models.dto.Rezervimet.Rezervimet;
import models.dto.Rezervimet.UpdateRezervimetDto;
import services.LanguageManager;
import services.RezervimetService;

import java.time.LocalDate;

public class RezervimetController {

    @FXML
    private TableView<Rezervimet> tabelaRezervimet;

    @FXML
    private TableColumn<Rezervimet, Integer> colRezervimiId;

    @FXML
    private TableColumn<Rezervimet, String> colKlientiId;

    @FXML
    private TableColumn<Rezervimet, String> colVeturaId;

    @FXML
    private TableColumn<Rezervimet, String> colDataRezervimit;

    @FXML
    private TableColumn<Rezervimet, String> colStatusi;

    @FXML
    private TextField klientiIdField;

    @FXML
    private TextField veturaIdField;

    @FXML
    private DatePicker dataRezervimitPicker;

    @FXML
    private TextField statusiField;

    @FXML
    private TextField rezervimiIdField;

    private RezervimetService rezervimetService;
    private LanguageManager languageManager;

    public RezervimetController(){
        this.rezervimetService = new RezervimetService();
        this.languageManager = LanguageManager.getInstance();
    }

    @FXML
    public void initialize(){
        colRezervimiId.setCellValueFactory(new PropertyValueFactory<>("rezervimiId"));
        colKlientiId.setCellValueFactory(new PropertyValueFactory<>("klientiId"));
        colVeturaId.setCellValueFactory(new PropertyValueFactory<>("veturaId"));
        colDataRezervimit.setCellValueFactory(new PropertyValueFactory<>("dataRezervimit"));
        colStatusi.setCellValueFactory(new PropertyValueFactory<>("statusi"));
        loadRezervimet();
    }

    public void loadRezervimet(){
        ObservableList<Rezervimet> rezervimeList = FXCollections.observableList(rezervimetService.getAll());
        tabelaRezervimet.setItems(rezervimeList);
    }

    @FXML
    public void handleCreate(){
        try {
            CreateRezervimetDto dto = new CreateRezervimetDto(
                    Integer.parseInt(klientiIdField.getText()),
                    Integer.parseInt(veturaIdField.getText()),
                    dataRezervimitPicker.getValue().toString(),
                    statusiField.getText()
            );

            rezervimetService.create(dto);
            loadRezervimet();
            clearFields();
        } catch (Exception e) {
            showAlert("Gabim gjate krijimit: " + e.getMessage());
        }
    }
    @FXML
    public void handleRowSelect(MouseEvent event) {
        Rezervimet rezervimi = tabelaRezervimet.getSelectionModel().getSelectedItem();

        if (rezervimi != null) {
            rezervimiIdField.setText(String.valueOf(rezervimi.getRezervimiId()));
            klientiIdField.setText(String.valueOf(rezervimi.getKlientiId()));
            veturaIdField.setText(String.valueOf(rezervimi.getVeturaId()));
            dataRezervimitPicker.setValue(LocalDate.parse(rezervimi.getDataRezervimit()));
            statusiField.setText(rezervimi.getStatusi());
        }
    }
    @FXML
    public void handleUpdate(){
        try {
            UpdateRezervimetDto dto = new UpdateRezervimetDto();
            dto.setRezervimiId(Integer.parseInt(rezervimiIdField.getText()));
            dto.setKlientiId(Integer.parseInt(klientiIdField.getText()));
            dto.setVeturaId(Integer.parseInt(veturaIdField.getText()));
            dto.setDataRezervimet(dataRezervimitPicker.getValue().toString());
            dto.setStatusi(statusiField.getText());

            rezervimetService.update(dto);
            loadRezervimet();
            clearFields();
        } catch (Exception e) {
            showAlert("Gabim gjate përditësimit: " + e.getMessage());
        }
    }

    @FXML
    public void handleDelete(){
        try {
            int id = Integer.parseInt(rezervimiIdField.getText());
            rezervimetService.delete(id);
            loadRezervimet();
            clearFields();
        } catch (Exception e) {
            showAlert("Gabim gjate fshirjes: " + e.getMessage());
        }
    }



    private void clearFields() {
        rezervimiIdField.clear();
        klientiIdField.clear();
        veturaIdField.clear();
        dataRezervimitPicker.setValue(null);
        statusiField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Njoftim");
        alert.setContentText(message);
        alert.show();
    }


}
