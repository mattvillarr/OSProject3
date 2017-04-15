/******************************************************************************
* Matthew Villarreal (miv140130)
* CS 4348.002
* Project 3
*******************************************************************************
*******************************************************************************
*                               Project3.java
*
* This program initializes the list of job objects and any necessary copies 
* from a given .txt file to be scheduled by one of the six scheduling algoritms.
* The algorithm to be used is given as a command-line argument with a quantum 
* size as necessary. There is also an "ALL" option that will run all six 
* algorithms at once.
******************************************************************************/

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Project3 {
    public static void main(String args[]) {
        Scanner scan = null;
    	
    	File infile = new File(args[0]);

        //List of job initialization with necessary copies for the "ALL" option
        List<Job> jobList = new ArrayList<>();
        List<Job> jobList2 = new ArrayList<>();
        List<Job> jobList3 = new ArrayList<>();
        List<Job> jobList4 = new ArrayList<>();
        List<Job> jobList5 = new ArrayList<>();
        List<Job> jobList6 = new ArrayList<>();

        if(args.length < 2 || args.length > 3) { //checks for a valid number of arguments
            System.out.println("ERROR! Incorrect number of arguments.");
            System.exit(0);
        } //end if
    	
    	try {
    		scan = new Scanner(infile);
    		
            //parse job input file
    		while(scan.hasNextLine()) {
    			
    			String temp = scan.nextLine();
    			String split[] = temp.split("\\t");

                //job object initialization and necessary copies
    			Job makeJob = new Job(split[0].charAt(0), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                Job makeJob2 = new Job(makeJob);
                Job makeJob3 = new Job(makeJob);
                Job makeJob4 = new Job(makeJob);
                Job makeJob5 = new Job(makeJob);
                Job makeJob6 = new Job(makeJob);

                //add jobs to lists
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

        int quantum; //necessary if quantum size is passed from command-lines

        //determine correct algorithm to run
        switch(args[1]) {

            case "FCFS" : //First-Come-First-Serve
                FCFS fSched = new FCFS(jobList);
                fSched.scheduleJobs();
                break;

            case "RR"   : //Round-Robin
                if(args.length != 3) { //RR needs a quantum value to run
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

            case "SPN"  : //Shortest Process Next
                SPN SPNSched = new SPN(jobList);
                SPNSched.scheduleJobs();
                break;

            case "SRT"  : //Shortest Remaining Time
                SRT SRTSched = new SRT(jobList);
                SRTSched.scheduleJobs();
                break;

            case "HRRN" : //Highest Response Ration Next
                HRRN HRRNSched = new HRRN(jobList);
                HRRNSched.scheduleJobs();
                break;
                
            case "MF"   : //Multilevel Feedback 
                quantum = 1; //quantum is always 1
                MF MFSched = new MF(jobList, quantum);
                MFSched.scheduleJobs();
                break;

            case "ALL"  : //run all six scheduling algorithms
                if(args.length != 3) { //RR needs a quantum value to run
                    System.out.println("ERROR! A quantum value needs to be passed from the command line.");
                    System.exit(0);
                } //end if

                if(Integer.parseInt(args[2]) <= 0) { //invalid quantum input
                    System.out.println("ERROR! Invalid quantum value!");
                    System.exit(0);
                } //end if

                quantum = Integer.parseInt(args[2]);

                //Run FCFS
                FCFS fSched1 = new FCFS(jobList);
                fSched1.scheduleJobs();
                
                //Run RR
                RoundRobin RRSched1 = new RoundRobin(jobList2, quantum);
                RRSched1.scheduleJobs();

                //Run SPN
                SPN SPNSched1 = new SPN(jobList3);
                SPNSched1.scheduleJobs();

                //Run SRT
                SRT SRTSched1 = new SRT(jobList4);
                SRTSched1.scheduleJobs();

                //Run HRRN
                HRRN HRRNSched1 = new HRRN(jobList5);
                HRRNSched1.scheduleJobs();

                //Run MF
                MF MFSched1 = new MF(jobList6, quantum);
                MFSched1.scheduleJobs();
                break;     

            default     : //invalid scheduling algorithm name passed
                System.out.println("ERROR! Incorrect schedule algorithm name\n Valid names are: FCFS|RR|SPN|SRT|HRRN|MF|ALL");  
                System.exit(0);                     
        } //end switch

    } //end main
} //end Run