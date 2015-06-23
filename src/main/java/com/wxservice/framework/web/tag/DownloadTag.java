package com.wxservice.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.html.ButtonTag;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.util.RequestUtils;

import com.wxservice.framework.util.SysUtil;


public class DownloadTag extends ButtonTag {
	private static final long serialVersionUID = 9094641813188436664L;
	protected String invoke = "isPrint";

	public String getInvoke() {
		return invoke;
	}

	public void setInvoke(String invoke) {
		this.invoke = invoke;
	}
	public int doStartTag() throws JspException {
		this.setDisabled(!SysUtil.isRight(pageContext, invoke));
		this.text = null;
		return (EVAL_BODY_TAG);

	}
	public void release() {
		super.release();
		invoke = null;
	}
}
