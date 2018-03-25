package Pc.Logic.Java;

import Pc.Logic.Java.Objects.Choreoghraphy;

import java.io.*;

public class FileWorker {

    /**
     *
     * @param choreoghraphy
     * @param name
     */
    public void saveChoreography(Choreoghraphy choreoghraphy, String name){
        try {
            FileOutputStream fileOut = new FileOutputStream("choreography/"+name+".bin");

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(choreoghraphy);
            out.close();
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public Choreoghraphy loadChoreography(String name){

        Choreoghraphy choreoghraphy = null;

        try {
            FileInputStream fileIn = new FileInputStream("choreography/"+name+".bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            choreoghraphy = (Choreoghraphy) in.readObject();
            in.close();
            fileIn.close();

        } catch (FileNotFoundException e) {
            // Program can not find your choreography
            System.out.println("Make new choreography.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return choreoghraphy;
    }
}
