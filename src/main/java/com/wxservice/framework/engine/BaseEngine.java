package com.wxservice.framework.engine;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ClassUtils;

import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.engine.support.SysToken;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.action.support.JsonForward;
import com.wxservice.framework.web.action.support.JspForward;
import com.wxservice.framework.web.action.support.MethodForward;
import com.wxservice.framework.web.action.support.PdfForward;
import com.wxservice.framework.web.action.support.TextForward;
import com.wxservice.service.NameManager;
import com.wxservice.service.ServiceConfig;


public class BaseEngine {

	public SysToken token = SysToken.getInstance();
	public IDao dao = null;
	public NameManager nameManager=null;
	public ServiceConfig serviceConfig=null;
	public static Map<String, String> methodChinese = new HashMap<String, String>();

	/**
	 * 注册中文方法名，写log的时候自动取
	 * 
	 * @param method
	 * @param chinese
	 */
	public static void registMethodChinese(String method, String chinese) {
		methodChinese.put(method, chinese);
	}

	public String getMethodChinese(String method) {
		String chinese = methodChinese.get(method);
		if (StringUtil.isBlank(chinese)) {
			SystemLogger.warn(method + "方法名在"
					+ ClassUtils.getShortName(this.getClass()) + "中没有注册!");
			return "操作";
		}
		return chinese;
	}

	/**
	 * 返回一个jsp forward
	 * 
	 * @param page
	 * @return
	 */
	public IStrutsForward forwardJsp(String page) {
		return new JspForward(page);
	}

	/**
	 * 返回一个JSON的forward
	 * 
	 * @param map
	 * @return
	 */
	public IStrutsForward forwardJson(Object map) {
		return new JsonForward(map);
	}
	
	public IStrutsForward forwardText(String text) {
		return new TextForward(text);
	}

	public IStrutsForward forwardPdf(Object o) {
		return new PdfForward(o);
	}
	/**
	 * 返回一个方法forward
	 * 
	 * @param method
	 * @return
	 */
	public IStrutsForward forwardMethod(String method) {
		return new MethodForward(method);
	}

	/**
	 * 设置dao
	 * 
	 * @param dao
	 */
	public void setDao(IDao dao) {
		this.dao = dao;
	}



	/**
	 * 处理操作失败的时候情形
	 * 
	 * @param context
	 * @param e
	 * @param actionName
	 * @param isWriteRequest
	 * @param entity
	 */
	protected void processFailure(ActionContext context, Exception e,
			boolean isWriteRequest, String messageEx) {
		String actionName = this
				.getMethodChinese(context.getForm().getMethod());
		StringBuffer msg = new StringBuffer("");
		msg.append(actionName);
		if(StringUtil.isNotBlank(context.getForm().getModuleTitle())){
			msg.append(context.getForm().getModuleTitle());
		}
		if (StringUtil.isNotBlank(messageEx)) {
			msg.append("[");
			msg.append(messageEx);
			msg.append("]");
		}
		if (e != null) {// 操作是成功的,没有出现异常
			msg.append("失败.");
			if (!"".equals(e.getMessage()) && null != e.getMessage()) {
				msg.append("[");
				msg.append(e.getMessage());
				msg.append("]");
			}
		}
		if (isWriteRequest) {
			context.getRequest().setAttribute(SysFinal.WEB_MESSAGE,
					msg.toString());
		}
		SystemLogger.errorDb(msg.toString(), e);
	}

	/**
	 * 处理操作成功的时候的情形
	 * 
	 * @param context
	 * @param actionName
	 * @param isWriteRequest
	 * @param entity
	 */
	protected void processSuccess(ActionContext context,
			boolean isWriteRequest, String messageEx) {
		String actionName = this
				.getMethodChinese(context.getForm().getMethod());
		StringBuffer msg = new StringBuffer("");
		msg.append(actionName);
		if(StringUtil.isNotBlank(context.getForm().getModuleTitle())){
			msg.append(context.getForm().getModuleTitle());
		}
		
		if (StringUtil.isNotBlank(messageEx)) {
			msg.append("[");
			msg.append(messageEx);
			msg.append("]");
		}
		msg.append("成功.");

		if (isWriteRequest) {
			context.getRequest().setAttribute(SysFinal.WEB_MESSAGE,
					msg.toString());
		}
		SystemLogger.infoDb(msg.toString());
	}

	protected void processFailure(ActionContext context, Exception e,
			boolean isWriteRequest) {
		this.processFailure(context, e, isWriteRequest, "");
	}

	protected void processSuccess(ActionContext context, boolean isWriteRequest) {
		this.processSuccess(context, isWriteRequest, "");
	}


	public void setNameManager(NameManager nameManager) {
		this.nameManager = nameManager;
	}

	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	public void setServiceConfig(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}
}
