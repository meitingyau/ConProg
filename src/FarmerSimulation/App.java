package FarmerSimulation;

// public class App {
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

// import FarmerSimulation.WriteToLogFile;

public class App {
    // use the classname for the logger, this way you can refactor
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public void doSomeThingAndLog() {
        // ... more code

        // now we demo the logging

        // set the LogLevel to Severe, only severe Messages will be written
        LOGGER.setLevel(Level.SEVERE);
        LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");
        LOGGER.info("Info Log");
        LOGGER.finest("Really not important");

        // set the LogLevel to Info, severe, warning and info will be written
        // finest is still not written
        LOGGER.setLevel(Level.INFO);
        LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");
        LOGGER.info("Info Log");
        LOGGER.finest("Really not important");
    }

    public static void main(String[] args) {
        // Timer timer = new Timer();
        // timer.startTimer();
        // System.out.println("Hello, 2 World!");
        // System.out.println("Hello, 3 World!");
        // System.out.println("Hello, 4 World!");
        // System.out.println("Hello, 5 World!");
        // System.out.println("Hello, 6 World!");
        // System.out.println("Hello, 7 World!");
        // System.out.println("Hello, 8 World!");
        // System.out.println("peepoPogClimbingTreeHard4House");
        // System.out.println("Woohoo weihao bring fly");
        // timer.stopTimer();
        // System.out.println(timer.getTimeTakeninMiliSec());

        // App tester = new App();
        
        // try {
        //     FileLogger.setup();
        // } catch (IOException e) {
        //     e.printStackTrace();
        //     throw new RuntimeException("Problems with creating the log files");
        // }

        // tester.doSomeThingAndLog();
        Farmer[] farmers = FarmerSimulator.generateFarmers(4);
        Timer timer = new Timer();
        timer.startTimer();
        for (int i = 0; i < farmers.length; i++) {
            FarmerSimulator.simulateActivity(farmers[i].userId.toString());
        }
        timer.stopTimer();
        System.out.println("Time taken to generate activities is "+timer.getTimeTakeninMiliSec()+" ms.");
        // System.out.println(FarmerSimulator.getRowColumn());
        // System.out.println(getAction());
    }
    static private int getAction(){
        Random r = new Random();
        int rand = r.nextInt(6-1) + 1;
        return rand;
    }
}