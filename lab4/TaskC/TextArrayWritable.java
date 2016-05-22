import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Writable;
import java.util.Arrays;

// A writable class for formatting collections of Text values
class TextArrayWritable extends ArrayWritable
{
	public TextArrayWritable(Class<? extends Writable> valueClass, Writable[] values)
	{
		super(valueClass, values);
	}

	public TextArrayWritable(Class<? extends Writable> valueClass)
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
