package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.dto.Ofertat.CreateOfertaDto;
import models.dto.Ofertat.Oferta;
import models.dto.Ofertat.UpdateOfertaDto;
import services.OfertaService;
import services.VeturatService;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OfertaDashboardController {

    private final OfertaService ofertaService = new OfertaService();
    private final VeturatService veturaService = new VeturatService();

    @FXML
    private TableView<Oferta> ofertaTable;
    @FXML
    private TableColumn<Oferta, Integer> idColumn;
    @FXML
    private TableColumn<Oferta, Integer> veturaIdColumn;
    @FXML
    private TableColumn<Oferta, Double> zbritjaIdColumn;
    @FXML
    private TableColumn<Oferta, Double> cmimiFinalColumn;
    @FXML
    private TableColumn<Oferta, String> dataFillimitColumn;
    @FXML
    private TableColumn<Oferta, String> dataMbarimitColumn;

    @FXML
    private TextField txtVeturaId;
    @FXML
    private TextField txtZbritja;
    @FXML
    private TextField txtCmimiFinal;
    @FXML
    private DatePicker dataFillimit;
    @FXML
    private DatePicker dataMbarimit;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ofertaId"));
        veturaIdColumn.setCellValueFactory(new PropertyValueFactory<>("veturaId"));
        zbritjaIdColumn.setCellValueFactory(new PropertyValueFactory<>("zbritja"));
        cmimiFinalColumn.setCellValueFactory(new PropertyValueFactory<>("cmimiFinal"));
        dataFillimitColumn.setCellValueFactory(new PropertyValueFactory<>("dataFillimit"));
        dataMbarimitColumn.setCellValueFactory(new PropertyValueFactory<>("dataMbarimit"));

        loadOferta();
    }

    private void loadOferta() {
        List<Oferta> oferta = ofertaService.getAll();
        ofertaTable.setItems(FXCollections.observableArrayList(oferta));
    }

    @FXML
    public void handleCreate() {
        try {
            int veturaId = Integer.parseInt(txtVeturaId.getText());


            veturaService.getById(veturaId);

            CreateOfertaDto dto = new CreateOfertaDto(
            veturaId,
            Double.parseDouble(txtZbritja.getText()),
            Double.parseDouble(txtCmimiFinal.getText()),
            dataFillimit.getValue().format(formatter),
            dataMbarimit.getValue().format(formatter)
            );


            ofertaService.create(dto);
            showSuccess("Oferta eshte krijuar me sukses.");
            loadOferta();
            clearForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void handleUpdate() {
        try {
            Oferta selected = ofertaTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showError("Zgjidh një ofertë për përditësim.");
                return;
            }

            UpdateOfertaDto dto = new UpdateOfertaDto();
            dto.setOfertaId(selected.getOfertaId());

            if (!txtZbritja.getText().trim().isEmpty())
                dto.setZbritja(Double.parseDouble(txtZbritja.getText()));
            if (!txtCmimiFinal.getText().trim().isEmpty())
                dto.setCmimiFinal(Double.parseDouble(txtCmimiFinal.getText()));
            if (dataFillimit.getValue() != null)
                dto.setDataFillimit(dataFillimit.getValue().format(formatter));
            if (dataMbarimit.getValue() != null)
                dto.setDataMbarimit(dataMbarimit.getValue().format(formatter));

            ofertaService.update(dto);
            showSuccess("Oferta eshte perditsuar me sukses.");
            loadOferta();
            clearForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void handleDelete() {
        try {
            Oferta selected = ofertaTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showError("Zgjidh një ofertë për fshirje.");
                return;
            }

            ofertaService.delete(selected.getOfertaId());
            showSuccess("Oferta eshte fshire me sukses.");
            loadOferta();
            clearForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void clearForm() {
        txtVeturaId.clear();
        txtZbritja.clear();
        txtCmimiFinal.clear();
        dataFillimit.setValue(null);
        dataMbarimit.setValue(null);
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
