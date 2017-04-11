import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class MF extends Schedule {
    private List<Job> jList;
    private int qs;

    public MF(List<Job> jList, int qs) {
        this.jList = jList;
        this.qs = qs;
    } //end MF(1)

    public void scheduleJobs() {
        StringBuilder jOut[] = new StringBuilder[jList.size()];
        for(int i = 0; i < jOut.length; i++) 
            jOut[i] = new StringBuilder("");
        
        int time = 0;
        
        Queue<Job> highQ = new LinkedList<>();
        Queue<Job> midQ = new LinkedList<>();
        Queue<Job> lowQ = new LinkedList<>();

        while(!jList.isEmpty() || !highQ.isEmpty() || !midQ.isEmpty() || !lowQ.isEmpty()) {
             for(int i = 0; i < jList.size(); i++) {
                if(time == jList.get(i).getArrTime()) {
                    highQ.add(jList.get(i));
                    jList.remove(i);
                } //end if
             } //end for

             String temp = null;
             Job currJob = null; 
             if(!highQ.isEmpty()) {
                 currJob = highQ.poll();
                 temp = currJob.runJob(qs);

                 if(currJob.getDuration() > 0)
                    midQ.add(currJob);
             } //end if
             else if(!midQ.isEmpty()) {
                 currJob = midQ.poll();
                 temp = currJob.runJob(qs);

                 if(currJob.getDuration() > 0)
                    lowQ.add(currJob);
             } //end else if
             else { //run job in low queue
                currJob = lowQ.poll();
                temp = currJob.runJob(qs);

                if(currJob.getDuration() > 0)
                    lowQ.add(currJob);
             } //end else

            //  add code to put temp in the correct index of jOut[]

             time += temp.length(); //can't use qs since it may be greater than the duration of the job
        } //end while
        System.out.println("Multilevel Feedback, quantum " + qs + "\n");
        printjobs(jOut);
    } //end scheduleJobs

    public void printJobs(StringBuilder jOut[]) {
        for(int i = 0; i < jOut.length; i++) 
            System.out.println(jOut[i]);
    } //end printJobs

} //end MF