package Arezzo.Backend;

public class Notation
{
    public enum TypeNote
    {
        GRAVE, MEDUIM, AIGU, BARRE
    }

    public enum Silences
    {
        DEMI_SOUPIR, SOUPIR, DEMI_PAUSE,
        DEMI_PAUSE_POINTEE, PAUSE
    }

    public enum Durees
    {
        CROCHE, NOIRE, BLANCHE, RONDE, NONE
    }

    public enum Notes
    {
        DO, RE, MI, FA, SOL, LA, SI, DO_NOIR, RE_NOIR, FA_NOIR, SOL_NOIR, LA_NOIR
    }

    public enum Alterations
    {
        DIESE, BEMOL
    }

    public enum Instrument
    {
        PIANO, GUITARE, SAXOPHONE, TROMPETTE
    }

    public static String GetNotation(Instrument instru)
    {
        if (instru == Instrument.PIANO)
            return "Piano";
        else if(instru == Instrument.GUITARE)
            return "Guitare";
        else if(instru == Instrument.SAXOPHONE)
            return "Saxophone";
        else if(instru == Instrument.TROMPETTE)
            return "Trompette";

        return "Piano"; // default
    }

    public static String GetNotation(Notes note, TypeNote type)
    {
        String noteNotation = "|";
        if (type == TypeNote.BARRE)
            return noteNotation;

        switch (note){
            case DO:
                noteNotation = "c";
                break;
            case RE:
                noteNotation = "d";
                break;
            case MI:
                noteNotation = "e";
                break;
            case FA:
                noteNotation = "f";
                break;
            case SOL:
                noteNotation = "g";
                break;
            case LA:
                noteNotation = "a";
                break;
            case SI:
                noteNotation = "b";
                break;
            case DO_NOIR:
                noteNotation = "^c";
                break;
            case RE_NOIR:
                noteNotation = "^d";
                break;
            case FA_NOIR:
                noteNotation = "^e";
                break;
            case SOL_NOIR:
                noteNotation = "^f";
                break;
            case LA_NOIR:
                noteNotation = "^g";
                break;
        }

        if(type == TypeNote.AIGU){
            return noteNotation;
        }else if (type == TypeNote.GRAVE){
            noteNotation = noteNotation.toUpperCase()+",";
        }else if(type == TypeNote.MEDUIM){
            noteNotation = noteNotation.toUpperCase();
        }

        return noteNotation;
    }

    public static String GetNotation(Notes note, TypeNote type, Durees durees)
    {
        String normal = GetNotation(note, type);
        normal += GetNotation(durees);
        return normal;
    }

    public static String GetNotation(Silences s)
    {
        String silencesNotation = "z1/2";
        if (s == Silences.DEMI_SOUPIR)
            return silencesNotation;
        if (s == Silences.SOUPIR){
            silencesNotation = "z1";
        }else if(s == Silences.DEMI_PAUSE){
            silencesNotation = "z2";
        }else if(s == Silences.DEMI_PAUSE_POINTEE){
            silencesNotation = "z3";
        }else if(s == Silences.PAUSE){
            silencesNotation = "z4";
        }
        return silencesNotation;
    }

    public static String GetNotation(Durees d)
    {
        String dureeNotation = "";
        if (d == Durees.NONE){
            return dureeNotation;
        } else if (d == Durees.CROCHE){
            dureeNotation = "/";
        }else if(d == Durees.NOIRE){
            dureeNotation = "";
        }else if(d == Durees.BLANCHE){
            dureeNotation = "2";
        }else if(d == Durees.RONDE){
            dureeNotation = "4";
        }
        return dureeNotation;
    }

    public static String GetNotation(Alterations a){
        if (a == Alterations.DIESE){
            return "^";
        }else{
            return "_";
        }
    }

    public static Notes notes[] = {Notes.DO, Notes.DO_NOIR, Notes.RE, Notes.RE_NOIR, Notes.MI, Notes.FA, Notes.FA_NOIR, Notes.SOL, Notes.SOL_NOIR, Notes.SI};
}
