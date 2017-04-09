public class Job {
    private char name;
    private int arrTime;
    private int duration;

    public Job(char name, int arrTime, int duration) {
        this.name = name;
        this.arrTime = arrTime;
        this.duration = duration;
    } //end Job(3)

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

    public void jobRan() { //reduces duration after being run for a time slice 
        duration--;
    } //end jobRan
} //end duration