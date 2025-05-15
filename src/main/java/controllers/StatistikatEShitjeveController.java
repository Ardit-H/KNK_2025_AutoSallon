package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.dto.StatistikatEShitjeve.CreateStatistikatEShitjeveDto;
import models.dto.StatistikatEShitjeve.StatistikatEShitjeve;
import models.dto.StatistikatEShitjeve.UpdateStatistikatEShitjeveDto;
import services.StatistikatEShitjeveService;
import services.LanguageManager;
import services.SceneManager;

import java.util.List;
import java.util.Locale;

public class StatistikatEShitjeveController {
    @FXML private TextField txtMuaji;
    @FXML private TextField txtFitimi;
    @FXML private TextField txtShpenzimet;
    @FXML private TextField txtTotaliShitjeve;
    @FXML private TextField searchField;

    @FXML private BarChart<String, Number> barChart;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;



    @FXML private TableView<StatistikatEShitjeve> statistikatEShitjeveTableView;
    @FXML private TableColumn<StatistikatEShitjeve, String> colMuaji;
    @FXML private TableColumn<StatistikatEShitjeve, String> colFitimi;
    @FXML private TableColumn<StatistikatEShitjeve, String> colShpenzimet;
    @FXML private TableColumn<StatistikatEShitjeve, String> colTotaliShitjeve;

    @FXML private Label messageLabel;
    private StatistikatEShitjeveService statistikatEShitjeveService;
    private LanguageManager languageManager;

    public StatistikatEShitjeveController(){
        this.statistikatEShitjeveService = new StatistikatEShitjeveService();
        this.languageManager = LanguageManager.getInstance();
    }

    @FXML
    public void initialize() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                statistikatEShitjeveTableView.setItems(FXCollections.observableArrayList(statistikatEShitjeveService.getAll()));
            } else {
                List<StatistikatEShitjeve> filtruar = statistikatEShitjeveService.kerkoStatistikat(newValue);
                statistikatEShitjeveTableView.setItems(FXCollections.observableArrayList(filtruar));
            }
        });

        colMuaji.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMuaji()));
        colFitimi.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFitimi())));
        colShpenzimet.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getShpenzimet())));
        colTotaliShitjeve.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTotali_shitjeve())));

        loadStatistikatEShitjeve();

        loadChart();

    }

    private void loadStatistikatEShitjeve() {
        List<StatistikatEShitjeve> statistikat = statistikatEShitjeveService.getAll();
        statistikatEShitjeveTableView.getItems().setAll(statistikat);
    }

    @FXML
    private void handleCreateStatistikat() {
        try {
            String muaji = txtMuaji.getText();
            Double fitimi = Double.parseDouble(txtFitimi.getText());
            Double shpenzimet = Double.parseDouble(txtShpenzimet.getText());
            Double totaliShitjeve = Double.parseDouble(txtTotaliShitjeve.getText());

            CreateStatistikatEShitjeveDto dto = new CreateStatistikatEShitjeveDto(
                    muaji,
                    fitimi,
                    shpenzimet,
                    totaliShitjeve
            );

            statistikatEShitjeveService.create(dto);

            messageLabel.setText("Statistika e shitjeve u shtua me sukses.");
            loadStatistikatEShitjeve();

            clearForm();
        } catch (Exception e) {

            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdateStatistikat() {
        try {
            int selectedId = getSelectedStatistikaId();
            if (selectedId == -1) return;

            UpdateStatistikatEShitjeveDto dto = new UpdateStatistikatEShitjeveDto(selectedId);

            if (!txtMuaji.getText().trim().isEmpty())
                dto.setMuaji(txtMuaji.getText().trim());

            if (!txtFitimi.getText().trim().isEmpty())
                dto.setFitimi(Double.parseDouble(txtFitimi.getText().trim()));

            if (!txtShpenzimet.getText().trim().isEmpty())
                dto.setShpenzimet(Double.parseDouble(txtShpenzimet.getText().trim()));

            if (!txtTotaliShitjeve.getText().trim().isEmpty())
                dto.setTotaliShitjeve(Double.parseDouble(txtTotaliShitjeve.getText().trim()));

            statistikatEShitjeveService.update(dto);
            messageLabel.setText("Statistika e shitjeve u përditësua me sukses.");
            loadStatistikatEShitjeve();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDeleteStatistikat() {
        try {
            int selectedId = getSelectedStatistikaId();
            if (selectedId == -1) return;

            statistikatEShitjeveService.delete(selectedId);
            messageLabel.setText("Statistika e shitjeve u fshi me sukses.");
            loadStatistikatEShitjeve();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private int getSelectedStatistikaId() {
        StatistikatEShitjeve selected = statistikatEShitjeveTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidhni një statistike në tabelë.");
            return -1;
        }
        return selected.getStatistika_id();
    }

    private void clearForm() {
        txtMuaji.clear();
        txtFitimi.clear();
        txtShpenzimet.clear();
        txtTotaliShitjeve.clear();
    }


    private void loadChart() {
        barChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Fitimi");

        List<StatistikatEShitjeve> statistikat = statistikatEShitjeveService.getAll();
        for (StatistikatEShitjeve stat : statistikat) {
            // Kjo do shtojë çdo muaj unik si pikë në grafik
            series.getData().add(new XYChart.Data<>(stat.getMuaji(), stat.getFitimi()));
        }

        barChart.getData().add(series);
    }
}
