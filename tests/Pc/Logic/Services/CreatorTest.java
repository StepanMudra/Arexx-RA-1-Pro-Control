package Pc.Logic.Services;

import Pc.Logic.Objects.Possibility;
import Pc.Logic.Services.Calculations.ForwardKinematics;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreatorTest {

    @Test
    public void createPossibility() {
        Creator creator = new Creator();
        Possibility possibilityCreated = creator.createPossibility(0,0,0,0);
        Possibility possibilityUser = new Possibility();
        int[] angles = {0,0,0,0};
        possibilityUser.setX(ForwardKinematics.calculateFK(angles)[0]);
        possibilityUser.setY(ForwardKinematics.calculateFK(angles)[1]);
        possibilityUser.setZ(ForwardKinematics.calculateFK(angles)[2]);
        assertTrue(possibilityUser.equals(possibilityCreated));

    }
}