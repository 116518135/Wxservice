package com.wxservice.web.form.base;

import com.wxservice.framework.web.form.BaseForm;

public class AjaxForm extends BaseForm {
	public AjaxForm() {
		this.setService("ajaxManager");
		this.setAnonymous("1");
	}

	int start = 0;
	int limit = 10;
	String querystring;// 需要过滤的where
	String code;// 输入的编号
	String mapping;// 需要返回的值
	String jsname;
	String nextjsname;
	
	private String objecttype; 
	private String table="";
	private int limitcmpflag=0;//是否限制公司
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getQuerystring() {
		return querystring;
	}
	public void setQuerystring(String querystring) {
		this.querystring = querystring;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMapping() {
		return mapping;
	}
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	public String getJsname() {
		return jsname;
	}
	public void setJsname(String jsname) {
		this.jsname = jsname;
	}
	public String getNextjsname() {
		return nextjsname;
	}
	public void setNextjsname(String nextjsname) {
		this.nextjsname = nextjsname;
	}
	public String getObjecttype() {
		return objecttype;
	}
	public void setObjecttype(String objecttype) {
		this.objecttype = objecttype;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public int getLimitcmpflag() {
		return limitcmpflag;
	}
	public void setLimitcmpflag(int limitcmpflag) {
		this.limitcmpflag = limitcmpflag;
	}
	

}
