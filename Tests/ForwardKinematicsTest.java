import Pc.Logic.Java.Calculations.ForwardKinematics;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ForwardKinematicsTest {
    @Test
    public void testForwardKinematics(){
        int[] angles =  new int[6];
        angles[0] = 0;
        angles[1] = 0;
        angles[2] = 0;
        angles[3] = 0;
        angles[4] = 0;
        angles[5] = 0;
        double[] expected = new double[3];
        expected[0] = 30.50;
        expected[1] = 0.00;
        expected[2] = 3.50;
        double[] coordinates = ForwardKinematics.calculateFK(angles);
        for (int i = 0; i < coordinates.length; i++) {
            System.out.println(Math.round(coordinates[i]));
            assertTrue(expected[i] == Math.round(coordinates[i]));
        }

        angles[0] = -90;
        angles[1] = 0;
        angles[2] = 0;
        angles[3] = 0;
        angles[4] = 0;
        angles[5] = 0;

        expected[0] = 0.0;
        expected[1] = -30.5;
        expected[2] = 3.5;

        coordinates = ForwardKinematics.calculateFK(angles);
        for (int i = 0; i < coordinates.length; i++) {
            assertTrue(expected[i] == Math.round(coordinates[i]));
        }
    }
}
