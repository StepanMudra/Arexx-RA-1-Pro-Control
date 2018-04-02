package Pc.Logic.Java.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Choreography implements Serializable {
    private ArrayList<Step> choreography;

    public Choreography(ArrayList<Step> choreography) {
        this.choreography = choreography;
    }

    public ArrayList<Step> getChoreography() {
        return choreography;
    }

    public void setChoreography(ArrayList<Step> choreography) {
        this.choreography = choreography;
    }
    public void addStep(Step step, int indexPreviosStep){
        choreography.add(indexPreviosStep+1,step);
    }
}
