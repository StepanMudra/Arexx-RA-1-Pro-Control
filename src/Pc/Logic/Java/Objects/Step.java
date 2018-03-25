package Pc.Logic.Java.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Step implements Serializable{

    private ArrayList<Servo> servos;

    public Step() {
        this.servos = new ArrayList<>();
    }

    public ArrayList<Servo> getServos() {
        return servos;
    }

    public void addServo(Servo servo){
        this.servos.add(servo);
    }

    public static void main(String[] args) {
        Step step = new Step();
        step.addMove("1000","150");
        step.addMove("5000","150");
        step.addMove("7000","150");
        step.addMove("9000","150");
        step.addMove("15000","150");
        System.out.println(step.getMoves().size());
        step.removeSteps();
        System.out.println(step.getMoves().size());
    }
}
