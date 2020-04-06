package Arezzo.Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import Arezzo.ArezzoMain;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SplashScreen implements Initializable {

    @FXML
    private Pane pane;
    private ArezzoMain main;

    public void setMain(ArezzoMain arezzoMain)
    {
        main = arezzoMain;
    }

    class ShowSplashScreen extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(2000);

                Platform.runLater(() -> {
                    main.LoadMain();
                    pane.getScene().getWindow().hide();
                });
            } catch (InterruptedException ex) {
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.setStyle("-fx-background-color: transparent;");
        new ShowSplashScreen().start();
    }
}