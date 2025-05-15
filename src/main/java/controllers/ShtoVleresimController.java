package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.dto.Vleresimet.CreateVleresimetDto;
import services.VleresimetService;

public class ShtoVleresimController {
    @FXML
    private Spinner<Integer> vleresimi;
    @FXML private TextField komentiField;

    private int userId;
    private int veturaId;

    private final VleresimetService vleresimetService = new VleresimetService();

    public void initialize() {
        vleresimi.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 3));
    }

    public void setUseriDheVetura(int userId, int veturaId) {
        this.userId = userId;
        this.veturaId = veturaId;
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
    private void handleKthehu() {
        ((Stage) komentiField.getScene().getWindow()).close();
    }
}

