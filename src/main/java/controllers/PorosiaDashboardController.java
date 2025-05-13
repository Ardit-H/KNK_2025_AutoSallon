package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.dto.Porosite.CreatePorosiaDto;
import models.dto.Porosite.Porosia;
import models.dto.Porosite.UpdatePorosiaDto;
import services.PorosiaService;

import java.util.List;

public class PorosiaDashboardController {
    @FXML
    private TableView<Porosia> porosiaTable;
    @FXML
    private TableColumn<Porosia, Integer> idColumn;
    @FXML
    private TableColumn<Porosia, Integer> kidColumn;
    @FXML
    private TableColumn<Porosia, Integer> veturaIdColumn;
    @FXML
    private TableColumn<Porosia, Double> cmimiColumn;
    @FXML
    private TableColumn<Porosia, String> statusColumn;
    @FXML
    private TextField txtCmimiOfruar;
    @FXML
    private Label messageLabel;
    @FXML
    private ComboBox<String> comboStatusi;
//    @FXML
//    private Button btnPerditeso;

    private final PorosiaService porosiaService = new PorosiaService();


    @FXML
    public void initialize() {
        // Map properties to columns

        idColumn.setCellValueFactory(new PropertyValueFactory<>("porosiaId"));
        kidColumn.setCellValueFactory(new PropertyValueFactory<>("kid"));
        veturaIdColumn.setCellValueFactory(new PropertyValueFactory<>("veturaId"));
        cmimiColumn.setCellValueFactory(new PropertyValueFactory<>("cmimiOfruar"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusiPorosise"));

        comboStatusi.getItems().addAll("Ne pritje", "Ne proces", "E kompletuar", "E refuzuar");
        loadPorosite();
        System.out.println("Initialize: ");

    }

    private void loadPorosite() {
        List<Porosia> porosite = porosiaService.getAll();
        System.out.println("Porosite e gjetura: " + porosite.size());
        porosiaTable.setItems(FXCollections.observableArrayList(porosite));
    }

    @FXML
    private void handleUpdate(MouseEvent event) {
        try {
            Porosia selected = porosiaTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një porosi për përditësim.");
                return;
            }

            double cmimi = Double.parseDouble(txtCmimiOfruar.getText());
            String status = comboStatusi.getValue();

            UpdatePorosiaDto dto = new UpdatePorosiaDto();
            dto.setPorosiaId(selected.getPorosiaId());
            dto.setCmimiOfruar(cmimi);
            dto.setStatusiPorosise(status);

            porosiaService.update(dto);
            messageLabel.setText("Porosia u përditësua me sukses.");
            loadPorosite();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(MouseEvent event) {
        try {
            Porosia selected = porosiaTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                messageLabel.setText("Zgjidh një porosi për fshirje.");
                return;
            }

            porosiaService.delete(selected.getPorosiaId());
            messageLabel.setText("Porosia u fshi me sukses.");
            loadPorosite();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private void clearForm() {
        txtCmimiOfruar.clear();
        comboStatusi.getSelectionModel().clearSelection();
    }

}
