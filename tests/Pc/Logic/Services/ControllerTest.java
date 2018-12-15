package Pc.Logic.Services;

import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {

    @Test
    public void checkAngles() {
        Controller controller = new Controller();
        boolean error = true;
        int[] angles = new int[6];
        angles[0] = 0;
        angles[1] = 0;
        angles[2] = 0;
        angles[3] = 0;
        angles[4] = 0;
        angles[5] = 0;
        error = controller.checkAngles(angles);
        assertFalse(error);
        angles[0] = 180;
        angles[1] = 180;
        angles[2] = 180;
        angles[3] = 180;
        angles[4] = 180;
        angles[5] = 180;
        error = controller.checkAngles(angles);
        assertFalse(error);
        angles[0] = 180;
        angles[1] = 180;
        angles[2] = 180;
        angles[3] = 180;
        angles[4] = 180;
        angles[5] = 181;
        error = controller.checkAngles(angles);
        assertTrue(error);
        angles[0] = 0;
        angles[1] = 0;
        angles[2] = 0;
        angles[3] = 0;
        angles[4] = 0;
        angles[5] = -1;
        error = controller.checkAngles(angles);
        assertTrue(error);
    }

    @Test
    public void workSpaceControl() {
        Controller controller = new Controller();
        double[] coordinates = new double[3];
        controller.workSpaceControl(coordinates);
    }
}