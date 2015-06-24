package com.wxservice.framework.start;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wxservice.framework.util.WebUtil;

public class MyContextLoaderListener extends ContextLoaderListener {
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
		.getRequiredWebApplicationContext(event.getServletContext());
		WebUtil.setWebApplicationContext(webApplicationContext);
	}

}
