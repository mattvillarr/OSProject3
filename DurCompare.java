/******************************************************************************
* Matthew Villarreal (miv140130)
* CS 4348.002
* Project 3
*******************************************************************************
*******************************************************************************
*                               DurCompare.java
*
* This class overrides the compare(2) function used by the priority queue in
* the SRT and SPN scheduling algorithms. This override makes sure the priority
* queues are sorted by the duration variable in all Job objects. 
******************************************************************************/

import java.util.Comparator;

class DurCompare implements Comparator<Job> {

  @Override
  public int compare(Job a, Job b) {
    Integer a2 = new Integer(a.getDuration());
    Integer b2 = new Integer(b.getDuration());

    if(a2 - b2 == 0) { //both durations are equal
      Integer a3 = new Integer(a.getArrTime());
      Integer b3 = new Integer(b.getArrTime());
      
      return a3.compareTo(b3); //order by arrival time
    } //end if

    return a2.compareTo(b2); //order by duration time
  } //end compare
} //end DurCompare