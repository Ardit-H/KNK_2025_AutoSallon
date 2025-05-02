package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import models.dto.Sherbimet.CreateSherbimetDto;
import models.dto.Sherbimet.Sherbimet;
import models.dto.Sherbimet.UpdateSherbimetDto;
import services.LanguageManager;
import services.SceneManager;
import services.SherbimetService;

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SherbimetController {
    @FXML private TextField txtEmri;
    @FXML private TextField txtPershkrimi;
    @FXML private TextField txtCmimi;
    @FXML private Label messageLabel;
    @FXML private TableView<Sherbimet> SherbimetTableView;
    @FXML private TableColumn<Sherbimet, String> colEmri;
    @FXML private TableColumn<Sherbimet, String> colPershkrimi;
    @FXML private TableColumn<Sherbimet, String> colCmimi;


    private SherbimetService sherbimetService;
    private LanguageManager languageManager;
    public SherbimetController(){
        this.sherbimetService=new SherbimetService();
        this.languageManager=LanguageManager.getInstance();
    }

    @FXML
    public void initialize() {
        colEmri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmri()));
        colPershkrimi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPershkrimi()));
        colCmimi.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getÇmimi())));
        loadSherbimet();
    }


    private void loadSherbimet() {
        List<Sherbimet> sherbimet = sherbimetService.getAll();
        SherbimetTableView.getItems().setAll(sherbimet);
    }

    @FXML
    private void handleCreate(MouseEvent event) {
        try {
            String emri = txtEmri.getText();
            String pershkrimi = txtPershkrimi.getText();
            double cmimi = Double.parseDouble(txtCmimi.getText());

            Sherbimet sherbimi = sherbimetService.create(new CreateSherbimetDto(emri, pershkrimi, cmimi));
            messageLabel.setText("Shërbimi u shtua me sukses.");
            loadSherbimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }
    @FXML
    private void handleUpdate(MouseEvent event) {
        try {
            int selectedId = getSelectedSherbimId();
            if (selectedId == -1) return;

            UpdateSherbimetDto dto = new UpdateSherbimetDto();
            dto.setId(selectedId);
            if (!txtEmri.getText().trim().isEmpty())
                dto.setEmri(txtEmri.getText().trim());
            if (!txtPershkrimi.getText().trim().isEmpty())
                dto.setPershkrimi(txtPershkrimi.getText().trim());
            if (!txtCmimi.getText().trim().isEmpty())
                dto.setÇmimi(Double.parseDouble(txtCmimi.getText().trim()));
            sherbimetService.update(dto);
            messageLabel.setText("Sherbimi u përditësua me sukses.");
            loadSherbimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(MouseEvent event) {
        try {
            int selectedId = getSelectedSherbimId();
            if (selectedId == -1) return;

            sherbimetService.delete(selectedId);
            messageLabel.setText("Sherbimi u fshi me sukses.");
            loadSherbimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private int getSelectedSherbimId() {
        Sherbimet selected = SherbimetTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një sherbim në listë.");
            return -1;
        }
        return selected.getId();
    }

    private void clearForm() {
        txtEmri.clear();
        txtPershkrimi.clear();
        txtCmimi.clear();
    }
    @FXML
    private void handleLanguageEnglishClick()throws Exception{
        loadLanguage(Locale.ENGLISH);
    }
    @FXML
    private void handleLanguageAlbanianClick()throws Exception{
        loadLanguage(new Locale("sq"));
    }
    private void loadLanguage(Locale locale) throws Exception{
        languageManager.setLocale(locale);
        SceneManager.reload();
    }
}