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
    private Button handleDergo;
    @FXML
    private TextField txtCmimiOfruar;

    private Veturat veturaZgjedhur;

    private final PorosiaService porosiaService = new PorosiaService();



    public void setVetura(Veturat vetura) {
        System.out.println("➡️ setVetura() u thirr");
        this.veturaZgjedhur = vetura;

        labelModeli.setText("Modeli i vetures: " + vetura.getModeli());
        labelProdhuesi.setText("Prodhuesi i vetures: " + vetura.getProdhuesi());
        labelViti.setText("Viti i prodhimit: " + String.valueOf(vetura.getVitiProdhimit()));
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
            Parent ofertaPane = loader.load();

            OfertaController ofertaController = loader.getController();
            System.out.println(vetura);
            System.out.println("here");
            ofertaController.setOferta(vetura);
            System.out.println("Oferta u ngarkua në VBox.");

            ofertaContainer.getChildren().setAll(ofertaPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleDergo(ActionEvent event) {
        try {
            double cmimiOfruar = Double.parseDouble(txtCmimiOfruar.getText());

            int klientiId;
            if (SessionManager.getInstance().isLoggedIn()) {
                Perdoruesit user = SessionManager.getInstance().getcurrentUser();
                klientiId = user.getPid();
            } else {
                throw new Exception("Ju nuk jeni te kyqur");
            }

            CreatePorosiaDto dto = new CreatePorosiaDto(klientiId, veturaZgjedhur.getId(), cmimiOfruar, "Ne pritje");

            porosiaService.create(dto);

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Sukses");
            success.setHeaderText(null);
            success.setContentText("Porosia u dërgua me sukses!");
            success.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
