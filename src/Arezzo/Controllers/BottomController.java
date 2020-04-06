package Arezzo.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import Arezzo.Backend.Notation.*;

public class BottomController extends Controller implements Initializable
{
    @FXML
    private Button
            B_DoBlanche, B_ReBlanche, B_MiBlanche, B_FaBlanche, B_SolBlanche, B_LaBlanche,
            B_SiBlanche, B_DoNoir, B_ReNoir, B_FaNoir, B_SolNoir, B_LaNoir, B_PlayBack, B_Silence;
    @FXML
    private RadioButton R_Aigue, R_Meduim, R_Grave, R_Ronde, R_Blanche,
            R_Noire, R_Croche, R_Piano, R_Saxo, R_Guitare, R_Tromp;
    @FXML
    private ToggleGroup RG_Type, RG_Instru, RG_Durees;
    @FXML
    private Slider S_Volume, S_Tempo;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        B_Silence.setOnAction(event -> this.OnSilenceClick());

        B_DoBlanche.setOnAction(event -> this.OnNoteClick(Notes.DO));
        B_ReBlanche.setOnAction(event -> this.OnNoteClick(Notes.RE));
        B_MiBlanche.setOnAction(event -> this.OnNoteClick(Notes.MI));
        B_FaBlanche.setOnAction(event -> this.OnNoteClick(Notes.FA));
        B_SolBlanche.setOnAction(event -> this.OnNoteClick(Notes.SOL));
        B_LaBlanche.setOnAction(event -> this.OnNoteClick(Notes.LA));
        B_SiBlanche.setOnAction(event -> this.OnNoteClick(Notes.SI));
        B_DoNoir.setOnAction(event -> this.OnNoteClick(Notes.DO_NOIR));
        B_ReNoir.setOnAction(event -> this.OnNoteClick(Notes.RE_NOIR));
        B_FaNoir.setOnAction(event -> this.OnNoteClick(Notes.FA_NOIR));
        B_SolNoir.setOnAction(event -> this.OnNoteClick(Notes.SOL_NOIR));
        B_LaNoir.setOnAction(event -> this.OnNoteClick(Notes.LA_NOIR));

        R_Aigue.setUserData(TypeNote.AIGU);
        R_Meduim.setUserData(TypeNote.MEDUIM);
        R_Grave.setUserData(TypeNote.GRAVE);

        R_Ronde.setUserData(Durees.RONDE);
        R_Blanche.setUserData(Durees.BLANCHE);
        R_Noire.setUserData(Durees.NOIRE);
        R_Croche.setUserData(Durees.CROCHE);

        R_Piano.setUserData(Instrument.PIANO);
        R_Guitare.setUserData(Instrument.GUITARE);
        R_Saxo.setUserData(Instrument.SAXOPHONE);
        R_Tromp.setUserData(Instrument.TROMPETTE);

        RG_Type.selectedToggleProperty().addListener(event -> OnNoteTypeCange());
        RG_Instru.selectedToggleProperty().addListener(event -> OnInstruChange());
        RG_Durees.selectedToggleProperty().addListener(event -> OnDureesChange());

        S_Volume.valueProperty().addListener(listener -> OnVolumeScroll());
        S_Tempo.valueProperty().addListener(listener -> OnTempoScroll());

        B_PlayBack.setOnAction(event -> OnPlayBack());
    }

    public void OnNoteClick(Notes note)
    {
        model.setNote(note);
    }

    public void OnSilenceClick()
    {
        model.ajouterSilence();
    }

    public void OnNoteTypeCange()
    {
        model.setTypeNote((TypeNote) RG_Type.getSelectedToggle().getUserData());
    }

    public void OnInstruChange()
    {
        model.setInstru((Instrument) RG_Instru.getSelectedToggle().getUserData());
    }

    public void OnVolumeScroll()
    {
        model.setVolume(S_Volume.getValue());
    }

    public void OnTempoScroll()
    {
        model.setTempo(S_Tempo.getValue());
    }

    public void OnPlayBack()
    {
        model.Play();
    }

    @Override
    public void update(Observable o, Object arg)
    {

    }

    public void OnDureesChange()
    {
        Durees durees_type = (Durees) RG_Durees.getSelectedToggle().getUserData();
        model.setDurees(durees_type);
        switch (durees_type){
            case NOIRE:
                model.setSilence(Silences.SOUPIR);
                break;
            case RONDE:
                model.setSilence(Silences.PAUSE);
                break;
            case CROCHE:
                model.setSilence(Silences.DEMI_SOUPIR);
                break;
            case BLANCHE:
                model.setSilence(Silences.DEMI_PAUSE);
                break;
        }
    }
}
