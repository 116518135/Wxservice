package com.wxservice.framework.start;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.wxservice.framework.components.online.UserInfoTool;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.session.ClientSession;


public class SessionEventListener
    implements HttpSessionListener {

    public static final String module = SessionEventListener.class.getName();

    protected static long totalActiveSessions = 0;
    protected static long totalPassiveSessions = 0;



    public SessionEventListener() {}

    public static long getTotalActiveSessions() {
        return totalActiveSessions;
    }

    public static long getTotalPassiveSessions() {
        return totalPassiveSessions;
    }

    public static long getTotalSessions() {
        return totalActiveSessions + totalPassiveSessions;
    }


    public void sessionCreated(HttpSessionEvent event) {
    	Runtime.getRuntime().gc();
        HttpSession session = event.getSession();
        SystemLogger.info("会话已创建");


    }
	
    public void sessionDestroyed(HttpSessionEvent event) {
		Runtime.getRuntime().gc();
		SystemLogger.debug("会话已过期");
        try{
        HttpSession session = event.getSession();
        if(session==null){
        	return;
        }
        ClientSession client= (ClientSession) session.getAttribute(SysFinal.CLIENT_SESSION);
		if (client != null) {
		 UserInfoTool.removeOnlineUser(client.getSessionid());
			SystemLogger.info(client.getUsername() + "已经退出系统.");
		}
        }catch(Exception e){
        	e.printStackTrace(System.out);
        }


    }
}
