package Helpers;

import Interfaces.SchedulerInterface;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;

public class Scheduler implements SchedulerInterface {

    protected final List<CPUProcess> processList, readyQueue;
    protected float averageWaitTime, averageTurnAroundTime, averageResponseTime, utilization;
    protected int currentTime=0, currentPriorityLevel = 1;
    protected boolean finished = false;
    private DecimalFormat df = new DecimalFormat("0.00");

    public Scheduler() {
        processList = new ArrayList<CPUProcess>();
        readyQueue = new ArrayList<CPUProcess>();
    }

    @Override
    public void fillScheduler(CPUProcess[] arr) {
        processList.addAll(asList(arr));
        readyQueue.addAll(asList(arr));
    }

    //update all public private declarations
    //Loads each process since all arrive at time 0 into the system
    @Override
    public void startEach(){
        int currentBurst = 0;
        for (CPUProcess c :
                processList) {
            if (!c.isFinished()) {
                currentBurst = c.loadProcess(currentTime);
                updateWaitTimer(currentBurst);
            }
        }
    }
    //Returns the first CPU process to arrive in the the readyQueue based on arrivalTime
    @Override
    public CPUProcess findFirstToArrive(){
        int index = 0, arrival, i = 0;
        arrival = readyQueue.get(0).getArrivalTime();
        for (CPUProcess c :
                readyQueue) {
            if(c.isReady() && c.getArrivalTime() < arrival && c.getPriorityLevel() == currentPriorityLevel){
                index = i;
                arrival  = c.getArrivalTime();
            }
            i++;
        }
        return readyQueue.get(index);
    }
    //updates teh wait time of all events that are not finished.
    //Returns true or false if something was ready after update
    @Override
    public void updateWaitTimer(int burst){
        currentTime+=burst;
        for (CPUProcess c :
                processList) {
            if (c.isLoaded() && !c.isFinished() && c.updateWaitTime(burst) && c.isReady() && !readyQueue.contains(c)) {
                //Console output
                System.out.println("Time: "+ c.getArrivalTime()+ " Completed Process: "+c.getName()+" has been added to the ready queue!");
                readyQueue.add(c);
            }
        }
    }
    //Prints some info to console and updates teh wait time of all processes
    //Removes current process from ready queue since it has ran
    @Override
    public void status(CPUProcess process){
        if(process.isFinished())
            System.out.println("TIme: "+currentTime+" "+process.getName() + " has finished running.");
        if (readyQueue.contains(process) && !process.isReady())
            readyQueue.remove(process);
        updateWaitTimer(process.getCurrentBurst());
        printReadyQueueInfo();
    }

    //returns the number that are finished, 0 if none
    @Override
    public int allDone(){
        int num = 0;
        for (CPUProcess c :
                processList) {
            if(c.isFinished())
                num++;
        }
        if(num == processList.size())
            finished = true;
        return num;
    }

    public int howManyInIO(){
        int n = 0;
        for (CPUProcess c:
                readyQueue) {
            if(!c.isReady() && !c.isFinished()) n++;
        }
        return n;
    }

    //Returns how many processes are left in the current priority queue
    @Override
    public int howManyLeftInPriority() {
        int n = 0;
        for (CPUProcess c:
               readyQueue) {
            if(c.getPriorityLevel() == currentPriorityLevel) n++;
        }
        return n;
    }

    //Method recalculates all the averages
    @Override
    public void CalcAverages(){
        float b = 0;
        averageTurnAroundTime = 0;
        averageResponseTime = 0;
        averageWaitTime = 0;
        for (CPUProcess p :
                processList) {
            averageWaitTime += p.getWaitTime();
            averageResponseTime += p.getResponseTime();
            averageTurnAroundTime += p.getTurnaroundTime();
            b += p.getTotalBT();
        }
        utilization = (100*(b / currentTime));
        averageTurnAroundTime /= processList.size();
        averageResponseTime /= processList.size();
        averageWaitTime /= processList.size();
    }


    //prints out a table of the scheduler info
    @Override
    public void printTable(){
        CalcAverages();
        printProcessEventInfo();
        System.out.println();
        System.out.println("Utilization: "+df.format(utilization)+"%");
        System.out.format("%5s%15s%20s%20s", "Process Name","Total Wait","TurnAround Time","Response Time");
        System.out.println();
        for (CPUProcess c :
                processList) {
            System.out.format("%5s%15d%15d%20d",c.getName(),c.getWaitTime(),c.getTurnaroundTime(),c.getResponseTime());
            System.out.println();
        }
        printInfo();
    }
    //Prints out the averages
    @Override
    public void printInfo(){
        System.out.println();
        System.out.println("Time to complete all processes: "+currentTime);
        System.out.println(
                "Average Wait Time: "+df.format(averageWaitTime)+"\n"+
                "Average Turnaround Time: "+df.format(averageTurnAroundTime)+"\n"+
                "Average Response Time: "+df.format(averageResponseTime)+"\n");
    }
    //Prints out info about each process, this information includes all processor events,
    // how long they have left to wait and burst and wait time for each event
    @Override
    public void printProcessEventInfo(){
        for (CPUProcess c :
                processList) {
            c.printProcessInfo();
        }
    }
    //Prints out the ready queue and each ones burst
    public void printReadyQueueInfo(){
        System.out.println("Time: "+currentTime+" There are "+allDone()+" processes finished");
        System.out.println("Time: "+currentTime+" There are "+readyQueue.size()+" processes in the ready queue.");
        for (CPUProcess c:
             readyQueue) {
            System.out.println("Time: "+currentTime+" \t"+c.getName()+": Burst: "+c.getCurrentBurst()+" ");
        }
        System.out.println("Time: "+currentTime+" There are "+howManyInIO()+" processes in the ready queue.");
        System.out.println("Time: "+currentTime+" Processes in I/O");
        for (CPUProcess c:
                processList) {
            if(!c.isReady() && !c.isFinished()) {
                System.out.println("Time: " + currentTime + " \t" + c.getName() + ": I/O: " + c.getWaitTime() + " ");
            }
        }

    }
}
