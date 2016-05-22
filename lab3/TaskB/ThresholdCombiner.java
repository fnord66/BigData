import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// The combiner class
public class ThresholdCombiner extends Reducer<FloatWritable, Text, FloatWritable, Text>
{
  /*
   * TODO:  Implement the combiner
   */
  public void reduce(FloatWritable key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException
      {
      	for(Text word: values)
      	{
      		if(key.get() > 0.5)
      		{
      			context.write(key, word);
      		}
      	}
      }
}
