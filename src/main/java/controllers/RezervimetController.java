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
import services.RezervimetService;

public class RezervimetController {

    @FXML
    private TableView<Rezervimet> tabelaRezervimet;

    @FXML
    private TableColumn<Rezervimet, String> colId;

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
    private TextField dataRezervimitField;

    @FXML
    private TextField statusiField;

    @FXML
    private TextField rezervimiIdField;


    private final RezervimetService rezervimetService = new RezervimetService();

    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("rezervimiId"));
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
        try{
            CreateRezervimetDto dto = new CreateRezervimetDto(
                    Integer.parseInt(klientiIdField.getText()),
                    Integer.parseInt(veturaIdField.getText()),
                    dataRezervimitField.getText(),
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
    public void handleUpdate(){
        try{
            UpdateRezervimetDto dto = new UpdateRezervimetDto();
            dto.setRezervimiId(Integer.parseInt(rezervimiIdField.getText()));
            dto.setKlientiId(Integer.parseInt(klientiIdField.getText()));
            dto.setVeturaId(Integer.parseInt(veturaIdField.getText()));
            dto.setDataRezervimet(dataRezervimitField.getText());
            dto.setStatusi(statusiField.getText());

            rezervimetService.update(dto);
            loadRezervimet();
            clearFields();
        } catch (Exception e) {
            showAlert("Gabim gjate perditsimit" +  e.getMessage());
        }
    }

    @FXML
    public void handleDelete(){
        try{
            int id = Integer.parseInt(rezervimiIdField.getText());
            rezervimetService.delete(id);
            loadRezervimet();
            clearFields();
        } catch (Exception e) {
            showAlert("Gabim gjate fshirjes: " + e.getMessage());
        }
    }


    @FXML
    public void handleRowSelect(MouseEvent event) {
        Rezervimet rezervimi = tabelaRezervimet.getSelectionModel().getSelectedItem();
        if (rezervimi != null) {
            rezervimiIdField.setText(String.valueOf(rezervimi.getRezervimiId()));
            klientiIdField.setText(String.valueOf(rezervimi.getKlientiId()));
            veturaIdField.setText(String.valueOf(rezervimi.getVeturaId()));
            dataRezervimitField.setText(rezervimi.getDataRezervimit());
            statusiField.setText(rezervimi.getStatusi());
        }
    }

    private void clearFields() {
        rezervimiIdField.clear();
        klientiIdField.clear();
        veturaIdField.clear();
        dataRezervimitField.clear();
        statusiField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
