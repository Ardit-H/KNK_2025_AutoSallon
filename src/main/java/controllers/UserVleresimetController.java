package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.dto.Vleresimet.Vleresimet;
import services.SessionManager;
import services.VleresimetService;

import java.util.List;

public class UserVleresimetController {
    @FXML
    private TableView<Vleresimet> vleresimetTable;
    @FXML private TableColumn<Vleresimet, String> colVetura;
    @FXML private TableColumn<Vleresimet, String> colVleresimi;
    @FXML private TableColumn<Vleresimet, String> colKomenti;
    @FXML private TableColumn<Vleresimet, String> colData;

    private final VleresimetService vleresimetService=new VleresimetService();

    @FXML
    public void initialize() {
        colVetura.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVeturaEmri()));
        colVleresimi.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVleresimi())));
        colKomenti.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKomenti()));
        colData.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataVleresimit()));

        int userId = getCurrentUserId();
        List<Vleresimet> vleresimet = vleresimetService.getVleresimetByUserId(userId);
        vleresimetTable.getItems().setAll(vleresimet);
    }

    private int getCurrentUserId() {
        // Merr ID-në e përdoruesit të kyçur permes login
        return SessionManager.getInstance().getcurrentUser().getPid();
    }
}
