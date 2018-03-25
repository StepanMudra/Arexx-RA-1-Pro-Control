package Pc.Graphics;

import Pc.Graphics.SystemsMessages.Connecting;
import Pc.Graphics.SystemsMessages.NewOrOpenChoreography;
import Pc.Logic.Java.Communication.PythonInterface;
import Pc.Logic.Java.Objects.Choreoghraphy;
import Pc.Logic.Java.Objects.Step;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by stepanmudra on 10.07.17.
 */
public class Handeling extends JFrame{

    public JPanel mainPanelm;
    private JPanel panelSNastroji;
    private JPanel ovladaciPanel;

    private JButton forward;
    private JButton inverse;

    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JSlider slider4;
    private JSlider slider5;
    private JSlider slider6;

    private JLabel hodnota1;
    private JLabel hodnota2;
    private JLabel hodnota3;
    private JLabel hodnota4;
    private JLabel hodnota5;
    private JLabel hodnota6;
    private JRadioButton realtime;
    private JRadioButton krokove;

    private JButton choreoghraphyButton;
    private JButton step;
    private JComboBox arduinoPorts;
    private JButton bacStep;
    private JButton nexStep;
    //ZapisDoSouboru zapisDoSouboru;

    ArrayList<Object> list;

    private int stara1;
    private int stara2;
    private int stara3;
    private int stara4;
    private int stara5;
    private int stara6;

    private int nova1;
    private int nova2;
    private int nova3;
    private int nova4;
    private int nova5;
    private int nova6;


    private PythonInterface pythonInterface;

    private Forward forwardFrame;
    private int indexOfActualStep;

    private Choreoghraphy choreoghraphy;
    private Step actualStep;

    public Handeling(){
        indexOfActualStep = 0;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(700, 500));
        this.add(mainPanelm);
        this.setTitle("Arexx RA1-PRO Controller");

        hodnota1.setText("0");
        hodnota2.setText("0");
        hodnota3.setText("0");
        hodnota4.setText("0");
        hodnota5.setText("0");
        hodnota6.setText("0");

        //callPython = new CallPython();

        slider1.setMinimum(0);
        slider2.setMinimum(0);
        slider3.setMinimum(0);
        slider4.setMinimum(0);
        slider5.setMinimum(0);
        slider6.setMinimum(0);

        slider1.setMaximum(180);
        slider2.setMaximum(180);
        slider3.setMaximum(180);
        slider4.setMaximum(180);
        slider5.setMaximum(180);
        slider6.setMaximum(180);

        slider1.setValue(0);
        slider2.setValue(0);
        slider3.setValue(0);
        slider4.setValue(0);
        slider5.setValue(0);
        slider6.setValue(0);

        stara1 = 0;
        stara2 = 0;
        stara3 = 0;
        stara4 = 0;
        stara5 = 0;
        stara6 = 0;

        list = new ArrayList<>();

        list.add(slider1);
        list.add(slider2);
        list.add(slider3);
        list.add(slider4);
        list.add(slider5);
        list.add(slider6);
        this.pythonInterface = new PythonInterface();
        forward.addActionListener(e -> {
            if (forwardFrame == null) {
                forwardFrame = new Forward(this);
            } else {
                forwardFrame.setVisible(true);
                this.setVisible(false);
            }
        });
        choreoghraphyButton.addActionListener(e -> {new NewOrOpenChoreography();});
        inverse.addActionListener(e -> {});

        realtime.setSelected(true);

        this.setVisible(true);
        ArrayList<String> portsPath = PythonInterface.findPorts();
        for (int i = 0; i < portsPath.size(); i++) {
            arduinoPorts.addItem(portsPath.get(i));
        }
        arduinoPorts.addActionListener(e -> {
            System.out.println("nlihkliugzkugugukz");
            new Connecting();
            this.setVisible(false);
            System.out.println();
            this.setVisible(true);
            try {
                pythonInterface.openPort(arduinoPorts.getSelectedItem().toString());
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        });
        this.pythonInterface = new PythonInterface();
        slider1.addChangeListener(e -> {
            hodnota1.setText(String.valueOf(slider1.getValue()));
            String[] dat = {"1000", hodnota1.getText()};
            try {
                pythonInterface.sendData(dat);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        slider2.addChangeListener(e -> {
            hodnota2.setText(String.valueOf(slider2.getValue()));
            String[] dat = {"2000", hodnota2.getText()};
            try {
                pythonInterface.sendData(dat);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        slider3.addChangeListener(e -> {
            hodnota3.setText(String.valueOf(slider3.getValue()));
            String[] dat = {"3000", hodnota3.getText()};
            try {
                pythonInterface.sendData(dat);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        slider4.addChangeListener(e -> {
            hodnota4.setText(String.valueOf(slider4.getValue()));
            String[] dat = {"4000", hodnota4.getText()};
            try {
                pythonInterface.sendData(dat);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        slider5.addChangeListener(e -> {
            hodnota5.setText(String.valueOf(slider5.getValue()));
            String[] dat = {"5000", hodnota5.getText()};
            try {
                pythonInterface.sendData(dat);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        slider6.addChangeListener(e -> {
            hodnota6.setText(String.valueOf(slider6.getValue()));
            String[] dat = {"6000", hodnota6.getText()};
            try {
                pythonInterface.sendData(dat);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        realtime.addItemListener(e -> {
            if(e.getItem().equals(realtime)&&realtime.isSelected()){
                krokove.setSelected(false);
            }
        });
        //
        krokove.addItemListener(e -> {
            if(e.getItem().equals(krokove)&&krokove.isSelected()){
                realtime.setSelected(false);
            }
        });

        bacStep.addActionListener(e -> {
            if (choreoghraphy != null && indexOfActualStep !=0){
                indexOfActualStep -= 1;
                actualStep = choreoghraphy.getChoreography().get(indexOfActualStep);
            }
        });

        step.addActionListener(e -> {
            try {
                pythonInterface.sendData(actualStep);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        nexStep.addActionListener(e -> {
            if (choreoghraphy != null && indexOfActualStep != choreoghraphy.getChoreography().size()){
                indexOfActualStep += 1;
                actualStep = choreoghraphy.getChoreography().get(indexOfActualStep);
            }
        });
    }

    private int[] ulozeniStavuPosuvniku(){
        int[]stavyPosuvniku = new int[6];
        stavyPosuvniku[0] = slider1.getValue();
        stavyPosuvniku[1] = slider1.getValue();
        stavyPosuvniku[2] = slider1.getValue();
        stavyPosuvniku[3] = slider1.getValue();
        stavyPosuvniku[4] = slider1.getValue();
        stavyPosuvniku[5] = slider1.getValue();
        return stavyPosuvniku;
    }

/**
    public ZapisDoSouboru getZapisDoSouboru() {
        if(zapisDoSouboru == null){
            return new ZapisDoSouboru();
        }else {
            return zapisDoSouboru;
        }
    }
 **/
}
