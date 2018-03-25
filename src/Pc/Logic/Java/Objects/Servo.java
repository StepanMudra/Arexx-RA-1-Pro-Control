package Pc.Logic.Java.Objects;

import java.io.Serializable;

public class Servo implements Serializable {
    private int id;
    private int angle;

    public Servo(int id, int angle) {
        this.id = id;
        this.angle = angle;
    }

    public int getId() {
        return id;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
