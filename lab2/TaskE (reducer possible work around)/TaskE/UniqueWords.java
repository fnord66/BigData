import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

// The driver class
public class UniqueWords
{
  public static void main(String[] args) throws Exception
  {
    if(args.length == 2)
    {
      Job job = new Job();
      job.setJarByClass(UniqueWords.class);
      job.setJobName("Unique Words");

      // Set input and output paths from command-line arguments
      FileInputFormat.setInputPaths(job, new Path(args[0]));
      FileOutputFormat.setOutputPath(job, new Path(args[1]));

      // Set mapper and reducer classes
      job.setMapperClass(WordMapper.class);
      job.setReducerClass(UniqueReducer.class);

      // Set key and value types for the mapper output
      job.setMapOutputKeyClass(Text.class);
      job.setMapOutputValueClass(IntWritable.class);

      // Set types for keys and values
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(IntWritable.class);

      boolean success = job.waitForCompletion(true);
      System.out.printf("Job %s.", success ? "succeeded" : "failed");
    }
    else
    {
      System.out.println("Usage: UniqueWords <input dir> <output dir>");
    }
  }
}
