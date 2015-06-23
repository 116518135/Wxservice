package com.wxservice.framework.web.tag;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.TagUtils;

import com.wxservice.framework.util.DateUtil;
import com.wxservice.framework.util.FormatUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.web.tag.support.TagUtil;


public class TextTag extends org.apache.struts.taglib.html.TextTag {
	String button = null;
	String map = null;
	String querystring = null;
	String javascriptname=null;
	private static final long serialVersionUID = 1L;

	protected void prepareValue(StringBuffer results) throws JspException {
		if (value != null) {
			results.append(" value=\"");
			results.append(this.formatValue(value));
			results.append('"');

		} else if (redisplay || !"password".equals(type)) {
			Object value = TagUtils.getInstance().lookup(pageContext, name,
					property, null);
			if (value instanceof Date) {
				prepareAttribute(results, "id", getProperty()); // 如果日期,需要加上id
				value = DateUtil.formatSimplyDate((Date) value);
			}
			if (value instanceof Number) {
				if (value != null) {
					Number dbl = (Number) value;
					value=FormatUtil.format(property, dbl);
				}
			}
			results.append(" value=\"");
			results.append(this.formatValue(value));
			results.append('"');
		}

	}

	public int doStartTag() throws JspException {
		StringBuffer results = new StringBuffer();
		results.append(this.renderInputElement());
		prepareButton(results);
		TagUtils.getInstance().write(this.pageContext, results.toString());
		return (EVAL_BODY_TAG);

	}

	protected String prepareButton(StringBuffer results) {

		if (StringUtils.isNotBlank(button)) {
			HttpServletRequest request = (HttpServletRequest) this.pageContext
					.getRequest();
			if(button.equals(TagUtil.BUTTON_DATE_TYPE)){
				results.append(TagUtil.prepareDateButton(this, request));
			}else if(button.equals(TagUtil.BUTTON_AREA_TYPE)){
				results.append(TagUtil.prepareAreaButton(this, request));
			}else if(button.equals(TagUtil.BUTTON_DEPT_TYPE)){
				results.append(TagUtil.prepareStoreButton(this, request));
			}else if(button.equals(TagUtil.BUTTON_USER_TYPE)){
				results.append(TagUtil.prepareUserButton(this, request));
			}else if(button.equals(TagUtil.BUTTON_CMP_TYPE)){
				results.append(TagUtil.prepareCmpButton(this, request));
			}else if(button.equals(TagUtil.BUTTON_MEMO_TYPE)){
				results.append(TagUtil.prepareMemoButton(this, request));
			}else if(button.equals(TagUtil.BUTTON_PRODUCT_TYPE)){
				results.append(TagUtil.prepareProdButton(this, request));
			}
			
		}
		return "";
	}

	public void release() {
		super.release();
		this.button = null;
		this.map = null;
		this.querystring = null;
        this.javascriptname=null;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}


	
	public String getJavascriptname() {
		return javascriptname;
	}

	public void setJavascriptname(String javascriptname) {
		this.javascriptname = javascriptname;
	}

	public String getQuerystring() {
		return querystring;
	}

	public void setQuerystring(String querystring) {
		this.querystring = querystring;
	}
}
