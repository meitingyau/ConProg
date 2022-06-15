package FarmerSimulation;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Concurrent{
    public static void main(String[] args){
        int threadNum = 95;
        int farmerNum = 95;
        try {
            FileLogger.setupConcurrent();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
        ExecutorService ex = Executors.newFixedThreadPool(threadNum);
        FarmerSimulator fs = new FarmerSimulator();
        Farmer[] farmers = fs.generateFarmers(farmerNum);
       
        Timer cTimer = new Timer();
        cTimer.startTimer();
        
        for(Farmer farmer:farmers){
            ex.execute(farmer);
        }
        ex.shutdown();
        while(true){
            if(ex.isTerminated()){
                break;
            }
        }
        cTimer.stopTimer();
        System.out.println("Successfully generate activities for "+farmerNum+" farmers using "+threadNum+" threads.");
        System.out.println("Time taken is "+cTimer.getTimeTakeninMiliSec()+" ms.");
    }
}

class FarmerThread implements Runnable{
    String farmerID;
    public FarmerThread(String farmerID){
        this.farmerID = farmerID;
    }
    public void run(){
        System.out.println(Thread.currentThread().getName()+" start.");
        generateActivities();
        System.out.println(Thread.currentThread().getName()+" end.");
    }
    private void generateActivities(){
        try{
            FarmerSimulator.simulateActivity(this.farmerID);
            Thread.sleep(10);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
