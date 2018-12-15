package Pc.Logic.Services.Calculations;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class ForwardKinematicsTest {

    @Test
    public void calculateFK() {
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
            assertEquals(expected[i], coordinates[i], 0.01);
        }

        angles[0] = 0;
        angles[1] = 270;
        angles[2] = 0;
        angles[3] = 0;
        angles[4] = 0;
        angles[5] = 0;

        expected[0] = 0;
        expected[1] = 0.0;
        expected[2] = 34;

        coordinates = ForwardKinematics.calculateFK(angles);
        for (int i = 0; i < coordinates.length; i++) {
            assertEquals(expected[i], coordinates[i], 0.01);
        }
    }
}