package com.wxservice.framework.web.action;

import org.apache.struts.action.ActionForward;

public interface IStrutsForward {

	public abstract ActionForward findForward(ActionContext context);
}
