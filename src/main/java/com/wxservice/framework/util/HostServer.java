package com.wxservice.framework.util;

public class HostServer {
	String serverAddress;
	int port = 80;
	String path="";
	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String toString(){
		StringBuffer buf=new StringBuffer();
		buf.append("地址：");
		buf.append(serverAddress);
		buf.append("端口：");
		buf.append(port);
		buf.append("路径：");
		buf.append(path);
		return buf.toString();
	}

}
