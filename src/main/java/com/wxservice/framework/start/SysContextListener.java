package com.wxservice.framework.start;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wxservice.framework.components.License.LicenseService;




public class SysContextListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event) {
		LicenseService.contextInitialized(event.getServletContext());
	}

	public void contextDestroyed(ServletContextEvent event) {

	}
}
