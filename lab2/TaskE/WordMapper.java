import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// The mapper class
public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException
  {
    String line = value.toString();

    /*
     * TODO:  Split the line into words and emit each word and its length.
     *        Hint: you may find the regular expression \W+ useful.
     */

    for (String word : line.split("\\W+"))
    {
      if (word.length() > 0)
      {
        context.write(new Text(word), new IntWritable(word.length()));
      }
    }
  }
}
