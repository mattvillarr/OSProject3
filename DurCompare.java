import java.util.Comparator;

class DurCompare implements Comparator<Job> {

  @Override
  public int compare(Job a, Job b) {
    Integer a2 = new Integer(a.getDuration());
    Integer b2 = new Integer(b.getDuration());
    
    if(a2 - b2 == 0) {
      Integer a3 = new Integer(a.getArrTime());
      Integer b3 = new Integer(b.getArrTime());
      
      return a3.compareTo(b3);
    }
    return a2.compareTo(b2);
  } //end compare
} //end ASTARCompare