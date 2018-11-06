package Pc.Logic.Services;

import Pc.Graphics.Forward;
import Pc.Graphics.SystemsMessages.OutOfWorkSpace;
import Pc.Graphics.SystemsMessages.WrongAngle;
import Pc.Logic.Objects.Possibility;
import org.apache.commons.math3.ode.JacobianMatrices;

public class Controller {

    public boolean chceckAngles(WrongAngle wrongAngle, int[] angles, boolean err) {
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

    public void singularityControl(Possibility possibility){
    }
}
