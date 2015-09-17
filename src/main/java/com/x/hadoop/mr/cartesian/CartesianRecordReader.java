package com.x.hadoop.mr.cartesian;

import java.io.IOException;

import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.join.CompositeInputSplit;

public class CartesianRecordReader implements RecordReader {
	
	private RecordReader leftRR = null,rightRR = null;
	
	public CartesianRecordReader(CompositeInputSplit split, JobConf conf,
			Reporter reporter) {
		// TODO Auto-generated constructor stub
	}

	public boolean next(Object key, Object value) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	public Object createKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object createValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getPos() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public float getProgress() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
