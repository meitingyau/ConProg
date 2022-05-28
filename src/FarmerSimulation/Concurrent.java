package FarmerSimulation;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrent{
    public static void main(String[] args){
        ExecutorService ex = Executors.newFixedThreadPool(10);
        Runnable farmer = new FarmerThread("Farmer");
        ex.execute(farmer);
        ex.shutdown();
        while(!ex.isTerminated()){}
        
    }
}

class FarmerThread implements Runnable{
    public FarmerThread(String s){
        
    }
    public void run(){
        generateActivities();
    }
    private void generateActivities(){
        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

