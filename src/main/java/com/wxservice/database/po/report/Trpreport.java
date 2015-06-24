package com.wxservice.database.po.report;

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
@Table(name = "trpreport")
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Trpreport implements Serializable

{
	private static final long serialVersionUID = -1L;
	/**
	 * 主键 系统ID
	 */
	protected Integer trpreportid;// //字段名称： 系统ID

	protected String code; // 字段名称： 报表编码
	protected String name; // 字段名称： 报表名称
	protected String processclass; // 字段名称： 处理类
	protected String listenclass; // 字段名称： 监听类
	protected String serverurl; // 字段名称： server地址
	protected Integer parentid; // 字段名称： 父菜单
	protected String dispprocessor;// 显示处理类

	public String getDispprocessor() {
		return dispprocessor;
	}

	public void setDispprocessor(String dispprocessor) {
		this.dispprocessor = dispprocessor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Trpreport")
	@TableGenerator(name = "Trpreport", table = "tsyscounter", pkColumnName = "tablekey", valueColumnName = "countvalue", pkColumnValue = "Trpreport", allocationSize = 1)
	public Integer getTrpreportid() {
		return trpreportid;
	}

	public void setTrpreportid(Integer trpreportid) {
		this.trpreportid = trpreportid;
	}

	/**
	 * @return 返回 code,报表编码
	 */
	@Column(length = 30)
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
	 * @return 返回 name,报表名称
	 */
	@Column(length = 60)
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
	 * @return 返回 processclass,处理类
	 */
	@Column(length = 50)
	public String getProcessclass() {
		return processclass;
	}

	/**
	 * @param processclass
	 */
	public void setProcessclass(String processclass) {
		this.processclass = processclass;
	}

	/**
	 * @return 返回 listenclass,监听类
	 */
	@Column(length = 50)
	public String getListenclass() {
		return listenclass;
	}

	/**
	 * @param listenclass
	 */
	public void setListenclass(String listenclass) {
		this.listenclass = listenclass;
	}

	/**
	 * @return 返回 serverurl,server地址
	 */
	@Column(length = 50)
	public String getServerurl() {
		return serverurl;
	}

	/**
	 * @param serverurl
	 */
	public void setServerurl(String serverurl) {
		this.serverurl = serverurl;
	}

	/**
	 * @return 返回 parentid,父菜单
	 */

	public Integer getParentid() {
		return parentid;
	}

	/**
	 * @param parentid
	 */
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("trpreportid", this.trpreportid).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof Trpreport))
			return false;
		Trpreport castOther = (Trpreport) other;
		return new EqualsBuilder().append(this.getTrpreportid(),
				castOther.getTrpreportid()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.getTrpreportid()).toHashCode();
	}

}
