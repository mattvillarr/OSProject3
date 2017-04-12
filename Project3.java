import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Project3 {
    public static void main(String args[]) {
        Scanner scan = null;
    	
    	File infile = new File(args[0]);

        List<Job> jobList = new ArrayList<>();

        if(args.length < 2 || args.length > 3){
            System.out.println("ERROR! Incorrect number of arguments.");
            System.exit(0);
        } //end if
    	
    	try {
    		scan = new Scanner(infile);
    		
    		while(scan.hasNextLine()) {
    			
    			String temp = scan.nextLine();
    			String split[] = temp.split("\\t");

    			Job makeJob = new Job(split[0].charAt(0), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                jobList.add(makeJob);
    		}// end while
    	}// end try
    	catch(FileNotFoundException e){
    		e.printStackTrace();
    	}// end catch

        int quantum;

        switch(args[1]) {

            case "FCFS" :
                FCFS fSched = new FCFS(jobList);
                fSched.scheduleJobs();
                break;

            case "RR"   :
                if(args.length != 3) {
                    System.out.println("ERROR! Round Robin needs a quantum value passed from the command line.");
                    System.exit(0);
                } //end if
                
                if(Integer.parseInt(args[2]) <= 0) { //invalid quantum input
                    System.out.println("ERROR! Round Robin needs a valid quantum value!");
                    System.exit(0);
                } //end if

                quantum = Integer.parseInt(args[2]);
                RoundRobin RRSched = new RoundRobin(jobList, quantum);
                RRSched.scheduleJobs();
                break;

            case "SPN"  :
                SPN SPNSched = new SPN(jobList);
                SPNSched.scheduleJobs();
                break;

            case "SRT"  :
                SRT SRTSched = new SRT(jobList);
                SRTSched.scheduleJobs();
                break;

            case "HRRN" :
                HRRN HRRNSched = new HRRN(jobList);
                HRRNSched.scheduleJobs();
                break;
                
            case "MF"   :
                if(args.length != 3) {
                    System.out.println("ERROR! Multilevel Feedback needs a quantum value passed from the command line.");
                    System.exit(0);
                } //end if
                
                if(Integer.parseInt(args[2]) <= 0) { //invalid quantum input
                    System.out.println("ERROR! Multilevel Feedback needs a valid quantum value!");
                    System.exit(0);
                } //end if

                quantum = Integer.parseInt(args[2]);
                MF MFSched = new MF(jobList, quantum);
                MFSched.scheduleJobs();
                break;

            case "ALL"  :
                if(Integer.parseInt(args[2]) <= 0) { //invalid quantum input
                    System.out.println("ERROR! Invalid quantum value!");
                    System.exit(0);
                } //end if

                quantum = Integer.parseInt(args[2]);

                // I need to deep copy a list in java for each schedule algorithm

                FCFS fSched1 = new FCFS(jobList);
                fSched1.scheduleJobs();
                
                RoundRobin RRSched1 = new RoundRobin(jobList, quantum);
                RRSched1.scheduleJobs();

                SPN SPNSched1 = new SPN(jobList);
                SPNSched1.scheduleJobs();

                SRT SRTSched1 = new SRT(jobList);
                SRTSched1.scheduleJobs();

                HRRN HRRNSched1 = new HRRN(jobList);
                HRRNSched1.scheduleJobs();

                MF MFSched1 = new MF(jobList, quantum);
                MFSched1.scheduleJobs();
                break;     

            default     :
                System.out.println("ERROR! Incorrect schedule algorithm name\n Valid names are: FCFS|RR|SPN|SRT|HRRN|MF|ALL");  
                System.exit(0);                     
                
        } //end switch

    } //end main
} //end Run