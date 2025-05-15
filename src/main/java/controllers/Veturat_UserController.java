package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.dto.Veturat.Veturat;
import services.SceneManager;
import services.SessionManager;
import services.VeturatService;

import java.io.IOException;
import java.util.List;
import services.LanguageManager;
import utils.SceneLocator;

public class Veturat_UserController {
    @FXML private TextField searchField;
    @FXML private Label messageLabel;
    @FXML private Button porositeButton;

    @FXML private TableView<Veturat> veturatTableView;
    @FXML private TableColumn<Veturat, String> colProdhuesi;
    @FXML private TableColumn<Veturat, String> colModeli;
    @FXML private TableColumn<Veturat, String> colVitiProdhimit;
    @FXML private TableColumn<Veturat, String> colNgjyra;
    @FXML private TableColumn<Veturat, String> colCmimi;
    @FXML private TableColumn<Veturat, String> colGjendja;
    @FXML private TableColumn<Veturat, String> colKilometrazha;
    @FXML private TableColumn<Veturat, String> colTipiKarburant;

    private final VeturatService veturatService;
    private final LanguageManager languageManager;


    public Veturat_UserController() {
        this.veturatService = new VeturatService();
        this.languageManager = LanguageManager.getInstance();
    }

    @FXML public void initialize() {
        colProdhuesi.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProdhuesi()));
        colModeli.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModeli()));
        colVitiProdhimit.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getVitiProdhimit())));
        colNgjyra.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNgjyra()));
        colCmimi.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCmimi())));
        colGjendja.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGjendja()));
        colKilometrazha.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getKilometrazha())));
        colTipiKarburant.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipiKarburant()));

        loadVeturat();

        if (!SessionManager.getInstance().isLoggedIn()) {
            porositeButton.setDisable(true);
        }

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                loadVeturat();
                messageLabel.setText("");
            } else {
                List<Veturat> filtered = veturatService.kerkoVeturat(newVal.trim());
                if (filtered.isEmpty()) {
                    messageLabel.setText("Nuk u gjet asnjë veturë për këtë prodhues.");
                } else {
                    messageLabel.setText("");
                }
                veturatTableView.setItems(FXCollections.observableArrayList(filtered));
            }
        });
    }

    private void loadVeturat() {
        List<Veturat> lista = veturatService.getAll();
        veturatTableView.getItems().setAll(lista);
    }

    public void handleOrder(){
        Veturat vetura = veturatTableView.getSelectionModel().getSelectedItem();
        if (vetura == null) {
            showError("Zgjidh një veturë për të bërë porosine!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(SceneLocator.POROSIA));
            loader.setResources(LanguageManager.getInstance().getResourceBundle());

            AnchorPane porosiaPane = loader.load();

            PorosiaController controller = loader.getController();
            controller.setVetura(vetura);

            BorderPane root = (BorderPane) SceneManager.getInstance().getScene().getRoot();
            AnchorPane centerPane = (AnchorPane) root.getCenter();
            centerPane.getChildren().setAll(porosiaPane);
        } catch (Exception e) {
            showError("Nuk u mundësua hapja e faqes së porosisë.");
        }
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
