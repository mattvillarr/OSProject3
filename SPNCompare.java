import java.util.Comparator;

class SPNCompare implements Comparator<Job> {

  @Override
  public int compare(Job a, Job b) {
    Integer a2 = new Integer(a.getDuration());
    Integer b2 = new Integer(b.getDuration());
    return a2.compareTo(b2);
  } //end compare
} //end ASTARCompare