package Schedulers;

import Helpers.CPUProcess;
import Helpers.Scheduler;

public class MultiLevelFeedbackQueue extends Scheduler {
    private Scheduler scheduler;
    private FirstComeFirstServe fcfs;
    private  int tq_1 = 5, tq_2 = 10;
    public void MultiLevelFeedbackQueue(){
        scheduler = new Scheduler();
        fcfs = new FirstComeFirstServe();

    }
    public void run(){
        roundRobin();
        firstComeFirstServe();
        finishUp();
        System.out.println();
        System.out.println("MultiLevel Feedback Queue");
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
    //round robin algorithm
    public void roundRobin() {
        int leftover, timeQuantum = tq_1;
        CPUProcess c;
        while (!readyQueue.isEmpty() && currentPriorityLevel!=3) {
            c = findFirstToArrive();
            leftover = c.getCurrentBurst() - timeQuantum;
            if (leftover < 0)
                c.setCurrentBurst(timeQuantum);
            c.loadProcess(currentTime);
            if (leftover > 0) {
                c.setCurrentBurst(leftover);
                c.setReady();
                c.setPriorityLevel(currentPriorityLevel + 1);
            }
            status(c);
            System.out.println("Current Priority level: "+currentPriorityLevel);
            System.out.println("Left in current priority: "+howManyLeftInPriority());
            if(howManyLeftInPriority()==0) {
                currentPriorityLevel++;
                timeQuantum = tq_2;
            }
        }
    }
    //Finishes running the IO for teh final processes,
    // increments wait by 1 until something is added to readyQueue
    public void finishUp(){
        while(!finished){
            System.out.println("Time: "+currentTime+" Waiting for IO to finish");
            updateWaitTimer(1);
            if(!readyQueue.isEmpty())
                firstComeFirstServe();
        }
    }
}
