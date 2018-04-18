package Pc.Graphics.SystemsMessages;

import javax.swing.*;
import java.awt.*;

public class Connecting extends JDialog {
    private JPanel pane;
    private JLabel con;

    public Connecting() {
        this.setSize(new Dimension(300, 300));
        setModal(true);
        setVisible(true);
    }
}
