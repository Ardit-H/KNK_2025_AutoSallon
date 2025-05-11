package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.dto.Sherbimet.Sherbimet;
import services.LanguageManager;
import services.SceneManager;
import services.SherbimetService;

import java.util.List;
import java.util.Locale;

public class UserSherbimetController {
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Sherbimet> SherbimetTableView;
    @FXML
    private TableColumn<Sherbimet, String> colEmri;
    @FXML
    private TableColumn<Sherbimet, String> colPershkrimi;
    @FXML
    private TableColumn<Sherbimet, Double> colCmimi;

    private  SherbimetService sherbimetService;
    private final ObservableList<Sherbimet> sherbimetList = FXCollections.observableArrayList();
    private LanguageManager languageManager;
    public UserSherbimetController(){
        this.sherbimetService=new SherbimetService();
        this.languageManager=LanguageManager.getInstance();
    }
    @FXML
    public void initialize() {
        colEmri.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmri()));
        colPershkrimi.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPershkrimi()));
        colCmimi.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getÇmimi()));

        SherbimetTableView.setItems(sherbimetList);
        loadSherbimet();
    }

    private void loadSherbimet() {
        sherbimetList.setAll(sherbimetService.getAll());
    }

    @FXML
    private void onSearch() {
        String query = searchField.getText();
        List<Sherbimet> filtered;
        if (query == null || query.trim().isEmpty()) {
            filtered = sherbimetService.getAll();
        } else {
            filtered = sherbimetService.kerkoSherbiminNgaEmri(query.trim());
        }
        sherbimetList.setAll(filtered);
    }
}

