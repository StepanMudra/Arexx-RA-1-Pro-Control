package Pc.Logic.Objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServoTest {

    @Test
    public void getId() {
        Servo servo = new Servo(0, 150);
        assertTrue(servo.getId() == 0);
    }

    @Test
    public void getAngle() {
        Servo servo = new Servo(5, 15);
        assertTrue(servo.getAngle() == 15);
    }
}