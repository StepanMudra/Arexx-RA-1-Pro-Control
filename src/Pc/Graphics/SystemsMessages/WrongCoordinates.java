package Pc.Graphics.SystemsMessages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WrongCoordinates extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;

    public WrongCoordinates() {
        setContentPane(contentPane);
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
        WrongCoordinates dialog = new WrongCoordinates();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
