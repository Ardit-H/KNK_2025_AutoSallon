package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.dto.Perdoruesit.CreatePerdoruesitDto;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;
import services.PerdoruesitService;

import java.util.List;
public class PerdoruesitController {
    @FXML
    private TextField txtEmri;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFjalekalimi;

    @FXML
    private TextField txtRoli;

    @FXML
    private ListView<String> txtPerdoruesitList;

    @FXML
    private Label messageLabel;

    private PerdoruesitService perdoruesitService;


    public PerdoruesitController(){
        this.perdoruesitService = new PerdoruesitService();
    }

    @FXML
    public void initialize(){
        loadPerdoruesit();
    }

    private void loadPerdoruesit(){
        txtPerdoruesitList.getItems().clear();
        List<Perdoruesit> perdoruesit = perdoruesitService.getAll();
        for (Perdoruesit p: perdoruesit){
            txtPerdoruesitList.getItems().add(p.getPid() + " - " + p.getEmri() + " (" + p.getRoli() + ")");
        }
    }

    @FXML
    private void handleCreate(MouseEvent event){
        try{
            CreatePerdoruesitDto dto = new CreatePerdoruesitDto(
                    txtEmri.getText(),
                    txtEmail.getText(),
                    txtFjalekalimi.getText(),
                    txtRoli.getText()
            );
            perdoruesitService.create(dto);
            messageLabel.setText("Perdoruesi u shtua me sukses!");
            loadPerdoruesit();
            clearForm();


        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }


    @FXML
    private void handleUpdate(MouseEvent event){
        try{
            int selectedId = getSelectedPerdoruesitId();
            if(selectedId == -1) return;

            UpdatePerdoruesitDto dto = new UpdatePerdoruesitDto();
            dto.setId(selectedId);
            if (!txtEmail.getText().trim().isEmpty())
                dto.setEmail(txtEmail.getText().trim());
            if (!txtFjalekalimi.getText().trim().isEmpty())
                dto.setFjalekalimi(txtFjalekalimi.getText().trim());
            if (!txtRoli.getText().trim().isEmpty())
                dto.setRoli(txtRoli.getText().trim());

            perdoruesitService.update(dto);
            messageLabel.setText("Perdoruesi u perditesua me sukses!");
            loadPerdoruesit();

        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete(MouseEvent event) {
        try {
            int selectedId = getSelectedPerdoruesitId();
            if (selectedId == -1) return;

            perdoruesitService.delete(selectedId);
            messageLabel.setText("Perdoruesi u fshi me sukses.");
            loadPerdoruesit();
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }


    private int getSelectedPerdoruesitId(){
        String selected = txtPerdoruesitList.getSelectionModel().getSelectedItem();
        if(selected == null){
            messageLabel.setText("Zgjidh nje perdorues ne liste!");
            return -1;
        }

        return Integer.parseInt(selected.split(" - ")[0]);
    }


    private void clearForm(){
        txtEmri.clear();
        txtEmail.clear();
        txtFjalekalimi.clear();
        txtRoli.clear();
    }


}
