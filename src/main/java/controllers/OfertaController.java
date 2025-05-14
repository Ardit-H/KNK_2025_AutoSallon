package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.dto.Ofertat.Oferta;
import models.dto.Porosite.CreatePorosiaDto;
import models.dto.Veturat.Veturat;
import services.OfertaService;
import services.PorosiaService;
import services.SessionManager;

public class OfertaController {
    private final OfertaService ofertaService = new OfertaService();
    private final PorosiaService porosiaService = new PorosiaService();
    private Veturat veturaZgjedhur;
    private Oferta ofertaZgjedhur;
    @FXML
    private VBox ofertaBox;
    @FXML
    private Label zbritja;
    @FXML
    private Label cmimiFinal;
    @FXML
    private Label dataFillimit;
    @FXML
    private Label dataMbarimit;
    @FXML
    private Button aktivizoButoni;


    private void setOfertaAsNA() {
        zbritja.setText("N/A");
        cmimiFinal.setText("N/A");
        dataFillimit.setText("N/A");
        dataMbarimit.setText("N/A");
    }

    public void setOferta(Veturat vetura) {
        this.veturaZgjedhur = vetura;
        try {
            this.ofertaZgjedhur = ofertaService.getOfertaByVeturaId(vetura.getId());

            if (ofertaZgjedhur != null) {
                zbritja.setText(ofertaZgjedhur.getZbritja() + "€");
                cmimiFinal.setText("" + ofertaZgjedhur.getCmimiFinal());
                dataFillimit.setText(ofertaZgjedhur.getDataFillimit());
                dataMbarimit.setText(ofertaZgjedhur.getDataMbarimit());
            }
        } catch (IllegalArgumentException ex) {
            setOfertaAsNA();
        }
    }
    public void handleAktivizoOferten(ActionEvent event) {
        try {
            if (ofertaZgjedhur == null) {
                throw new IllegalStateException("Nuk ekziston oferte per veturen!");
            }

            if (!SessionManager.getInstance().isLoggedIn()) {
                throw new Exception("Ju nuk jeni të kyçur.");
            }

            int klientiId = SessionManager.getInstance().getcurrentUser().getPid();
            double cmimi = ofertaZgjedhur.getCmimiFinal();

            CreatePorosiaDto dto = new CreatePorosiaDto(
                    klientiId,
                    veturaZgjedhur.getId(),
                    cmimi,
                    "Ne pritje"
            );

            porosiaService.create(dto);

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Sukses");
            success.setHeaderText(null);
            success.setContentText("Oferta u aktivizua me sukses!");
            success.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Gabim");
            error.setHeaderText(null);
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }

}
