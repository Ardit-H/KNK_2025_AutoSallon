package controllers;

import javafx.fxml.FXML;
import services.SceneManager;
import utils.SceneLocator;

public class DashboardHelpController {
    @FXML private  void handleCloseInfo()throws Exception{
        SceneManager.load(SceneLocator.OVERALL_DASHBOARD);
    }
}
