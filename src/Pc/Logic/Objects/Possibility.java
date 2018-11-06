package Pc.Logic.Objects;

import java.io.Serializable;

public class Possibility implements Serializable {

    private int[] angles = {0,0,0,0};

    public Possibility() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.difference = 1000;
        this.singularite = false;
    }

    private double x;
    private double y;
    private double z;

    private double difference;

    private boolean singularite;

    public int getElement(int index){
        return angles[index];
    }

    public void setElement(int index, int element){
        angles[index] = element;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public boolean isSingularite() {
        return singularite;
    }

    public void setSingularite(boolean singularite) {
        this.singularite = singularite;
    }

    public int[] getAngles() {
        return angles;
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public boolean equals(Possibility possibility){
        if(
                getX() == possibility.getX() &&
                getY() == possibility.getY() &&
                getZ() == possibility.getZ() &&
                getElement(0) == possibility.getElement(0) &&
                getElement(1) == possibility.getElement(1) &&
                getElement(2) == possibility.getElement(3) &&
                getElement(3) == possibility.getElement(3) &&
                isSingularite() == possibility.isSingularite() &&
                getDifference() == possibility.getDifference()
        ){
            return true;
        } else {
          return false;
        }
    }
}

