package Pc.Logic.Services;

import Pc.Logic.Objects.Choreography;
import Pc.Logic.Objects.Servo;
import Pc.Logic.Objects.Step;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

public class FileWorkerTest {

    @Test
    public void saveChoreography() {
    }

    @Test
    public void loadMoznosti() {
    }

    @Test
    public void saveMoznosti() {
    }

    @Test
    public void loadChoreography() {
        FileWorker fileWorker = new FileWorker();
        Choreography choreography = fileWorker.loadChoreography("choreographyTest");

        Step step1 = new Step();
        step1.addMove(new Servo(0,180));
        step1.addMove(new Servo(1,0));
        step1.addMove(new Servo(2,18));
        step1.addMove(new Servo(3,15));
        step1.addMove(new Servo(4,90));
        step1.addMove(new Servo(5,160));

        Step step2 = new Step();
        step2.addMove(new Servo(0,0));
        step2.addMove(new Servo(1,15));
        step2.addMove(new Servo(2,180));
        step2.addMove(new Servo(3,30));
        step2.addMove(new Servo(4,90));
        step2.addMove(new Servo(5,170));

        Step step3 = new Step();
        step3.addMove(new Servo(0,42));
        step3.addMove(new Servo(1,42));
        step3.addMove(new Servo(2,42));
        step3.addMove(new Servo(3,42));
        step3.addMove(new Servo(4,42));
        step3.addMove(new Servo(5,43));

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        Choreography choreography1 = new Choreography(steps);

        assertTrue(choreography.getSteps().size() == choreography1.getSteps().size());

        for (int i = 0; i < choreography.getSteps().size(); i++) {
            for (int j = 0; j < choreography.getSteps().get(i).getMoves().size(); j++) {
                assertTrue(choreography.getSteps().get(i).getMoves().get(j).getId() == choreography1.getSteps().get(i).getMoves().get(j).getId());
                assertTrue(choreography.getSteps().get(i).getMoves().get(j).getAngle() == choreography1.getSteps().get(i).getMoves().get(j).getAngle());
            }
        }
    }
}