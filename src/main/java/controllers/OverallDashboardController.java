package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.LanguageManager;
import services.SceneManager;
import utils.SceneLocator;

import java.util.Locale;

public class OverallDashboardController {
    @FXML
    private ImageView mainImage;
    @FXML
    private Button buttoni_qasja;
    @FXML
    private ImageView infoimg;

    @FXML
    private Pagination pagPagination;
    private final String[] imagePaths = {
            "/Images/Car.jpg",
            "/Images/AdobeStock_735926213_Preview.jpeg",
            "/Images/AdobeStock_930679947_Preview.jpeg",
            "/Images/pexels-maria-geller-801267-2127037.jpg",
            "/Images/AdobeStock_1011767359_Preview.jpeg",
            "/Images/pexels-lynxexotics-3849555.jpg"
    };
    @FXML
    private ImageView imgTranslate;
    @FXML private Button languageToggleButton;
    private boolean isEnglish = true;
    private LanguageManager languageManager;

    public OverallDashboardController(){
        this.languageManager=LanguageManager.getInstance();
    }
    @FXML public void initialize(){
        if (languageManager.getLocale().equals(new Locale("en"))) {
            setLanguageIcon("/Images/language-en.png");
            isEnglish = true;
        } else {
            setLanguageIcon("/Images/language-sq.png");
            isEnglish = false;
        }
        pagPagination.setPageCount(imagePaths.length);
        pagPagination.setCurrentPageIndex(0);

        pagPagination.setPageFactory(pageIndex -> {
            if (pageIndex >= 0 && pageIndex < imagePaths.length) {
                mainImage.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream(imagePaths[pageIndex])));
            }
            return new AnchorPane(); // kthen një AnchorPane bosh vetëm që Pagination të punojë
        });
    }

    @FXML
    private void handleLoadLogin()throws Exception{
        SceneManager.load(SceneLocator.LOGIN);
    }
    @FXML
    private void handleTextQasja()throws Exception{
        SceneManager.load(SceneLocator.LOGIN);
    }
    @FXML
    public void buttoni_qasja_hover(MouseEvent event){
        buttoni_qasja.setStyle("-fx-opacity: 0.9;" +
                "-fx-background-color:  linear-gradient(to bottom, #0a84ff, #00a5ff, #00c0ff, #00d7f7, #12ebe5)");
    }

    @FXML
    public void buttoni_qasja_exit(MouseEvent event){

        buttoni_qasja.setStyle("-fx-opacity: 0.5;" +
                "-fx-background-color:  linear-gradient(to bottom, #0a84ff, #00a5ff, #00c0ff, #00d7f7, #12ebe5)");
    }
    @FXML
    public void teksti_qasja_hover(MouseEvent event) {
        buttoni_qasja.setStyle("-fx-opacity: 0.9;" +
                "-fx-background-color:  linear-gradient(to bottom, #0a84ff, #00a5ff, #00c0ff, #00d7f7, #12ebe5)");
    }

    @FXML
    public void teksti_qasja_exit(MouseEvent event) {

        buttoni_qasja.setStyle("-fx-opacity: 0.5;" +
                "-fx-background-color:  linear-gradient(to bottom, #0a84ff, #00a5ff, #00c0ff, #00d7f7, #12ebe5)");
    }
    @FXML
    private void handleAutoSalloni(ActionEvent ae){
        String url = "https://www.example.com";
        openWebpage(url);
    }
    private void openWebpage(String urlString) {
        String url = "https://www.example.com";
    }
    @FXML private void handleLanguageToggle() throws Exception{
        if (isEnglish) {
            loadLanguage(new Locale("sq"));
            setLanguageIcon("/Images/language-sq.png");
        } else {
            loadLanguage(Locale.ENGLISH);
            setLanguageIcon("/Images/language-en.png");
        }
        isEnglish =!isEnglish;
    }

    private void setLanguageIcon(String imagePath) {
        ImageView imageView = new ImageView(new javafx.scene.image.Image(getClass().getResourceAsStream(imagePath)));
        imageView.setFitHeight(20);
        imageView.setFitWidth(30);
        languageToggleButton.setGraphic(imageView);
    }
    private void loadLanguage(Locale locale) throws Exception{
        languageManager.setLocale(locale);
        SceneManager.reload();
    }
}
