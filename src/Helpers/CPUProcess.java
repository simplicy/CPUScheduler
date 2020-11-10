package Helpers;

import Interfaces.CPUProcessInterface;

import java.util.ArrayList;

public class CPUProcess implements CPUProcessInterface {

    private String processName;
    private int
            numeral,
            turnAroundTime,
            waitTime = 0,
            arrivalTime = 0,
            responseTime,
            totalIO = 0,
            totalBT = 0,
            currentWait = 0,
            priorityLevel = 1,
            currentPos = 0;
    private Event[] events;
    private boolean ready, finished, loaded = false;

    public CPUProcess(String name, int[] arr){
        ready = true;
        processName = name;
        numeral = Integer.parseInt(name.substring(1));
        fillEvents(arr);
    }

    private void fillEvents(int[] arr){
        int i = 0;
        int size = Math.round((float)(arr.length/2) +1);
        boolean b = true;
        events = new Event[size];

        for (int n: arr) {
            if(b) {
                events[i] = new Event(n,(i+1));
                totalBT += events[i].getBurstTime();
                b = false;
            }else{
                events[i].setIOTime(n);
                totalIO += events[i].getIOTime();
                i++;
                b = true;
            }
        }
    }
    //Updates values for the process and performs some checks while logging anything that has changed
    private int performTasksAndUpdate(int currentTime){
        int burstTime = 0;
        Event e = events[currentPos];

        loaded = true;
        ready = false;
        arrivalTime = currentTime+burstTime+e.getIOTime();
        turnAroundTime = currentTime;
        burstTime = e.getBurstTime();
        waitTime = turnAroundTime - totalIO - totalBT + burstTime;

        e.setArrivalTime(currentTime);
        e.setNextReadyTime(currentTime+burstTime+e.getIOTime());

        System.out.println("Time: "+currentTime+
                " Running Process: "+getName()+ ": " +
                events[currentPos].getEventName()+
                " I/O Wait will be finished at: "+
                events[currentPos].getNextReady()+" ");

        if(currentPos == events.length-1) {
            finished = true;
            loaded = false;
            turnAroundTime = events[currentPos].getNextReady() - responseTime;
        }

        return burstTime;
    }

    //loads the current event, updates wait time by burst for all events
    // and returns the current process burst
    @Override
    public int loadProcess(int currentTime){
        if(finished)return 0;
        if(loaded)
            currentPos++;
        if(currentPos==0)
            responseTime = currentTime;
        return performTasksAndUpdate(currentTime);
    }

    @Override
    public boolean updateWaitTime(int currentBurst){
        if(!finished && ready)
            currentWait+=currentBurst;
        else {
            if(!finished && events[currentPos].updateWaitTime(currentBurst)){
                ready = true;
                currentWait += events[currentPos].getWaitTime();
                return true;
            }
        }
        return false;
    }

    @Override
    public void printProcessInfo(){
        System.out.println("\n"+getName()+" info...");
        for (Event e: events)
           System.out.println(e.getEventInfo());
        System.out.println("Total IO Time: "+totalIO);
        System.out.println("Total Burst Time: "+totalBT);
        System.out.println("Total Wait Time: "+ waitTime);
        System.out.println("Turnaround Time: "+turnAroundTime);
        System.out.println("Response Time: "+responseTime);
    }

    //Returns if the process is ready
    @Override
    public boolean isReady(){ return ready; }

    public void setReady(){ ready = true; }
    //returns if the process has finished running
    @Override
    public boolean isFinished() { return finished; }
    //returns if the process has been loaded (it is loaded upon initial start)
    @Override
    public boolean isLoaded(){ return loaded; }
    //Returns the response time of the process
    @Override
    public int getResponseTime(){ return responseTime; }
    //Returns the total Burst Time of the process
    @Override
    public int getTotalBT(){ return totalBT; }
    //returns the Total IO of the process
    @Override
    public int getTotalIO(){ return totalIO; }
    //returns the current burst of the process
    @Override
    public int getCurrentBurst(){ return events[currentPos].getBurstTime(); }
    //returns the priority level of the process
    @Override
    public void setPriorityLevel(int level){ priorityLevel = level;}
    //Set the current events burst, used in MLFQ RR
    @Override
    public  void setCurrentBurst(int n){ events[currentPos].setBurstTime(n);}
    //Returns the numeral of the process
    @Override
    public int getNumeral(){ return numeral; }
    //Returns the name of the process
    @Override
    public String getName() { return processName; }
    //Retunrs the wait time of the process
    @Override
    public int getWaitTime() { return currentWait; }
    //returns the arrival time of the process (used in comparison for FCFS)
    @Override
    public int getArrivalTime() { return events[currentPos].getNextReady(); }
    //Returns the Turnaround time
    @Override
    public int getTurnaroundTime() { return turnAroundTime; }
    //returns the priority level
    @Override
    public int getPriorityLevel() { return priorityLevel; }
}
