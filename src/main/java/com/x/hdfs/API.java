//package com.x.hdfs;
//
//import java.io.IOException;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FSDataInputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.util.RunJar;
//import org.junit.Test;
//
//public class API {
//	@Test
//	public void testGet() {
//		Configuration configuration = new Configuration();
//		configuration.set("fs.defaultFS", "hdfs://ns1");
//		configuration.set("dfs.nameservices", "ns1");
//		configuration.set("dfs.ha.namenodes.ns1", "nn1,nn2");
//		configuration.set("dfs.namenode.rpc-address.ns1.nn1",
//				"192.168.80.101:9000");
//		configuration.set("dfs.namenode.rpc-address.ns1.nn2",
//				"192.168.80.102:9000");
//		configuration
//				.set("dfs.client.failover.proxy.provider.ns1",
//						"org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
//
//		try {
//			FileSystem fileSystem = FileSystem.get(configuration);
//			FSDataInputStream open = fileSystem.open(new Path(
//					"/cc/data/1/20140814"));
//			RunJar.main(args);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//}
