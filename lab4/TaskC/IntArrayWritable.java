import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Writable;
import java.util.Arrays;

// A writable class for formatting collections of IntWritable values
class IntArrayWritable extends ArrayWritable
{
	public IntArrayWritable(Class<? extends Writable> valueClass, Writable[] values)
	{
		super(valueClass, values);
	}

	public IntArrayWritable(Class<? extends Writable> valueClass)
	{
		super(valueClass);
	}


	// Specify the formatting to use the standard Array output format
	@Override
	public String toString()
	{
		return Arrays.toString(get());
	}	
}
