package com.wxservice.framework.start;

import javax.servlet.ServletException;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextListener;


public class SysRequestListener extends RequestContextListener {
	public static final String ACEGI_SECURITY_CONTEXT_KEY = "ACEGI_SECURITY_CONTEXT";

	/**
	 * 对销毁客户端请求进行监听  
	 */
	public void requestDestroyed(ServletRequestEvent arg0) {
         super.requestDestroyed(arg0);
		SysSecurityContextHolder.clearContext();

	}

	/**
	 * 初始化request
	 */
	public void requestInitialized(ServletRequestEvent arg0) {
		super.requestInitialized(arg0);
		
		HttpServletRequest request = (HttpServletRequest) arg0
				.getServletRequest();
	    SysSecurityContextHolder.setContext(generateNewContext());
		SysSecurityContext context =SysSecurityContextHolder.getContext();
		context.setRequest(request);
	}

	/**
	 * 生成一个context
	 * 
	 * @return
	 * @throws ServletException
	 */
	public SysSecurityContext generateNewContext() {
		SysSecurityContext context = new SysSecurityContext();

		return context;

	}

}
