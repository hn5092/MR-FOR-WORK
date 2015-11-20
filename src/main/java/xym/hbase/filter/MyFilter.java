package xym.hbase.filter;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.exceptions.DeserializationException;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterBase;
import org.apache.hadoop.hbase.protobuf.generated.FilterProtos;
import org.junit.Test;


public class MyFilter extends FilterBase {
	private boolean filterRow = false;

	 public MyFilter() {
	  }

	@Override
	public ReturnCode filterKeyValue(Cell cell) throws IOException {
		if (new String(CellUtil.cloneValue(cell)).equals("1440x900")) {
			return ReturnCode.INCLUDE;
		} else {
			this.filterRow = true;
			return ReturnCode.SKIP;
		}
		// if(cell != null){
		// try {
		// throw new Exception("我进去了");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// return ReturnCode.INCLUDE;
	}

	@Override
	public boolean filterRow() throws IOException {
		return this.filterRow;
	}

	@Override
	public void reset() throws IOException {
		this.filterRow = false;
	}

	public static Filter createFilterFromArguments(
			ArrayList<byte[]> filterArguments) {
		return new MyFilter();
	}
	
	public byte[] toByteArray() {
		FilterProtos.MyFilter.Builder builder = FilterProtos.MyFilter.newBuilder();
		byte[] byteArray = builder.build().toByteArray();
		return byteArray;
	}

	public static MyFilter parseFrom(final byte[] pbBytes)
			throws DeserializationException {
		// There is nothing to deserialize. Why do this at all?
		// Just return a new instance.
		return new MyFilter();
	}

	boolean areSerializedFieldsEqual(Filter o) {
		if (o == this)
			return true;
		if (!(o instanceof MyFilter))
			return false;

		return true;
	}
//	@Test
//	public void testPB(){
//		FilterProtos.MyFilter.Builder builder = FilterProtos.MyFilter.newBuilder();
//		 byte[] byteArray = builder.build().toByteArray();
//	}
}
