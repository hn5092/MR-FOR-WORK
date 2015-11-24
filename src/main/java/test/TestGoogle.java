package test;

import com.google.common.collect.MinMaxPriorityQueue;
import com.google.common.collect.MinMaxPriorityQueue.Builder;

public class TestGoogle {
	public static void main(String[] args) {
		Builder<Comparable> maximumSize = MinMaxPriorityQueue.maximumSize(10);
		MinMaxPriorityQueue<Comparable> create = maximumSize.create();
		create.add(10);
		create.add(12);
		create.add(16);
		create.add(14);
		create.add(15);
		create.add(18);
		create.add(12);
		create.add(104);
		for(Comparable c : create){
			System.out.println(c);
		}
		System.out.println(create.size());
	
	}
}
