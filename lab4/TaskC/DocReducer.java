import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.ArrayList;

// The reducer class
public class DocReducer extends Reducer<Text, IntWritable, Text, Text>
{
	// The reduce method runs once for each key received after the shuffle phase
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
	  throws IOException, InterruptedException
	{
		// Add each value to a collection
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		StringBuilder words = new StringBuilder();
		
		for (IntWritable s : values)
		{
			if(!m.containsKey(s.get()))
			{
				m.put(s.get(), 1);
			}
			else
			{
				m.put(s.get(), m.get(s.get())+1);
			}

		}

		words.append("[");

		for(Map.Entry<Integer, Integer> entry : m.entrySet())
		{
			Integer mkey = entry.getKey();
			Integer value = entry.getValue();

			words.append("(" + mkey + "," + value + ")");
		}

		words.append("]");

		String a = words.toString();
		
		// Emit the doc ID as key and the grouped words as a serializable collection
		context.write(key, new Text(a));
	}
}
