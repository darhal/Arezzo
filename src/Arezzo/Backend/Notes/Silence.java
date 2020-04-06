package Arezzo.Backend.Notes;

import Arezzo.Backend.Notation;

public class Silence extends BasicNote
{
    private Notation.Silences silence;

    public Silence(Notation.Silences s)
    {
        super(Notation.GetNotation(s));
        silence = s;
    }

    public Silence()
    {
        super();
        silence = Notation.Silences.PAUSE;
        updateNotation();
    }

    public String getOctave()
    {
        return silence.toString();
    }

    public String getImage()
    {
        switch (silence){
            case PAUSE:
                return "/images/pause.png";
            case DEMI_PAUSE:
                return "/images/demiPause.png";
            case DEMI_SOUPIR:
                return "/images/demiSoupir.png";
            case SOUPIR:
                return "/images/soupir.png";
        }
        return "/images/soupir.png";
    }

    public void monterLeTon()
    {
        if (silence == Notation.Silences.DEMI_SOUPIR){
            silence = Notation.Silences.SOUPIR;
        }else if(silence == Notation.Silences.SOUPIR) {
            silence = Notation.Silences.DEMI_PAUSE;
        }else if(silence == Notation.Silences.DEMI_PAUSE_POINTEE) {
            silence = Notation.Silences.PAUSE;
        }
        updateNotation();
    }

    public void descendreLeTon()
    {
        if (silence == Notation.Silences.PAUSE){
            silence = Notation.Silences.DEMI_PAUSE_POINTEE;
        }else if(silence == Notation.Silences.DEMI_PAUSE_POINTEE) {
            silence = Notation.Silences.DEMI_PAUSE;
        }else if(silence == Notation.Silences.DEMI_PAUSE) {
            silence = Notation.Silences.SOUPIR;
        }else if(silence == Notation.Silences.SOUPIR){
            silence = Notation.Silences.DEMI_SOUPIR;
        }
        updateNotation();
    }

    public void updateNotation()
    {
        notation = Notation.GetNotation(silence);
    }

    public void transposer(int transposeValue)
    {
        for (int i = 0; i <transposeValue; i++) {
            this.monterLeTon();
        }
    }

    public Notation.Silences getSilence()
    {
        return silence;
    }
}
