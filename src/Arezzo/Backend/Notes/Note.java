package Arezzo.Backend.Notes;

import Arezzo.Backend.Notation;


public class Note extends BasicNote
{
    private Notation.Notes note;
    private Notation.TypeNote typeNote;
    private Notation.Durees durees;

    public Note()
    {
        super();
        this.note = Notation.Notes.DO;
        this.typeNote = Notation.TypeNote.MEDUIM;
        this.durees = Notation.Durees.NONE;
        updateNotation();
    }

    public Note(Notation.Notes note, Notation.TypeNote type, Notation.Durees durees)
    {
        super(Notation.GetNotation(note, type, durees));
        this.note = note;
        this.typeNote = type;
        this.durees = durees;
        updateNotation();
    }

    public String getOctave()
    {
        return typeNote.toString();
    }

    public String getImage()
    {
        if (typeNote == Notation.TypeNote.MEDUIM){
            return "/images/noire.png";
        }
        return "/images/croche.png";
    }

    public void monterLeTon()
    {
        int index = 0;
        for (; index < Notation.notes.length; index++){
            if (note == Notation.notes[index]){
                break;
            }
        }
        int nextIndex = (index + 1) % Notation.notes.length;
        note = Notation.notes[nextIndex];
        updateNotation();
    }

    public void descendreLeTon()
    {
        int index = 0;
        for (; index < Notation.notes.length; index++){
            if (note == Notation.notes[index]){
                break;
            }
        }
        int nextIndex = (index - 1) % Notation.notes.length;
        note = Notation.notes[nextIndex];
        updateNotation();
    }

    public void updateNotation()
    {
        this.notation = Notation.GetNotation(note, typeNote, durees);
    }

    public Notation.Durees getDurees() {
        return durees;
    }
    public Notation.TypeNote getTypeNote() { return typeNote; }
    public Notation.Notes getNote() { return note; }

    public void transposer(int transposeValue)
    {
        for (int i = 0; i <transposeValue; i++) {
            this.monterLeTon();
        }
    }
}
