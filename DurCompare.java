import java.util.Comparator;

class DurCompare implements Comparator<Job> {

  @Override
  public int compare(Job a, Job b) {
    Integer a2 = new Integer(a.getDuration());
    Integer b2 = new Integer(b.getDuration());
    if(a2 - b2 == 0) {
      Integer a3 = new Integer(a.getArrTime());
      Integer b3 = new Integer(b.getArrTime());
      System.out.println("a3 = " + a3 + "b3 = " + b3);
      return b3.compareTo(a3);
    }
    return a2.compareTo(b2);
  } //end compare
} //end ASTARCompare