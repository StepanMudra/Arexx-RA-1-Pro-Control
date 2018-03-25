package Pc.Graphics.SystemsMessages;

import javax.swing.*;

public class OutOfWorkSpace extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;

    public OutOfWorkSpace() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }
}
