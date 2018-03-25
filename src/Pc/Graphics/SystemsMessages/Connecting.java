package Pc.Graphics.SystemsMessages;

import javax.swing.*;

public class Connecting extends JFrame {
    private JPanel contentPane;
    private JProgressBar progressBar1;
    private JButton buttonOK;

    public Connecting() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setVisible(true);
    }
}
