package Interfaces;

public interface EventInterface {
    //Prints out the Event name and information relating to it
    String getEventInfo();
    String getEventName();
    //updates the IO wait time and returns true if it has finished
    boolean updateWaitTime(int n);
    //Stores the arrival time of when this even was put on the processor
    void setArrivalTime(int n);
    //Stores the current process time of when this event will be done
    void setNextReadyTime(int n);
    //Sets the passed value to be the new burst time
    void setBurstTime(int n);
    //General Accessor
    int getBurstTime();
    int getIOTime();
    int getNextReady();
    int getWaitTime();
    void setIOTime(int n);
}
