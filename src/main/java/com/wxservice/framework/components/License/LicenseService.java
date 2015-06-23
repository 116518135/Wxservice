package com.wxservice.framework.components.License;

import javax.servlet.ServletContext;

import com.wxservice.framework.engine.support.SysConfig;

public class LicenseService {

	

	public static final String systemTitle = "systemTitle";
	

	public static String systemPath = null;

	
	public static void contextInitialized(ServletContext context) {
		systemPath = context.getRealPath("");
		
		StringBuffer webname = new StringBuffer();
		String str = SysConfig.getStringConfig("wxservice.software.name");
		try {
			webname.append(new String(str.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace(System.out);
			webname.append("");
		}
		
		context.setAttribute(systemTitle, webname.toString());
		
	}

	

}
