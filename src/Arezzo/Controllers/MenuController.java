package Arezzo.Controllers;

import Arezzo.Backend.Melodie;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuController extends Controller implements Initializable
{
    @FXML
    private Menu M_Melodie, M_Outils;
    @FXML
    private MenuItem MI_Nouveau, MI_Ouvrir, MI_Enregistrer, MI_Quitter,
            MI_Renommer, MI_Transposer;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        MI_Renommer.setOnAction(event -> OnChangeName());
        MI_Quitter.setOnAction(event -> Quit());
        MI_Ouvrir.setOnAction(event -> Open());
        MI_Enregistrer.setOnAction(event -> Save());
        MI_Nouveau.setOnAction(event -> NewParition());
        MI_Transposer.setOnAction(event -> OnTransposerClick());
    }

    private void OnTransposerClick()
    {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Transposer");
        dialog.setContentText("Entrez la valeur de transposer SVP :");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            int transposeValue = 1;
            transposeValue = Integer.parseInt(result.get());
            model.transposeMelodie(transposeValue);
        }
    }

    private void NewParition()
    {
        model.reset();
    }

    private void Save()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer une parition :");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arezzo files (*.arezzo)", "*.arezzo");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                FileOutputStream fos = new FileOutputStream(file.getPath());
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(model.getMelodie());
                oos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void Open()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner un fichier a ouvrir :");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arezzo files (*.arezzo)", "*.arezzo");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                FileInputStream fileIn = new FileInputStream(file.getPath());
                ObjectInputStream ois = new ObjectInputStream(fileIn);
                Melodie melodie = (Melodie) ois.readObject();
                model.setMelodieNotes(melodie.getNotes());
                model.setName(melodie.getTitre());
                ois.close();
                fileIn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void Quit()
    {
        Platform.exit();
        System.exit(0);
    }

    private void OnChangeName()
    {
        TextInputDialog dialog = new TextInputDialog("Renommer Partition");
        dialog.setTitle("Renommer Partition");
        dialog.setHeaderText("Renommer la partition courrante");
        dialog.setContentText("Veuillez entrer le nom de parition :");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            model.setName(result.get());
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {

    }
}
