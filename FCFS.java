import java.util.List;
import java.util.ArrayList;

public class FCFS extends Schedule {
    private List<Job> jList;
    //private Queue<Job> jQueue = new LinkedList<>();

    public FCFS(List<Job> jList) {
        this.jList = jList;
    } //end FCFS(1)

    public void scheduleJobs() {
        StringBuilder sb[] = new StringBuilder[jList.size()];
        for(int i = 0; i < sb.length; i++) 
            sb[i] = new StringBuilder("");

        int sbInx = 0;
        int time = 0;
        while(!jList.isEmpty()) {
            for(int i = 0; i < jList.size(); i++) {
                if(time == jList.get(i).getArrTime()) {
                    String temp = jList.get(i).runJob(); //run job to completion

                    for(int j = 0; j < sb.length; j++) {
                        if(j == sbInx)
                            sb[j].append(temp);
                        else {
                            for(int k = 0; k < temp.length(); k++)
                                sb[j].append(" ");   
                        } //end else      
                    } //end for
                    
                    sbInx++;

                    jList.remove(i);
                } //end if
            } //end for
            time++; //increment the time
        } //end while
        printJobs(sb);
    } //end scheduleJobs

    public void printJobs(StringBuilder jOut[]) {
        System.out.println("FCFS\n");
        for(int i = 0; i < jOut.length; i++) 
            System.out.println(jOut[i]);
    } //end printJobs
} //end FCFS


        