package Arezzo.Backend.Notes;

import partition.Partition;

import java.io.Serializable;

public abstract class BasicNote implements Serializable
{
    protected String notation;

    public BasicNote(String notation)
    {
        this.notation = notation;
    }

    public BasicNote()
    {
        notation = "";
    }

    public void play(Partition p)
    {
        p.play(notation);
    }

    public String getNotation() {
        return notation;
    }
    public void setNotation(String n){this.notation=n;}

    public String getOctave(){ return ""; }
    public String getImage() { return ""; }

    @Override
    public String toString() {
        return getNotation();
    }

    public abstract void monterLeTon();
    public abstract void descendreLeTon();

    public abstract void transposer(int transposeValue);
}
