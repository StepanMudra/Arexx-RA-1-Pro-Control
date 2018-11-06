package Pc.Logic.Objects;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ChoreographyTest {

    @Test
    public void getSteps() {
        ArrayList<Step> steps = new ArrayList<>();
        Choreography choreography = new Choreography(steps);
        assertEquals(steps, choreography.getSteps());
    }

    @Test
    public void setSteps() {
        ArrayList<Step> steps = new ArrayList<>();
        Choreography choreography = new Choreography(steps);
        Step step = new Step();
        step.addMove(new Servo(0,180));
        step.addMove(new Servo(1,0));
        step.addMove(new Servo(2,18));
        step.addMove(new Servo(3,15));
        step.addMove(new Servo(4,90));
        step.addMove(new Servo(5,160));
        steps.add(step);
        assertEquals(steps, choreography.getSteps());

        ArrayList<Step> steps1 = new ArrayList<>();
        Choreography choreography1 = new Choreography(steps);
        Step step1 = new Step();
        step1.addMove(new Servo(0,150));
        step1.addMove(new Servo(1,0));
        step1.addMove(new Servo(2,18));
        step1.addMove(new Servo(3,15));
        step1.addMove(new Servo(4,90));
        step1.addMove(new Servo(5,160));
        steps.add(step);
        choreography.setSteps(steps1);
        assertEquals(steps, choreography1.getSteps());
        assertEquals(steps.get(0), choreography1.getSteps().get(0));
        assertEquals(steps.get(0).getMoves(), choreography1.getSteps().get(0).getMoves());
        assertEquals(steps.get(0).getMoves().get(0), choreography1.getSteps().get(0).getMoves().get(0));
        assertNotSame(steps.get(0).getMoves().get(0).getAngle(), choreography1.getSteps().get(0).getMoves().get(0).getAngle());
        assertEquals(steps.get(0).getMoves().get(1).getAngle(), choreography1.getSteps().get(0).getMoves().get(1).getAngle());
        assertEquals(steps.get(0).getMoves().get(2).getAngle(), choreography1.getSteps().get(0).getMoves().get(2).getAngle());
        assertEquals(steps.get(0).getMoves().get(3).getAngle(), choreography1.getSteps().get(0).getMoves().get(3).getAngle());
        assertEquals(steps.get(0).getMoves().get(4).getAngle(), choreography1.getSteps().get(0).getMoves().get(4).getAngle());
        assertEquals(steps.get(0).getMoves().get(5).getAngle(), choreography1.getSteps().get(0).getMoves().get(5).getAngle());
    }

    @Test
    public void addStep() {
        Step step1 = new Step();
        step1.addMove(new Servo(0,180));
        step1.addMove(new Servo(1,0));
        step1.addMove(new Servo(2,18));
        step1.addMove(new Servo(3,15));
        step1.addMove(new Servo(4,90));
        step1.addMove(new Servo(5,160));

        ArrayList<Step> steps = new ArrayList<>();
        Choreography choreography = new Choreography(steps);

        assertEquals(0, choreography.getSteps().size());

        choreography.addStep(step1, -1);

        assertEquals(step1, choreography.getSteps().get(0));
        assertEquals(1, choreography.getSteps().size());
        assertTrue(step1.equals(choreography.getSteps().get(0)));

    }
}