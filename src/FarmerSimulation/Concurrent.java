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
        //BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        // ThreadPoolExecutor ex = new ThreadPoolExecutor(5,10, 5000, TimeUnit.MILLISECONDS, queue);
        // ex.setRejectedExecutionHandler(new RejectedExecutionHandler() {
        //      @Override
        //     public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //         System.out.println("Activity by thread "+Thread.currentThread().getName()+" rejected");
                
        //     };
        // });
        // BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        // CustomThreadPoolExecutor ex = new CustomThreadPoolExecutor(threadNum, farmerNum, 5, TimeUnit.SECONDS, queue);
        
        Timer cTimer = new Timer();
        cTimer.startTimer();
        // while(true){
        //     if(ex.getQueue().remainingCapacity()==0){
        //         break;
        //     }
        // ex.prestartAllCoreThreads(); 
        // for(int i=0;i<farmerNum;i++){
        //     queue.offer(new FarmerThread(farmers[i].userId.toString()));
        // }   
        // ex.shutdown();
        // }
        // for(int i=0;i<10;i++){
        //     ex.execute(new FarmerThread(String.valueOf(i)));
        // }
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
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class CustomThreadPoolExecutor extends ThreadPoolExecutor {

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
            long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println("Perform beforeExecute() logic");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t != null) {
            System.out.println("Perform exception handler logic");
        }
        System.out.println("Perform afterExecute() logic");
    }
}