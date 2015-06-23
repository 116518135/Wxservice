package com.wxservice.web.form.report;

import java.util.List;

import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述： Struts Form 项目名称：七匹狼运动信息化平台 创建人：Gererator
 */

public class TrpreportpluginForm extends BaseForm

{
	private static final long serialVersionUID = -1L;

	public TrpreportpluginForm() {
		this.setService("trpreportpluginManager");
		this.setModuleTitle("报表插件");
		this.setAnonymous("0");
	}

	Integer trpreportpluginid;
	Integer trpreportid;
	String name;
	String springname;
	String fieldalias;
	String template;
	String methodflag;
	    
	public String getMethodflag() {
		return methodflag;
	}
	public void setMethodflag(String methodflag) {
		this.methodflag = methodflag;
	}
	public Integer getTrpreportpluginid() {
		return trpreportpluginid;
	}

	public void setTrpreportpluginid(Integer trpreportpluginid) {
		this.trpreportpluginid = trpreportpluginid;
	}

	/**
	 * @return 返回 报表id
	 */
	public Integer getTrpreportid() {
		return trpreportid;
	}

	/**
	 * @param trpreportid
	 *            要设置的 报表id
	 */
	public void setTrpreportid(Integer trpreportid) {
		this.trpreportid = trpreportid;
	}

	/**
	 * @return 返回 插件名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 插件名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回 插件Spring定义
	 */
	public String getSpringname() {
		return springname;
	}

	/**
	 * @param springname
	 *            要设置的 插件Spring定义
	 */
	public void setSpringname(String springname) {
		this.springname = springname;
	}

	/**
	 * @return 返回 插件字段别名
	 */
	public String getFieldalias() {
		return fieldalias;
	}

	/**
	 * @param fieldalias
	 *            要设置的 插件字段别名
	 */
	public void setFieldalias(String fieldalias) {
		this.fieldalias = fieldalias;
	}

	/**
	 * @return 返回 插件Sql模板
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template
	 *            要设置的 插件Sql模板
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	/*
	 * xj
	 */
	Integer trppluginid;

	public Integer getTrppluginid() {
		return trppluginid;
	}

	public void setTrppluginid(Integer trppluginid) {
		this.trppluginid = trppluginid;
	}
	
}
