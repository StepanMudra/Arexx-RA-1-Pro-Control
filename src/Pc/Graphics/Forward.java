package Pc.Graphics;

import Pc.Graphics.SystemsMessages.OutOfWorkSpace;
import Pc.Graphics.SystemsMessages.WrongAngle;
import Pc.Logic.Java.Calculations.ForwardKinematics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by stepanmudra on 11.07.17.
 */
public class Forward extends JFrame{

    private JButton back;
    private JPanel panelm;
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

    public Forward(Handeling handeling){
        handeling.setVisible(false);
        this.setVisible(true);
        this.setSize(new Dimension(500, 150));
        this.forward = this;
        forward.add(panelm);
        back.addActionListener(e -> {
            this.setVisible(false);
            this.setFocusable(false);
            handeling.setVisible(true);
        });
        count.addActionListener((ActionEvent e) -> {
            int angle1 = Integer.parseInt(motor1.getText());
            int angle2 = Integer.parseInt(motor2.getText());
            int angle3 = Integer.parseInt(motor3.getText());
            int angle4 = Integer.parseInt(motor4.getText());
            int angle5 = Integer.parseInt(motor5.getText());
            int[]angles = new int[5];
            angles[0] = angle1;
            angles[1] = angle2;
            angles[2] = angle3;
            angles[3] = angle4;
            angles[4] = angle5;
            for (int i = 0; i < 5; i++) {
                if (0>angles[i]||angles[i]>180){
                    new WrongAngle();
                    return;
                }
            }
            double[] coordinates = ForwardKinematics.calculateFK(angles);
            workSpaceControl(coordinates);
            x.setText(String.valueOf(coordinates[0]));
            y.setText(String.valueOf(coordinates[1]));
            z.setText(String.valueOf(coordinates[1]));
        });
        save.addActionListener(e -> {});
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
}
