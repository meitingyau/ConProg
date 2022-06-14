package FarmerSimulation;

public class Timer {
    private static long startTime;
    private static long stopTime;
    private static long endTime;

    // record start time
    public void startTimer() {
        startTime = System.nanoTime();
    }

    // record end time
    public void stopTimer() {
        endTime = System.nanoTime();
    }

    // calculate duration
    public long getTimeTaken() {
        return (endTime - startTime);
    }

    // get duration in millisecond instead nanosecond
    public long getTimeTakeninMiliSec() {
        return (endTime - startTime) / 1000000;
    }

    public long stopThreadInSpecificTime() {
        stopTime = startTime + 5000;

        return stopTime;
    }

}
