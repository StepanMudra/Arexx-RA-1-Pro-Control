package Pc.Logic.Java.Communication;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.io.InputStream;

public class RcievData extends Thread{
    public String recieving(String por) throws IOException {
        String dat;
        SerialPort serialPort = SerialPort.getCommPort("dev/"+por);

        serialPort.openPort();

        InputStream inputStream = serialPort.getInputStream();
        while (inputStream.available() < 1){}
        dat = String.valueOf(inputStream.read());
        return dat;
    }
}
