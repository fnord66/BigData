import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Writable;
import java.util.Arrays;

// A writable class for formatting collections of TupleWritable values
class TupleArrayWritable extends ArrayWritable
{
	public TupleArrayWritable(Class<? extends Writable> valueClass, Writable[] values)
	{
		super(valueClass, values);
	}

	public TupleArrayWritable(Class<? extends Writable> valueClass)
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
