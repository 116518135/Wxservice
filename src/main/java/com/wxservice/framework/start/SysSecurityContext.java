package com.wxservice.framework.start;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class SysSecurityContext implements  Serializable{
	HttpServletRequest request;
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	

}
