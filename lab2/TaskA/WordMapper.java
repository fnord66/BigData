import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// The mapper class
public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
  // The map method runs once for each line of text in the input file
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException
  {
    // Get the line as a simple Java String
    String line = value.toString();

    // Split the line into words
    for (String word : line.split("\\W+"))
    {
      if (word.length() > 0)
      {
        // Emit the word accompanied by an intermediate count of 1
        context.write(new Text(word), new IntWritable(1));
      }
    }
  }
}
