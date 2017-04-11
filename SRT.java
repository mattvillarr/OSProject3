import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

public class SRT extends Schedule {
    private List<Job> jList;

    public SRT(List<Job> jList) {
        this.jList = jList;
    } //end SRT(1)

    public void scheduleJobs() {
        StringBuilder jOut[] = new StringBuilder[jList.size()];
        for(int i = 0; i < jOut.length; i++) 
            jOut[i] = new StringBuilder("");
        
        int time = 0;
        Comparator<Job> comparator = new DurCompare(); //ordered by duration
        PriorityQueue<Job> pq = new PriorityQueue<>(jList.size(), comparator);

        while(!jList.isEmpty() || !pq.isEmpty()) { //run until no more jobs in list and no jobs waiting in queue
            for(int i = 0; i < jList.size(); i++) {
                if(time == jList.get(i).getArrTime()) {
                    pq.add(jList.get(i));
                    jList.remove(jList.get(i));
                } //end if
            } //end for

            if(!pq.isEmpty()) {
                Job currJob = pq.peek();
                String temp = currJob.runJob(1); //runs for one quanta

                if(currJob.getDuration() == 0)
                    pq.poll(); //job is completed, remove job from queue

                for(int j = 0; j < jOut.length; j++) {
                    if(j + 65 == (int) currJob.getName())
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

    public void printJobs(StringBuilder jOut[]) {
        System.out.println("Shortest Remaining Time\n");
        for(int i = 0; i < jOut.length; i++) 
            System.out.println(jOut[i]);
    }
} //end SRT