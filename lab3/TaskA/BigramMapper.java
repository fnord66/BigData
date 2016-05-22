import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// The mapper class
public class BigramMapper extends Mapper<LongWritable, Text, FloatWritable, Text>
{
  // The query word to search for
  private static String QUERY_WORD = "computer";
  StringSimilarity s = new StringSimilarity();

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException
  {
    String line = value.toString();

    for(String word : line.split("\\W+"))
    {
      if(word.length() > 0)
      {
        /*
         * TODO: Calculate similarity rating and emit
         */
        float result = s.calculate(QUERY_WORD, word);
        context.write(new FloatWritable(result), new Text(word));

      }
    }
  }
}