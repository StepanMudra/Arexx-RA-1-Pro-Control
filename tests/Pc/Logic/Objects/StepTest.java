package Pc.Logic.Objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class StepTest {

    @Test
    public void getMoves() {
        Step step = new Step();
        Step stepNew = new Step();
        assertEquals(stepNew.getMoves(), step.getMoves());
        step.addMove(new Servo(3, 15));
        stepNew.addMove(new Servo(3, 15));
        assertEquals(stepNew.getMoves().get(0).getAngle(), step.getMoves().get(0).getAngle());
        assertEquals(stepNew.getMoves().get(0).getId(), step.getMoves().get(0).getId());
    }

    @Test
    public void getMove() {
        Step step = new Step();
        Servo servo = new Servo(3, 15);
        step.addMove(servo);
        assertEquals(servo, step.getMove(0));
    }

    @Test
    public void addMove() {
        Step step = new Step();
        assertEquals(0, step.getMoves().size());
        Step stepNew = new Step();
        stepNew.addMove(new Servo(3, 15));
        stepNew.addMove(new Servo(1, 15));
        stepNew.addMove(new Servo(4, 15));
        assertEquals(3, stepNew.getMoves().size());
    }
}