
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import models.dto.Vleresimet.CreateVleresimetDto;
import models.dto.Vleresimet.UpdateVleresimetDto;
import models.dto.Vleresimet.Vleresimet;
import services.KlientetService;
import services.VleresimetService;

import java.util.List;

public class VleresimetController {

    @FXML
    private TextField klientiid;
    @FXML private TextField veturaId;
    @FXML private TextField vleresimi;
    @FXML private TextField komenti;
    @FXML private Label messageLabel;
    @FXML private ListView<String> txtVleresimetList;
    @FXML private VleresimetService vleresimetService;
    public VleresimetController(){
        this.vleresimetService=new VleresimetService();
    }
    @FXML
    public void initialize() {
        loadVleresimet();
    }

    private void loadVleresimet() {
        txtVleresimetList.getItems().clear();
        List<Vleresimet> vleresimet = vleresimetService.getAll();
        for (Vleresimet v : vleresimet) {
            txtVleresimetList.getItems().add(v.getVleresimiId()+ " - " + v.getKlientiId()+ " - " + v.getVeturaId()+" - "+v.getVleresimi() + " - " + v.getKomenti()+" - "+v.getDataVleresimit());
        }
    }

    @FXML
    private void handleCreateVleresim(MouseEvent event) {
        try {
            int klientiIdInt = Integer.parseInt(klientiid.getText().trim());
            int veturaIdInt = Integer.parseInt(veturaId.getText().trim());
            int vleresimiInt = Integer.parseInt(vleresimi.getText().trim());

            CreateVleresimetDto dto = new CreateVleresimetDto(
                    klientiIdInt,
                    veturaIdInt,
                    vleresimiInt,
                    komenti.getText()
            );
            vleresimetService.create(dto);
            messageLabel.setText("Vleresimi u shtua me sukses.");
            loadVleresimet();
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdateVleresim(MouseEvent event) {
        try {
            int selectedId = getSelectedVleresimId();
            if (selectedId == -1) return;
            Integer klientiIdInt = null;
            if (!klientiid.getText().trim().isEmpty()) {
                klientiIdInt = Integer.parseInt(klientiid.getText().trim());
            }
            Integer veturaIdInt = null;
            if (!veturaId.getText().trim().isEmpty()) {
                veturaIdInt = Integer.parseInt(veturaId.getText().trim());
            }
            Integer vleresimiInt = null;
            if (!vleresimi.getText().trim().isEmpty()) {
                vleresimiInt = Integer.parseInt(vleresimi.getText().trim());
            }
            UpdateVleresimetDto dto = new UpdateVleresimetDto();
            dto.setVleresimiId(selectedId);
            if (klientiIdInt != null) {
                dto.setKlientiId(klientiIdInt);
            }
            if (veturaIdInt != null) {
                dto.setVeturaId(veturaIdInt);
            }
            if (vleresimiInt != null) {
                dto.setVleresimi(vleresimiInt);
            }
            if (!komenti.getText().trim().isEmpty())
                dto.setKomenti(komenti.getText().trim());
            vleresimetService.update(dto);
            messageLabel.setText("Vlerësimi u përditësua me sukses.");
            loadVleresimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDeleteVleresim(MouseEvent event) {
        try {
            int selectedId = getSelectedVleresimId();
            if (selectedId == -1) return;

            vleresimetService.delete(selectedId);
            messageLabel.setText("Klienti u fshi me sukses.");
            loadVleresimet();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    private int getSelectedVleresimId() {
        String selected = txtVleresimetList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një vlerësim në listë.");
            return -1;
        }
        return Integer.parseInt(selected.split(" - ")[0]);
    }

    private void clearForm() {
        klientiid.clear();
        veturaId.clear();
        vleresimi.clear();
        komenti.clear();

    }
}

