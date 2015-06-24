package com.wxservice.web.form.system;

import java.util.List;

import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述： Struts Form 创建人：Gererator
 */

public class TsysbillnoruleForm extends BaseForm

{
	private static final long serialVersionUID = -1L;

	public TsysbillnoruleForm() {
		this.setService("tsysbillnoruleManager");
		this.setModuleTitle("单据编号规则表");
		this.setAnonymous("0");
	}

	Integer tsysbillnoruleid;
	String code;
	String name;
	String mark;
	Integer billtype = 0;
	List billtypeOptions;
	Integer numlength = 4;
	Integer counttype = 0;
	List counttypeOptions;
	Integer curcount = 1;
	Integer tbscmpid;
	String createusername;
	java.util.Date createdate;
	Integer createtbscmpid;
	String editusername;
	java.util.Date editdate;

	public Integer getTsysbillnoruleid() {
		return tsysbillnoruleid;
	}

	public void setTsysbillnoruleid(Integer tsysbillnoruleid) {
		this.tsysbillnoruleid = tsysbillnoruleid;
	}

	/**
	 * @return 返回 单据编号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            要设置的 单据编号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return 返回 单据名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 单据名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回 单据标识
	 */
	public String getMark() {
		return mark;
	}

	/**
	 * @param mark
	 *            要设置的 单据标识
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}

	/**
	 * @return 返回 构成方式
	 */
	public Integer getBilltype() {
		return billtype;
	}

	/**
	 * @param billtype
	 *            要设置的 构成方式
	 */
	public void setBilltype(Integer billtype) {
		this.billtype = billtype;
	}

	/**
	 * @return 返回 流水号长度
	 */
	public Integer getNumlength() {
		return numlength;
	}

	/**
	 * @param numlength
	 *            要设置的 流水号长度
	 */
	public void setNumlength(Integer numlength) {
		this.numlength = numlength;
	}

	/**
	 * @return 返回 重新计数方式
	 */
	public Integer getCounttype() {
		return counttype;
	}

	/**
	 * @param counttype
	 *            要设置的 重新计数方式
	 */
	public void setCounttype(Integer counttype) {
		this.counttype = counttype;
	}

	/**
	 * @return 返回 当前计数
	 */
	public Integer getCurcount() {
		return curcount;
	}

	/**
	 * @param curcount
	 *            要设置的 当前计数
	 */
	public void setCurcount(Integer curcount) {
		this.curcount = curcount;
	}

	/**
	 * @return 返回 所属组织
	 */
	public Integer getTbscmpid() {
		return tbscmpid;
	}

	/**
	 * @param tbscmpid
	 *            要设置的 所属组织
	 */
	public void setTbscmpid(Integer tbscmpid) {
		this.tbscmpid = tbscmpid;
	}

	/**
	 * @return 返回 创建人
	 */
	public String getCreateusername() {
		return createusername;
	}

	/**
	 * @param createusername
	 *            要设置的 创建人
	 */
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	/**
	 * @return 返回 创建日期
	 */
	public java.util.Date getCreatedate() {
		return createdate;
	}

	/**
	 * @param createdate
	 *            要设置的 创建日期
	 */
	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}

	/**
	 * @return 返回 创建人所属组织
	 */
	public Integer getCreatetbscmpid() {
		return createtbscmpid;
	}

	/**
	 * @param createtbscmpid
	 *            要设置的 创建人所属组织
	 */
	public void setCreatetbscmpid(Integer createtbscmpid) {
		this.createtbscmpid = createtbscmpid;
	}

	/**
	 * @return 返回 修改人
	 */
	public String getEditusername() {
		return editusername;
	}

	/**
	 * @param editusername
	 *            要设置的 修改人
	 */
	public void setEditusername(String editusername) {
		this.editusername = editusername;
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

	public List getBilltypeOptions() {
		return billtypeOptions;
	}

	public void setBilltypeOptions(List billtypeOptions) {
		this.billtypeOptions = billtypeOptions;
	}

	public List getCounttypeOptions() {
		return counttypeOptions;
	}

	public void setCounttypeOptions(List counttypeOptions) {
		this.counttypeOptions = counttypeOptions;
	}
}
