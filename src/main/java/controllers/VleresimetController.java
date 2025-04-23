package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.dto.Vleresimet.CreateVleresimetDto;
import models.dto.Vleresimet.Vleresimet;
import services.VleresimetService;

import java.util.List;

public class VleresimetController {

    @FXML
    private TextField klientiid;
    @FXML private TextField veturaId;
    @FXML private TextField vleresimi;
    @FXML private TextField komenti;
    @FXML private TextArea outputarea;

    private VleresimetService vleresimetService ;
    public VleresimetController(){
      this.vleresimetService=new VleresimetService();
    }

    @FXML
    private void handleCreateVleresim() {
        try {
            int klientiId = Integer.parseInt(klientiid.getText());
            int vetura = Integer.parseInt(veturaId.getText());
            int vleresim = Integer.parseInt(vleresimi.getText());
            String koment = komenti.getText();

            CreateVleresimetDto dto = new CreateVleresimetDto(klientiId, vetura, vleresim, koment);
            Vleresimet vleresimet = vleresimetService.create(dto);

            outputarea.setText("U shtua vlerësimi me ID: " + vleresimet.getVleresimiId());
        } catch (Exception e) {
            outputarea.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleShowAllVleresimet() {
        List<Vleresimet> all = vleresimetService.getAll();
        StringBuilder sb = new StringBuilder();
        for (Vleresimet v : all) {
            sb.append("ID: ").append(v.getVleresimiId())
                    .append(" | KlientiID: ").append(v.getKlientiId())
                    .append(" | VeturaID: ").append(v.getVeturaId())
                    .append(" | Nota: ").append(v.getVleresimi())
                    .append(" | Koment: ").append(v.getKomenti())
                    .append("\n");
        }
        outputarea.setText(sb.toString());
    }
}