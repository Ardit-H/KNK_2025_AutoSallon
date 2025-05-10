package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.dto.Vleresimet.Vleresimet;
import services.LanguageManager;
import services.VleresimetService;

import java.util.List;

public class UserVleresimetController {
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
}
