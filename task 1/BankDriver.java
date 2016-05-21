import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

// The driver class
public class BankDriver
{
  public static void main(String[] args) throws Exception
  {
    if(args.length == 2)
    {
      // Instantiate a Job object for your job's configuration
      Job job = new Job();
      
      // Tell Hadoop which Jar file contains our code
      job.setJarByClass(BankDriver.class);
      
      // Set a job name which will appear in logs
      job.setJobName("Word Count");

      // Set input and output names from arguments
      FileInputFormat.setInputPaths(job, new Path(args[0]));
      FileOutputFormat.setOutputPath(job, new Path(args[1]));

      // Set mapper and reducer classes
      job.setMapperClass(BankMapper.class);
      job.setReducerClass(BankReducer.class);

      // Set key and value types for the mapper output
      job.setMapOutputKeyClass(Text.class);
      job.setMapOutputValueClass(IntWritable.class);

      // Set key and value types for the reducer (final) output
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(IntWritable.class);

      // Start the MapReduce job and wait for it to finish
      boolean success = job.waitForCompletion(true);
      System.out.printf("Job %s.", success ? "succeeded" : "failed");
    }
    else
    {
      System.out.println("Usage: WordCount <input dir> <output dir>");
    }
  }
}
