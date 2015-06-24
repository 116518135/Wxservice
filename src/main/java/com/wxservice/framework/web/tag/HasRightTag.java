package com.wxservice.framework.web.tag;


import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.logic.CompareTagBase;

import com.wxservice.framework.util.SysUtil;

public class HasRightTag extends CompareTagBase {

	private static final long serialVersionUID = 9094641813188436664L;

	private String name = Constants.BEAN_KEY; // 取到formbean

	protected String invoke = null;



	protected boolean condition() throws JspException {
		return SysUtil.isRight(pageContext, invoke);
	}

	public void release() {

		super.release();
		  name = null;
	      invoke = null;
	    }

	public String getInvoke() {
		return invoke;
	}

	public void setInvoke(String invoke) {
		this.invoke = invoke;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}