package FarmerSimulation;

import java.io.IOException;

public class Sequential {

    public static void main(String[] args) {
        FarmerSimulator fs = new FarmerSimulator();
        Farmer[] farmers = fs.generateFarmers(20);
        try {
            FileLogger.setupSequential();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
        Timer timer = new Timer();
        timer.startTimer();
        for (int i = 0; i < farmers.length; i++) {
            farmers[i].run();
        }
        timer.stopTimer();
        System.out.println("Time taken to generate activities is "+timer.getTimeTakeninMiliSec()+" ms.");

    }
}