/******************************************************************************
* Matthew Villarreal (miv140130)
* CS 4348.002
* Project 3
*******************************************************************************
*******************************************************************************
*                               Schedule.java
*
* This abstract class holds the scheduling and printing abstract methods to be
* used by all the scheduling algorithm classes. 
******************************************************************************/
public abstract class Schedule {

    //schedules jobs
    abstract public void scheduleJobs();

    //receives array of StringBuilders to print out jobs horizontally
    abstract public void printJobs(StringBuilder jobs[]);
} //end schedule
