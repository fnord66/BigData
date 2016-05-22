import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// The mapper class
public class BankMapper extends Mapper<LongWritable, Text, Text, Text>
{
  // The map method runs once for each line of text in the input file
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException
  {
    // Get the line as a simple Java String
    String line = value.toString();
    int count = 0;
    String x = "200812";
    String y = "200910";
    String[] attribute = line.split("\t");
        // Emit the word accompanied by an intermediate count of 1
    if(attribute.length > 0 && (attribute[1].compareTo(x) == 0 || attribute[1].compareTo(y) == 0))
    {
        context.write(new Text(attribute[3]), new Text(attribute[2] + "," + attribute[1]));
    }
  }
}
