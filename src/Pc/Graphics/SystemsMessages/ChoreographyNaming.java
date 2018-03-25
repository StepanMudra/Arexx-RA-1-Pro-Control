package Pc.Graphics.SystemsMessages;

import Pc.Logic.Java.FileWorker;
import Pc.Logic.Java.Objects.Choreoghraphy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class ChoreographyNaming extends JFrame {
    private JPanel contentPanel;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;

    private FileWorker fileWorker;

    public ChoreographyNaming(FileWorker fileWorker) {
        this.setTitle("Choreograpy");
        this.setContentPane(contentPanel);
        this.getRootPane().setDefaultButton(buttonOK);
        this.setVisible(true);
        this.setSize(new Dimension(300,130));
        this.fileWorker = fileWorker;
        buttonOK.addActionListener(e -> openChoreography(textField1.getText()));
        buttonCancel.addActionListener(e -> this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
    }

    private Choreoghraphy openChoreography(String name){
        return fileWorker.loadChoreography(name);
    }

    private void createChoreography(String name, Choreoghraphy choreoghraphy){
        fileWorker.saveChoreography(choreoghraphy,name);
    }

    public ChoreographyNaming(FileWorker fileWorker, Choreoghraphy choreoghraphy){
        this.setTitle("New choreograpy");
        this.setContentPane(contentPanel);
        this.getRootPane().setDefaultButton(buttonOK);
    }

    public static void main(String[] args) {
        ChoreographyNaming choreographyNaming = new ChoreographyNaming(new FileWorker());
        System.out.println("bla");
    }
}
