import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// The mapper class
public class WordMapper extends Mapper<LongWritable, Text, Text, Text>
{
  // The map method runs once for each line of text in the input file
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException
  {
    // Get the line as a simple Java String
    String line = value.toString();
    int count = 0;
    String education= "";
    String rest = "";
    String balance = "";
    // Split the line into words
    for (String word : line.split(";"))
    {
      if (word.length() > 0 && count == 3)
      {
        education = word + ","; 
      }
      if(word.length() > 0 && (count == 1 || count == 2 || count == 6))
      {
        if(count == 6)
        {
          rest = rest + word;
        }
        else
        {
          rest = rest + word + ", ";
        }
      }
      if(count == 5)
      {
        balance = word + ", ";
      }
      count++;
      if(count == 16)
      {
        rest = balance + rest;
        context.write(new Text(education.replace("\"","")), new Text(rest.replace("\"","")));
        count = 0;
      }
    }
  }
}
