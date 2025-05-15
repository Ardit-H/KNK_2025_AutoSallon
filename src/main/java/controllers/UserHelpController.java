package controllers;

import javafx.fxml.FXML;
import services.SceneManager;
import utils.SceneLocator;

public class UserHelpController {

    @FXML private void handleCloseInfo()throws Exception {
        SceneManager.getInstance().setCenterPanePath(SceneLocator.USER_DASHBOARD_HOME);
        SceneManager.reload();
    }
}
