import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

public class SPN extends Schedule {
    private List<Job> jList;

    public SPN(List<Job> jList) {
        this.jList = jList;
    } //end SPN(1)

    public void scheduleJobs() {
        StringBuilder jOut[] = new StringBuilder[jList.size()];
        for(int i = 0; i < jOut.length; i++) 
            jOut[i] = new StringBuilder("");
        
        int time = 0;
        Comparator<Job> comparator = new DurCompare(); //ordered by duration
        PriorityQueue<Job> pq = new PriorityQueue<>(jList.size(), comparator);

        while(!jList.isEmpty() || !pq.isEmpty()) { //run until no more jobs and no more jobs waiting
            for(int i = 0; i < jList.size(); i++) {
                //time is accumulated all at once so anything with a start time less than it is waiting to run
                if(time >= jList.get(i).getArrTime()) { 
                    pq.add(jList.get(i)); //add job to priority queue
                    jList.remove(jList.get(i)); //remove from list of jobs
                } //end if    
            } //end for

            if(!pq.isEmpty()) {
                Job currJob = pq.poll(); //the next job with the shortest SPN
                String temp = currJob.runJob();

                for(int j = 0; j < jOut.length; j++) {
                    if(j + 65 == (int) currJob.getName())
                        jOut[j].append(temp);
                    else {
                        for(int k = 0; k < temp.length(); k++)
                            jOut[j].append(" ");
                    } //end else
                } //end for
                time += currJob.getDuration() -1; //minus one because time is incremented every while iteration
            } //end if
            time++; //increment the time
        } //end while
        printJobs(jOut);
    } //end scheduleJobs

    public void printJobs(StringBuilder jOut[]) {
        System.out.println("Shortest Process Next\n");
        for(int i = 0; i < jOut.length; i++) 
            System.out.println(jOut[i]);
    } //end printJobs

} //end SPN