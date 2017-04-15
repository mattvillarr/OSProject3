/******************************************************************************
* Matthew Villarreal (miv140130)
* CS 4348.002
* Project 3
*******************************************************************************
*******************************************************************************
*                               RoundRobin.java
*
* This class implements the RoundRobin algorithm, which is a preemptive 
* scheduler that gives each job a time quantum to execute, but then is put at
* the end of a queue if it has not finished executing. It receives the list
* of jobs to run from Project3.java, and runs until all jobs in the list have
* executed to completion
******************************************************************************/

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class RoundRobin extends Schedule {
    private List<Job> jList;
    private int qs; //quantum size;

    public RoundRobin(List<Job> jList, int qs) { //Constructor
        this.jList = jList;
        this.qs = qs;
    } //end RoundRobin(2)

    public void scheduleJobs() { //Roundrobin scheduling algorithm
        Queue<Job> rOrder = new LinkedList<>(); //RR queue
        StringBuilder jOut[] = new StringBuilder[jList.size()];

        //initialize array of stringbuilders
        for (int i = 0; i < jOut.length; i++) 
            jOut[i] = new StringBuilder("");

        int time = 0; 

        while(!jList.isEmpty()) { //runs until all jobs have finished executing
            if(time == 0) { //only needed for initialization of first job into queue
                for(int i = 0; i < jList.size(); i++) {
                    if(time == jList.get(i).getArrTime()) 
                        rOrder.add(jList.get(i));
                } //end for
            } //end if  
            
            Job currJob = rOrder.poll(); //get first job in the queue
            String temp = currJob.runJob(qs); //run job
            
            //update stringbuffer array for current quantum
            for(int j = 0; j < jOut.length; j++) {
                if(j + 65 == (int) currJob.getName()) //compare ASCII values
                    jOut[j].append(temp);
                else {
                    for(int k = 0; k < qs; k++)
                        jOut[j].append(" ");   
                } //end else      
            } //end for
            
            //check time and add jobs to RR queue
            for(int j = 0; j < qs; j++) {
                time++;
                for(int k = 0; k < jList.size(); k++) {
                    if(time == jList.get(k).getArrTime()) 
                        rOrder.add(jList.get(k)); //new jobs are added in front of old jobs
                } //end nested for
            } //end for
            
            if(currJob.getDuration() > 0) //job has not finished executing
                rOrder.add(currJob);
            else  //job is complete, remove from list of jobs
                jList.remove(currJob);
        
        } //end while
        System.out.println("Round Robin, quantum " + qs + "\n");
        printJobs(jOut); //method call to output jobs
    } //end scheduleJobs

    public void printJobs(StringBuilder jOut[]) { //print job schedluling order horizontally
        for(int i = 0; i < jOut.length; i++) 
            System.out.println(jOut[i]);
    } //end printJobs
} //end RoundRobin