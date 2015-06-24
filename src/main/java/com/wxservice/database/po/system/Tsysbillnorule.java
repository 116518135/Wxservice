
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
@Table(name = "tsysbillnorule")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tsysbillnorule implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 规则ID
  */
  protected Integer  tsysbillnoruleid;// //字段名称： 规则ID

  protected String  code;  //字段名称： 单据编号
  protected String  name;  //字段名称： 单据名称
  protected String  mark;  //字段名称： 单据标识
  protected Integer  billtype;  //字段名称： 构成方式
  protected Integer  numlength;  //字段名称： 流水号长度
  protected Integer  counttype;  //字段名称： 重新计数方式
  protected Integer  curcount;  //字段名称： 当前计数
  protected Integer  tbscmpid;  //字段名称： 所属组织
  protected String  createusername;  //字段名称： 创建人
  protected java.util.Date  createdate;  //字段名称： 创建日期
  protected Integer  createtbscmpid;  //字段名称： 创建人所属组织
  protected String  editusername;  //字段名称： 修改人
  protected java.util.Date  editdate;  //字段名称： 修改日期
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tsysbillnorule")
	@TableGenerator(
	   name = "Tsysbillnorule",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tsysbillnorule",
	   allocationSize = 1
	)
  public Integer getTsysbillnoruleid () {
     return tsysbillnoruleid;
  }
  public void setTsysbillnoruleid(Integer tsysbillnoruleid) {
     this.tsysbillnoruleid = tsysbillnoruleid;
  }
 /**
  * @return 返回 code,单据编号
  */
  @Column(length=20)
  public String getCode () {
     return code;
  }
	
 /**
  * @param code 
  */
  public void setCode(String code) {
     this.code = code;
  }
 /**
  * @return 返回 name,单据名称
  */
  @Column(length=50)
  public String getName () {
     return name;
  }
	
 /**
  * @param name 
  */
  public void setName(String name) {
     this.name = name;
  }
 /**
  * @return 返回 mark,单据标识
  */
  @Column(length=6)
  public String getMark () {
     return mark;
  }
	
 /**
  * @param mark 
  */
  public void setMark(String mark) {
     this.mark = mark;
  }
 /**
  * @return 返回 billtype,构成方式
  */
  
  public Integer getBilltype () {
     return billtype;
  }
	
 /**
  * @param billtype 
  */
  public void setBilltype(Integer billtype) {
     this.billtype = billtype;
  }
 /**
  * @return 返回 numlength,流水号长度
  */
  
  public Integer getNumlength () {
     return numlength;
  }
	
 /**
  * @param numlength 
  */
  public void setNumlength(Integer numlength) {
     this.numlength = numlength;
  }
 /**
  * @return 返回 counttype,重新计数方式
  */
  
  public Integer getCounttype () {
     return counttype;
  }
	
 /**
  * @param counttype 
  */
  public void setCounttype(Integer counttype) {
     this.counttype = counttype;
  }
 /**
  * @return 返回 curcount,当前计数
  */
  
  public Integer getCurcount () {
     return curcount;
  }
	
 /**
  * @param curcount 
  */
  public void setCurcount(Integer curcount) {
     this.curcount = curcount;
  }
 /**
  * @return 返回 tbscmpid,所属组织
  */
  
  public Integer getTbscmpid () {
     return tbscmpid;
  }
	
 /**
  * @param tbscmpid 
  */
  public void setTbscmpid(Integer tbscmpid) {
     this.tbscmpid = tbscmpid;
  }
 /**
  * @return 返回 createusername,创建人
  */
  @Column(length=20)
  public String getCreateusername () {
     return createusername;
  }
	
 /**
  * @param createusername 
  */
  public void setCreateusername(String createusername) {
     this.createusername = createusername;
  }
 /**
  * @return 返回 createdate,创建日期
  */
  
  public java.util.Date getCreatedate () {
     return createdate;
  }
	
 /**
  * @param createdate 
  */
  public void setCreatedate(java.util.Date createdate) {
     this.createdate = createdate;
  }
 /**
  * @return 返回 createtbscmpid,创建人所属组织
  */
  
  public Integer getCreatetbscmpid () {
     return createtbscmpid;
  }
	
 /**
  * @param createtbscmpid 
  */
  public void setCreatetbscmpid(Integer createtbscmpid) {
     this.createtbscmpid = createtbscmpid;
  }
 /**
  * @return 返回 editusername,修改人
  */
  @Column(length=20)
  public String getEditusername () {
     return editusername;
  }
	
 /**
  * @param editusername 
  */
  public void setEditusername(String editusername) {
     this.editusername = editusername;
  }
 /**
  * @return 返回 editdate,修改日期
  */
  
  public java.util.Date getEditdate () {
     return editdate;
  }
	
 /**
  * @param editdate 
  */
  public void setEditdate(java.util.Date editdate) {
     this.editdate = editdate;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tsysbillnoruleid", this.tsysbillnoruleid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsysbillnorule) ) return false;
     Tsysbillnorule castOther = (Tsysbillnorule) other;
     return new EqualsBuilder()
       .append(this.getTsysbillnoruleid(), castOther.getTsysbillnoruleid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTsysbillnoruleid())
            .toHashCode();
  }
 

}


