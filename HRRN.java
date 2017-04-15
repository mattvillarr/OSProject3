/******************************************************************************
* Matthew Villarreal (miv140130)
* CS 4348.002
* Project 3
*******************************************************************************
*******************************************************************************
*                               HRRN.java
*
* This class implements the Highest Response Ratio Next algorithm, which is a 
* nonpreemptive scheduler that picks the job with the shortest response ratio 
* to run to completion. It calculates the response ratio using a method within
* Job.java. It receives the list of jobs to run from Project3.java, and runs 
* until all jobs in the list have executed to completion.
******************************************************************************/

import java.util.List;
import java.util.ArrayList;

public class HRRN extends Schedule {
    private List<Job> jList;

    public HRRN(List<Job> jList) { //Constructor
        this.jList = jList;
    } //end HRRT(1)

    public void scheduleJobs() { //HRRN scheduling algorithm
        StringBuilder jOut[] = new StringBuilder[jList.size()];

        //initialize array of StringBuilders
        for(int i = 0; i < jOut.length; i++) 
            jOut[i] = new StringBuilder("");

        int time = 0;
        
        while(!jList.isEmpty()) {
            int HRRIdx = -1; //index of job with the highest response ratio
            double hrr = -1; //high response ratio

            for(int i = 0; i < jList.size(); i++) {

                //time is accumulated all at once so anything with a start time less than it is waiting to run
                if(time >= jList.get(i).getArrTime()) { 
                    double check = jList.get(i).calcRR(time);
                    if(Double.compare(check, hrr) > 0) { 
                        hrr = check; //new max hrr
                        HRRIdx = i; //updates the index
                    } //end nested if
                } //end if
            } //end for

            Job currJob = jList.get(HRRIdx);

            String temp = currJob.runJob(); //run job to completion

            jList.remove(HRRIdx); //remove job from jList

        
            for(int j = 0; j < jOut.length; j++) { //update StringBuilder array
                if(j + 65 == (int) currJob.getName()) //compare ASCII values
                    jOut[j].append(temp);
                else {
                    for(int k = 0; k < temp.length(); k++)
                        jOut[j].append(" ");   
                } //end else      
            } //end for
            time += currJob.getDuration(); //increment time based on how long job ran
        } //end while
        printJobs(jOut);
    } //end scheduleJobs

    public void printJobs(StringBuilder jOut[]) { //output job schedule horizontally
        System.out.println("Highest Response Ratio Next\n");
        for(int i = 0; i < jOut.length; i++)
            System.out.println(jOut[i]);
    } //end printJobs
} //end HRRT