package Pc;

import Pc.Graphics.Handeling;
import Pc.Logic.Services.Calculations.CalculatePos;
import Pc.Logic.Services.FileWorker;

public class Run {
    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker();
        if(null == fileWorker.loadMoznosti()){
            fileWorker.saveMoznosti(new CalculatePos().calculate());
        }
        new Handeling(fileWorker);
    }
}
