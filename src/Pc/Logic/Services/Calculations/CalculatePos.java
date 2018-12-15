package Pc.Logic.Services.Calculations;

import Pc.Logic.Objects.Possibilities;
import Pc.Logic.Objects.Possibility;
import Pc.Logic.Services.Creator;

public class CalculatePos {
    public static void main(String[] args) {
        //CalculatePos calculatePos = new CalculatePos();
        //calculatePos.calculate();
        Possibilities possibilities = new Possibilities();
        for (int i = 0; i < 360; i += 5) {
            for (int j = 0; j < 360; j += 5) {
                for (int k = 0; k < 360; k +=5) {
                    for (int l = 0; l < 360; l += 5) {
                        Possibility possibility = new Possibility();
                        possibility.setElement(0, i);
                        possibility.setElement(1, j);
                        possibility.setElement(2, k);
                        possibility.setElement(3, l);
                        double[] coordinates = ForwardKinematics.calculateFK(possibility.getAngles());
                        possibility.setX(coordinates[0]);
                        possibility.setY(coordinates[1]);
                        possibility.setZ(coordinates[2]);
                        possibilities.addPossibility(possibility);
                    }
                }
            }
        }
        System.out.println(possibilities.getAllPossibilities().size());
    }
    public Possibilities calculate(){
        Possibilities possibilities = new Possibilities();
        Creator creator = new Creator();
        for (int i = 0; i < 180; i += 5) {
            for (int j = 0; j < 180; j += 5) {
                for (int k = 0; k < 180; k +=5) {
                    for (int l = 0; l < 180; l += 5) {
                        possibilities.addPossibility(creator.createPossibility(i, j, k, l));
                    }
                }
            }
        }
        return possibilities;
    }
}
