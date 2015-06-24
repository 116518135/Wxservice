package com.wxservice.database.po.report;

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

@Entity
@Table(name = "Trpdcubetpl")
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Trpdcubetpl {
	private static final long serialVersionUID = -1L;
	/**
	 * 主键 系统ID
	 */
	protected Integer trpdcubetplid;// //字段名称： 系统ID
	protected Integer reportid;
	protected Integer tbsuserid;
	protected Integer showsubflag;
	protected String colband;
	protected String rowband;
	protected String pageband;
	protected String databand;
	protected String hiddenband;
	protected String name;
	protected String subband;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Trpdcubetpl")
	@TableGenerator(name = "Trpdcubetpl", table = "tsyscounter", pkColumnName = "tablekey", valueColumnName = "countvalue", pkColumnValue = "Trpdcubetpl", allocationSize = 1)
	public Integer getTrpdcubetplid() {
		return trpdcubetplid;
	}

	public void setTrpdcubetplid(Integer trpdcubetplid) {
		this.trpdcubetplid = trpdcubetplid;
	}

	public Integer getReportid() {
		return reportid;
	}

	public String getRowband() {
		return rowband;
	}

	public void setRowband(String rowband) {
		this.rowband = rowband;
	}

	public void setReportid(Integer reportid) {
		this.reportid = reportid;
	}

	public Integer getTbsuserid() {
		return tbsuserid;
	}

	public void setTbsuserid(Integer tbsuserid) {
		this.tbsuserid = tbsuserid;
	}

	public Integer getShowsubflag() {
		return showsubflag;
	}

	public String getSubband() {
		return subband;
	}

	public void setSubband(String subband) {
		this.subband = subband;
	}

	public void setShowsubflag(Integer showsubflag) {
		this.showsubflag = showsubflag;
	}

	public String getColband() {
		return colband;
	}

	public void setColband(String colband) {
		this.colband = colband;
	}

	public String getPageband() {
		return pageband;
	}

	public void setPageband(String pageband) {
		this.pageband = pageband;
	}

	public String getDataband() {
		return databand;
	}

	public void setDataband(String databand) {
		this.databand = databand;
	}

	public String getHiddenband() {
		return hiddenband;
	}

	public void setHiddenband(String hiddenband) {
		this.hiddenband = hiddenband;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("trpdcubetplid", this.trpdcubetplid).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof Trpdcubetpl))
			return false;
		Trpdcubetpl castOther = (Trpdcubetpl) other;
		return new EqualsBuilder().append(this.getTrpdcubetplid(),
				castOther.getTrpdcubetplid()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.getTrpdcubetplid())
				.toHashCode();
	}

}
