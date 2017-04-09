import java.util.List;
import java.util.ArrayList;

public class RoundRobin extends Schedule {
    private List<Job> jList;
    private int qs; //quantum size;
    public RoundRobin(List<Job> jList, int qs) {
        this.jList = jList;
        this.qs = qs;
    } //end RoundRobin(2)

    public void scheduleJobs() {

    }

} //end RoundRobin