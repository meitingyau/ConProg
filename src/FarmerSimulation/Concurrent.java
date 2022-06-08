package FarmerSimulation;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrent{
    public static void main(String[] args){
        int num = 5;
        try {
            FileLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
        ExecutorService ex = Executors.newFixedThreadPool(num);
        Timer cTimer = new Timer();
        cTimer.startTimer();
        for(int i=0;i<10;i++){
            Runnable farmer = new FarmerThread("Thread"+i);
            ex.execute(farmer);
        }
        ex.shutdown();
        while(true){
            if(ex.isTerminated()){
                break;
            }
        }
        cTimer.stopTimer();
        System.out.println("Successfully generate activities using "+num+" threads.");
        System.out.println("Time taken is "+cTimer.getTimeTakeninMiliSec()+" ms.");
    }
}

class FarmerThread implements Runnable{
    User farmer;
    public FarmerThread(String s){
        
    }
    public void run(){
        System.out.println(Thread.currentThread().getName()+" start.");
        generateActivities();
        System.out.println(Thread.currentThread().getName()+" end.");
    }
    private void generateActivities(){
        try{
            FarmerSimulator.simulateActivity();
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

