package Pc.Logic.Java.Calculations;

public class ForwardKinematics {

    public static double[] calculateFK(int[]angles){
        double servo1Rad = Math.toRadians(angles[0]);
        double servo2Rad = Math.toRadians(angles[1]);
        double servo3Rad = Math.toRadians(angles[2]);
        double servo4Rad = Math.toRadians(angles[3]);
        double servo5Rad = Math.toRadians(angles[4]);
        double servo6Rad = Math.toRadians(angles[5]);
        double x = Math.cos(servo1Rad)*(14.5*Math.cos(servo2Rad + servo3Rad + servo4Rad) + 8 * Math.cos(servo2Rad + servo3Rad) + 8 * Math.cos(servo2Rad));
        double y = Math.sin(servo1Rad)*(14.5*Math.cos(servo2Rad + servo3Rad + servo4Rad) + 8 * Math.cos(servo2Rad + servo3Rad) + 8 * Math.cos(servo2Rad));
        double z = -14.5*Math.sin(servo2Rad + servo3Rad + servo4Rad)-8*Math.sin(servo2Rad + servo3Rad)-8*Math.sin(servo2Rad)+3.5;
        double[]coordinates = new double[3];
        coordinates[0]=x;
        coordinates[1]=y;
        coordinates[2]=z;
        return coordinates;
    }


    public static void main(String[] args) {
        int[] angles = {
                0,
                0,
                90,
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
