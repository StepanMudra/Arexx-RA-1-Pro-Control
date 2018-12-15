package Pc.Graphics;

import Pc.Graphics.SystemsMessages.ChoreographyNaming;
import Pc.Graphics.SystemsMessages.Connecting;
import Pc.Logic.Services.Communication.Communicator;
import Pc.Logic.Services.Controller;
import Pc.Logic.Services.FileWorker;
import Pc.Logic.Objects.Choreography;
import Pc.Logic.Objects.Servo;
import Pc.Logic.Objects.Step;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by stepanmudra on 10.07.17.
 */
public class Handeling extends JFrame {

    public JPanel mainPanel;
    private JPanel panelSNastroji;
    private JPanel ovladaciPanel;

    private JButton forward;
    private JButton inverseButton;

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

    private JButton newChoreography;
    private JButton step;
    private JComboBox arduinoPorts;
    private JButton bacStep;
    private JButton nexStep;
    private JButton openChoreography;
    private JButton saveStep;
    private JButton addStepToNewButton;
    private JButton choreographySenda;
    private JTextField pauseBetweenStepsMsTextField;

    private boolean portOpened;
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

    private Timer timer;

    private int[] oldValues;
    private int[] newValues;


    private Communicator communicator;

    private Forward forwardFrame;
    private Inverse inverseFrame;
    private int indexOfActualStep;

    private Choreography choreography;
    private Choreography choreographyTemp;
    private ArrayList<Step> stepsTemp;
    private Step actualStep;

    private FileWorker fileWorker;
    private ChoreographyNaming choreographyNaming;
    private Connecting connecting;
    private Controller controller;


    public Handeling(FileWorker fileWorker) {
        this.controller = new Controller();
        this.fileWorker = fileWorker;
        indexOfActualStep = 0;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(700, 400));
        this.add(mainPanel);
        this.setTitle("Arexx RA1-PRO Controller");
        this.setAutoRequestFocus(false);
        this.setFocusable(true);
        this.setResizable(false);
        this.stepsTemp = new ArrayList<>();

        stara1 = 0;
        stara2 = 0;
        stara3 = 0;
        stara4 = 0;
        stara5 = 0;
        stara6 = 0;

        list = new ArrayList<>();

        this.oldValues = new int[6];
        this.newValues = new int[6];

        this.timer = new Timer(30, new ListenerTimer(this));

        list.add(slider1);
        list.add(slider2);
        list.add(slider3);
        list.add(slider4);
        list.add(slider5);
        list.add(slider6);

        this.communicator = new Communicator();
        findPorts();
        comboBoxListeners();

        setMinimumSliders();
        setMaximumSliders();
        setSliderValues();
        sliderListeners();

        radioButtonListeners();

        buttonListeners();

        realtime.setSelected(true);

