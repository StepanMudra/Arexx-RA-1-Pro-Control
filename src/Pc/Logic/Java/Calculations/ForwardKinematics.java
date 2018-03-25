package Pc.Logic.Java.Calculations;

public class ForwardKinematics {
    public static double[] calculateFK(int[]angles){
        double x = 2*Math.cos(angles[0])*(5*Math.cos(angles[1]+angles[2]+angles[3])+4*(Math.cos(angles[1]+angles[2]+Math.cos(angles[1]))));
        double y = 2*Math.sin(angles[0])*(5*Math.cos(angles[1]+angles[2]+angles[3])+4*(Math.cos(angles[1]+angles[2]+Math.cos(angles[1]))));
        double z = 10*Math.sin(angles[1]+angles[2]+angles[3])+8*Math.sin(angles[1]+angles[2])+8*Math.sin(angles[1])+5;
        double[]coordinates = new double[3];
        coordinates[0]=x;
        coordinates[1]=y;
        coordinates[2]=z;
        return coordinates;
    }
}
