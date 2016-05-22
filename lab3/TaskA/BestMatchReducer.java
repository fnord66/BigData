import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// The reducer class
public class BestMatchReducer extends Reducer<FloatWritable, Text, Text, FloatWritable>
{
  /*
   * TODO: Declare member variable here
   */
  boolean first = false;

  @Override
  public void reduce(FloatWritable key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException
  {
    /*
     * TODO: Emit only the first result (use the member variable to keep
     *       track of things)
     */
    for(Text word : values)
    {
      if(first == false)
      {
        context.write(new Text(word), key);
        first = true;
      }
    }

  }
}

