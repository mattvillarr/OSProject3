import java.util.List;
import java.util.LinkedList;
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
        Comparator<Job> comparator = new SPNCompare();
        PriorityQueue<Job> pq = new PriorityQueue<>(jList.size(), comparator);

        while(!jList.isEmpty()) {
            for(int i = 0; i < jList.size(); i++) {
                if(time >= jList.get(i).getArrTime()) 
                    pq.add(jList.get(i));
            } //end for

            if(!pq.isEmpty()) {
                Job currJob = pq.poll();
                String temp = currJob.runJob();

                for(int j = 0; j < jOut.length; j++) {
                    if(j + 65 == (int) currJob.getName())
                        jOut[j].append(temp);
                    else {
                        for(int k = 0; k < temp.length(); k++)
                            jOut[j].append(" ");
                    } //end else
                } //end for
                jList.remove(currJob);
                time += currJob.getDuration() -1; //
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