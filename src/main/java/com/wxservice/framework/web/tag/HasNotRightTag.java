package com.wxservice.framework.web.tag;



import javax.servlet.jsp.JspException;

public class HasNotRightTag  extends HasRightTag{
	private static final long serialVersionUID = 1L;

	public boolean condition() throws JspException {
       
        return !super.condition();
    }

}
