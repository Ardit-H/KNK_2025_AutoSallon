package controllers;

import javafx.fxml.FXML;
import services.SceneManager;
import utils.SceneLocator;

public class AdminHelpController {

    @FXML private void handleCloseInfo()throws Exception {
        SceneManager.getInstance().setCenterPanePath(SceneLocator.ADMIN_DASHBOARD_HOME);
        SceneManager.reload();
    }
}
