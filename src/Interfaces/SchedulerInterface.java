package Interfaces;

import Helpers.CPUProcess;

public interface SchedulerInterface {
    //Fills the scheduler and ready queue with all of the CPUProcesses
    void fillScheduler(CPUProcess[] arr);
    //update all public private declarations
    //Loads each process since all arrive at time 0 into the system
    void startEach();
    //Returns the first CPUProcess to arrive into the ready queue.
    CPUProcess findFirstToArrive();
    //updates the wait time of all events that are not finished.
    void updateWaitTimer(int burst);
    //returns the number that are finished, 0 if none
    int allDone();
    //Returns the # of ready events there are that are in the current priority queue
    int howManyLeftInPriority();
    //Method recalculates
    void CalcAverages();
    //Prints some data about the currently running process and updates ready queue
    void status(CPUProcess process);
    //prints out a table of the scheduler info
    void printTable();
    //Prints all the Scheduler Information (Averages, etc.)
    void printInfo();
    //Prints the info about each event including wait time, burst time, etc.
    void printProcessEventInfo();
}
