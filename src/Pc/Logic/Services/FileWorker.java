package Pc.Logic.Services;

import Pc.Logic.Objects.*;

import java.io.*;
import java.util.ArrayList;

public class FileWorker {

    /**
     * Method which can create a new file for you choreography.
     * @param choreography Your choreography to save.
     * @param path Path with name of the choreography.
     */
    public void saveChoreography(Choreography choreography, String path){
        try {
            FileOutputStream fileOut = new FileOutputStream(path);

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(choreography);
            out.close();
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Possibilities loadMoznosti(){
        Possibilities possibilities = null;
        try {
            FileInputStream fileIn = new FileInputStream("possibilities.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            try {
                possibilities = (Possibilities) in.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return possibilities;
    }
    public void saveMoznosti(Possibilities possibilities){
        try {
            FileOutputStream fileOut = new FileOutputStream("possibilities.bin");

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(possibilities);
            out.close();
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which serve as a loader of your choreography.
     * @param name Name of your choreography.
     * @return choreography Loaded choreography.
     */
    public Choreography loadChoreography(String name){

        Choreography choreography = null;

        try {
            FileInputStream fileIn = new FileInputStream("Choreography/"+name+".bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            try {
                choreography = (Choreography) in.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println("oungvriuabegiuvb");
                e.printStackTrace();
            }
            in.close();
            fileIn.close();

        } catch (FileNotFoundException e) {
            // Program can not find your choreography
            System.out.println("Make new choreography.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return choreography;
    }


    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker();
        Step step1 = new Step();
        step1.addMove(new Servo(0,180));
        step1.addMove(new Servo(1,0));
        step1.addMove(new Servo(2,18));
        step1.addMove(new Servo(3,15));
        step1.addMove(new Servo(4,90));
        step1.addMove(new Servo(5,160));

        Step step2 = new Step();
        step2.addMove(new Servo(0,0));
        step2.addMove(new Servo(1,15));
        step2.addMove(new Servo(2,180));
        step2.addMove(new Servo(3,30));
        step2.addMove(new Servo(4,90));
        step2.addMove(new Servo(5,170));

        Step step3 = new Step();
        step3.addMove(new Servo(0,42));
        step3.addMove(new Servo(1,42));
        step3.addMove(new Servo(2,42));
        step3.addMove(new Servo(3,42));
        step3.addMove(new Servo(4,42));
        step3.addMove(new Servo(5,43));

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        Choreography choreography1 = new Choreography(steps);

        fileWorker.saveChoreography(choreography1, "Choreography/choreographyTest.bin");
    }
}
