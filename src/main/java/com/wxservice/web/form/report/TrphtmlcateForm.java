package com.wxservice.web.form.report;

import java.util.List;

import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述： Struts Form 创建人：Gererator
 */

public class TrphtmlcateForm extends BaseForm

{
	private static final long serialVersionUID = -1L;

	public TrphtmlcateForm() {
		this.setService("trphtmlcateManager");
		this.setModuleTitle("HTML控件分类");
		this.setAnonymous("0");
	}

	Integer trphtmlcateid;
	String code;
	String name;

	Integer trphtmlctrlid;
	String jsname;
	String wheres;
	String content;
	String processclass;
	List processclasslist;
	
	String sql;

	public List getProcessclasslist() {
		return processclasslist;
	}

	public void setProcessclasslist(List processclasslist) {
		this.processclasslist = processclasslist;
	}

	public Integer getTrphtmlctrlid() {
		return trphtmlctrlid;
	}

	public void setTrphtmlctrlid(Integer trphtmlctrlid) {
		this.trphtmlctrlid = trphtmlctrlid;
	}

	/**
	 * @return 返回 编号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            要设置的 编号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return 返回 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回 控件分类
	 */
	public Integer getTrphtmlcateid() {
		return trphtmlcateid;
	}

	/**
	 * @param trphtmlcateid
	 *            要设置的 控件分类
	 */
	public void setTrphtmlcateid(Integer trphtmlcateid) {
		this.trphtmlcateid = trphtmlcateid;
	}

	/**
	 * @return 返回 JS名称
	 */
	public String getJsname() {
		return jsname;
	}

	/**
	 * @param jsname
	 *            要设置的 JS名称
	 */
	public void setJsname(String jsname) {
		this.jsname = jsname;
	}

	/**
	 * @return 返回 where语句
	 */
	public String getWheres() {
		return wheres;
	}

	/**
	 * @param wheres
	 *            要设置的 where语句
	 */
	public void setWheres(String wheres) {
		this.wheres = wheres;
	}

	/**
	 * @return 返回 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            要设置的 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return 返回 处理类
	 */
	public String getProcessclass() {
		return processclass;
	}

	/**
	 * @param processclass
	 *            要设置的 处理类
	 */
	public void setProcessclass(String processclass) {
		this.processclass = processclass;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
