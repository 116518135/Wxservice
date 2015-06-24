package com.wxservice.database.po.system;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
/**
 * 
 * 描述： Hibernate PO 创建人：Gererator
 */
@Entity
@Table(name = "tsysrole")
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tsysrole implements Serializable

{
	private static final long serialVersionUID = -1L;
	/**
	 * 主键 权限组ID
	 */
	protected Integer tsysroleid;// //字段名称： 权限组ID

	protected String code; // 字段名称： 编号
	protected String name; // 字段名称： 名称
	protected Integer tbscmpid; // 字段名称： 所属组织

	protected Integer createtbscmpid; // 字段名称： 创建人所属组织

	protected String memo; // 字段名称： 备注
	protected Integer publicflag;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tsysrole")
	@TableGenerator(name = "Tsysrole", table = "tsyscounter", pkColumnName = "tablekey", valueColumnName = "countvalue", pkColumnValue = "Tsysrole", allocationSize = 1)
	public Integer getTsysroleid() {
		return tsysroleid;
	}

	public void setTsysroleid(Integer tsysroleid) {
		this.tsysroleid = tsysroleid;
	}

	/**
	 * @return 返回 code,编号
	 */
	@Column(length = 12)
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return 返回 name,名称
	 */
	@Column(length = 20)
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回 tbscmpid,所属组织
	 */

	public Integer getTbscmpid() {
		return tbscmpid;
	}

	/**
	 * @param tbscmpid
	 */
	public void setTbscmpid(Integer tbscmpid) {
		this.tbscmpid = tbscmpid;
	}

	public Integer getCreatetbscmpid() {
		return createtbscmpid;
	}

	/**
	 * @param createtbscmpid
	 */
	public void setCreatetbscmpid(Integer createtbscmpid) {
		this.createtbscmpid = createtbscmpid;
	}

	/**
	 * @return 返回 memo,备注
	 */
	@Column(length = 50)
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("tysroleid", this.tsysroleid).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof Tsysrole))
			return false;
		Tsysrole castOther = (Tsysrole) other;
		return new EqualsBuilder().append(this.getTsysroleid(),
				castOther.getTsysroleid()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.getTsysroleid()).toHashCode();
	}

	public Integer getPublicflag() {
		return publicflag;
	}

	public void setPublicflag(Integer publicflag) {
		this.publicflag = publicflag;
	}

}
