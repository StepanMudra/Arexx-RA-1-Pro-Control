package Pc.Logic.Services.Communication;

import Pc.Logic.Objects.Choreography;
import Pc.Logic.Objects.Servo;
import Pc.Logic.Objects.Step;
import com.fazecast.jSerialComm.SerialPort;

import java.io.*;
import java.util.ArrayList;

public class Communicator {

    private SerialPort serialPort;
    public static void main(String[] args) throws InterruptedException, IOException {
        Communicator pythonInterface = new Communicator();
        pythonInterface.openPort(findPorts().get(3));
        String[] data = {"0","180"};
        //pythonInterface.sendData(data);
    }

    public static ArrayList<String> findPorts(){
        ArrayList<String> ports = new ArrayList<>();
        SerialPort[] portsH = SerialPort.getCommPorts();
        for (int i = 0; i < portsH.length; i++) {
            System.out.println(portsH[i].getSystemPortName());
            ports.add(portsH[i].getSystemPortName());
        }
        return ports;
    }

    public boolean openPort(String por) throws InterruptedException {
        SerialPort[] serialPorts = SerialPort.getCommPorts();
        for (int i = 0; i < serialPorts.length; i++) {
            System.out.println(serialPorts[i].getDescriptivePortName());
            if(serialPorts[i].getSystemPortName().equals(por)){
                serialPort = serialPorts[i];
            }
        }
        this.serialPort.setBaudRate(9600);
        this.serialPort.openPort();
        System.out.println(serialPort.getBaudRate());
        System.out.println(serialPort.isOpen());
        Thread.sleep(3000);
        System.out.println(serialPort.isOpen());
        return serialPort.isOpen();
    }

    public void sendData(Choreography choreography, int waitingTime){
        for (int i = 0; i < choreography.getSteps().size(); i++) {
            Step step = choreography.getSteps().get(i);

            sendData(step);
            try {
                Thread.sleep(waitingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendData(Step step){
        for (Servo s: step.getMoves()){
           int[] data = {s.getId(), s.getAngle()};
            sendData(data);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*
        for (int i = 0; i < step.getMoves().size(); i++) {
            int id = step.getMoves().get(i).getId();
            int angle = step.getMoves().get(i).getAngle();
            int[] data = {id,angle};

            sendData(data);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */
    }

    public void sendData (int[] data) {
        PrintWriter writer = new PrintWriter(this.serialPort.getOutputStream());

        System.out.println("m "+data[0]+" "+data[1]);
        //System.out.println(serialPort.isOpen());
        writer.println("m "+data[0]+" "+data[1]);
        writer.flush();
    }
}

