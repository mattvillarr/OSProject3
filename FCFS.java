/******************************************************************************
* Matthew Villarreal (miv140130)
* CS 4348.002
* Project 3
*******************************************************************************
*******************************************************************************
*                               FCFS.java
*
* This class implements the FCFS algorithm, which is a nonpreemtive scheduler
* that runs jobs based on which onces arrive first. It receives a list of jobs
* from Project3.java and runs until it completes all jobs in the list.
******************************************************************************/

import java.util.List;
import java.util.ArrayList;

public class FCFS extends Schedule {
    private List<Job> jList;

    public FCFS(List<Job> jList) { //Constructor
        this.jList = jList;
    } //end FCFS(1)

    public void scheduleJobs() { //FCFS scheduling algorithm
        StringBuilder sb[] = new StringBuilder[jList.size()]; //array of StringBuilders to hold output

        for(int i = 0; i < sb.length; i++) //initialize array with empty StringBuilders
            sb[i] = new StringBuilder("");

        int time = 0;

        while(!jList.isEmpty()) { //runs until all jobs are run and removed
            for(int i = 0; i < jList.size(); i++) {
                if(time == jList.get(i).getArrTime()) { //job has arrived
                    Job currJob = jList.get(i);
                    String temp = currJob.runJob(); //run job to completion

                    for(int j = 0; j < sb.length; j++) {
                        if(j + 65 == (int) currJob.getName()) //compare ASCII values (65 == A in ASCII)
                            sb[j].append(temp);
                        else {
                            for(int k = 0; k < temp.length(); k++)
                                sb[j].append(" "); //append spaces in rest of array
                        } //end else      
                    } //end for

                    jList.remove(i); //job is done running, remove job from list
                } //end if
            } //end for
            time++; //increment the time
        } //end while
        printJobs(sb); //method call to print job schedule ordering
    } //end scheduleJobs

    public void printJobs(StringBuilder jOut[]) { //print job scheduling order
        System.out.println("FCFS\n");
        for(int i = 0; i < jOut.length; i++) 
            System.out.println(jOut[i]);
    } //end printJobs
} //end FCFS


        