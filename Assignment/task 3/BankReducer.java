import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// The reducer class
public class BankReducer extends Reducer<Text, Text, Text, Text>
{
  // The reduce method runs once for each key received after the shuffle phase
  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException
  {
    String[] temp = new String[2];

    String month;
    String count;

    for(Text t : values)
    {
      temp = values.split(",");
      if(temp[1].compareTo(month) != 0)
      {
        
      }

      context.write(key, new Text(t));
    }
  }
}
