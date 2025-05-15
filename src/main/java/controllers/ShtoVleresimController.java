package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.dto.Vleresimet.CreateVleresimetDto;
import services.SceneManager;
import services.SessionManager;
import services.VleresimetService;
import utils.SceneLocator;

public class ShtoVleresimController {
    @FXML
    private Spinner<Integer> vleresimi;
    @FXML private TextField komentiField;

    private int userId;
    private int veturaId;

    private final VleresimetService vleresimetService = new VleresimetService();

    public void initialize() {
        vleresimi.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 3));
        userId = SessionManager.getInstance().getTempUserId();
        veturaId = SessionManager.getInstance().getTempVeturaId();
    }

    @FXML
    private void handleVlereso() {
        int rating = vleresimi.getValue();
        String koment = komentiField.getText();

        CreateVleresimetDto dto = new CreateVleresimetDto(userId, veturaId, rating, koment);
        try {
            vleresimetService.create(dto);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukses");
            alert.setContentText("Vlerësimi u ruajt me sukses.");
            alert.showAndWait();

            // Mbyll dritaren
            ((Stage) komentiField.getScene().getWindow()).close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setContentText("Ndodhi një gabim gjatë ruajtjes së vlerësimit.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    private void handleKthehu()throws Exception {
        SceneManager.getInstance().setCenterPanePath(SceneLocator.VETURAT_USER);
        SceneManager.reload();
    }
}

