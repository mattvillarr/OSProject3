import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class RoundRobin extends Schedule {
    private List<Job> jList;
    private int qs; //quantum size;
    public RoundRobin(List<Job> jList, int qs) {
        this.jList = jList;
        this.qs = qs;
    } //end RoundRobin(2)

    public void scheduleJobs() { 
        Queue<Job> rOrder = new LinkedList<>();
        StringBuilder jOut[] = new StringBuilder[jList.size()];

        //initialize array of stringbuilders
        for (int i = 0; i < jOut.length; i++) 
            jOut[i] = new StringBuilder("");

        int time = 0; 

        while(!jList.isEmpty()) {
            if(time == 0) { //only needed for initialization of process. ask ozbirn if there will always be a job at time ==0
                for(int i = 0; i < jList.size(); i++) {
                    if(time == jList.get(i).getArrTime()) 
                        rOrder.add(jList.get(i));
                } //end for
            }   
            
            Job currJob = rOrder.poll(); 
            String temp = currJob.runJob(qs);
            
            //update stringbuffer array for current quantum
            for(int j = 0; j < jOut.length; j++) {
                if(j + 65 == (int) currJob.getName()) //compare ASCII
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
            
            if(currJob.getDuration() > 0) 
                rOrder.add(currJob);
            else 
                jList.remove(currJob);
        
        } //end while
        System.out.println("Round Robin, quantum " + qs + "\n");
        printJobs(jOut);
    } //end scheduleJobs

    public void printJobs(StringBuilder jOut[]) { 
        for(int i = 0; i < jOut.length; i++) 
            System.out.println(jOut[i]);
    }
} //end RoundRobin