package Pc.Graphics;

import Pc.Graphics.SystemsMessages.OutOfWorkSpace;
import Pc.Graphics.SystemsMessages.WrongAngle;
import Pc.Logic.Java.Calculations.ForwardKinematics;
import Pc.Logic.Java.Communication.Communicator;
import Pc.Logic.Java.Objects.Servo;
import Pc.Logic.Java.Objects.Step;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created by stepanmudra on 11.07.17.
 */
public class Forward extends JFrame{

    private JButton back;
    private JPanel panel;
    private JButton count;
    private JButton save;
    private JTextField motor1;
    private JTextField motor2;
    private JTextField motor3;
    private JTextField motor4;
    private JTextField motor5;
    private JTextField motor6;
    private JTextField x;
    private JTextField y;
    private JTextField z;
    private Forward forward;
    private WrongAngle wrongAngle;
    private Communicator communicator;
    int angle1;
    int angle2;
    int angle3;
    int angle4;
    int angle5;
    int angle6;

    public Forward(Handeling handeling, Communicator pythonInterface){
        this.communicator = pythonInterface;
        handeling.setVisible(false);
        this.setVisible(true);
        this.setSize(new Dimension(500, 150));
        this.forward = this;
        forward.add(panel);
        back.addActionListener(e -> {
            this.setVisible(false);
            this.setFocusable(false);
            handeling.setVisible(true);
        });
        count.addActionListener((ActionEvent e) -> {
            nullValuesControl();
            try {
                angle1 = Integer.parseInt(motor1.getText());
                angle2 = Integer.parseInt(motor2.getText());
                angle3 = Integer.parseInt(motor3.getText());
                angle4 = Integer.parseInt(motor4.getText());
                angle5 = Integer.parseInt(motor5.getText());
                angle6 = Integer.parseInt(motor6.getText());
            }catch (NumberFormatException exc){
                if(wrongAngle == null){
                    wrongAngle = new WrongAngle();
                }else {
                    wrongAngle.setVisible(true);
                }
            }
            int[]angles = new int[6];
            angles[0] = angle1;
            angles[1] = angle2;
            angles[2] = angle3;
            angles[3] = angle4;
            angles[4] = angle5;
            angles[5] = angle6;
            for (int i = 0; i < 5; i++) {
                if (0>angles[i]||angles[i]>180){
                    if(wrongAngle == null){
                        wrongAngle = new WrongAngle();
                    }else {
                        wrongAngle.setVisible(true);
                    }
                    return;
                }
            }
            double[] coordinates = ForwardKinematics.calculateFK(angles);
            workSpaceControl(coordinates);
            this.x.setText(String.valueOf(coordinates[0]));
            this.y.setText(String.valueOf(coordinates[1]));
            this.z.setText(String.valueOf(coordinates[1]));
        });
        save.addActionListener(e -> {
            nullValuesControl();
            Step step = new Step();
            createStep(step);
            sendStep(step);
        });
    }
    private void workSpaceControl(double[] coordinates){
        boolean xOutOfWorkSpace = -10.5<coordinates[0] && coordinates[0]<10.5;
        boolean yOutOfWorkSpace = -10.5<coordinates[1] && coordinates[1]<10.5;
        boolean zOutOfWorkSpace = -6<coordinates[2] && coordinates[2]<0;
        if((xOutOfWorkSpace && yOutOfWorkSpace && zOutOfWorkSpace) || coordinates[2] <= -6){
            this.save.disable();
            new OutOfWorkSpace();
        }
        this.save.enable();
    }
    private void createStep(Step step){
        step.addMove(new Servo(0, angle1));
        step.addMove(new Servo(1, angle2));
        step.addMove(new Servo(2, angle3));
        step.addMove(new Servo(3, angle4));
        step.addMove(new Servo(4, angle5));
        step.addMove(new Servo(5, angle6));
    }
    private void nullValuesControl() {
        if (motor1.getText() == null && nullWrongAngle()) {
            wrongAngle = new WrongAngle();
        }
        if (motor2.getText() == null && nullWrongAngle()) {
            wrongAngle = new WrongAngle();
        }
        if (motor3.getText() == null && nullWrongAngle()) {
            wrongAngle = new WrongAngle();
        }
        if (motor4.getText() == null && nullWrongAngle()) {
            wrongAngle = new WrongAngle();
        }
        if (motor5.getText() == null && nullWrongAngle()) {
            wrongAngle = new WrongAngle();
        }
        if (motor6.getText() == null && nullWrongAngle()) {
            wrongAngle = new WrongAngle();
        }
    }
    private boolean nullWrongAngle(){
        if(wrongAngle == null){
            return true;
        }else {
            return false;
        }
    }
    private void sendStep(Step step) {
        try {
            communicator.sendData(step);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
