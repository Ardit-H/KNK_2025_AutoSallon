
package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.dto.Vleresimet.CreateVleresimetDto;
import models.dto.Vleresimet.UpdateVleresimetDto;
import models.dto.Vleresimet.Vleresimet;
import services.LanguageManager;
import services.SceneManager;
import services.VleresimetService;

import java.util.List;
import java.util.Locale;

public class VleresimetController {

    @FXML private TextField veturaId;
    @FXML private TextField perdoruesiId;
    @FXML private Spinner vleresimi;
    @FXML private TextField komenti;
    @FXML private Label messageLabel;
    @FXML private TableView<Vleresimet> VleresimetTableView;
    @FXML private TableColumn<Vleresimet, String> colPerdoruesi;
    @FXML private TableColumn<Vleresimet, String> colVetura;
    @FXML private TableColumn<Vleresimet, String> colVleresimi;
    @FXML private TableColumn<Vleresimet, String> colKomenti;
    @FXML private TableColumn<Vleresimet, String> colDataVleresimit;
    @FXML private VleresimetService vleresimetService;
    private LanguageManager languageManager;

    public VleresimetController(){
        this.vleresimetService=new VleresimetService();
        this.languageManager=LanguageManager.getInstance();
    }
    @FXML
    public void initialize() {
        colPerdoruesi.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPerdoruesiEmriPlote())));
        colVetura.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVeturaEmri())));
        colVleresimi.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVleresimi())));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        vleresimi.setValueFactory(valueFactory);
        colKomenti.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKomenti()));
        colDataVleresimit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataVleresimit()));
        loadVleresimet();

    }

    private void loadVleresimet() {
        List<Vleresimet> vleresimet = vleresimetService.getVleresimetWithJoins();
        VleresimetTableView.getItems().setAll(vleresimet);
    }

    @FXML
    private void handleCreateVleresim(MouseEvent event) {
        try {
            int perdoruesiIdInt = Integer.parseInt(perdoruesiId.getText().trim());
            int veturaIdInt = Integer.parseInt(veturaId.getText().trim());
            int vleresimiInt = (Integer) vleresimi.getValue();

            CreateVleresimetDto dto = new CreateVleresimetDto(
                    perdoruesiIdInt,
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
            Integer perdoruesiIdInt = null;
            if (!perdoruesiId.getText().trim().isEmpty()) {
                perdoruesiIdInt = Integer.parseInt(perdoruesiId.getText().trim());
            }
            Integer veturaIdInt = null;
            if (!veturaId.getText().trim().isEmpty()) {
                veturaIdInt = Integer.parseInt(veturaId.getText().trim());
            }
            Integer vleresimiInt = null;
            if (vleresimi.getValue() != null) {
                vleresimiInt = (Integer) vleresimi.getValue();
            }
            UpdateVleresimetDto dto = new UpdateVleresimetDto();
            dto.setVleresimiId(selectedId);
            if (perdoruesiIdInt != null) {
                dto.setPerdoruesiId(perdoruesiIdInt);
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
        Vleresimet selected = VleresimetTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një vlerësim në listë.");
            return -1;
        }
        return selected.getVleresimiId();
    }

    private void clearForm() {
        perdoruesiId.clear();
        veturaId.clear();
        vleresimi.getValueFactory().setValue(1);
        komenti.clear();
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

