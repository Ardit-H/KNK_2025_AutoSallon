package App;

import controllers.PorosiaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.dto.Veturat.Veturat;
import utils.SceneLocator;

public class Porosite extends Application {
    public void start(Stage stage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource(SceneLocator.POROSIA));

        Parent root = loader.load();

        PorosiaController controller = loader.getController();
        System.out.println("➡️ Controller: " + controller);
        controller.setVetura(new Veturat(9, "Bmw", "bmw", 2019, "e zeza", 2000.00, "e re", 100000, "Benzines")); // nëse teston manualisht

        stage.setScene(new Scene(root, 700, 500));
        stage.setTitle("Test Porosia");
        stage.show();
    }
}
