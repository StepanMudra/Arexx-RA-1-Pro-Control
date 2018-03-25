package Pc.Logic.Java.Communication;

import arduino.Arduino;

public class ArduinoCommunicator {

    public static void main(String[] args) {

        Arduino arduino = new Arduino(PythonInterface.findPorts().get(2), 9600);
        arduino.openConnection();
        arduino.serialWrite("Ahoj");
        System.out.println(arduino.serialRead());
    }
}
