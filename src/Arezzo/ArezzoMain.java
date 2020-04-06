package Arezzo;

import Arezzo.Backend.Model;
import Arezzo.Controllers.BottomController;
import Arezzo.Controllers.MenuController;
import Arezzo.Controllers.MiddleController;
import Arezzo.Controllers.SplashScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class ArezzoMain extends Application {

    @Override
    public void start(Stage stage) {
        Pane root = null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            root = fxmlLoader.load(getClass().getResource("Vues/SplashScreen.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SplashScreen splashScreenController = fxmlLoader.getController();
        splashScreenController.setMain(this);
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);
    }

    public void LoadMain()
    {
        Pane root = new VBox();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane middleview = null;
        try {
            middleview = fxmlLoader.load(getClass().getResource("Vues/MiddleView.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        MiddleController middleController = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader();
        Pane bottomview = null;
        try {
            bottomview = fxmlLoader.load(getClass().getResource("Vues/BottomView.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BottomController bottomController = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader();
        MenuBar menuView = null;
        try {
            menuView = fxmlLoader.load(getClass().getResource("Vues/MainMenu.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController menuController = fxmlLoader.getController();

        root.getChildren().addAll(menuView, middleview, bottomview);

        Model model = new Model(middleController, bottomController, menuController);

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Arezzo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
