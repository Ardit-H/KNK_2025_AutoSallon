package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ComboBox<String> comboStatusi;


    private final PorosiaService porosiaService = new PorosiaService();


    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("PorosiaId"));
        kidColumn.setCellValueFactory(new PropertyValueFactory<>("Kid"));
        veturaIdColumn.setCellValueFactory(new PropertyValueFactory<>("VeturaId"));
        cmimiColumn.setCellValueFactory(new PropertyValueFactory<>("CmimiOfruar"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("StatusiPorosise"));

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
    public void handleUpdate() {
        try {
            Porosia selected = porosiaTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showError("Zgjidh një porosi për përditësim.");
                return;
            }

            String cmimiText = txtCmimiOfruar.getText();
            double cmimi = (cmimiText == null || cmimiText.trim().isEmpty()) ? selected.getCmimiOfruar() : Double.parseDouble(cmimiText);

            String status = comboStatusi.getValue();

            if (status == null || status.trim().isEmpty()) {
                status = selected.getStatusiPorosise();
            }

            UpdatePorosiaDto dto = new UpdatePorosiaDto();
            dto.setPorosiaId(selected.getPorosiaId());
            dto.setCmimiOfruar(cmimi);
            dto.setStatusiPorosise(status);

            porosiaService.update(dto);
            showSuccess("Porosia u përditësua me sukses.");
            loadPorosite();
            clearForm();
        } catch (Exception e) {
            showError("Ne fushen cmimi i perditesuar lejohen vetem vlera numerike pozitive!");
        }
    }

    @FXML
    public void handleDelete() {
        try {
            Porosia selected = porosiaTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showError("Zgjidh një porosi për fshirje.");
                return;
            }

            porosiaService.delete(selected.getPorosiaId());
            showSuccess("Porosia u fshi me sukses.");
            loadPorosite();
            clearForm();
        } catch (Exception e) {

            showError("Gabim " + e.getMessage());
        }
    }

    private void clearForm() {
        txtCmimiOfruar.clear();
        comboStatusi.getSelectionModel().clearSelection();
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
