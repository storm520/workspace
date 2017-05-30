package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.until.FtpUtil;

/**
 *@title: testFTP.java
 * @package com.taotao.controller
 * @description: TODO
 * @copyright: 技术部 (c) 
 * @author storm
 * @date 2017年4月25日
 * @version 1.0
 */
public class testFTP{
	@Test
	public void testFtp() throws Exception {
		//1、创建ftp服务器
		FTPClient ftpClient = new FTPClient();
		//ftp默认的端口是21.
		ftpClient.connect("192.168.5.129",21);
		//2、登录ftp服务器
		ftpClient.login("ftpuser", "ftpuser");
		//3、读取本地文件
		FileInputStream fileInputStream = new FileInputStream(new File("D:\\image\\ui.jpg"));
		//4、上传文件
		//1）指定上传目录
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//2）指定文件类型
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		//第一个参数是远处程的文件名称，第二个参数是文件流
		ftpClient.storeFile("hello.jpg", fileInputStream);
		//5、退出登入
		ftpClient.logout();
	}
	//封装成工具类来测试
	
	@Test
	public void testftpUtil() throws Exception{
		FileInputStream fileInputStream = new FileInputStream(new File("D:\\image\\ui.jpg"));
		FtpUtil.uploadFile("192.168.5.129", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images", "2017/4/24", "hello.jpg", fileInputStream);
	}
	
}
