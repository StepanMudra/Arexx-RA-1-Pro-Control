package Pc.Logic.Java.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Choreoghraphy implements Serializable {
    private ArrayList<Step> choreography;

    public Choreoghraphy(ArrayList<Step> choreography) {
        this.choreography = choreography;
    }

    public ArrayList<Step> getChoreography() {
        return choreography;
    }

    public void setChoreography(ArrayList<Step> choreography) {
        this.choreography = choreography;
    }
}
