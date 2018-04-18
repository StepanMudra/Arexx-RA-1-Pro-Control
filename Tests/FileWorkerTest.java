import Pc.Logic.Java.Services.FileWorker;
import Pc.Logic.Java.Objects.Choreography;
import Pc.Logic.Java.Objects.Servo;
import Pc.Logic.Java.Objects.Step;
import org.junit.*;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

public class FileWorkerTest {

    @Test
    public void testLoadChoreography(){

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

        assertTrue(choreography.getChoreography().size() == choreography1.getChoreography().size());

        for (int i = 0; i < choreography.getChoreography().size(); i++) {
            for (int j = 0; j < choreography.getChoreography().get(i).getMoves().size(); j++) {
                assertTrue(choreography.getChoreography().get(i).getMoves().get(j).getId() == choreography1.getChoreography().get(i).getMoves().get(j).getId());
                assertTrue(choreography.getChoreography().get(i).getMoves().get(j).getAngle() == choreography1.getChoreography().get(i).getMoves().get(j).getAngle());
            }
        }
    }
}
