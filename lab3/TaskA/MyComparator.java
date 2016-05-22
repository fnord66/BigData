import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableComparable;

public class MyComparator extends WritableComparator
{
  protected MyComparator()
  {
    super(FloatWritable.class, true);
  }

  @Override
  @SuppressWarnings("unchecked")
  public int compare(WritableComparable w1, WritableComparable w2)
  {
    return -w1.compareTo(w2);
  }
}

