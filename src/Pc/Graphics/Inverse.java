package Pc.Graphics;

import Pc.Graphics.SystemsMessages.WrongAngle;
import Pc.Graphics.SystemsMessages.WrongCoordinates;
import Pc.Logic.Objects.Possibility;
import Pc.Logic.Objects.Servo;
import Pc.Logic.Objects.Step;
import Pc.Logic.Services.Calculations.InverseKinematics;
import Pc.Logic.Services.Communication.Communicator;
import Pc.Logic.Services.Controller;
import Pc.Logic.Services.FileWorker;

import javax.swing.*;
import java.awt.*;

public class Inverse extends JFrame {


    private JPanel panel1;
    private JTextField servo1;
    private JTextField servo2;
    private JTextField servo3;
    private JTextField servo5;
    private JTextField servo4;
    private JTextField servo6;
    private JTextField xValue;
    private JTextField yValue;
    private JTextField zValue;
    private JButton backButton;
    private JButton countButton;
    private JButton doButton;
    private JLabel x;
    private JLabel y;
    private JLabel z;

    private Handeling handeling;
    private Communicator communicator;
    private Controller controller;
    private WrongCoordinates wrongCoordinates;
    private WrongAngle wrongAngle;
    private InverseKinematics inverseKinematics;
    private FileWorker fileWorker;

    private int[] angles;

    public Inverse(Handeling handeling, Communicator communicator, Controller controller, FileWorker fileWorker) {
        this.handeling = handeling;
        this.communicator = communicator;
        this.controller = controller;
        this.fileWorker = fileWorker;
        handeling.setVisible(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel1);
        this.setSize(new Dimension(500, 150));
        this.angles = new int[6];
        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.setFocusable(false);
            handeling.setVisible(true);
        });
        countButton.addActionListener(e -> {
            if(inverseKinematics == null){
                inverseKinematics = new InverseKinematics(fileWorker, controller);
            }
            if(xValue.getText() == null || yValue.getText() == null || zValue.getText() == null) {
            }else{
                try {
                    double[] coordinates = {Double.parseDouble(xValue.getText()), Double.parseDouble(yValue.getText()), Double.parseDouble(zValue.getText())};
                    if(!controller.workSpaceControl(coordinates)){}else {
                        Possibility possibility = inverseKinematics.getPossibility(coordinates);
                        servo1.setText(String.valueOf(possibility.getAngles()[0]));
                        servo2.setText(String.valueOf((possibility.getAngles()[1] < 180 ? ((possibility.getAngles()[1]) - 180) * (-1) : possibility.getAngles()[1] - 180)));
                        servo3.setText(String.valueOf((possibility.getAngles()[2] < 90 ? ((possibility.getAngles()[2]) - 90) * (-1) : possibility.getAngles()[2] - 90)));
                        servo4.setText(String.valueOf((possibility.getAngles()[3] + 90 >= 360 ? possibility.getAngles()[3] + 90 - 360 : possibility.getAngles()[3] + 90)));
                    }
                }catch(NumberFormatException nfe)
                {
                    if(wrongCoordinates == null){
                        wrongCoordinates = new WrongCoordinates();
                        wrongCoordinates.setVisible(true);
                    }else {
                        wrongCoordinates.setVisible(true);
                    }
                }
            }
        });
        doButton.addActionListener(e -> {
            if(controller.nullValuesControl(servo1.getText(), servo2.getText(), servo3.getText(), servo4.getText(), servo5.getText(), servo6.getText())){
                if(wrongAngle == null){
                    wrongAngle = new WrongAngle();
                    wrongAngle.setVisible(true);
                } else {
                    wrongAngle.setVisible(true);
                }
            }else {
                Step step = new Step();
                try {
                    fillAngles(Integer.parseInt(servo1.getText()), Integer.parseInt(servo2.getText()), Integer.parseInt(servo3.getText()), Integer.parseInt(servo4.getText()), Integer.parseInt(servo5.getText()), Integer.parseInt(servo6.getText()));
                    createStep(step);
                    sendStep(step);
                }catch (NumberFormatException nfe){
                    if(wrongAngle == null){
                        wrongAngle = new WrongAngle();
                        wrongAngle.setVisible(true);
                    } else {
                        wrongAngle.setVisible(true);
                    }
                }
            }
        });
    }

    private void fillAngles(int angle1, int angle2, int angle3, int angle4, int angle5, int angle6){
        angles[0] = angle1;
        angles[1] = angle2;
        angles[2] = angle3;
        angles[3] = angle4;
        angles[4] = angle5;
        angles[5] = angle6;
    }

    private void createStep(Step step){
        step.addMove(new Servo(0, angles[0]));
        step.addMove(new Servo(1, angles[1]));
        step.addMove(new Servo(2, angles[2]));
        step.addMove(new Servo(3, angles[3]));
        step.addMove(new Servo(4, angles[4]));
        step.addMove(new Servo(5, angles[5]));
    }

    private void sendStep(Step step) {
        communicator.sendData(step);
    }
}