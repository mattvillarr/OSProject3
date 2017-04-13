
public class Job {
    private char name;
    private int arrTime;
    private int duration;

    public Job(char name, int arrTime, int duration) {
        this.name = name;
        this.arrTime = arrTime;
        this.duration = duration;
    } //end Job(3)

    public Job(Job copy) {
        this.name = copy.name;
        this.arrTime = copy.arrTime;
        this.duration = copy.duration;
    } //end Job(1)
    public char getName() {
        return name;
    } //end getName
    public int getArrTime() {
        return arrTime;
    } //end getArrTime
    public int getDuration() {
        return duration;
    } //end getDuration

    public void setName(char name) {
        this.name = name;
    } //end setName
    public void setArrTime(int arrTime) {
        this.arrTime = arrTime;
    }  //end arrTime
    public void setDuration(int duration) {
        this.duration = duration;
    } //end duration

    public double calcRR(int time) { //for HRRN
        int waitTime = time - arrTime;
        return (waitTime + duration) / duration;
    } //end calcRR

    public String runJob() { //non-preemptive run
        StringBuilder running = new StringBuilder();
        for(int i = 0; i < duration; i++) 
            running.append(name);

        return running.toString();
    } //end runJob(0)

    public String runJob(int quantum) { //preemptive run
        StringBuilder running = new StringBuilder();
        for(int i = 0; i < quantum; i++) {
            if(duration == 0)
                break;
            running.append(name);
            duration--;
        } //end for
    
        return running.toString();
    } //end runJob(1)
} //end Job