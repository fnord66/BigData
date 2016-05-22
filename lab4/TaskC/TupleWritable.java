import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.DataInput;
import java.io.DataOutput;

// The custom tuple class that stores a key and value as integer pairs
public class TupleWritable implements Writable
{
	public int key;
	public int value;
	
	public TupleWritable(int key, int value)
	{
		this.key = key;
		this.value = value;
	}
	
	public TupleWritable(int key)
	{
		this.key = key;
		this.value = 1;
	}
	
	public TupleWritable()
	{
		this.key = -1;
		this.value = 0;
	}
	
	public TupleWritable(TupleWritable other)
	{
		this.key = other.key;
		this.value = other.value;
	}
	
	// Define how to write the tuple out
	public void write(DataOutput out) throws IOException
	{
		out.writeInt(key);
		out.writeInt(value);
	}
	
	// Define how to read the tuple in
	public void readFields(DataInput in) throws IOException
	{
		key = in.readInt();
		value = in.readInt();
	}
	
	// Override the base Object class equality method
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (this.key == ((TupleWritable)o).key)
			return true;
			
		return false;
	}
	
	// Define how to has this tuple (for implementation with hash sets)
	@Override
	public int hashCode()
	{
		return Integer.valueOf(this.key).hashCode();
	}
	
	// Describe the serialised string representation of the tuple
	public String toString()
	{
		return "(" + Integer.toString(key) + " " + Integer.toString(value) + ")";
	}
}
