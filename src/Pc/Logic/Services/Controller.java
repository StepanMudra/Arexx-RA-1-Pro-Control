package Pc.Logic.Services;

import Pc.Graphics.Forward;
import Pc.Graphics.SystemsMessages.OutOfWorkSpace;
import Pc.Graphics.SystemsMessages.WrongAngle;
import Pc.Logic.Objects.Possibility;

public class Controller {

    public boolean checkAngles(int[] angles) {
        for (int i = 0; i <= 5 ; i++) {
            if (0>angles[i]||angles[i]>180) {
                return true;
            }
        }
        return false;
    }

    public boolean workSpaceControl(double[] coordinates){
        boolean xOutOfWorkSpace = -10.5<coordinates[0] && coordinates[0]<10.5;
        boolean yOutOfWorkSpace = -10.5<coordinates[1] && coordinates[1]<10.5;
        boolean zOutOfWorkSpace = -6<coordinates[2] && coordinates[2]<0;
        if((xOutOfWorkSpace && yOutOfWorkSpace && zOutOfWorkSpace) || coordinates[2] <= -6){
            new OutOfWorkSpace();
            return false;
        }
        return true;
    }

    public boolean workSpaceControl(double[] coordinates, Forward forward){
        boolean xOutOfWorkSpace = -10.5<coordinates[0] && coordinates[0]<10.5;
        boolean yOutOfWorkSpace = -10.5<coordinates[1] && coordinates[1]<10.5;
        boolean zOutOfWorkSpace = -6<coordinates[2] && coordinates[2]<0;
        if((xOutOfWorkSpace && yOutOfWorkSpace && zOutOfWorkSpace) || coordinates[2] <= -6){
            forward.getSave().disable();
            new OutOfWorkSpace();
            return false;
        }
        forward.getSave().enable();
        return true;
    }

    public boolean nullValuesControl(String servo1, String servo2, String servo3, String servo4, String servo5, String servo6) {
        if(servo1 == null || servo2 == null || servo3 == null || servo4 == null || servo5 == null || servo6 == null){
            return true;
        }else {
            return false;
        }
    }
}
