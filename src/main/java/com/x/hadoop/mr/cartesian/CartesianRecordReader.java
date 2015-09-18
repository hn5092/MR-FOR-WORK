package com.x.hadoop.mr.cartesian;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.join.CompositeInputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.util.ReflectionUtils;

import com.sun.tools.classfile.Opcode.Set;

/**
 * 该方法使用的是老的mapreduce 的 包， 新的包待看过源码之后在进行重写
 * 
 * @author imad
 *
 * @param <K1>
 * @param <K2>
 * @param <V1>
 * @param <V2>
 */
public class CartesianRecordReader<K1, K2, V1, V2> extends
		RecordReader<Text, Text> {

	private RecordReader leftRR = null, rightRR = null;

	private FileInputFormat rightFIF;
	private JobConf rightConf;
	private InputSplit rightIS;
	private Reporter rightReporter;
	// help variables
	private K1 lkey;
	private V1 lvalue;
	private K2 rkey;
	private V2 rvalue;
	private boolean goToNextLeft = true, alldone = false;

	@SuppressWarnings("unchecked")
	public CartesianRecordReader(CompositeInputSplit split, JobConf conf,
			Reporter reporter) throws ClassNotFoundException, IOException {
		this.rightConf = conf;
		this.rightIS = split;
		this.rightReporter = reporter;
		// Create left record reader
		FileInputFormat leftFIF = (FileInputFormat) ReflectionUtils
				.newInstance(
						Class.forName(CartesianInputFormat.LEFT_INPUT_FORMAT),
						conf);
		leftRR = leftFIF.getRecordReader(split.get(0), conf, reporter);
		FileInputFormat rightFIF = (FileInputFormat) ReflectionUtils
				.newInstance(
						Class.forName(CartesianInputFormat.RIGHT_INPUT_FORMAT),
						conf);
		rightRR = rightFIF.getRecordReader(split.get(1), conf, reporter);
		lkey = (K1) this.leftRR.createKey();
		lvalue = (V1) this.leftRR.createValue();
		rkey = (K2) this.rightRR.createKey();
		rvalue = (V2) this.rightRR.createValue();

	}
	

	public boolean next(Text key, Text value) throws IOException {
		do{
			if (goToNextLeft) {
				//如果下一个key false 不存在 那么跳出循环
				if(!leftRR.next(lkey, lvalue)){
					alldone = true;
					break;
				}else{
					key.set(value.toString());
					goToNextLeft = alldone = false;
					this.rightRR = this.rightFIF.getRecordReader(this.rightIS, this.rightConf, this.rightReporter);
				}
			}
			if(rightRR.next(rkey, rvalue)){
				value.set(value.toString());
			}else{
				goToNextLeft = true;
			}
		}while(goToNextLeft);
		return !alldone;
	}


	@Override
	public void initialize(org.apache.hadoop.mapreduce.InputSplit split,
			TaskAttemptContext context) throws IOException,
			InterruptedException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Text getCurrentKey() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public float getProgress() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
