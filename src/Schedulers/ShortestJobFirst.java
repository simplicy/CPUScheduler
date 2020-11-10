package Schedulers;

import Helpers.CPUProcess;
import Helpers.Scheduler;

public class ShortestJobFirst extends Scheduler {
    private Scheduler scheduler;
    public void ShortestJobFirst(){
        scheduler = new Scheduler();
    }
    public void run(){
        shortestJobFirst();
        finishUp();
        System.out.println();
        System.out.println("Shortest Job First");
        printTable();
    }
    //algorithm for shortest job
    public void shortestJobFirst(){
        CPUProcess process;
        while(!readyQueue.isEmpty()){
            process = findShortestJob();
            process.loadProcess(currentTime);
            status(process);
        }
    }
    //Returns the CPUProccess Object of the shortest Burst Time job in readyQueue
    public CPUProcess findShortestJob(){
        int index = 0, burstTime, i = 0;
        burstTime = readyQueue.get(0).getCurrentBurst();
        for (CPUProcess c :
                readyQueue) {
            if(c.isReady() && c.getCurrentBurst() < burstTime){
                index = i;
                burstTime  = c.getCurrentBurst();
            }
            i++;
        }
        return readyQueue.get(index);
    }
    //Increments the updateWaitTimer by 1 until another job is ready
    public void finishUp(){
        while(!finished){
            System.out.println("Time: "+currentTime+" Waiting for IO to finish");
            updateWaitTimer(1);
            if(!readyQueue.isEmpty())
                shortestJobFirst();
        }
    }
}
