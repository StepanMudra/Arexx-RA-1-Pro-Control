package Pc.Logic.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Choreography implements Serializable {
    private ArrayList<Step> steps;

    public Choreography(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
    public void addStep(Step step, int indexPreviousStep){
        steps.add(indexPreviousStep+1,step);
    }
}
