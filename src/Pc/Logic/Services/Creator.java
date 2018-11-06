package Pc.Logic.Services;

import Pc.Logic.Objects.Possibility;
import Pc.Logic.Services.Calculations.ForwardKinematics;

public class Creator {
    public Possibility createPossibility(int i, int j, int k, int l){
        Possibility possibility = new Possibility();
        possibility.setElement(0, i);
        possibility.setElement(1, j);
        possibility.setElement(2, k);
        possibility.setElement(3, l);
        double[] coordinates = ForwardKinematics.calculateFK(possibility.getAngles());
        possibility.setX(coordinates[0]);
        possibility.setY(coordinates[1]);
        possibility.setZ(coordinates[2]);
        return possibility;
    }
}
