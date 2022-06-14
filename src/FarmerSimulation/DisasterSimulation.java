package FarmerSimulation;

public class DisasterSimulation {

    public static void main(String[] args) {
        Farmer[] farmers = FarmerSimulator.generateFarmers(5);
        int stoppedOnce = 0;
        Thread t1 = null;
        int stoppedFarmer = 0;
        Timer timer = new Timer();
        timer.startTimer();
        // start thread for all farmer
        for (int i = 0; i < farmers.length; i++) {
            t1 = new Thread(farmers[i]);
            t1.start();
            // Stop the thread after specific of time
            if (System.nanoTime() >= timer.stopThreadInSpecificTime() && stoppedOnce != 1) {
                System.out.println("\n\n\n=====================disaster is coming=====================");
                System.out.println("Farmer ID that faced disaster: " + farmers[i].getUserId() + "\n\n\n");
                t1.stop();
                stoppedOnce = 1;
                stoppedFarmer = i;
                // break;
            }
            // wait till last thread finished running
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
                // continue to start back the stopped thread
                new Thread(farmers[stoppedFarmer]).start();
            }
        }
    }
}