        this.setVisible(true);
        this.portOpened = false;
        setNewValues();
    }

    private int[] ulozeniStavuPosuvniku() {
        int[] stavyPosuvniku = new int[6];
        stavyPosuvniku[0] = slider1.getValue();
        stavyPosuvniku[1] = slider1.getValue();
        stavyPosuvniku[2] = slider1.getValue();
        stavyPosuvniku[3] = slider1.getValue();
        stavyPosuvniku[4] = slider1.getValue();
        stavyPosuvniku[5] = slider1.getValue();
        return stavyPosuvniku;
    }


    /**
     * public ZapisDoSouboru getZapisDoSouboru() {
     * if(zapisDoSouboru == null){
     * return new ZapisDoSouboru();
     * }else {
     * return zapisDoSouboru;
     * }
     * }
     **/


    private void setValues(int[] values) {
        for (int i = 0; i < values.length; i++) {
            values[i] = 0;
        }
    }

    private void setMinimumSliders() {
        slider1.setMinimum(0);
        slider2.setMinimum(0);
        slider3.setMinimum(0);
        slider4.setMinimum(0);
        slider5.setMinimum(0);
        slider6.setMinimum(0);
    }

    private void setMaximumSliders() {
        slider1.setMaximum(180);
        slider2.setMaximum(180);
        slider3.setMaximum(180);
        slider4.setMaximum(180);
        slider5.setMaximum(180);
        slider6.setMaximum(180);
    }
    private boolean checkSBS() {
        //System.out.println(krokove.isSelected());
        return krokove.isSelected();
    }
    private boolean isPortOpened() {
        return portOpened;
    }

    private void timerChecker(){
        if(!timer.isRunning()){
            timer.start();
        }
    }
    private void createStep(){
        setNewValues();
        actualStep.addMove(new Servo(0, newValues[0]));
        actualStep.addMove(new Servo(1, newValues[1]));
        actualStep.addMove(new Servo(2, newValues[2]));
        actualStep.addMove(new Servo(3, newValues[3]));
        actualStep.addMove(new Servo(4, newValues[4]));
        actualStep.addMove(new Servo(5, newValues[5]));
    }
    private void sliderListeners(){
        slider1.addChangeListener(e -> {
            int value = slider1.getValue();
            hodnota1.setText(String.valueOf(value));
            newValues[0] = value;
            if (!checkSBS()) {
                timerChecker();
            }
        });
        slider2.addChangeListener(e -> {
            hodnota2.setText(String.valueOf(slider2.getValue()));
            int value = slider2.getValue();
            hodnota2.setText(String.valueOf(value));
            newValues[1] = value;
            if (!checkSBS()) {
                timerChecker();
            }
        });
        slider3.addChangeListener(e -> {
            int value = slider3.getValue();
            hodnota3.setText(String.valueOf(value));
            newValues[2] = value;
            if (!checkSBS()) {
                timerChecker();
            }
        });
        slider4.addChangeListener(e -> {
            int value = slider4.getValue();
            hodnota4.setText(String.valueOf(value));
            newValues[3] = value;
            if (!checkSBS()) {
                timerChecker();
            }
        });
        slider5.addChangeListener(e -> {
            int value = slider5.getValue();
            hodnota5.setText(String.valueOf(value));
            newValues[4] = value;
            if (!checkSBS()) {
                timerChecker();
            }
        });
        slider6.addChangeListener(e -> {
            int value = slider6.getValue();
            hodnota6.setText(String.valueOf(value));
            newValues[5] = value;
            if (!checkSBS()) {
                timerChecker();
            }
        });
    }
    private void setSliderValues(){
        System.out.println(indexOfActualStep);
        System.out.println(choreography != null ? choreography.getSteps().get(indexOfActualStep).getMove(0).getId()+" "+choreography.getSteps().get(indexOfActualStep).getMove(0).getAngle() : "bla");
        slider1.setValue(choreography == null ? 90 : choreography.getSteps().get(indexOfActualStep).getMove(0).getAngle());
        slider2.setValue(choreography == null ? 90 : choreography.getSteps().get(indexOfActualStep).getMove(1).getAngle());
        slider3.setValue(choreography == null ? 90 : choreography.getSteps().get(indexOfActualStep).getMove(2).getAngle());
        slider4.setValue(choreography == null ? 90 : choreography.getSteps().get(indexOfActualStep).getMove(3).getAngle());
        slider5.setValue(choreography == null ? 90 : choreography.getSteps().get(indexOfActualStep).getMove(4).getAngle());
        slider6.setValue(choreography == null ? 90 : choreography.getSteps().get(indexOfActualStep).getMove(5).getAngle());

        hodnota1.setText(choreography == null ? "90" : String.valueOf(choreography.getSteps().get(indexOfActualStep).getMove(0).getAngle()));
        hodnota2.setText(choreography == null ? "90" : String.valueOf(choreography.getSteps().get(indexOfActualStep).getMove(1).getAngle()));
        hodnota3.setText(choreography == null ? "90" : String.valueOf(choreography.getSteps().get(indexOfActualStep).getMove(2).getAngle()));
        hodnota4.setText(choreography == null ? "90" : String.valueOf(choreography.getSteps().get(indexOfActualStep).getMove(3).getAngle()));
        hodnota5.setText(choreography == null ? "90" : String.valueOf(choreography.getSteps().get(indexOfActualStep).getMove(4).getAngle()));
        hodnota6.setText(choreography == null ? "90" : String.valueOf(choreography.getSteps().get(indexOfActualStep).getMove(5).getAngle()));

    }
    private void buttonListeners(){
        bacStep.addActionListener(e -> {
            if (choreography != null && indexOfActualStep != 0) {
                indexOfActualStep -= 1;
                actualStep = choreography.getSteps().get(indexOfActualStep);
                setSliderValues();
            }
        });
        step.addActionListener(e -> {
            if (choreography == null){
                actualStep = new Step();
                //setNewValues();
                createStep();
            }
            communicator.sendData(actualStep);
        });
        nexStep.addActionListener(e -> {
            if (choreography != null && indexOfActualStep != choreography.getSteps().size() - 1) {
                indexOfActualStep += 1;
                actualStep = choreography.getSteps().get(indexOfActualStep);
                setSliderValues();
            }
        });
        newChoreography.addActionListener(e -> {
            if(choreographyNaming==null){
                this.choreographyNaming = new ChoreographyNaming(choreographyTemp,fileWorker);
            }else {
                choreographyNaming.setVisible(true);
            }
        });
        openChoreography.addActionListener(e -> {
            if(choreographyNaming==null){
                this.choreographyNaming = new ChoreographyNaming(fileWorker, this);
            }
            else {
                choreographyNaming.setVisible(true);
            }
        });
        forward.addActionListener(e -> {
            if (forwardFrame == null) {
                forwardFrame = new Forward(this, communicator, controller);
            } else {
                forwardFrame.setVisible(true);
                this.setVisible(false);
            }
        });
        saveStep.addActionListener(e -> {
            if(choreography != null && actualStep == null){
                actualStep = new Step();
            }
            createStep();
            choreography.addStep(actualStep, indexOfActualStep);
        });
        addStepToNewButton.addActionListener(e -> {
            actualStep = new Step();
            createStep();
            stepsTemp.add(actualStep);
            if(choreographyTemp == null){
                choreographyTemp = new Choreography(stepsTemp);
            }else{
                choreographyTemp.setSteps(stepsTemp);
            }
        });
        choreographySenda.addActionListener(e -> {
            int variable = Integer.parseInt(pauseBetweenStepsMsTextField.getText());
            if(choreography != null) {
                communicator.sendData(choreography, variable);
            }
        });
        inverseButton.addActionListener(e -> {
            if(inverseFrame == null){
                inverseFrame = new Inverse(this, communicator, controller, fileWorker);
            } else {
                inverseFrame.setVisible(true);
                this.setVisible(false);
            }
        });
    }
    private void findPorts(){
        ArrayList<String> portsPath = Communicator.findPorts();
        for (int i = 0; i < portsPath.size(); i++) {
            arduinoPorts.addItem(portsPath.get(i));
        }
    }
    private void comboBoxListeners(){
        arduinoPorts.addActionListener(e -> {
            //new Connecting();
            //this.setVisible(false);
            System.out.println();
            //this.setVisible(true);
            try {
                if(connecting == null){
                    //connecting = new Connecting();
                }
                //connecting.setVisible(true);
                portOpened = communicator.openPort(arduinoPorts.getSelectedItem().toString());
                //connecting.setVisible(false);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        });
    }
    private void radioButtonListeners(){
        realtime.addItemListener(e -> {
            if (e.getItem().equals(realtime) && realtime.isSelected()) {
                krokove.setSelected(false);
            }
        });
        //
        krokove.addItemListener(e -> {
            if (e.getItem().equals(krokove) && krokove.isSelected()) {
                realtime.setSelected(false);
            }
        });
    }

    /**
     * Inner anonymous class
     */
    private class ListenerTimer implements ActionListener {

        private Handeling handeling;

        ListenerTimer(Handeling handeling) {
            this.handeling = handeling;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean rewrite = false;
            for (int i = 0; i < newValues.length; i++) {
                if (oldValues[i] != newValues[i]) {
                    oldValues[i] = newValues[i];
                    int[] data = {i, newValues[i]};
                    rewrite = true;
                    if(handeling.isPortOpened()) {
                        communicator.sendData(data);
                    }else {
                        timer.stop();
                    }
                }
            }
            if (!rewrite) {
                timer.stop();
            }
        }
    }
    public void setChoreography(Choreography choreography) {
        this.choreography = choreography;
        System.out.println(choreography == null);
        setSliderValues();
    }
    public void setNewValues(){
        newValues[0] = slider1.getValue();
        newValues[1] = slider2.getValue();
        newValues[2] = slider3.getValue();
        newValues[3] = slider4.getValue();
        newValues[4] = slider5.getValue();
        newValues[5] = slider6.getValue();
    }
}
