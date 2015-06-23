
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
 * 描述：   Hibernate PO
 * 创建人：Gererator
 */
@Entity
@Table(name = "Tsysexceldtl")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tsysexceldtl implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统ID
  */
  protected Integer  tsysexceldtlid;// //字段名称： 系统ID

  protected Integer  tsysexcelid;  //字段名称： 主表ID
  protected String  fieldname;  //字段名称： 字段名称
  protected Integer  fieldtype;  //字段名称： 字段日型
  protected String  fieldtitle;  //字段名称： 字段标题
  protected String  fieldprocess;  //字段名称： 字段处理器
  protected Integer  colindex;  //字段名称： 列数
  protected Integer  colwidth;  //字段名称： 列宽
  protected String  fieldvalue;  //字段名称： 变量/常量值
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tsysexceldtl")
	@TableGenerator(
	   name = "Tsysexceldtl",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tsysexceldtl",
	   allocationSize = 1
	)
  public Integer getTsysexceldtlid () {
     return tsysexceldtlid;
  }
  public void setTsysexceldtlid(Integer tsysexceldtlid) {
     this.tsysexceldtlid = tsysexceldtlid;
  }
 /**
  * @return 返回 tsysexcelid,主表ID
  */
  
  public Integer getTsysexcelid () {
     return tsysexcelid;
  }
	
 /**
  * @param tsysexcelid 
  */
  public void setTsysexcelid(Integer tsysexcelid) {
     this.tsysexcelid = tsysexcelid;
  }
 /**
  * @return 返回 fieldname,字段名称
  */
  @Column(length=20)
  public String getFieldname () {
     return fieldname;
  }
	
 /**
  * @param fieldname 
  */
  public void setFieldname(String fieldname) {
     this.fieldname = fieldname;
  }
 /**
  * @return 返回 fieldtype,字段日型
  */
  
  public Integer getFieldtype () {
     return fieldtype;
  }
	
 /**
  * @param fieldtype 
  */
  public void setFieldtype(Integer fieldtype) {
     this.fieldtype = fieldtype;
  }
 /**
  * @return 返回 fieldtitle,字段标题
  */
  @Column(length=30)
  public String getFieldtitle () {
     return fieldtitle;
  }
	
 /**
  * @param fieldtitle 
  */
  public void setFieldtitle(String fieldtitle) {
     this.fieldtitle = fieldtitle;
  }
 /**
  * @return 返回 fieldprocess,字段处理器
  */
  @Column(length=30)
  public String getFieldprocess () {
     return fieldprocess;
  }
	
 /**
  * @param fieldprocess 
  */
  public void setFieldprocess(String fieldprocess) {
     this.fieldprocess = fieldprocess;
  }
 /**
  * @return 返回 colindex,列数
  */
  
  public Integer getColindex () {
     return colindex;
  }
	
 /**
  * @param colindex 
  */
  public void setColindex(Integer colindex) {
     this.colindex = colindex;
  }
 /**
  * @return 返回 colwidth,列宽
  */
  
  public Integer getColwidth () {
     return colwidth;
  }
	
 /**
  * @param colwidth 
  */
  public void setColwidth(Integer colwidth) {
     this.colwidth = colwidth;
  }
 /**
  * @return 返回 fieldvalue,变量/常量值
  */
  @Column(length=30)
  public String getFieldvalue () {
     return fieldvalue;
  }
	
 /**
  * @param fieldvalue 
  */
  public void setFieldvalue(String fieldvalue) {
     this.fieldvalue = fieldvalue;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tsysexceldtlid", this.tsysexceldtlid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsysexceldtl) ) return false;
     Tsysexceldtl castOther = (Tsysexceldtl) other;
     return new EqualsBuilder()
       .append(this.getTsysexceldtlid(), castOther.getTsysexceldtlid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTsysexceldtlid())
            .toHashCode();
  }
 

}


