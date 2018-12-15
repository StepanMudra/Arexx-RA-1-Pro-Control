package Pc.Logic.Services.Calculations;

import Pc.Logic.Objects.Possibility;
import Pc.Logic.Services.Controller;
import Pc.Logic.Services.FileWorker;
import org.junit.Test;

import static org.junit.Assert.*;

public class InverseKinematicsTest {

    @Test
    public void findNearest() {

        double[] coordinates = {30.5, 0, 3.5};

        InverseKinematics inverseKinematics = new InverseKinematics(new FileWorker(), new Controller());

        Possibility possibility = inverseKinematics.findNearest(coordinates, new FileWorker().loadMoznosti());

        //System.out.println(possibility.getX());
        //System.out.println(possibility.getY());
        //System.out.println(possibility.getZ());

        //System.out.println(possibility.getAngles()[0]);
        //System.out.println(possibility.getAngles()[1]);
        //System.out.println(possibility.getAngles()[2]);
        //System.out.println(possibility.getAngles()[3]);

        assertEquals(0, possibility.getAngles()[0]);
        assertEquals(0, possibility.getAngles()[1]);
        assertEquals(0, possibility.getAngles()[2]);
        assertEquals(0, possibility.getAngles()[3]);

        //System.out.println(new FileWorker().loadMoznosti().getAllPossibilities().get(0).getX());
        //System.out.println(new FileWorker().loadMoznosti().getAllPossibilities().get(0).getY());
        //System.out.println(new FileWorker().loadMoznosti().getAllPossibilities().get(0).getZ());
    }

    @Test
    public void getPossibility() {

        double[] coordinates = {22.3062878993847, 20.800949981906204, 3.5};

        InverseKinematics inverseKinematics = new InverseKinematics(new FileWorker(), new Controller());

        Possibility possibility = inverseKinematics.getPossibility(coordinates);

        assertEquals(43, possibility.getAngles()[0]);
        assertEquals(0, possibility.getAngles()[1]);
        assertEquals(0, possibility.getAngles()[2]);
        assertEquals(0, possibility.getAngles()[3]);
    }
}