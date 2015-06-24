package com.wxservice.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.BaseHandlerTag;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.web.form.BaseForm;

public class SimplePageTag extends BaseHandlerTag {
	private static final long serialVersionUID = 1L;
	protected String name = Constants.BEAN_KEY; // 取到formbean

	public String getName() {
		return (this.name);
	}

	public void setName(String name) {
		this.name = name;//
	}

	public String javascriptname = "changePage";
	private String method = "list";
	private String querystring = "";

	// --------------------------------------------------------- Public Methods

	public String getQuerystring() {
		return querystring;
	}

	public void setQuerystring(String querystring) {
		this.querystring = querystring;
	}

	/**
	 * Generate the required input tag.
	 * <p>
	 * Support for indexed property since Struts 1.1
	 * 
	 * @exception JspException
	 *                if a JSP exception has occurred
	 */
	@SuppressWarnings("deprecation")
	public int doStartTag() throws JspException {
		Object bean = RequestUtils.lookup(this.pageContext, name, null);
		BaseForm form = (BaseForm) bean;
		int pageNumber = form.getEc_p();
		int limit = form.getEc_crd();
		int totalcount = (int) form.getPage().getTotalCount();
		boolean hasPrev = true;
		if (pageNumber == 1) {
			hasPrev = false;
		} else {
			hasPrev = true;
		}
		boolean hasNext = false;
		if (pageNumber * limit >= totalcount) {
			hasNext = false;
		} else {
			hasNext = true;
		}
		StringBuffer results = new StringBuffer("");
		if (hasPrev) {
			results.append("<a href=\"");
			results.append(this.getUrl(pageNumber - 1));
			results.append("\">");
			results.append("上页</a>&nbsp;&nbsp;");
		} else {
			results.append("上页&nbsp;&nbsp;");
		}
		if (hasNext) {
			results.append("<a href=\"");
			results.append(this.getUrl(pageNumber + 1));
			results.append("\">");
			results.append("下页</a>");
		} else {
			results.append("下页");
		}
		ResponseUtils.write(pageContext, results.toString());
		return (EVAL_BODY_TAG);
	}

	public String getUrl(int pageNumber) {
		StringBuffer url = new StringBuffer();
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		url.append(request.getContextPath());
		url.append(method);
		if (StringUtil.isNotBlank(querystring)) {
			String str[] = querystring.split(",");
			for (String param : str) {
				String v = request.getParameter(param);
				if (v != null) {
					url.append("&");
					url.append(param);
					url.append("=");
					url.append(v);
				}
			}
		}
		url.append("&ec_p=");
		url.append(pageNumber);
		return url.toString();
	}

	/**
	 * Release any acquired resources.
	 */
	public void release() {
		super.release();
		this.javascriptname = null;
		this.method = null;
		this.querystring = null;
	}

	public String getJavascriptname() {
		return javascriptname;
	}

	public void setJavascriptname(String javascriptname) {
		this.javascriptname = javascriptname;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
