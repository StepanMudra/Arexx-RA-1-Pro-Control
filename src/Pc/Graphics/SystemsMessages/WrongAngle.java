package Pc.Graphics.SystemsMessages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WrongAngle extends JDialog {
    private JPanel contentPanel;
    private JButton buttonOK;
    private JButton buttonCancel;

    public WrongAngle() {
        setContentPane(contentPanel);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setSize(new Dimension(300, 300));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
        WrongAngle dialog = new WrongAngle();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
