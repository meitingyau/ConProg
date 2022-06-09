package FarmerSimulation;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Concurrent{
    public static void main(String[] args){
        int threadNum = 4;
        int farmerNum = 4;
        try {
            FileLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
        ExecutorService ex = Executors.newFixedThreadPool(threadNum);
        Farmer[] farmers = FarmerSimulator.generateFarmers(farmerNum);
       
        Timer cTimer = new Timer();
        cTimer.startTimer();
        
        for(int i=0;i<farmerNum;i++){
            Runnable farmer = new FarmerThread(farmers[i].userId.toString());
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
