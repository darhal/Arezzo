package Arezzo.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import partition.Partition;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class MiddleController extends Controller implements Initializable
{
    @FXML
    private ScrollPane P_MelodiePane;
    @FXML
    private Button B_LesNotes;
    @FXML
    private Label L_SongName;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        B_LesNotes.setOnAction(event -> OnNotesClick());
    }

    private void OnNotesClick()
    {
        Stage stage = null;
        if(stage == null){
            Parent parent = null;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Arezzo/Vues/NotesWindow.fxml"));
                parent = loader.load();
                NotesWindowController notesWindowController = loader.getController();
                model.addObserver(notesWindowController);
                notesWindowController.updateList(model);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Liste Des Notes : ");
            stage.setScene(new Scene(parent));
        }
        stage.show();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Partition p = model.getMelodie().getPartition();
        p.setPreferedMaxWidth((int)P_MelodiePane.getWidth());
        if (p != null) {
            Image img = p.getImage();
            if (img != null){
                P_MelodiePane.setContent(new ImageView(img));
            }
        }
        L_SongName.setText(model.getMelodie().getTitre());
    }

}
