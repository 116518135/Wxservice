package com.wxservice.framework.web.action.support;

import javax.servlet.RequestDispatcher;

import org.apache.struts.action.ActionForward;

import com.wxservice.framework.components.json.JSONTool;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.AbstractStrutsForward;
import com.wxservice.framework.web.action.ActionContext;


public class DirectJspForward extends AbstractStrutsForward {
	public DirectJspForward(Object forward) {
		super(forward);
	}

	/**
	 * 导向struts forward
	 * 
	 * @param context
	 * @return
	 * @see com.wxservice.framework.web.action.IStrutsForward#findForward(com.wxservice.framework.web.action.ActionContext)
	 */
	public ActionForward findForward(ActionContext context) {
		try {

			RequestDispatcher dispatcher = context.getRequest()
					.getRequestDispatcher((String)forward);
			dispatcher.forward(context.getRequest(), context.getResponse());
			return null;
		} catch (Exception e) {
			SystemLogger.error("直接返回jsp文件发生错误:");
			return context.getMapping().findForward(
					SysFinal.STRUTS_SYSTEM_ERROR);
		}

	}
}
