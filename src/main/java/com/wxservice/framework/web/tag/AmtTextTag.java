package com.wxservice.framework.web.tag;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseFieldTag;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

import com.wxservice.framework.engine.support.SysConfig;
import com.wxservice.framework.util.FormatUtil;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SysUtil;

public class AmtTextTag extends TextTag {
	public String viewtype = null;
	protected String invoke = null;

	public AmtTextTag() {
		super();
		viewtype = "text";
		invoke = "isBrowseAmt";
	}

	public int doStartTag() throws JspException {
		if ("text".equals(viewtype)) {
			if (SysUtil.isBrowseAmt(pageContext, invoke)) {
				super.doStartTag();
			} else {
				writeHiddenTag();
			}
		} else {
			writeLabelTag();
		}
		// Continue processing this page
		return (EVAL_BODY_TAG);
	}

	public void writeHiddenTag() throws JspException {
		StringBuffer results = new StringBuffer("<input type=\"");
		results.append("hidden");
		results.append("\" name=\"");
		if (indexed) {
			prepareIndex(results, name);
		}
		results.append(property);
		results.append("\"");
		results.append(" value=\"");
		results.append(getTextValue());
		results.append('"');
		results.append(getElementClose());
		results.append(SysFinal.BR0WSEAMT_HIDDENSIGN);
		ResponseUtils.write(pageContext, results.toString());

	}

	public void writeLabelTag() throws JspException {
		StringBuffer results = new StringBuffer();
		if (SysUtil.isRight(pageContext, invoke)) {
			results.append(getTextValue());
		} else {
			results.append(SysFinal.BR0WSEAMT_HIDDENSIGN);
		}
		ResponseUtils.write(pageContext, results.toString());
	}

	protected String getTextValue() throws JspException {
		Object v = TagUtils.getInstance().lookup(pageContext, name, property,
				null);
		if (v != null) {
			if (v instanceof Number) {
				if (v != null) {
					Number dbl = (Number) v;
					String value = FormatUtil.format(property, dbl);
					return value;
				}
			} else {
				return v.toString();
			}
		}
		return "";

	}

	/**
	 * Release any acquired resources.
	 */
	public void release() {
		viewtype = null;
		name = null;
		super.release();

	}

	public String getDisplay() {
		return viewtype;
	}

	public void setDisplay(String viewType) {
		this.viewtype = viewType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
