package Pc.Graphics.SystemsMessages;

import javax.swing.*;

public class NewOrOpenChoreography extends JDialog {
    private JPanel contentPanel;
    private JButton newButton;
    private JButton openButton;

    public NewOrOpenChoreography() {
        setContentPane(contentPanel);
        setModal(false);
        getRootPane().setDefaultButton(newButton);
        this.setVisible(true);

    }
}
