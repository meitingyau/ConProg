package FarmerSimulation;

public class DisasterSimulation {

    public static void main(String[] args) {
        Farmer[] farmers = FarmerSimulator.generateFarmers(4);
        Timer timer = new Timer();
        timer.startTimer();
        for (int i = 0; i < farmers.length; i++) {
            farmers[i].run();
            if (System.nanoTime() >= timer.stopThreadIn500Ms()) {
                farmers[i].stop();
                break;
            }
            farmers[i].run();
            // if (System.nanoTime() >= timer.stopThreadIn500Ms()) {
            // System.out.println("=====================disaster is
            // coming=====================");
            // farmers[i].stop();
            // // Thread.sleep(500);
            // }

            // if (System.nanoTime() == timer.stopThreadIn500Ms()) {
            // System.out.println(" disaster is coming");
            // farmers[i].stop();
            // }
        }

        // for (int i = 0; i < farmers.length; i++) {
        // FarmerSimulator.simulateActivity(farmers[i].userId.toString());
        // }
        timer.stopTimer();
        System.out.println("Time taken to generate activities is " + timer.getTimeTakeninMiliSec() + " ms.");
    }
}
