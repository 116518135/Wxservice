package com.wxservice.web.form.system;

import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述： Struts Form 创建人：Gererator
 */

public class TsyscmpparamForm extends BaseForm

{
	private static final long serialVersionUID = -1L;

	public TsyscmpparamForm() {
		this.setService("tsyscmpparamManager");
		this.setModuleTitle("组织参数表");
		this.setAnonymous("0");
	}

	Integer tsyscmpparamid;
	String paramname;
	String paramvalue;
	java.util.Date editdate;
	String chinesename;

	Integer txdtaskid;
	String taskcode;

	public Integer getTsyscmpparamid() {
		return tsyscmpparamid;
	}

	public void setTsyscmpparamid(Integer tsyscmpparamid) {
		this.tsyscmpparamid = tsyscmpparamid;
	}

	/**
	 * @return 返回 参数名称
	 */
	public String getParamname() {
		return paramname;
	}

	/**
	 * @param paramname
	 *            要设置的 参数名称
	 */
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	/**
	 * @return 返回 参数值
	 */
	public String getParamvalue() {
		return paramvalue;
	}

	/**
	 * @param paramvalue
	 *            要设置的 参数值
	 */
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

	/**
	 * @return 返回 修改日期
	 */
	public java.util.Date getEditdate() {
		return editdate;
	}

	/**
	 * @param editdate
	 *            要设置的 修改日期
	 */
	public void setEditdate(java.util.Date editdate) {
		this.editdate = editdate;
	}

	/**
	 * @return 返回 中文名称
	 */
	public String getChinesename() {
		return chinesename;
	}

	/**
	 * @param chinesename
	 *            要设置的 中文名称
	 */
	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
	}

	public Integer getTxdtaskid() {
		return txdtaskid;
	}

	public void setTxdtaskid(Integer txdtaskid) {
		this.txdtaskid = txdtaskid;
	}

	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}
	
	
	

}
