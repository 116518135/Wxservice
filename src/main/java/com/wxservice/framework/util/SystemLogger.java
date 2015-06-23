package com.wxservice.framework.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.wxservice.database.po.system.Tsyslog;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.start.SysSecurityContextHolder;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.framework.web.session.ClientSession;

public class SystemLogger {
	public static final String module = "ucard.logger";

	/**
	 * 开发, debug级别
	 *    
	 * @param debugStr
	 */
	public static void debug(String debugStr) {
		Logger log = Logger.getLogger("debug");
		log.log(module, Level.DEBUG, debugStr, null);
	}
	public static void info(String infoStr) {
		Logger log = Logger.getLogger("info");
		log.log(module, Level.INFO, infoStr, null);
	}
	/**
	 * 信息级别
	 * 
	 * @param infoStr
	 */
	public static void infoDb(String infoStr) {
		Logger log = Logger.getLogger("info");
		log.log(module, Level.INFO, infoStr, null);
		log2Db("info", infoStr);
	}
	public static void infoDb(String infoStr,String add1) {
		Logger log = Logger.getLogger("info");
		log.log(module, Level.INFO, infoStr, null);
		log2Db("info",infoStr,add1);
	}
	public static void error(String errStr, Throwable e) {
		Logger log = Logger.getLogger("system");
		log.log(module, Level.ERROR, errStr, e);
	}
	public static void error(String errStr) {
		error(errStr,null);
	}	
	/**
	 * 错误级别
	 * 
	 * @param errStr
	 * @param e
	 */

	public static void errorDb(String errStr, Throwable e) {
		Logger log = Logger.getLogger("system");
		log.log(module, Level.ERROR, errStr, e);
		log2Db("error", errStr);
	}
	public static void errorDb(String errStr,String add1, Throwable e) {
		Logger log = Logger.getLogger("system");
		log.log(module, Level.ERROR, errStr, e);
		log2Db("error", errStr,add1);
	}
   
	/**
	 * 错误级别
	 * @param errStr
	 */ 
	public static void errorDb(String errStr) {
		Logger log = Logger.getLogger("system");
		log.log(module, Level.ERROR, errStr, null);
		log2Db("error", errStr);
	}
    /**
     * 警告级别
     * @param warnStr
     */
	public static void warn(String warnStr) {
		Logger log = Logger.getLogger("warn");
		log.log(module, Level.WARN, warnStr, null);
	}
	
	  /**
     * 警告级别
     * @param warnStr
     */
	public static void warnDb(String warnStr) {
		Logger log = Logger.getLogger("warn");
		log.log(module, Level.WARN, warnStr, null);
		log2Db("warn", warnStr);
	}

	public static void log2Db(String priority, String message) {
		log2Db(priority,message,"");

	}
	
	public static void log2Db(String priority, String message,String add1) {
		try {
			if(StringUtils.isBlank(message)){
				return;
			}
			if(message.length()>1000){
				message=message.substring(0,1000);
			}
			HttpServletRequest request = SysSecurityContextHolder.getContext()
					.getRequest();
			ClientSession client = WebUtil.getClientSession(request);
			String action = request.getParameter("method");
			IDao dao = WebUtil.getDao(request);
			Tsyslog log = new Tsyslog();
			log.setPriority(priority);
			log.setLogdate(new Date());
			log.setIp(request.getRemoteAddr());
			log.setAction(action);
			log.setMessage(message);
			log.setAdd1(add1);
			if (client != null) {
				log.setUsername(client.getUsername());
				log.setCmpname(client.getCmpname());
				log.setCreatetbscmpid(client.getCmpid());
				log.setCreatetbsuserid(client.getUserid());
			}
			Object form = request.getAttribute(SysFinal.STRUTS_FORM);
			if (form instanceof BaseForm) {
				BaseForm fm = (BaseForm) form;
				log.setModule(fm.getModuleTitle());
			}
			dao.addSave(log);
		} catch (Exception e) {
			SystemLogger.error("保存操作日志发生错误",e);
		}

	}
}
