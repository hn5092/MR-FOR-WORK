package com.x.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.connection.ChannelInputStream;
import com.sshtools.j2ssh.connection.ChannelOutputStream;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.sftp.SftpFile;

/**
 * <dependency>
	<groupId>sshtools</groupId>
	<artifactId>j2ssh-core</artifactId>
	<version>0.2.9</version>
</dependency>
 * @author imad
 *
 */
public class ssh读取远程服务器文件 {
	public static void main(String[] args) {
//		所需jar包：j2ssh-core-0.2.2.jar
//		java代码：
		SshClient client=new SshClient();
		        try{
		            client.connect("此处是Linux服务器IP");
		            //设置用户名和密码
		            PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();
		            pwd.setUsername("root");
		            pwd.setPassword("123456");
		            int result=client.authenticate(pwd);
		            if(result==AuthenticationProtocolState.COMPLETE){//如果连接完成
		                System.out.println("==============="+result);
		                SessionChannelClient openSessionChannel = client.openSessionChannel();
		                boolean executeCommand = openSessionChannel.executeCommand("ls");
		               ChannelInputStream inputStream = openSessionChannel.getInputStream();
		               inputStream.read();
		                List<SftpFile> list = client.openSftpClient().ls("/etc/mail/");
		                for (SftpFile f : list) {
		                    System.out.println(f.getFilename());  
		                    System.out.println(f.getAbsolutePath());
		                    if(f.getFilename().equals("aliases")){
		                        OutputStream os = new FileOutputStream("d:/mail/"+f.getFilename());
		                        client.openSftpClient().get("/etc/mail/aliases", os);
		                        //以行为单位读取文件start
		                        File file = new File("d:/mail/aliases");
		                        BufferedReader reader = null;
		                        try {
		                            System.out.println("以行为单位读取文件内容，一次读一整行：");
		                            reader = new BufferedReader(new FileReader(file));
		                            String tempString = null;
		                            int line = 1;//行号
		                            //一次读入一行，直到读入null为文件结束
		                            while ((tempString = reader.readLine()) != null) {
		                                //显示行号
		                                System.out.println("line " + line + ": " + tempString);
		                                line++;
		                            }
		                            reader.close();
		                        } catch (IOException e) {
		                            e.printStackTrace();
		                        } finally {
		                            if (reader != null) {
		                                try {
		                                    reader.close();
		                                } catch (IOException e1) {
		                                }
		                            }
		                        }
		                        //以行为单位读取文件end
		                    }
		                }
		            }
		        }catch(IOException e){
		            e.printStackTrace();
		        }
	}

}

