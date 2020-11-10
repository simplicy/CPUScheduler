package Interfaces;

public interface CPUProcessInterface {
    //loads the current event, updates wait time by burst for all events
    // and returns the current process burst
    int loadProcess(int currentTime);
    boolean updateWaitTime(int currentBurst);
    //Prints all the info about the process including each of the event info
    void printProcessInfo();
    //Returns if the process is ready
    boolean isReady();
    //returns if the process has finished
    boolean isFinished();
    //returns if the process has been loaded (it is loaded upon initial start)
    boolean isLoaded();
    //Returns the response time of the process
    int getResponseTime();
    //Returns the total Burst Time of the process
    int getTotalBT();
    //returns the Total IO of the process
    int getTotalIO();
    //returns the current burst of the process
    int getCurrentBurst();
    //returns the priority level of the process
    void setPriorityLevel(int level);
    //Set the current events burst, used in MLFQ RR
    void setCurrentBurst(int n);
    //Returns the numeral of the process
    int getNumeral();
    //Accessor functions
    String getName();
    int getWaitTime();
    int getArrivalTime();
    int getTurnaroundTime();
    int getPriorityLevel();
}
