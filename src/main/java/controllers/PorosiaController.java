package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Porosite.CreatePorosiaDto;
import models.dto.Veturat.Veturat;
import services.PorosiaService;
import services.SessionManager;
import utils.SceneLocator;

import java.io.IOException;
import java.util.ResourceBundle;

public class PorosiaController {


    @FXML
    private Label labelModeli;
    @FXML
    private Label labelProdhuesi;
    @FXML
    private Label labelViti;
    @FXML
    private VBox ofertaContainer;

    @FXML
    private Button dergoButoni;
    @FXML
    private TextField txtCmimiOfruar;

    private Veturat veturaZgjedhur;

    private final PorosiaService porosiaService = new PorosiaService();

    public void setVetura(Veturat vetura) {
        this.veturaZgjedhur = vetura;
        labelModeli.setText(vetura.getModeli());
        labelProdhuesi.setText(vetura.getProdhuesi());
        labelViti.setText(String.valueOf(vetura.getVitiProdhimit()));

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(SceneLocator.OFERTA));
            loader.setResources(ResourceBundle.getBundle("languages.messages"));
            Parent ofertaPane = loader.load();

            OfertaController ofertaController = loader.getController();
            ofertaController.setOferta(vetura);

            ofertaContainer.getChildren().setAll(ofertaPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDergo(ActionEvent event) {
        try {
            if(!isValidInput())
                return;

            double cmimiOfruar = Double.parseDouble(txtCmimiOfruar.getText());
            Perdoruesit user;
            if (SessionManager.getInstance().isLoggedIn()) {
                user = SessionManager.getInstance().getcurrentUser();
            } else {
                showError("Ju nuk jeni te kyqur");
                return;
            }

            CreatePorosiaDto dto = new CreatePorosiaDto(user.getPid(), veturaZgjedhur.getId(), cmimiOfruar, "Ne pritje");
            porosiaService.create(dto);

            showSuccess("Porosia u dërgua me sukses!");
            dergoButoni.setDisable(true);

            clearForm();

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }
    private boolean isValidInput() {
        String cmimiOfruar = txtCmimiOfruar.getText();

        if (cmimiOfruar.isEmpty()) {
            showError("Mbush fushën: Cmimi i ofruar!");
            return false;
        }

        try {
            double cmimi = Double.parseDouble(cmimiOfruar);
            if (cmimi <= 0) {
                showError("Cmimi i ofruar nuk mund të jetë numër negativ ose zero!");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Cmimi i ofruar duhet të jetë numër.");
            return false;
        }

        return true;
    }

    private void clearForm() {
        txtCmimiOfruar.clear();
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
