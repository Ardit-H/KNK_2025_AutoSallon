package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.dto.Ofertat.Oferta;
import models.dto.Pagesat.CreatePagesaDto;
import models.dto.Pagesat.Pagesa;
import models.dto.Pagesat.UpdatePagesaDto;
import services.PagesaService;
import services.PorosiaService;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PagesatDashboardController {
 PagesaService pagesaService = new PagesaService();
 PorosiaService porosiaService = new PorosiaService();

    @FXML
    private TableView<Pagesa> pagesatTable;
    @FXML
    private TableColumn<Pagesa, Integer> idColumn;
    @FXML
    private TableColumn<Pagesa, Integer> porosiaIdColumn;
    @FXML
    private TableColumn<Pagesa, String> metodaPagesesColumn;
    @FXML
    private TableColumn<Pagesa, Double> shumaColumn;
    @FXML
    private TableColumn<Pagesa, Integer> dataPagesesColumn;
    @FXML
    private TextField txtShumaPageses;
    @FXML
    private DatePicker dataPageses;
    @FXML
    private ComboBox<String> comboMetodaPageses;
    @FXML
    private TextField txtPorosia;
    @FXML
    public Button btnKrijo;
    @FXML
    public Button btnPerditso;
    @FXML
    public Button btnFshije;


    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("PagesaId"));
        porosiaIdColumn.setCellValueFactory(new PropertyValueFactory<>("porosiaId"));
        metodaPagesesColumn.setCellValueFactory(new PropertyValueFactory<>("metodaPageses"));
        shumaColumn.setCellValueFactory(new PropertyValueFactory<>("shuma"));
        dataPagesesColumn.setCellValueFactory(new PropertyValueFactory<>("dataPageses"));

        comboMetodaPageses.getItems().addAll("KARTELE", "CASH", "KREDI", "TJETER");

        loadPagesa();
    }

    private void loadPagesa() {
        List<Pagesa> pagesa = pagesaService.getAll();
        pagesatTable.setItems(FXCollections.observableArrayList(pagesa));
    }

    @FXML
    public void handleCreate() {
        try {
            if (txtPorosia.getText().trim().isEmpty()) {
                showError("Ju lutem vendosni ID-në e porosisë.");
                return;
            }

            int porosiaId = Integer.parseInt(txtPorosia.getText());

            if (porosiaService.getById(porosiaId) == null) {
                showError("Porosia me këtë ID nuk ekziston.");
                return;
            } else if(porosiaId <= 0){
                showError("ID e porosise nuk mund te jete numer negative.");
            }

            CreatePagesaDto dto = new CreatePagesaDto(
                    porosiaId,
                    comboMetodaPageses.getValue(),
                    Double.parseDouble(txtShumaPageses.getText()),
                    dataPageses.getValue().format(formatter)
            );

            pagesaService.create(dto);

            showSuccess("Pagesa u shtua me sukses.");
            loadPagesa();
            clearForm();
        } catch (NumberFormatException e) {
            showError("Shuma ose PorosiaID nuk janë në format të saktë numerik.");
        } catch (Exception e) {
            showError("Gabim gjatë krijimit të pagesës: " + e.getMessage());
        }
    }

    @FXML
    public void handleUpdate() {
        try {
            Pagesa selected = pagesatTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showError("Zgjidh një pagesë për përditësim.");
                return;
            }

            UpdatePagesaDto dto = new UpdatePagesaDto();
            dto.setPagesaId(selected.getPagesaId());

            if (!txtShumaPageses.getText().trim().isEmpty())
                dto.setShuma(Double.parseDouble(txtShumaPageses.getText()));

            if (comboMetodaPageses.getValue() != null)
                dto.setMetodaPageses(comboMetodaPageses.getValue());

            if (dataPageses.getValue() != null)
                dto.setDataPageses(dataPageses.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

            pagesaService.update(dto);
            showSuccess("Pagesa u përditësua me sukses.");
            loadPagesa();
            clearForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void handleDelete() {
        try {
            Pagesa selected = pagesatTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showError("Zgjidh një pagesë për fshirje.");
                return;
            }

            pagesaService.delete(selected.getPagesaId());
            showSuccess("Pagesa u fshi me sukses.");
            loadPagesa();
            clearForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void clearForm() {
        txtShumaPageses.clear();
        comboMetodaPageses.getSelectionModel().clearSelection();
        dataPageses.setValue(null);
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
