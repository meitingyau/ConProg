package FarmerSimulation;

public class DisasterSimulation {

    public static void main(String[] args) {
        Farmer[] farmers = FarmerSimulator.generateFarmers(5);
        for (int i = 0; i < farmers.length; i++) {
            System.out.println(farmers[i].toString());
        }
        int stoppedOnce = 0;
        Thread t1 = null;
        int stoppedFarmer = 0;
        Timer timer = new Timer();
        timer.startTimer();
        for (int i = 0; i < farmers.length; i++) {
            // farmers[i].run();
            t1 = new Thread(farmers[i]);
            t1.start();
            if (System.nanoTime() >= timer.stopThreadIn500Ms() && stoppedOnce != 1) {
                System.out.println("\n\n\n=====================disaster is coming=====================");
                System.out.println("Farmer ID that faced disaster: " + farmers[i].getUserId() + "\n\n\n");
                t1.interrupt();
                stoppedOnce = 1;
                stoppedFarmer = i;
                // break;
            }
            if (i == farmers.length - 1) {
                try {
                    t1.join();
                } catch (Exception ex) {
                    System.out.println("Exception has " +
                            "been caught" + ex);
                }
                System.out.println("\n\n\n=====================disaster has gone=====================");
                System.out.println(
                        "Farmer ID " + farmers[stoppedFarmer].getUserId() + " can continue his operations now\n\n\n");
                new Thread(farmers[stoppedFarmer]).start();
                

            }
            // if (i == farmers.length - 1) {
            // try {
            // t1.join();

            // }

            // catch (Exception ex) {
            // System.out.println("Exception has " +
            // "been caught" + ex);
            // }
            // timer.stopTimer();
            // System.out.println("Time taken to generate activities is " +
            // timer.getTimeTakeninMiliSec() + " ms.");

            // }

            // farmers[i].run();
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
        // System.out.println("\n\n\n=====================disaster has
        // gone=====================");
        // System.out.println("Farmer ID " + farmers[stoppedFarmer].getUserId() + " can
        // continue his operations now\n\n\n");
        // new Thread(farmers[stoppedFarmer]).start();

        // for (int i = 0; i < farmers.length; i++) {
        // FarmerSimulator.simulateActivity(farmers[i].userId.toString());
        // }
        timer.stopTimer();
        System.out.println("Time taken to generate activities is " + timer.getTimeTakeninMiliSec() + " ms.");
    }
}
