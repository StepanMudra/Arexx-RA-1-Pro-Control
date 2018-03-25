package Pc.Logic.Java.Communication;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.io.OutputStream;

public class SendData extends Thread{
    public void sending(String[]data, String por) throws IOException {
        SerialPort serialPort = SerialPort.getCommPort("dev/"+por);

        serialPort.openPort();
        OutputStream outputStream  =serialPort.getOutputStream();

        outputStream.write(15);
        outputStream.flush();
        outputStream.close();
        serialPort.closePort();

        Thread.currentThread().destroy();
    }
}
