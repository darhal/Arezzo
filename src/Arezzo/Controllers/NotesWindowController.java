package Arezzo.Controllers;

import Arezzo.Backend.Model;
import Arezzo.Backend.Notes.BasicNote;
import Arezzo.Backend.Notes.Note;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class NotesWindowController extends Controller implements Initializable
{
    @FXML
    private ListView LV_Notes;
    private ContextMenu contextMenu;
    private Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        contextMenu = new ContextMenu();

        // create menuitems
        MenuItem effacer = new MenuItem("Effacer");
        MenuItem monter = new MenuItem("Monter d'un 1/2 ton");
        MenuItem descendre = new MenuItem("Descendre d'un 1/2 ton");

        effacer.setOnAction(event -> ClickEffacer());
        monter.setOnAction(event -> ClickMonter());
        descendre.setOnAction(event -> ClickDescendre());

        // add menu items to menu
        contextMenu.getItems().add(effacer);
        contextMenu.getItems().add(monter);
        contextMenu.getItems().add(descendre);

        LV_Notes.setContextMenu(contextMenu);

    }

    private void ClickDescendre()
    {
        for (Object i : LV_Notes.getSelectionModel().getSelectedItems()){
            Pane notePane = (Pane)i;
            if (notePane != null){
                BasicNote note = (BasicNote)notePane.getUserData();
                if (note != null){
                    model.descendreLeTon(note);
                }
            }
        }
    }

    private void ClickMonter() 
    {
        for (Object i : LV_Notes.getSelectionModel().getSelectedItems()){
            Pane notePane = (Pane)i;
            if (notePane != null){
                BasicNote note = (BasicNote)notePane.getUserData();
                if (note != null){
                    model.monterLeTon(note);
                }
            }
        }
    }

    private void ClickEffacer() 
    {
        for (Object i : LV_Notes.getSelectionModel().getSelectedItems()){
            Pane notePane = (Pane)i;
            if (notePane != null){
                BasicNote note = (BasicNote)notePane.getUserData();
                if (note != null){
                    model.removeNote(note);
                }
            }
        }
    }

    public void updateList(Model model)
    {
        this.model = model;
        LV_Notes.getItems().clear();
        for(BasicNote note : model.getMelodie())
        {
            HBox notePane = new HBox();
            notePane.setSpacing(30);
            ImageView img = new ImageView(new Image(getClass().getResourceAsStream(note.getImage())));
            img.setFitWidth(30);
            img.setFitHeight(30);
            notePane.getChildren().addAll(
                    new Text(note.getOctave().replaceAll("_", " ")),
                    img,
                    new Text(note.toString()));
            notePane.setUserData(note);
            LV_Notes.getItems().add(notePane);
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (model == null) return;
        updateList(model);
    }
}
