import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Run {
    public static void main(String args[]) {
        Scanner scan = null;
    	
    	File infile = new File(args[0]);

        List<Job> jobList = new ArrayList<>();
    	
    	try {
    		scan = new Scanner(infile);
    		
    		while(scan.hasNextLine()) {
    			
    			String temp = scan.nextLine();
    			String split[] = tempString.split("\\t");

    			Job makeJob = new Job(split[0], (int) split[1], (int) split[2]);
                jobList.add(makeJob);
    		}// end while
    	}// end try
    	catch(FileNotFoundException e){
    		e.printStackTrace();
    	}// end catch

        switch(args[1]) {

            case "FCFS" :
                FCFS fSched = new FCFS(jobList);
                fSched.scheduleJobs();
                break;

            case "RR"   :
                if(args[2].isEmpty() || (int) args[2] <= 0) { //invalid quantum input
                    System.out.println("ERROR! Round Robin needs a valid quantum value!");
                    System.exit(0);
                } //end if

                int quantum = args[2];
                RoundRobin RRSched = new RoundRobin(jobList, quantum);
                break;

            case "SPN"  :
                break;
            case "SRT"  :
                break;
            case "HRRN" :
                break;
            case "MF"   :
                break;
            case "ALL"  :
                break;     
            default     :
                System.out.println("ERROR! Incorrect schedule algorithm name\n Valid names are: FCFS|RR|SPN|SRT|HRRN|MF|ALL");  
                System.exit(0);                     
                
        } //end switch

    } //end main
} //end Run