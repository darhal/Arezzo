package Arezzo.Backend;


import Arezzo.Backend.Notes.BasicNote;
import Arezzo.Backend.Notes.Note;
import Arezzo.Backend.Notes.Silence;
import partition.Partition;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Melodie implements Iterable<BasicNote>, Serializable
{
    private String titre;
    private transient Partition partition;
    private ArrayList<BasicNote> notes;

    public Melodie(String titre)
    {
        try {
            Synthesizer syn = MidiSystem.getSynthesizer();
            partition = new Partition(syn);
            notes = new ArrayList<>();
            this.titre = titre;
            partition.setMelodie(getMelodieNotation());
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Melodie()
    {
        try {
            Synthesizer syn = MidiSystem.getSynthesizer();
            partition = new Partition(syn);
            notes = new ArrayList<>();
            this.titre = "Melodie Sans Titre";
            partition.setMelodie(getMelodieNotation());
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void ajouter(BasicNote a)
    {
        notes.add(a);
        partition.setMelodie(getMelodieNotation());
        a.play(partition);
    }

    public String constructMelodie()
    {
        String melodie_notation = "";
        for (BasicNote n : notes){
            melodie_notation = melodie_notation + n + " ";
        }
        return melodie_notation;
    }


    public void PlayCurrent()
    {
        partition.setMelodie(constructMelodie());
        partition.play();
    }

    public void ajouterSilence(Silence s)
    {
        notes.add(s);
        partition.setMelodie(getMelodieNotation());
    }

    public void removeNote(BasicNote note)
    {
        int index = notes.indexOf(note);
        if (index != notes.size() - 1 && note instanceof Note){
            Note n = (Note) note;
            switch (n.getDurees()){
                case NONE:
                    notes.add(index, new Silence(Notation.Silences.DEMI_SOUPIR));
                    break;
                case NOIRE:
                    notes.add(index, new Silence(Notation.Silences.SOUPIR));
                    break;
                case RONDE:
                    notes.add(index, new Silence(Notation.Silences.PAUSE));
                    break;
                case CROCHE:
                    notes.add(index, new Silence(Notation.Silences.DEMI_SOUPIR));
                    break;
                case BLANCHE:
                    notes.add(index, new Silence(Notation.Silences.DEMI_PAUSE));
                    break;
            }
        }
        notes.remove(note);
        partition.setMelodie(getMelodieNotation());
    }

    public void monterLeTon(BasicNote note)
    {
        if (note instanceof Note){
            Note n = (Note)note;
            n.monterLeTon();
        }else if(note instanceof Silence){
            Silence s = (Silence)note;
            s.monterLeTon();
        }
        partition.setMelodie(getMelodieNotation());
    }

    public void descendreLeTon(BasicNote note)
    {
        if (note instanceof Note){
            Note n = (Note)note;
            n.descendreLeTon();
        }else if(note instanceof Silence){
            Silence s = (Silence)note;
            s.descendreLeTon();
        }
        partition.setMelodie(getMelodieNotation());
    }

    public void setInstrument(String instru)
    {
        partition.setInstrument(instru);
    }

    public void setVolume(double v)
    {
        partition.setVolume(v);
    }

    public void setTempo(double t)
    {
        partition.setTempo((int)t);
    }

    public String getMelodieNotation()
    {
        return constructMelodie();
    }

    public Partition getPartition() { return partition; }

    @Override
    public Iterator<BasicNote> iterator() {
        return notes.iterator();
    }

    public String getTitre()
    {
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
        partition.setTitre(titre);
    }

    public ArrayList<BasicNote> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<BasicNote> notes) {
        this.notes = notes;
        partition.setMelodie(getMelodieNotation());
    }

    public void transposer(int transposeValue)
    {
        for (BasicNote n : notes){
            n.transposer(transposeValue);
        }
        partition.setMelodie(getMelodieNotation());
    }
}
