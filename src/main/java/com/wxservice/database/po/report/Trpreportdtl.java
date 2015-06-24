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
@Table(name = "trpreportdtl")
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Trpreportdtl implements Serializable

{
	private static final long serialVersionUID = -1L;
	/**
	 * 主键 系统ID
	 */
	protected Integer trpreportdtlid;// //字段名称： 系统ID

	protected Integer trpreportid; // 字段名称： 报表ID
	protected String title; // 字段名称： 标题
	protected String field; // 字段名称： 字段
	protected Integer fieldtype; // 字段名称： 字段类型
	protected Integer rowindex; // 字段名称： 行
	protected Integer colindex; // 字段名称： 列
	protected Integer width; // 字段名称： 宽度
	protected Integer align; // 字段名称： 对齐方式
	protected Integer linkflag; // 字段名称： 链接标志
	protected String linkurl; // 字段名称： 链接url
	protected String javacontent; // 字段名称： 脚本内容
	protected java.math.BigDecimal valuemin; // 字段名称： 最小值
	protected java.math.BigDecimal valuemax; // 字段名称： 最大值
	protected String color; // 字段名称： 颜色
	protected Integer trpreportpluginid; // 字段名称： 插件
	protected Integer reportarea; // 字段名称： 区域
	protected Integer dimvalueflag;// 维度值

	public Integer getDimvalueflag() {
		return dimvalueflag;
	}

	public void setDimvalueflag(Integer dimvalueflag) {
		this.dimvalueflag = dimvalueflag;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Trpreportdtl")
	@TableGenerator(name = "Trpreportdtl", table = "tsyscounter", pkColumnName = "tablekey", valueColumnName = "countvalue", pkColumnValue = "Trpreportdtl", allocationSize = 1)
	public Integer getTrpreportdtlid() {
		return trpreportdtlid;
	}

	public void setTrpreportdtlid(Integer trpreportdtlid) {
		this.trpreportdtlid = trpreportdtlid;
	}

	/**
	 * @return 返回 trpreportid,报表ID
	 */

	public Integer getTrpreportid() {
		return trpreportid;
	}

	/**
	 * @param trpreportid
	 */
	public void setTrpreportid(Integer trpreportid) {
		this.trpreportid = trpreportid;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return 返回 field,字段
	 */
	@Column(length = 30)
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return 返回 fieldtype,字段类型
	 */

	public Integer getFieldtype() {
		return fieldtype;
	}

	/**
	 * @param fieldtype
	 */
	public void setFieldtype(Integer fieldtype) {
		this.fieldtype = fieldtype;
	}

	/**
	 * @return 返回 rowindex,行
	 */

	public Integer getRowindex() {
		return rowindex;
	}

	/**
	 * @param rowindex
	 */
	public void setRowindex(Integer rowindex) {
		this.rowindex = rowindex;
	}

	/**
	 * @return 返回 colindex,列
	 */

	public Integer getColindex() {
		return colindex;
	}

	/**
	 * @param colindex
	 */
	public void setColindex(Integer colindex) {
		this.colindex = colindex;
	}

	/**
	 * @return 返回 width,宽度
	 */

	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return 返回 align,对齐方式
	 */

	public Integer getAlign() {
		return align;
	}

	/**
	 * @param align
	 */
	public void setAlign(Integer align) {
		this.align = align;
	}

	/**
	 * @return 返回 linkflag,链接标志
	 */

	public Integer getLinkflag() {
		return linkflag;
	}

	/**
	 * @param linkflag
	 */
	public void setLinkflag(Integer linkflag) {
		this.linkflag = linkflag;
	}

	/**
	 * @return 返回 linkurl,链接url
	 */
	@Column(length = 50)
	public String getLinkurl() {
		return linkurl;
	}

	/**
	 * @param linkurl
	 */
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	/**
	 * @return 返回 javacontent,脚本内容
	 */
	@Column(length = 1024)
	public String getJavacontent() {
		return javacontent;
	}

	/**
	 * @param javacontent
	 */
	public void setJavacontent(String javacontent) {
		this.javacontent = javacontent;
	}

	@Column(length = 16, precision = 6)
	public java.math.BigDecimal getValuemin() {
		return valuemin;
	}

	public void setValuemin(java.math.BigDecimal valuemin) {
		this.valuemin = valuemin;
	}
	
	@Column(length = 16, precision = 6)
	public java.math.BigDecimal getValuemax() {
		return valuemax;
	}

	public void setValuemax(java.math.BigDecimal valuemax) {
		this.valuemax = valuemax;
	}

	/**
	 * @return 返回 color,颜色
	 */
	@Column(length = 20)
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return 返回 trpreportpluginid,插件
	 */

	public Integer getTrpreportpluginid() {
		return trpreportpluginid;
	}

	/**
	 * @param trpreportpluginid
	 */
	public void setTrpreportpluginid(Integer trpreportpluginid) {
		this.trpreportpluginid = trpreportpluginid;
	}

	/**
	 * @return 返回 reportarea,区域
	 */

	public Integer getReportarea() {
		return reportarea;
	}

	/**
	 * @param reportarea
	 */
	public void setReportarea(Integer reportarea) {
		this.reportarea = reportarea;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("trpreportdtlid", this.trpreportdtlid).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof Trpreportdtl))
			return false;
		Trpreportdtl castOther = (Trpreportdtl) other;
		return new EqualsBuilder().append(this.getTrpreportdtlid(),
				castOther.getTrpreportdtlid()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.getTrpreportdtlid())
				.toHashCode();
	}

}
