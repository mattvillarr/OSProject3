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

        List<Job> listRm = new ArrayList<>();

        while(!jList.isEmpty() || !highQ.isEmpty() || !midQ.isEmpty() || !lowQ.isEmpty()) {

            if(time == 0) {
                for(int i = 0; i < jList.size(); i++) {
                    if(time == jList.get(i).getArrTime()) {
                        highQ.add(jList.get(i)); 
                        listRm.add(jList.get(i)); 
                    } //end if
                } //end for
                jList.removeAll(listRm);
                listRm.clear();
            } //end if
             String temp = null;
             Job currJob = null; 

             if(!highQ.isEmpty()) {
                 currJob = highQ.poll();
                 temp = currJob.runJob(1);

                 time++;

                 for(int i = 0; i < jList.size(); i++) {
                    if(time == jList.get(i).getArrTime()) {
                        highQ.add(jList.get(i));
                        listRm.add(jList.get(i));
                    } //end if
                 } //end for

                 jList.removeAll(listRm);
                 listRm.clear();

                 if(currJob.getDuration() > 0 && highQ.isEmpty() && midQ.isEmpty() && lowQ.isEmpty())
                    highQ.add(currJob);
                 else if(currJob.getDuration() > 0)
                    midQ.add(currJob);
                 else { } //do nothing
             } //end if
             else if(!midQ.isEmpty()) {
                 currJob = midQ.poll();
                 temp = currJob.runJob(1);

                 time++;

                 for(int i = 0; i < jList.size(); i++) {
                    if(time == jList.get(i).getArrTime()) {
                        highQ.add(jList.get(i));
                        listRm.add(jList.get(i));
                    } //end if
                 } //end for

                 jList.removeAll(listRm);
                 listRm.clear();

                 if(currJob.getDuration() > 0 && highQ.isEmpty() && midQ.isEmpty() && lowQ.isEmpty())
                    midQ.add(currJob);
                 else if(currJob.getDuration() > 0)
                    lowQ.add(currJob);
                 else { } //do nothing
             } //end else if
             else { //run job in low queue
                currJob = lowQ.poll();
                temp = currJob.runJob(1);

                time++;

                  for(int i = 0; i < jList.size(); i++) {
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

             //time += temp.length(); //can't use qs since it may be greater than the duration of the job
        } //end while
        System.out.println("Multilevel Feedback, quantum " + qs + "\n");
        printJobs(jOut);
    } //end scheduleJobs

    public void printJobs(StringBuilder jOut[]) {
        for(int i = 0; i < jOut.length; i++) 
            System.out.println(jOut[i]);
    } //end printJobs
} //end MF