package Pc.Logic.Services.Calculations;

public class ForwardKinematics {

    public static double[] calculateFK(int[]angles){
        //System.out.println(angles[0]+" "+angles[1]);
        double servo1Rad = Math.toRadians(angles[0]);
        double servo2Rad = Math.toRadians(angles[1]);
        double servo3Rad = Math.toRadians(angles[2]);
        double servo4Rad = Math.toRadians(angles[3]);
        double x = Math.cos(servo1Rad)*(14.5*Math.cos(servo2Rad + servo3Rad + servo4Rad) + 8 * Math.cos(servo2Rad + servo3Rad) + 8 * Math.cos(servo2Rad));
        double y = Math.sin(servo1Rad)*(14.5*Math.cos(servo2Rad + servo3Rad + servo4Rad) + 8 * Math.cos(servo2Rad + servo3Rad) + 8 * Math.cos(servo2Rad));
        double z = -14.5*Math.sin(servo2Rad + servo3Rad + servo4Rad)-8*Math.sin(servo2Rad + servo3Rad)-8*Math.sin(servo2Rad)+3.5;
        double[]coordinates = {x, y, z};
        return coordinates;
    }


    public static void main(String[] args) {
        int[] angles = {
                0,
                0,
                0,
                0,
                0,
                0
        };
        double[] coordinates = ForwardKinematics.calculateFK(angles);
        for (int i = 0; i < coordinates.length; i++) {
            System.out.println(coordinates[i]);
        }
    }
}
