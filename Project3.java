import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Project3 {
    public static void main(String args[]) {
        Scanner scan = null;
    	
    	File infile = new File(args[0]);

        List<Job> jobList = new ArrayList<>();
        List<Job> jobList2 = new ArrayList<>();
        List<Job> jobList3 = new ArrayList<>();
        List<Job> jobList4 = new ArrayList<>();
        List<Job> jobList5 = new ArrayList<>();
        List<Job> jobList6 = new ArrayList<>();

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
                Job makeJob2 = new Job(makeJob);
                Job makeJob3 = new Job(makeJob);
                Job makeJob4 = new Job(makeJob);
                Job makeJob5 = new Job(makeJob);
                Job makeJob6 = new Job(makeJob);
                jobList.add(makeJob);
                jobList2.add(makeJob2);
                jobList3.add(makeJob3);
                jobList4.add(makeJob4);
                jobList5.add(makeJob5);
                jobList6.add(makeJob6);
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
                
                RoundRobin RRSched1 = new RoundRobin(jobList2, quantum);
                RRSched1.scheduleJobs();

                SPN SPNSched1 = new SPN(jobList3);
                SPNSched1.scheduleJobs();

                SRT SRTSched1 = new SRT(jobList4);
                SRTSched1.scheduleJobs();

                HRRN HRRNSched1 = new HRRN(jobList5);
                HRRNSched1.scheduleJobs();

                MF MFSched1 = new MF(jobList6, quantum);
                MFSched1.scheduleJobs();
                break;     

            default     :
                System.out.println("ERROR! Incorrect schedule algorithm name\n Valid names are: FCFS|RR|SPN|SRT|HRRN|MF|ALL");  
                System.exit(0);                     
                
        } //end switch

    } //end main
} //end Run