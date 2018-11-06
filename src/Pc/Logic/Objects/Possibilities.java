package Pc.Logic.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Possibilities implements Serializable {

    private ArrayList<Possibility> possibilities;

    public Possibilities(){
    this.possibilities = new ArrayList<>();
    }

    public ArrayList<Possibility> getAllPossibilities() {
        return possibilities;
    }

    public void addPossibility(Possibility possibility){
        this.possibilities.add(possibility);
    }
}
