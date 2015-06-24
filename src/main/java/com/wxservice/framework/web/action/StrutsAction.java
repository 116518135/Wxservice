package com.wxservice.framework.web.action;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.springframework.util.ClassUtils;

import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.support.JspForward;

public class StrutsAction extends AbstractStrutsAction {

	protected HashMap<String,Method> service_methods = new HashMap<String, Method>();

	protected Class service_types[] = { ActionContext.class };

	public StrutsAction() {
		super();
	}

	
    /**
     * 生成一个Method方法
     * @param service
     * @param methodStr
     * @return
     */
	protected Object createMethod(Object service, String methodStr) {
		try {
			synchronized (service_methods) {
				String key = service.getClass().getName() + "." + methodStr;
				Method method = (Method) service_methods.get(key);
				if (method == null) {
					method = service.getClass().getMethod(methodStr,
							service_types);
					service_methods.put(key, method);
				}
				return (method);
			}
		} catch (Exception e) {
			SystemLogger.error("Method传入有错误,无法绑定Service!!!["+methodStr+"]",e);
			
			IStrutsForward forward = new JspForward(
					SysFinal.STRUTS_SYSTEM_ERROR);
			return forward;
		}
	}
    /**
     * 从spring配置文件中得到Manager
     * @param context
     * @return
     */
	public Object createService(ActionContext context) {
		String service = StringUtil.notNull(context.getForm().getService());
		if ("".equals(service)) {
			service = StringUtil.notNull(context.getMapping().getAttribute());
		}
		if ("".equals(service)) {
			SystemLogger.error("无法找到对应的Service!!!");
			IStrutsForward forward = new JspForward(
					SysFinal.STRUTS_SYSTEM_ERROR);
			return forward;
		}
		Object obj = WebUtil.getBean(service,context.getForm());
		if (obj == null) {
			SystemLogger.error("Service未在ServiceContext.xml中定义!!!");
			IStrutsForward forward = new JspForward(
					SysFinal.STRUTS_SYSTEM_ERROR);
			return forward;
		}
		return obj;
	}
     /**
      * 转发，调用Manager中的Method方法
      * @param context
      * @return  
      * @see com.wxservice.framework.web.action.AbstractStrutsAction#dispatch(com.wxservice.framework.web.action.ActionContext)
      */
	@Override
	public IStrutsForward dispatch(ActionContext context) {
		Object service = this.createService(context);
		if (service instanceof IStrutsForward) {
			IStrutsForward forward = (IStrutsForward) service;
			return forward;
		}
		String methodStr = context.getForm().getMethod();

		Object methodObj = this.createMethod(service, methodStr);
		if (methodObj instanceof IStrutsForward) {
			IStrutsForward forward = (IStrutsForward) methodObj;
			return forward;
		}

		try {
			Object args[] = { context };
			Method method = (Method) methodObj;
			long begin=System.currentTimeMillis();
			IStrutsForward forward = (IStrutsForward) method
					.invoke(service, args);
			long end=System.currentTimeMillis();
			StringBuffer msg=new StringBuffer();
			msg.append("[");
			msg.append(context.getRequest().getRemoteAddr());
			msg.append("]");
			msg.append(ClassUtils.getShortName(service.getClass()));
			msg.append(".");
			msg.append(methodStr);
			msg.append("(context):");
			msg.append(end-begin);
			SystemLogger.debug(msg.toString());
			
			return forward;
		} catch (Exception e) {
			SystemLogger.error("调用Serivce 方法出错", e);
			IStrutsForward forward = new JspForward(
					SysFinal.STRUTS_SYSTEM_ERROR);
			return forward;
		}

	}
}
