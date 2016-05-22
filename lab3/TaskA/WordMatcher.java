import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

// The driver class
public class WordMatcher
{
  public static void main(String[] args) throws Exception
  {
    if(args.length == 2)
    {
      // Instantiate a Job object for your job's configuration
      Job job = new Job();

      job.setJarByClass(WordMatcher.class);
      job.setJobName("Word Matcher");

      // Set input and output paths from command-line arguments
      FileInputFormat.setInputPaths(job, new Path(args[0]));
      FileOutputFormat.setOutputPath(job, new Path(args[1]));

      // Set mapper and reducer classes
      job.setMapperClass(BigramMapper.class);
      job.setReducerClass(BestMatchReducer.class);

      /*
       * TODO: Set types for keys and values
       */

      job.setMapOutputKeyClass(FloatWritable.class);
      job.setMapOutputValueClass(Text.class);

      job.setOutputKeyClass(FloatWritable.class);
      job.setOutputValueClass(Text.class);

      job.setSortComparatorClass(MyComparator.class);

      boolean success = job.waitForCompletion(true);
      System.out.printf("Job %s.", success ? "succeeded" : "failed");
    }
    else
    {
      System.out.println("Usage: WordMatcher <input dir> <output dir>");
    }
  }
}
