package Pc.Logic.Java.Communication;

import Pc.Logic.Java.Objects.Choreoghraphy;
import Pc.Logic.Java.Objects.Step;
import com.fazecast.jSerialComm.SerialPort;

import java.io.*;
import java.util.ArrayList;

public class PythonInterface {

    private SerialPort serialPort;
    public static void main(String[] args) throws InterruptedException, IOException {
        PythonInterface pythonInterface = new PythonInterface();
        pythonInterface.openPort(findPorts().get(4));
        String[] data = {"201","180"};
        pythonInterface.sendData(data);
        pythonInterface.recievData();
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

    public void openPort(String por) throws InterruptedException {
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
    }

    public void sendData(Choreoghraphy choreoghraphy) throws IOException, InterruptedException {
        for (int i = 0; i < choreoghraphy.getChoreography().size(); i++) {
            Step step = choreoghraphy.getChoreography().get(i);
            sendData(step);
        }
    }
    public void sendData(Step step) throws IOException, InterruptedException {
        for (int i = 1000; i < 7000; i += 1000) {
            String key = String.valueOf(i);
            String[] data = {key, step.getMove(key)};
            sendData(data);
        }

    }
    public void sendData(String[] data) throws IOException, InterruptedException {
        System.out.println(serialPort.isOpen());
        //OutputStream outputStream = this.serialPort.getOutputStream();

        PrintWriter writer = new PrintWriter(this.serialPort.getOutputStream());

        int port=Integer.parseInt(data[0]);
        //System.out.println(port);
        int angle=Integer.parseInt(data[1]);
        System.out.println(angle);
        //outputStream.write(port);
        //outputStream.flush();
//        outputStream.write(angle);
        //writer.write(port);
        //writer.flush();
        writer.write(angle);
        writer.flush();
//        outputStream.flush();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
        recievData();
    }
    public void recievData() throws IOException {
        InputStream inputStream = serialPort.getInputStream();
        System.out.println(inputStream.read());
    }
}

