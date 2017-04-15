/******************************************************************************
* Matthew Villarreal (miv140130)
* CS 4348.002
* Project 3
*******************************************************************************
*******************************************************************************
*                               MF.java
*
* This class implements the Multilevel algorithm, which is a 
* preemptive scheduler that receives jobs and runs them in the first queue for 
* a particular time quantum (for this project the time quantum will always be 1)
* until a new job arrives, in which it pushes the first job into the second 
* and then third queue, each of decreasing priority, until it runs for the 
* amount of time it needs. It receives the list of jobs to run from 
* Project3.java, and runs until all jobs in the list have executed to 
* completion.
******************************************************************************/

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class MF extends Schedule {
    private List<Job> jList;
    private int qs;

    public MF(List<Job> jList, int qs) { //constructor
        this.jList = jList;
        this.qs = qs;
    } //end MF(1)

    public void scheduleJobs() { //MF scheduling algorithm
        StringBuilder jOut[] = new StringBuilder[jList.size()];

        //initialize array of StringBuilders
        for(int i = 0; i < jOut.length; i++) 
            jOut[i] = new StringBuilder("");
        
        int time = 0;
        
        //three queue initialization
        Queue<Job> highQ = new LinkedList<>(); //1st tier queue
        Queue<Job> midQ = new LinkedList<>(); //2nd tier queue
        Queue<Job> lowQ = new LinkedList<>(); //lowest tier queue

        //keeps track of removed jobs
        List<Job> listRm = new ArrayList<>();

        //runs until no more jobs in job list and none in any of the queues
        while(!jList.isEmpty() || !highQ.isEmpty() || !midQ.isEmpty() || !lowQ.isEmpty()) { 

            if(time == 0) { //only needed for initialization of first job into queue
                for(int i = 0; i < jList.size(); i++) {
                    if(time == jList.get(i).getArrTime()) {
                        highQ.add(jList.get(i)); 
                        listRm.add(jList.get(i)); //accumulate list of jobs added to the queue
                    } //end if
                } //end for
                jList.removeAll(listRm);
                listRm.clear();
            } //end if
             String temp = null;
             Job currJob = null; 

             if(!highQ.isEmpty()) { //run job in highQ
                 currJob = highQ.poll();
                 temp = currJob.runJob(1);

                 time++;

                 for(int i = 0; i < jList.size(); i++) { //check jList for newly arrived jobs
                    if(time == jList.get(i).getArrTime()) {
                        highQ.add(jList.get(i));
                        listRm.add(jList.get(i));
                    } //end if
                 } //end for

                 jList.removeAll(listRm);
                 listRm.clear();

                 if(currJob.getDuration() > 0 && highQ.isEmpty() && midQ.isEmpty() && lowQ.isEmpty()) //job can run again
                    highQ.add(currJob);
                 else if(currJob.getDuration() > 0) //put job in 2nd tier queue
                    midQ.add(currJob);
                 else { } //do nothing, job is completed
             } //end if
             else if(!midQ.isEmpty()) { //run job in midQ
                 currJob = midQ.poll();
                 temp = currJob.runJob(1);

                 time++;

                 for(int i = 0; i < jList.size(); i++) { //check jList for newly arrived jobs
                    if(time == jList.get(i).getArrTime()) {
                        highQ.add(jList.get(i));
                        listRm.add(jList.get(i));
                    } //end if
                 } //end for

                 jList.removeAll(listRm);
                 listRm.clear();

                 if(currJob.getDuration() > 0 && highQ.isEmpty() && midQ.isEmpty() && lowQ.isEmpty()) //job can run again
                    midQ.add(currJob);
                 else if(currJob.getDuration() > 0) //put job in lowest queue tier
                    lowQ.add(currJob);
                 else { } //do nothing, job is completed
             } //end else if
             else { //run job in low queue
                currJob = lowQ.poll();
                temp = currJob.runJob(1);

                time++;

                for(int i = 0; i < jList.size(); i++) { //check jList for newly arrived jobs
                    if(time == jList.get(i).getArrTime()) {
                        highQ.add(jList.get(i));
                        listRm.add(jList.get(i));
                    } //end if
                 } //end for

                 jList.removeAll(listRm);
                 listRm.clear();

                if(currJob.getDuration() > 0)
                    lowQ.add(currJob);
             } //end else

             for(int j = 0; j < jOut.length; j++) {
                if(j + 65 == (int) currJob.getName()) //compare ASCII
                    jOut[j].append(temp);
                else {
                    for(int k = 0; k < temp.length(); k++)
                        jOut[j].append(" ");   
                } //end else      
            } //end for
        } //end while

        System.out.println("Multilevel Feedback, quantum " + qs + "\n");
        printJobs(jOut);
    } //end scheduleJobs

    public void printJobs(StringBuilder jOut[]) { //print job scheduling order horizontally
        for(int i = 0; i < jOut.length; i++) 
            System.out.println(jOut[i]);
    } //end printJobs
} //end MF