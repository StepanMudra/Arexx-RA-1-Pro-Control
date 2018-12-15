package Pc.Logic.Services.Calculations;

import Pc.Logic.Objects.Possibilities;
import Pc.Logic.Objects.Possibility;
import Pc.Logic.Services.Controller;
import Pc.Logic.Services.Creator;
import Pc.Logic.Services.FileWorker;

public class InverseKinematics {

    private Controller controller;
    private Possibility required;
    private double diference;
    private FileWorker fileWorker;
    private Creator creator;

    public InverseKinematics(FileWorker fileWorker, Controller controller) {
        this.fileWorker = fileWorker;
        this.controller = controller;
        this.creator = new Creator();
    }

    /**
     * Method finding nearest possibility.
     * @param coordinates
     * @return possibility
     */
    public Possibility findNearest(double[] coordinates, Possibilities possibilities){
        double tmpdif;
        double difference = 100000;
        double xDif;
        double yDif;
        double zDif;
        int index = -3;
        //for all possibilities calculate difference.
        for (int i = 0; i < possibilities.getAllPossibilities().size(); i++) {
            xDif = coordinates[0] - possibilities.getAllPossibilities().get(i).getX();
            yDif = coordinates[1] - possibilities.getAllPossibilities().get(i).getY();
            zDif = coordinates[2] - possibilities.getAllPossibilities().get(i).getZ();
            tmpdif = xDif + yDif + zDif;
            possibilities.getAllPossibilities().get(i).setDifference(tmpdif);
            //System.out.println(tmpdif);
            //remember min difference and index ofHer possibility
            if((tmpdif < 0 ? -1 * tmpdif : tmpdif) < (difference < 0 ? -1 * difference : difference)){
                difference = tmpdif;
                index = i;
            }
        }
    return index < 0 ? new Possibility() : possibilities.getAllPossibilities().get(index);
    }

    public Possibility getPossibility(double[] coordinates){
        Possibility possibility = findNearest(coordinates, fileWorker.loadMoznosti());
        Possibilities possibilities = new Possibilities();
        //
        if(possibility.getDifference() < 0){
            for (int i = possibility.getElement(0); i > possibility.getElement(0) - 4; i--) {
                for (int j = possibility.getElement(1); j > possibility.getElement(1) - 4; j--) {
                    for (int k = possibility.getElement(2); k > possibility.getElement(2) - 4; k--) {
                        for (int l = possibility.getElement(3); l > possibility.getElement(3) - 4; l--) {
                            possibilities.addPossibility(this.creator.createPossibility(i, j, k, l));
                        }
                    }
                }
            }
            //
            return findNearest(coordinates, possibilities);
            //
        }else if(possibility.getDifference() > 0){
            for (int i = possibility.getElement(0); i < possibility.getElement(0) + 4; i++) {
                for (int j = possibility.getElement(1); j < possibility.getElement(1) + 4; j++) {
                    for (int k = possibility.getElement(2); k < possibility.getElement(2) + 4; k++) {
                        for (int l = possibility.getElement(3); l < possibility.getElement(3) + 4; l++) {
                            possibilities.addPossibility(this.creator.createPossibility(i, j, k, l));
                        }
                    }
                }
            }
            //
            return findNearest(coordinates, possibilities);
            //
        }else {
            //if difference 0 return actual possibility
            return possibility;
        }
    }

    public Possibility getRequired() {
        return required;
    }

    public void setRequired(Possibility required) {
        this.required = required;
    }

    public double getDiference() {
        return diference;
    }

    public void setDiference(double diference) {
        this.diference = diference;
    }

}
