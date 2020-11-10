package Helpers;

import Interfaces.EventInterface;

public class Event implements EventInterface {
    private int
            burstTime,
            arrivalTime,
            ioTime = 0,
            ioWait = 0,
            eventWaitTime = 0,
            eventNumeral,
            nextReady;
    private String eventName;
    private boolean done;

    public Event(int burst, int numeral){
        burstTime = burst;
        eventNumeral = numeral;
        eventName = "E" + eventNumeral;
        done = false;
    }
    //Updates wait time and returns true when done.
    @Override
    public boolean updateWaitTime(int n){
        ioWait -= n;
        if(ioWait<=0 && !done){
            done = true;
            eventWaitTime = (ioWait * -1);
            return true;
        }
        return done;
    }

    //Stores the arrival time of when this even was put on the processor
    @Override
    public void setArrivalTime(int n){ arrivalTime = n; }

    //Stores the current process time of when this event will be done
    @Override
    public void setNextReadyTime(int n){ nextReady = n;}
    //Stores the burst Time, used mainly for Round Robin with a TQ
    @Override
    public void setBurstTime(int n){burstTime = n;}

    @Override
    public String getEventInfo() { return eventName+ ": \n Burst Time: "+ burstTime+ " IO Time: "+ ioTime+ " Arrival Time: " +arrivalTime+" Wait Time left: "+ ioWait+ "\n"; }
    @Override
    public int getNextReady(){ return  nextReady;}

    @Override
    public int getWaitTime(){ return eventWaitTime; }

    @Override
    public String getEventName(){ return eventName; }

    @Override
    public int getBurstTime() { return burstTime; }

    @Override
    public int getIOTime() { return ioTime; }
    @Override
    public void setIOTime(int n){ ioTime = n; ioWait = ioTime;}


}
