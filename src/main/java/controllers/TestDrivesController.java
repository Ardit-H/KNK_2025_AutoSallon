package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import services.LanguageManager;
import services.SceneManager;
import models.dto.TestDrives.CreateTestDrivesDto;
import models.dto.TestDrives.UpdateTestDrivesDto;
import models.dto.TestDrives.TestDrives;
import services.TestDrivesService;

import java.util.List;
import java.util.Locale;

public class TestDrivesController {
    @FXML private TextField duration;
    @FXML private TextField feedback;
    @FXML private TextField location;
    @FXML private TextField statusi;
    @FXML private TextField kid;
    @FXML private TextField vid;

    @FXML private Label messageLabel;
    @FXML public Button btn_shto;
    @FXML public Button btn_perditeso;
    @FXML public Button btn_fshij;
    @FXML public Button btn_shqip;
    @FXML public Button btn_anglisht;


    @FXML private TableView<TestDrives> testDrivesTableView;
    @FXML private TableColumn<TestDrives, String> colTestDriveKlienti;
    @FXML private TableColumn<TestDrives, String> colTestDriveVetura;
    @FXML private TableColumn<TestDrives, String> colTestDriveStatusi;
    @FXML private TableColumn<TestDrives, String> colTestDriveFeedback;
    @FXML private TableColumn<TestDrives, String> colTestDriveDuration;
    @FXML private TableColumn<TestDrives, String> colTestDriveLocation;
    private TestDrivesService testDrivesService;
    private LanguageManager languageManager;

    public TestDrivesController(){
        this.testDrivesService = new TestDrivesService();
        this.languageManager=LanguageManager.getInstance();
    }

    @FXML
    public void initialize() {
        colTestDriveKlienti.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getKid())));
        colTestDriveVetura.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVid())));
        colTestDriveStatusi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatusi()));
        colTestDriveFeedback.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFeedback()));
        colTestDriveDuration.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDuration())));
        colTestDriveLocation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));

        loadTestDrives();
    }


    private void loadTestDrives() {
        List<TestDrives> testDrives = testDrivesService.getAll();
        testDrivesTableView.getItems().setAll(testDrives);
    }

    @FXML
    private void handleCreateTestDrive( ActionEvent event) throws Exception {
        int durationInt = Integer.parseInt(duration.getText().trim());

        CreateTestDrivesDto dto = new CreateTestDrivesDto(
                "New", feedback.getText().trim(), durationInt, location.getText().trim()
        );
        testDrivesService.create(dto);
        messageLabel.setText("Test Drive u shtua me sukses.");
        loadTestDrives();
        clearForm();
    }


    @FXML
    private void handleUpdateTestDrive(ActionEvent event) {
        try {
            int selectedId = getSelectedTestDriveId();
            if (selectedId == -1) return;

            Integer durationInt = null;
            if (!duration.getText().trim().isEmpty()) {
                durationInt = Integer.parseInt(duration.getText().trim());
            }

            UpdateTestDrivesDto dto = new UpdateTestDrivesDto();
            dto.setId(selectedId);

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
    private void handleDeleteTestDrive(ActionEvent event) {
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

    private int getSelectedTestDriveId() {
        TestDrives selected = testDrivesTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Zgjidh një Test Drive në listë.");
            return -1;
        }
        return selected.getId();
    }

    private void clearForm() {
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
