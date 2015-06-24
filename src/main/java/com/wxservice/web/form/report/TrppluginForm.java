package com.wxservice.web.form.report;

import com.wxservice.framework.web.form.BaseForm;

public class TrppluginForm extends BaseForm{
private static final long serialVersionUID = -1L;
	
	public TrppluginForm() {
		this.setService("trppluginManager");
		this.setModuleTitle("报表插件 ");
		this.setAnonymous("0");
	}
	Integer trppluginid;//系统ID
	String name;//编号
	String code;//名称
	String springname;// 对象名
	String fieldalias;// 字段别名
	String template;// sql模板

	public Integer getTrppluginid() {
		return trppluginid;
	}
	public void setTrppluginid(Integer trppluginid) {
		this.trppluginid = trppluginid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSpringname() {
		return springname;
	}
	public void setSpringname(String springname) {
		this.springname = springname;
	}
	public String getFieldalias() {
		return fieldalias;
	}
	public void setFieldalias(String fieldalias) {
		this.fieldalias = fieldalias;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
}
