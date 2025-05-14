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
        SessionManager.getInstance().loginUser(
                new Perdoruesit(
                        1,
                        "Ardit",
                        "Berisha",
                        "ardit@example.com",
                        "+38349111222",
                        "Rr. B Prishtinë",
                        "2025-05-12 10:00:00",
                        "klient",
                        "hashed_password_value",
                        "random_salt_value"
                )
        );

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
            double cmimiOfruar = Double.parseDouble(txtCmimiOfruar.getText());
            if (cmimiOfruar <= 0) {
                showError("Cmimi i ofruar nuk mund te jete numer negativ!");
                return;
            }

            int klientiId;
            if (SessionManager.getInstance().isLoggedIn()) {
                Perdoruesit user = SessionManager.getInstance().getcurrentUser();
                klientiId = user.getPid();
            } else {
                showError("Ju nuk jeni te kyqur");
                return;
            }

            CreatePorosiaDto dto = new CreatePorosiaDto(klientiId, veturaZgjedhur.getId(), cmimiOfruar, "Ne pritje");

            porosiaService.create(dto);

            showSuccess("Porosia u dërgua me sukses!");
            dergoButoni.setDisable(true);

            clearForm();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
