package com.wxservice.framework.util;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.MultipartPostMethod;


public class WxserviceGradeup {
	public final static int BUFFER_SIZE = 8096;


	public static void gradeUp(HostServer host, String filename) throws Exception {
		System.out.println("开始升级");
		File file = new java.io.File(filename);
		if(!file.exists()){
			System.out.println("升级文件不存在");
			return;
		}
		HttpClient hc = new HttpClient();
		hc.getHostConfiguration().setHost(host.getServerAddress(),
				host.getPort());
		MultipartPostMethod mpm = new MultipartPostMethod(host.getPath()
				+ "/about.do");
		mpm.addParameter("method", "grade");
		mpm.addParameter("anonymous", "1");
		mpm
				.addParameter("formFile", file.getName(), new java.io.File(
						filename));
		hc.executeMethod(mpm);
		System.out.println("升级完成");
	}


	public static HostServer parse(String line) throws Exception {
		HostServer bean = new HostServer();
		String[] str = line.split("/");
		if (str.length == 2) {
			bean.setPath("/" + str[1]);
		}
		String[] str1 = str[0].split(":");
		bean.setServerAddress(str1[0]);
		if (str1.length == 2) {
			bean.setPort(MathUtil.parsetInt(str1[1], 80));
		}
		return bean;
	}

	public static void main(String[] args) {
		try {
			gradeAll(args);
		} catch (Exception e) {
			System.out.println("升级发生错误");
			e.printStackTrace();
		}

	}

	private static void gradeAll(String[] args) throws InterruptedException {
		String filename = "/Users/Mike/Documents/wxservice.rar";
		ZipCompressorByAnt.zip(filename);
		for (Object o : args) {
			String line = (String) o;
			gradeHost(filename, line);
		}
		System.out.println("暂停10秒后窗口自动关闭");
		Thread.sleep(100000);
	}
	
	

	public static void gradeHost(String filename, String line) {

		try {
			System.out.println("  ");
			System.out.println("  ");
			System.out.println("  ");
			System.out.println("  ");
			System.out.println("处理服务器:" + line);
			HostServer host = parse(line);
			gradeUp(host, filename);
			System.out.println( line+":升级成功,系统正在重新启动，请稍后访问!");
		} catch (Exception e) {
			System.out.println(line + ":无法升级,可能服务器没有启动或者系统有问题!");
			//e.printStackTrace();
		}
	}
	public static void reportDeploy(String filename, String line) {
		try {
			System.out.println("  ");
			System.out.println("  ");
			System.out.println("  ");
			System.out.println("  ");
			System.out.println("处理服务器:" + line);
			HostServer host = parse(line);
			reportDeploy(host, filename);
			System.out.println( line+":发布成功!");
		} catch (Exception e) {
			System.out.println(line + ":发布失败!");
			e.printStackTrace();
		}
	}
	public static void reportDeploy(HostServer host, String filename)
			throws Exception {
		System.out.println("开始发布");
		File file = new java.io.File(filename);
		if (!file.exists()) {
			System.out.println("报表文件不存在");
			throw new Exception("报表文件不存在");
		}
		HttpClient hc = new HttpClient();
		hc.getHostConfiguration().setHost(host.getServerAddress(),
				host.getPort());
		MultipartPostMethod mpm = new MultipartPostMethod(host.getPath()
				+ "/reporttool.do");
		mpm.addParameter("method", "upload");
		mpm.addParameter("anonymous", "1");
		mpm.addParameter("overDeploy", "1");
		mpm.addParameter("report", file.getName(), new java.io.File(filename));
		hc.executeMethod(mpm);
		System.out.println("发布完成");
	}
}
