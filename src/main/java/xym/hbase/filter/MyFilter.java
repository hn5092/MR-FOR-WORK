package xym.hbase.filter;

import java.io.IOException;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.filter.FilterBase;

public class MyFilter extends FilterBase {

	@Override
	public ReturnCode filterKeyValue(Cell ignored) throws IOException {
		if()
		return super.filterKeyValue(ignored);
	}
}
