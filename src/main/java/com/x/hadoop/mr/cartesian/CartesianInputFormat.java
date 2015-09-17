package com.x.hadoop.mr.cartesian;

import java.io.IOException;

import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.join.CompositeInputSplit;
import org.apache.hadoop.util.ReflectionUtils;

public class CartesianInputFormat extends FileInputFormat {
	public static final String LEFT_INPUT_FORMAT = "cart.left.inputformat";
	public static final String LEFT_INPUT_PATH = "cart.left.path";

	public static final String RIGHT_INPUT_FORMAT = "cart.right.inputformat";

	public static final String RIGHT_INPUT_PATH = "cart.right.path";

	public static void setLeftInputInfo (JobConf job , Class <? extends FileInputFormat> inputFormat, String inputPath){
		job.set(LEFT_INPUT_FORMAT, inputFormat.getCanonicalName());
		job.set(LEFT_INPUT_PATH, inputPath);
		
	}
	public static void setRightInputInfo (JobConf job , Class <? extends FileInputFormat> inputFormat, String inputPath){
		job.set(RIGHT_INPUT_FORMAT, inputFormat.getCanonicalName());
		job.set(RIGHT_INPUT_PATH, inputPath);
	}
	@Override
	public RecordReader getRecordReader(InputSplit split, JobConf conf,
			Reporter reporter) throws IOException {
		return new CartesianRecordReader((CompositeInputSplit) split, conf,
				reporter);
	}
	@Override
	public InputSplit[] getSplits(JobConf conf, int numSplits)
			throws IOException{
		//get the input split from both the left and right data sets
		InputSplit[] leftSplits = null;
		try {
			leftSplits = getInputSplits(conf, conf.get(LEFT_INPUT_FORMAT),conf.get(LEFT_INPUT_PATH), numSplits);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputSplit[] rightSplits = null;
		try {
			rightSplits = getInputSplits(conf, conf.get(RIGHT_INPUT_FORMAT),conf.get(RIGHT_INPUT_PATH), numSplits);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//创建一个composite inputsplit
		CompositeInputSplit[] coSplits = new CompositeInputSplit[leftSplits.length*rightSplits.length];
		int i = 0;
		for(InputSplit l: leftSplits){
			for(InputSplit r: rightSplits){
				coSplits[i] = new CompositeInputSplit(2);
				coSplits[i].add(l);
				coSplits[i].add(r);
				i++;
			}
		}
		return null;
		
	}
	private InputSplit[] getInputSplits(JobConf conf, String inputFormatClass,
			String inputPath, int numSplits) throws IOException, ClassNotFoundException {
		FileInputFormat inputFormat = (FileInputFormat)ReflectionUtils.newInstance(Class.forName(inputFormatClass), conf);
		inputFormat.setInputPaths(conf, inputPath);
		
		return inputFormat.getSplits(conf, numSplits);
	}

}
