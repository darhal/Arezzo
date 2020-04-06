package Arezzo.Backend;

import Arezzo.Backend.Notes.BasicNote;
import Arezzo.Backend.Notes.Note;
import Arezzo.Backend.Notes.Silence;
import Arezzo.Controllers.Controller;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable
{
    private double volume;
    private double tempo;
    private Notation.Notes note;
    private Notation.TypeNote typeNote;
    private Notation.Silences silence;
    private Notation.Durees durees;
    private Notation.Alterations alt;
    private Notation.Instrument instru;

    private Melodie melodie;
    private ArrayList<Controller> controllers;

    public Model(Controller... observers)
    {
        typeNote = Notation.TypeNote.MEDUIM;
        instru = Notation.Instrument.PIANO;
        silence = Notation.Silences.PAUSE;
        durees = Notation.Durees.NONE;
        alt = Notation.Alterations.DIESE;

        controllers = new ArrayList<>();
        for(Controller obs : observers){
            controllers.add(obs);
            addObserver(obs);
        }
        reset();
    }

    public void Play()
    {
        melodie.PlayCurrent();
    }

    public void setNote(Notation.Notes nt)
    {
        this.note = nt;
        melodie.ajouter(new Note(note, typeNote, durees));
        setChanged();
        notifyObservers();
    }
    public Notation.Notes getNote() { return note; }

    public Notation.TypeNote getTypeNote() { return typeNote; }
    public void setTypeNote(Notation.TypeNote type)
    {
        this.typeNote = type;
    }

    public Notation.Instrument getInstru() { return instru; }
    public void setInstru(Notation.Instrument instru) {
        this.instru = instru;
        melodie.setInstrument(Notation.GetNotation(instru));
    }

    public double getVolume() { return volume; }
    public void setVolume(double volume)
    {
        this.volume = volume;
        melodie.setVolume(volume);
    }

    public double getTempo() { return tempo; }
    public void setTempo(double temp) {
        this.tempo = temp;
        melodie.setTempo(tempo);
    }

    public Notation.Silences getSilence() { return silence; }
    public void setSilence(Notation.Silences silence) { this.silence = silence; }

    public Notation.Durees getDurees() { return durees; }
    public void setDurees(Notation.Durees durees) { this.durees = durees; }

    public Notation.Alterations getAlt() { return alt; }
    public void setAlt(Notation.Alterations alt) { this.alt = alt; }

    public Melodie getMelodie(){return melodie;}

    public void ajouterSilence()
    {
        melodie.ajouterSilence(new Silence(silence));
        setChanged();
        notifyObservers();
    }

    public void removeNote(BasicNote note)
    {
        melodie.removeNote(note);
        setChanged();
        notifyObservers();
    }

    public void monterLeTon(BasicNote note)
    {
        melodie.monterLeTon(note);
        setChanged();
        notifyObservers();
    }

    public void descendreLeTon(BasicNote note)
    {
        melodie.descendreLeTon(note);
        setChanged();
        notifyObservers();
    }

    public void setName(String titre)
    {
        melodie.setTitre(titre);
        setChanged();
        notifyObservers();
    }

    public void reset()
    {
        melodie = new Melodie();
        for (Controller controller : controllers){
            controller.setModel(this);
        }
        setChanged();
        notifyObservers();
    }

    public void setMelodieNotes(ArrayList<BasicNote> notes)
    {
        melodie.setNotes(notes);
        setChanged();
        notifyObservers();
    }

    public void transposeMelodie(int transposeValue)
    {
        melodie.transposer(transposeValue);
        setChanged();
        notifyObservers();
    }
}
