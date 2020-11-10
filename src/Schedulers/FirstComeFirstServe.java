package Schedulers;
import Helpers.CPUProcess;
import Helpers.Scheduler;

public class FirstComeFirstServe extends Scheduler {
    private Scheduler scheduler;
    private int currentRunningQueue = 1;

    public FirstComeFirstServe(){
        scheduler = new Scheduler();
   }
   public void run(){
       startEach();
       firstComeFirstServe();
       finishUp();
       System.out.println();
       System.out.println("First Come First Serve");
       printTable();
   }
    //Trace and see if this can have call to method that will run ready queue in order of arrival.
    public void firstComeFirstServe(){
        CPUProcess process;
        while(!readyQueue.isEmpty()){
            process = findFirstToArrive();
            process.loadProcess(currentTime);
            status(process);
        }
    }
    //Increments the updateWaitTimer by 1 until another job is ready
    public void finishUp(){
        while(!finished){
            System.out.println("Time: "+currentTime+" Waiting for IO to finish");
            updateWaitTimer(1);
            if(!readyQueue.isEmpty())
                firstComeFirstServe();
        }
    }

}
