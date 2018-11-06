package Pc.Logic.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Step implements Serializable{
    private ArrayList<Servo> servos;

    public Step() {
        this.servos = new ArrayList<Servo>();
    }
    public ArrayList<Servo> getMoves() {
        return servos;
    }
    public Servo getMove(int index){return servos.get(index);}
    public void addMove(Servo servo){
        servos.add(servo);
    }
    public boolean equals(Step step){
        if(
                getMoves().equals(step.getMoves())

        ){
            return true;
        } else {
            return false;
        }
    }
}