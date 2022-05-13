package FarmerSimulation;

public class Timer {
    private static long startTime;
    private static long endTime;

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public long getTimeTaken() {
        return (endTime - startTime);
    }

    public long getTimeTakeninMiliSec() {
        return (endTime - startTime) / 1000000;
    }

}
