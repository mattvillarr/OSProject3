import java.util.List;
import java.util.ArrayList;

public class HRRN extends Schedule {
    private List<Job> jList;

    public HRRN(List<Job> jList) {
        this.jList = jList;
    } //end HRRT(1)

    public void scheduleJobs() {
        StringBuilder jOut[] = new StringBuilder[jList.size()];
        for(int i = 0; i < jOut.length; i++) 
            jOut[i] = new StringBuilder("");

        List<Job> jWaiting = new ArrayList<>();
        
        int time = 0;
        while(!jList.isEmpty()) {
            int HRRIdx = -1;
            double hrr = -1;

            for(int i = 0; i < jList.size(); i++) {
                if(time >= jList.get(i).getArrTime()) {
                    double check = jList.get(i).calcRR(time);
                    if(Double.compare(check, hrr) > 0) {
                        hrr = check; //new max hrr
                        HRRIdx = i; //updates the index
                    }
                } //end if
            } //end for

            Job currJob = jList.get(HRRIdx);
            jList.remove(HRRIdx);

            String temp = currJob.runJob();

             for(int j = 0; j < jOut.length; j++) {
                if(j + 65 == (int) currJob.getName()) //compare ASCII
                    jOut[j].append(temp);
                else {
                    for(int k = 0; k < temp.length(); k++)
                        jOut[j].append(" ");   
                } //end else      
            } //end for
            time += currJob.getDuration();
        } //end while
        printJobs(jOut);
    } //end scheduleJobs

    public void printJobs(StringBuilder jOut[]) {
        System.out.println("Highest Response Ratio Next\n");
        for(int i = 0; i < jOut.length; i++)
            System.out.println(jOut[i]);
    } //end printJobs
} //end HRRT