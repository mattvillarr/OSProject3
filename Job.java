/******************************************************************************
* Matthew Villarreal (miv140130)
* CS 4348.002
* Project 3
*******************************************************************************
*******************************************************************************
*                                   Job.java
*
* This class is used to create all Job objects. All job objects store the char
* name of their job, the time which the job arrives, and the duration of said
* job. It also contains two overloaded methods for running the scheduled job
* to completion, or for the duration of the passed time quantum. It also 
* contains the method calcRR to determine the response ratio for the job, 
* which is necessary for the HRRN algorithm.
******************************************************************************/
public class Job {

    /******************************PRIVATE VARIABLES**************************/
    private char name;
    private int arrTime;
    private int duration;

    /********************************CONSTRUCTORS*****************************/
    public Job(char name, int arrTime, int duration) {
        this.name = name;
        this.arrTime = arrTime;
        this.duration = duration;
    } //end Job(3)

    public Job(Job copy) { //necessary for deep-copying Job objects
        this.name = copy.name;
        this.arrTime = copy.arrTime;
        this.duration = copy.duration;
    } //end Job(1)

    /*******************************GETTER METHODS*****************************/
    public char getName() {
        return name;
    } //end getName
    public int getArrTime() {
        return arrTime;
    } //end getArrTime
    public int getDuration() {
        return duration;
    } //end getDuration

    /*******************************SETTER METHODS******************************/
    public void setName(char name) {
        this.name = name;
    } //end setName
    public void setArrTime(int arrTime) {
        this.arrTime = arrTime;
    }  //end arrTime
    public void setDuration(int duration) {
        this.duration = duration;
    } //end duration

    /********************************OTHER METHODS*******************************/
    public double calcRR(int time) { //for HRRN
        int waitTime = time - arrTime;
        return (waitTime + duration) / duration;
    } //end calcRR

    public String runJob() { //non-preemptive run
        StringBuilder running = new StringBuilder();
        for(int i = 0; i < duration; i++) 
            running.append(name);

        return running.toString();
    } //end runJob(0)

    public String runJob(int quantum) { //preemptive run
        StringBuilder running = new StringBuilder();
        for(int i = 0; i < quantum; i++) {
            if(duration == 0)
                break;
            running.append(name);
            duration--;
        } //end for
    
        return running.toString();
    } //end runJob(1)
} //end Job