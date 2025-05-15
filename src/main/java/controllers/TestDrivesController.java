package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.LanguageManager;
import models.dto.TestDrives.CreateTestDrivesDto;
import models.dto.TestDrives.UpdateTestDrivesDto;
import models.dto.TestDrives.TestDrives;
import services.TestDrivesService;
import java.util.List;

public class TestDrivesController {
    @FXML public TextField kid;
    @FXML public TextField vid;
    @FXML public TextField statusi;
    @FXML public TextField duration;
    @FXML public TextField feedback;
    @FXML public TextField location;

    @FXML public TextField searchField;
    @FXML public Label messageLabel;
    @FXML public Button btn_shto;
    @FXML public Button btn_perditeso;
    @FXML public Button btn_fshij;

    @FXML private TableView<TestDrives> testDrivesTableView;
    @FXML private TableColumn<TestDrives, String> colKlienti;
    @FXML private TableColumn<TestDrives, String> colVetura;
    @FXML private TableColumn<TestDrives, String> colStatusi;
    @FXML private TableColumn<TestDrives, String> colFeedback;
    @FXML private TableColumn<TestDrives, String> colDuration;
    @FXML private TableColumn<TestDrives, String> colLocation;

    private final TestDrivesService testDrivesService;
    private final LanguageManager languageManager;

    public TestDrivesController(){
        this.testDrivesService = new TestDrivesService();
        this.languageManager = LanguageManager.getInstance();

    }

    @FXML public void initialize() {
        colKlienti.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getKid())));
        colVetura.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVid())));
        colStatusi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatusi()));
        colFeedback.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFeedback()));
        colDuration.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDuration())));
        colLocation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));

        loadTestDrives();
    }


    private void loadTestDrives() {
        List<TestDrives> testDrives = testDrivesService.getAll();
        testDrivesTableView.getItems().setAll(testDrives);
    }

    @FXML private void handleCreateTestDrive() {
        try {
            CreateTestDrivesDto dto = new CreateTestDrivesDto(
                    Integer.parseInt(kid.getText().trim()),
                    Integer.parseInt(vid.getText().trim()),
                    statusi.getText().trim(),
                    feedback.getText().trim(),
                    Integer.parseInt(duration.getText().trim()),
                    location.getText().trim()
            );

            testDrivesService.create(dto);
            messageLabel.setText("Test Drive u shtua me sukses.");

            loadTestDrives();
            clearForm();

        } catch (NumberFormatException e) {
            messageLabel.setText("Gabim në formatimin e numrave. Ju lutem, kontrolloni fushat numerike.");
        } catch (Exception e) {
            messageLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML private void handleUpdateTestDrive() {
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

    @FXML private void handleDeleteTestDrive() {
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
        kid.clear();
        vid.clear();
        statusi.clear();
    }

}
