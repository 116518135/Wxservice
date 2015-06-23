package com.wxservice.framework.web.action.support;

import org.apache.struts.action.ActionForward;

import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.AbstractStrutsForward;
import com.wxservice.framework.web.action.ActionContext;

public class ExcelForward extends AbstractStrutsForward {
	public ExcelForward(Object forward) {
		super(forward);
	}

    /**
     * 直接返回excel字节
     * @param context
     * @return  
     * @see com.wxservice.framework.web.action.IStrutsForward#findForward(com.wxservice.framework.web.action.ActionContext)
     */
	public ActionForward findForward(ActionContext context) {
		if (forward instanceof byte[]) {
			byte[] bytes = (byte[]) forward;
			this.readerExcel(context.getResponse(), bytes);
			return null;
		} else {
			SystemLogger.error("Forward类型应该为byte[],而不是:" + forward.toString());
			return context.getMapping().findForward(
					SysFinal.STRUTS_SYSTEM_ERROR);
		}

	}
}
