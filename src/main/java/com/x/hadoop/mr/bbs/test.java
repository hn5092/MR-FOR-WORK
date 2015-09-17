package com.x.hadoop.mr.bbs;

public class test {
	public static void main(String[] args) {
		String value = "70	2014-09-21 07:25:05	60";
		System.out.println(value.substring(1,value.length()));
		String[] data = value.split("\t");
		System.out.println(data);
	}
}
