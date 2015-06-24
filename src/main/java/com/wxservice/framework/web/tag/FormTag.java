package com.wxservice.framework.web.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.taglib.html.Constants;

import com.wxservice.framework.engine.support.SysToken;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.framework.web.tag.support.TagUtil;

public class FormTag extends org.apache.struts.taglib.html.FormTag {
	String token;

	protected String renderToken() {
		StringBuffer results = new StringBuffer();
		HttpSession session = pageContext.getSession();

		if (StringUtils.isBlank(token)) {
			token = Globals.TRANSACTION_TOKEN_KEY;
		}
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		BaseForm form = WebUtil.getForm(request);
		if (form != null) {
			String key = SysToken.getTokenKey(token, form.getFormname());
			if (session != null) {
				String tokenvalue = (String) session.getAttribute(key);
				if (token != null) {
					results.append("<div><input type=\"hidden\" name=\"");
					results.append(Constants.TOKEN_KEY);
					results.append("\" value=\"");
					results.append(tokenvalue);
					results.append("\" />");
					results.append("</div>");
				}
			}
		}
		return results.toString();
	}

	protected String renderHotkey() {
		StringBuffer results = new StringBuffer();
		String hotkeystr = (String) pageContext
				.getAttribute(TagUtil.hotkey_str);
		if (StringUtils.isNotBlank(hotkeystr)) {
			results.append(lineEnd);
			results.append("<script type=\"text/javascript\"");
			results.append(">");
			results.append(lineEnd);
			results.append(" var hotkey=function(){");
			results.append(lineEnd);
			results.append("  var key=window.event.keyCode;  ");
			results.append(lineEnd);
			results.append(hotkeystr);
			results.append(lineEnd);
			results.append("} ");
			results.append(lineEnd);
			results.append("document.onkeydown=hotkey; ");
			results.append("</script>");
		}
		return results.toString();
	}

	public int doEndTag() throws JspException {

		// Remove the page scope attributes we created
		pageContext.removeAttribute(Constants.BEAN_KEY,
				PageContext.REQUEST_SCOPE);
		pageContext.removeAttribute(Constants.FORM_KEY,
				PageContext.REQUEST_SCOPE);

		// Render a tag representing the end of our current form
		StringBuffer results = new StringBuffer("</form>");
		results.append(renderHotkey());
		// Render JavaScript to set the input focus if required
		if (this.focus != null) {
			results.append(this.renderFocusJavascript());
		}

		// Print this value to our output writer
		JspWriter writer = pageContext.getOut();
		try {
			writer.print(results.toString());
		} catch (IOException e) {
			throw new JspException(messages.getMessage("common.io", e
					.toString()));
		}

		// Continue processing this page
		return (EVAL_PAGE);

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void release() {
		this.token = null;
	}
}
