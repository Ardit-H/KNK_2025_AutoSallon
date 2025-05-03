
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
import models.dto.TestDrives.CreateTestDrivesDto;
import models.dto.TestDrives.UpdateTestDrivesDto;
import models.dto.TestDrives.TestDrives;
import services.TestDrivesService;

import java.util.List;
import java.util.Locale;

public class FeedbackController {

    @FXML
    private TextField klientiid;
    @FXML private TextField veturaId;
    @FXML private Spinner vleresimi;
    @FXML private TextField komenti;
    @FXML private Label messageLabel;
    @FXML private TableView<Vleresimet> VleresimetTableView;
    @FXML private TableColumn<Vleresimet, String> colKlienti;
    @FXML private TableColumn<Vleresimet, String> colVetura;
    @FXML private TableColumn<Vleresimet, String> colVleresimi;
    @FXML private TableColumn<Vleresimet, String> colKomenti;
    @FXML private TableColumn<Vleresimet, String> colDataVleresimit;
    @FXML private VleresimetService vleresimetService;

    @FXML private TextField duration;
    @FXML private TextField feedback;
    @FXML private TextField location;
    @FXML private TableView<TestDrives> testDrivesTableView;
    @FXML private TableColumn<TestDrives, String> colTestDriveKlienti;
    @FXML private TableColumn<TestDrives, String> colTestDriveVetura;
    @FXML private TableColumn<TestDrives, String> colTestDriveStatusi;
    @FXML private TableColumn<TestDrives, String> colTestDriveFeedback;
    @FXML private TableColumn<TestDrives, String> colTestDriveDuration;
    @FXML private TableColumn<TestDrives, String> colTestDriveLocation;
    private TestDrivesService testDrivesService;
    private LanguageManager languageManager;
    public FeedbackController(){
        this.vleresimetService=new VleresimetService();
        this.testDrivesService = new TestDrivesService();
        this.languageManager=LanguageManager.getInstance();
    }
    @FXML
    public void initialize() {
        colKlienti.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getKlientiId())));
        colVetura.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVeturaId())));
        colVleresimi.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVleresimi())));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        vleresimi.setValueFactory(valueFactory);
        colKomenti.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKomenti()));
        colDataVleresimit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataVleresimit()));
        loadVleresimet();

        colTestDriveKlienti.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getKid())));
        colTestDriveVetura.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVid())));
        colTestDriveStatusi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatusi()));
        colTestDriveFeedback.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFeedback()));
        colTestDriveDuration.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDuration())));
        colTestDriveLocation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));

        loadTestDrives();
    }

    private void loadVleresimet() {
        List<Vleresimet> vleresimet = vleresimetService.getAll();
        VleresimetTableView.getItems().setAll(vleresimet);
    }

    private void loadTestDrives() {
        List<TestDrives> testDrives = testDrivesService.getAll();
        testDrivesTableView.getItems().setAll(testDrives);
    }

    @FXML
    private void handleCreateVleresim(MouseEvent event) {
        try {
            int klientiIdInt = Integer.parseInt(klientiid.getText().trim());
            int veturaIdInt = Integer.parseInt(veturaId.getText().trim());
            int vleresimiInt = (Integer) vleresimi.getValue();

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
    private void handleCreateTestDrive(MouseEvent event) {
        try {
            int klientiIdInt = Integer.parseInt(klientiid.getText().trim());
            int veturaIdInt = Integer.parseInt(veturaId.getText().trim());
            int durationInt = Integer.parseInt(duration.getText().trim());

            CreateTestDrivesDto dto = new CreateTestDrivesDto(
                    "New", feedback.getText().trim(), durationInt, location.getText().trim()
            );
            testDrivesService.create(dto);
            messageLabel.setText("Test Drive u shtua me sukses.");
            loadTestDrives();
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
            if (vleresimi.getValue() != null) {
                vleresimiInt = (Integer) vleresimi.getValue();
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
    private void handleUpdateTestDrive(MouseEvent event) {
        try {
            int selectedId = getSelectedTestDriveId();
            if (selectedId == -1) return;

            Integer klientiIdInt = null;
            if (!klientiid.getText().trim().isEmpty()) {
                klientiIdInt = Integer.parseInt(klientiid.getText().trim());
            }
            Integer veturaIdInt = null;
            if (!veturaId.getText().trim().isEmpty()) {
                veturaIdInt = Integer.parseInt(veturaId.getText().trim());
            }
            Integer durationInt = null;
            if (!duration.getText().trim().isEmpty()) {
                durationInt = Integer.parseInt(duration.getText().trim());
            }

            UpdateTestDrivesDto dto = new UpdateTestDrivesDto();
            dto.setId(selectedId);
            if (klientiIdInt != null) {
                dto.setStatusi("Updated");
            }
            if (veturaIdInt != null) {
                dto.setStatusi("Updated");
            }
            if (durationInt != null) {
                dto.setDuration(durationInt);
            }
            if (!feedback.getText().trim().isEmpty())
                dto.setFeedback(feedback.getText().trim());
            if (!location.getText().trim().isEmpty())
                dto.setLocation(location.getText().trim());

            testDrivesService.update(dto);
            messageLabel.setText("Test Drive u përditësua me sukses.");
            loadTestDrives();
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

    @FXML
    private void handleDeleteTestDrive(MouseEvent event) {
        try {
            int selectedId = getSelectedTestDriveId();
            if (selectedId == -1) return;

            testDrivesService.delete(selectedId);
            messageLabel.setText("Test Drive u fshi me sukses.");
            loadTestDrives();
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

    private int getSelectedTestDriveId() {
        TestDrives selected = testDrivesTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një Test Drive në listë.");
            return -1;
        }
        return selected.getId();
    }

    private void clearForm() {
        klientiid.clear();
        veturaId.clear();
        vleresimi.getValueFactory().setValue(1);
        komenti.clear();
        feedback.clear();
        location.clear();
        duration.clear();
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

