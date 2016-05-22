import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// The reducer class
public class SumReducer extends Reducer<Text, Text, Text, Text>
{
  // The reduce method runs once for each key received after the shuffle phase
  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException
  { 
    // Emit the word accompanied by its final count
    for(Text t : values)
    {
      context.write(key, new Text(t));
    }
  }
}
