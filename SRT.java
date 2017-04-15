/******************************************************************************
* Matthew Villarreal (miv140130)
* CS 4348.002
* Project 3
*******************************************************************************
*******************************************************************************
*                               SRT.java
*
* This class implements the Shortest Remaining Time algorithm, which is a 
* preemptive scheduler that picks the job with the shortest duration to run
* until another job enters that has a shorter duration. It receives the list of 
* jobs to run from Project3.java, and runs until all jobs in the list have 
* executed to completion.
******************************************************************************/

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

public class SRT extends Schedule {
    private List<Job> jList;

    public SRT(List<Job> jList) { //Constructor
        this.jList = jList;
    } //end SRT(1)

    public void scheduleJobs() { //SRT scheduler algorithm
        StringBuilder jOut[] = new StringBuilder[jList.size()];
        for(int i = 0; i < jOut.length; i++) 
            jOut[i] = new StringBuilder("");
        
        int time = 0;
        Comparator<Job> comparator = new DurCompare(); //ordered by duration
        PriorityQueue<Job> pq = new PriorityQueue<>(jList.size(), comparator);

        while(!jList.isEmpty() || !pq.isEmpty()) { //run until no more jobs in list and no jobs waiting in queue

            int idxRm = -1; //index of job to remove

            for(int i = 0; i < jList.size(); i++) {
                if(time == jList.get(i).getArrTime()) {
                    pq.add(jList.get(i));
                    idxRm = i; //updates index of job to remove
                } //end if
            } //end for
            
            if(idxRm != -1) //there is a job to remove from the list
                jList.remove(idxRm);

            if(!pq.isEmpty()) {
                Job currJob = pq.peek(); //don't remove job from list as pq will always sort jobs by duration
                String temp = currJob.runJob(1); //runs for one quanta

                if(currJob.getDuration() == 0) //job is completed, remove job from queue
                    pq.poll(); 

                for(int j = 0; j < jOut.length; j++) { 
                    if(j + 65 == (int) currJob.getName()) //compare ASCII values
                        jOut[j].append(temp);
                    else {
                        jOut[j].append(" ");
                    } //end else
                } //end for
            } //end if
            time++;
        } //end while
        printJobs(jOut);
    } //end scheduleJobs

    public void printJobs(StringBuilder jOut[]) { //output jobs
        System.out.println("Shortest Remaining Time\n");
        for(int i = 0; i < jOut.length; i++) 
            System.out.println(jOut[i]);
    } //end printJobs
} //end SRT