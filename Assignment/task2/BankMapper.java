import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// The mapper class
public class BankMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
  // The map method runs once for each line of text in the input file
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException
  {
    // Get the line as a simple Java String
    String line = value.toString();
    int count = 0;
    String balanceBracket;

    // Split the line into words
    for (String attribute : line.split(";"))
    {
      attribute = attribute.replace("\"", "");
      
      if (count == 5)
      {
        int balance = Integer.parseInt(attribute);
        
        if(balance <= 500)
        {
          balanceBracket = "Low";
        }
        else if(balance > 1500)
        {
          balanceBracket = "High";
        }
        else
        {
          balanceBracket = "Medium";
        }
        // Emit the word accompanied by an intermediate count of 1
        context.write(new Text(balanceBracket), new IntWritable(1));
      }

      ++count;
    }
  }
}
