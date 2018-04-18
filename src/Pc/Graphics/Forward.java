package Pc.Graphics;

import Pc.Graphics.SystemsMessages.OutOfWorkSpace;
import Pc.Graphics.SystemsMessages.WrongAngle;
import Pc.Logic.Java.Services.Calculations.ForwardKinematics;
import Pc.Logic.Java.Services.Communication.Communicator;
import Pc.Logic.Java.Objects.Servo;
import Pc.Logic.Java.Objects.Step;
import Pc.Logic.Java.Services.AnlesTransformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
    private int[]angles;
    private boolean err = false;

    public Forward(Handeling handeling, Communicator pythonInterface){
        this.communicator = pythonInterface;
        handeling.setVisible(false);
        this.setVisible(true);
        this.setSize(new Dimension(500, 150));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.forward = this;
        this.angles = new int[6];
        forward.add(panel);
        back.addActionListener(e -> {
            this.setVisible(false);
            this.setFocusable(false);
            handeling.setVisible(true);
        });
        count.addActionListener((ActionEvent e) -> {
            nullValuesControl();
            try {
                angles[0] = Integer.parseInt(motor1.getText());
                angles[1] = Integer.parseInt(motor2.getText());
                angles[2] = Integer.parseInt(motor3.getText());
                angles[3] = Integer.parseInt(motor4.getText());
                angles[4] = Integer.parseInt(motor5.getText());
                angles[5] = Integer.parseInt(motor6.getText());
            }catch (NumberFormatException exc){
                if(wrongAngle == null){
                    wrongAngle = new WrongAngle();
                    wrongAngle.setVisible(true);
                }else {
                    wrongAngle.setVisible(true);
                }
                err = true;
            }
            chceckAngles();
            double[] coordinates = ForwardKinematics.calculateFK(transformAngles());
            boolean outWS = !workSpaceControl(coordinates);
            if(outWS){
                err = true;
            }
            if(!err) {
                this.x.setText(String.valueOf(coordinates[0]));
                this.y.setText(String.valueOf(coordinates[1]));
                this.z.setText(String.valueOf(coordinates[1]));
            }else {
                this.x.setText("error");
                this.y.setText("error");
                this.z.setText("error");
            }
        });
        save.addActionListener(e -> {
            nullValuesControl();
            Step step = new Step();
            createStep(step);
            sendStep(step);
        });
    }

    private void chceckAngles() {
        for (int i = 0; i < 5; i++) {
            if (0>angles[i]||angles[i]>180){
                if(wrongAngle == null){
                    wrongAngle = new WrongAngle();
                }else {
                    wrongAngle.setVisible(true);
                }
                err = true;
                return;
            }
        }
    }

    private boolean workSpaceControl(double[] coordinates){
        boolean xOutOfWorkSpace = -10.5<coordinates[0] && coordinates[0]<10.5;
        boolean yOutOfWorkSpace = -10.5<coordinates[1] && coordinates[1]<10.5;
        boolean zOutOfWorkSpace = -6<coordinates[2] && coordinates[2]<0;
        if((xOutOfWorkSpace && yOutOfWorkSpace && zOutOfWorkSpace) || coordinates[2] <= -6){
            this.save.disable();
            new OutOfWorkSpace();
            return false;
        }
        this.save.enable();
        return true;
    }
    private void createStep(Step step){
        step.addMove(new Servo(0, angles[0]));
        step.addMove(new Servo(1, angles[1]));
        step.addMove(new Servo(2, angles[2]));
        step.addMove(new Servo(3, angles[3]));
        step.addMove(new Servo(4, angles[4]));
        step.addMove(new Servo(5, angles[5]));
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
            communicator.sendData(step);
    }
    private int[] transformAngles(){
        int[] anglesTransform = new int[6];
        anglesTransform[0] = AnlesTransformation.gles(180, angles[0]);
        anglesTransform[1] = AnlesTransformation.gles(180, angles[1]);
        anglesTransform[2] = AnlesTransformation.gles(180, angles[2]);
        anglesTransform[3] = AnlesTransformation.gles(180, angles[3]);
        anglesTransform[4] = AnlesTransformation.gles(0, angles[4]);
        anglesTransform[5] = AnlesTransformation.gles(0, angles[5]);
        return anglesTransform;
    }
}
