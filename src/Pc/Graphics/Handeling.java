package Pc.Graphics;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Created by stepanmudra on 10.07.17.
 */
public class Handeling extends JFrame{

    private JPanel hlavniPanel;
    private JPanel panelSNastroji;
    private JPanel ovladaciPanel;

    private JButton prima;
    private JButton zpetna;

    private JProgressBar progressBar1;


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
    //TODO: Nastavit lambda výrazy pro buttony
    private JButton button1;
    private JButton choreography;
    private JButton button2;
    private JComboBox comboBox1;
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


    public Handeling(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(700, 500));
        this.add(hlavniPanel);
        this.setTitle("Bakalářka");

//        hodnota1.setText("0");
        hodnota2.setText("0");
        hodnota3.setText("0");
        hodnota4.setText("0");
        hodnota5.setText("0");
        hodnota6.setText("0");

        slider1.setMinimum(-100);
        slider2.setMinimum(-100);
        slider3.setMinimum(-100);
        slider4.setMinimum(-100);
        slider5.setMinimum(-100);
        slider6.setMinimum(-100);

        slider1.setMaximum(100);
        slider2.setMaximum(100);
        slider3.setMaximum(100);
        slider4.setMaximum(100);
        slider5.setMaximum(100);
        slider6.setMaximum(100);

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

        vytvorListenery();
        //this.setResizable(false);

        //progressBar1.setMinimum(0);
        //progressBar1.setMaximum(100);

        list = new ArrayList<>();

        list.add(slider1);
        list.add(slider2);
        list.add(slider3);
        list.add(slider4);
        list.add(slider5);
        list.add(slider6);

        //odesilani = new Odesilani();
        //prijimani = new Prijimani();

        //ovladani = this;
        choreography.addActionListener(e -> {

        });

        realtime.setSelected(true);

        this.setVisible(true);
    }

    private void vytvorListenery(){
        ChangeListener changeListener = e -> {
            for (int i = 0; i < list.size(); i++) {
                if(e.getSource().equals(list.get(i))){
                    switch (i){
                        case 0:
                            hodnota1.setText(String.valueOf(slider1.getValue()));
                            break;
                        case 1:
                            hodnota2.setText(String.valueOf(slider2.getValue()));
                            break;
                        case 2:
                            hodnota3.setText(String.valueOf(slider3.getValue()));
                            break;
                        case 3:
                            hodnota4.setText(String.valueOf(slider4.getValue()));
                            break;
                        case 4:
                            hodnota5.setText(String.valueOf(slider5.getValue()));
                            progressBar1.setValue(slider5.getValue());
                            progressBar1.setString(String.valueOf(slider5.getValue())+"%");
                            break;
                        case 5:
                            hodnota6.setText(String.valueOf(slider6.getValue()));
                            break;
                    }
                }
            }
        };
        //
        slider1.addChangeListener(e -> hodnota1.setText(String.valueOf(slider1.getValue())));
        slider2.addChangeListener(e -> hodnota2.setText(String.valueOf(slider2.getValue())));
        slider3.addChangeListener(e -> hodnota3.setText(String.valueOf(slider3.getValue())));
        slider4.addChangeListener(e -> hodnota4.setText(String.valueOf(slider4.getValue())));
        slider5.addChangeListener(e -> hodnota5.setText(String.valueOf(slider5.getValue())));
        slider6.addChangeListener(e -> hodnota6.setText(String.valueOf(slider6.getValue())));

        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getItem().equals(realtime)&&realtime.isSelected()){
                    krokove.setSelected(false);
                }else if(e.getItem().equals(krokove)&&krokove.isSelected()){
                    realtime.setSelected(false);
                }
            }
        };
        //
        //TODO:Vyřešit stavbu lambda výrazu
        //realtime.addItemListener(e -> e.getItem().equals(realtime)&&krokove.isSelected()?krokove.setSelected(false):realtime.setSelected(false));
        //
        //krokove.addItemListener(e -> e.getItem().equals(krokove)&&krokove.isSelected()?realtime.setSelected(false):);


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
    private boolean realtimeVybrano(){
        return realtime.isSelected();
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
