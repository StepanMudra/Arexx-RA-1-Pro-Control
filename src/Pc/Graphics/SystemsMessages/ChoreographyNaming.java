package Pc.Graphics.SystemsMessages;

import Pc.Graphics.Handeling;
import Pc.Logic.Services.FileWorker;
import Pc.Logic.Objects.Choreography;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;

public class ChoreographyNaming extends JFrame {
    private JPanel contentPanel;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JLabel message;
    private JButton revriteButton;
    private Handeling handeling;

    private FileWorker fileWorker;

    public ChoreographyNaming(Choreography choreography, FileWorker fileWorker) {
        this.setTitle("New Choreograpy");
        this.setContentPane(contentPanel);
        this.getRootPane().setDefaultButton(buttonOK);
        this.setVisible(true);
        this.setSize(new Dimension(500,130));
        this.fileWorker = fileWorker;
        buttonOK.addActionListener(e -> {
            String path = "Choreography/"+textField1.getText()+".bin";
            if(!new File(path).exists()) {
                fileWorker.saveChoreography(choreography, path);
            }else {
                message.setText("File with this name is exist. Pick diffierent name please or rewrite.");
                revriteButton.addActionListener(e1 -> {
                    fileWorker.saveChoreography(choreography, path);
                });
            }
        });
        buttonCancel.addActionListener(e -> this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
    }
    public ChoreographyNaming(FileWorker fileWorker, Handeling handeling){
        this.setTitle("Open choreography");
        this.setContentPane(contentPanel);
        this.setSize(new Dimension(500,130));
        this.getRootPane().setDefaultButton(buttonOK);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.fileWorker = fileWorker;
        this.setVisible(true);
        this.handeling = handeling;
        buttonOK.addActionListener(e -> {
            String path = "Choreography/"+textField1.getText()+".bin";
            if(new File(path).exists()) {
                handeling.setChoreography(fileWorker.loadChoreography(textField1.getText()));
                this.dispose();
            }else {
                message.setText("I could not find your file.");
            }
        });
        buttonCancel.addActionListener(e -> this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
    }
}