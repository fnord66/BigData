import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// The reducer class
public class UniqueReducer extends Reducer<Text, IntWritable, Text, IntWritable>
{
  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException
  {
    /*
     * TODO:  Count the number of values and, if the count is 1, emit the word
     *        and its length.
     */

    int wordCount = 0;
    IntWritable output = null;

    for (IntWritable value : values)
    {
      wordCount++;
    }

    if(wordCount == 1)
    {
    	context.write(key, values.iterator().next());
    }
  }
}
