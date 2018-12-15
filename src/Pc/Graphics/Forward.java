package Pc.Graphics;

import Pc.Graphics.SystemsMessages.OutOfWorkSpace;
import Pc.Graphics.SystemsMessages.WrongAngle;
import Pc.Logic.Services.Calculations.ForwardKinematics;
import Pc.Logic.Services.Communication.Communicator;
import Pc.Logic.Objects.Servo;
import Pc.Logic.Objects.Step;
import Pc.Logic.Services.AnlesTransformation;
import Pc.Logic.Services.Controller;

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
    private int[] angles;
    private boolean err = false;
    private Controller controller;

    public Forward(Handeling handeling, Communicator communicator, Controller controller){
        this.communicator = communicator;
        handeling.setVisible(false);
        this.setVisible(true);
        this.setSize(new Dimension(500, 150));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.forward = this;
        this.angles = new int[6];
        this.controller = controller;
        this.add(panel);
        back.addActionListener(e -> {
            this.setVisible(false);
            this.setFocusable(false);
            handeling.setVisible(true);
        });
        count.addActionListener((ActionEvent e) -> {
            if(controller.nullValuesControl(motor1.getText(), motor2.getText(), motor3.getText(), motor4.getText(), motor5.getText(), motor6.getText())){
                if(wrongAngle == null){
                    wrongAngle = new WrongAngle();
                } else {
                    wrongAngle.setVisible(true);
                }
            }
            try {
                //TODO: přepočítat úhly
                angles[0] = Integer.parseInt(motor1.getText());
                angles[1] = Integer.parseInt(motor2.getText());
                angles[2] = Integer.parseInt(motor3.getText());
                angles[3] = Integer.parseInt(motor4.getText());
            }catch (NumberFormatException exc){
                if(wrongAngle == null){
                    wrongAngle = new WrongAngle();
                    wrongAngle.setVisible(true);
                }else {
                    wrongAngle.setVisible(true);
                }
                err = true;
            }
             err = controller.checkAngles(angles);
            double[] coordinates = ForwardKinematics.calculateFK(transformAngles());
            System.out.println(coordinates[0]);
            System.out.println(coordinates[1]);
            System.out.println(coordinates[2]);
            boolean outWS = !controller.workSpaceControl(coordinates, this);
            if(outWS){
                err = true;
            }
            if(!err) {
                this.x.setText(String.valueOf(coordinates[0]));
                this.y.setText(String.valueOf(coordinates[1]));
                this.z.setText(String.valueOf(coordinates[2]));
            }else {
                this.x.setText("error");
                this.y.setText("error");
                this.z.setText("error");
            }
        });
        save.addActionListener(e -> {
            if(controller.nullValuesControl(motor1.getText(), motor2.getText(), motor3.getText(), motor4.getText(), motor5.getText(), motor6.getText())){
                if(wrongAngle == null){
                    wrongAngle = new WrongAngle();
                } else {
                    wrongAngle.setVisible(true);
                }
            }
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

    private boolean workSpaceControl(double[] coordinates, Forward forward){
        boolean xOutOfWorkSpace = -10.5<coordinates[0] && coordinates[0]<10.5;
        boolean yOutOfWorkSpace = -10.5<coordinates[1] && coordinates[1]<10.5;
        boolean zOutOfWorkSpace = -6<coordinates[2] && coordinates[2]<0;
        if((xOutOfWorkSpace && yOutOfWorkSpace && zOutOfWorkSpace) || coordinates[2] <= -6){
            forward.save.disable();
            new OutOfWorkSpace();
            return false;
        }
        forward.save.enable();
        return true;
    }
    private void createStep(Step step){
        step.addMove(new Servo(0, Integer.parseInt(motor1.getText())));
        step.addMove(new Servo(1, Integer.parseInt(motor2.getText())));
        step.addMove(new Servo(2, Integer.parseInt(motor3.getText())));
        step.addMove(new Servo(3, Integer.parseInt(motor4.getText())));
        step.addMove(new Servo(4, Integer.parseInt(motor5.getText())));
        step.addMove(new Servo(5, Integer.parseInt(motor6.getText())));
    }

    private void sendStep(Step step) {
            communicator.sendData(step);
    }
    private int[] transformAngles(){
        int[] anglesTransform = new int[6];
        anglesTransform[0] = AnlesTransformation.gles(0, angles[0]);
        anglesTransform[1] = 180 + angles[1];
        System.out.println(anglesTransform[0]+" "+anglesTransform[1]);
        anglesTransform[2] = 90 - angles[2];
        anglesTransform[3] = 270 + angles[3];
        anglesTransform[4] = AnlesTransformation.gles(0, angles[4]);
        anglesTransform[5] = AnlesTransformation.gles(0, angles[5]);
        return anglesTransform;
    }
    public JButton getSave() {
        return save;
    }
}
