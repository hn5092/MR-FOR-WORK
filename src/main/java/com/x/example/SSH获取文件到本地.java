package com.x.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SCPInputStream;
import ch.ethz.ssh2.Session;

public class SSH获取文件到本地 {
	public static void main(String[] args) {
		Connection connection = new Connection("", 8088);
		try {
			connection.connect();
			boolean authenticateWithPassword = connection.authenticateWithPassword("", "");
			
			SCPClient client = new SCPClient(connection);
			//获取远程文件
			SCPInputStream scpInputStream = client.get("");
			FileUtils.moveDirectoryToDirectory(new File(""), new File(""), true);
			
			
			Session openSession = connection.openSession();
			openSession.execCommand("ls");
			InputStream stdout = openSession.getStdout();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}
}
