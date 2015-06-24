
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
@Table(name = "tsysimg")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tsysimg implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统编号
  */
  protected Integer  tsysimgid;// //字段名称： 系统编号

  protected String  name;  //字段名称： 图片名
  protected String  module;  //字段名称： 模块名
  protected Integer  moduleid;  //字段名称： 模块ID
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tsysimg")
	@TableGenerator(
	   name = "Tsysimg",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tsysimg",
	   allocationSize = 1
	)
  public Integer getTsysimgid () {
     return tsysimgid;
  }
  public void setTsysimgid(Integer tsysimgid) {
     this.tsysimgid = tsysimgid;
  }
 /**
  * @return 返回 name,图片名
  */
  @Column(length=12)
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
  * @return 返回 module,模块名
  */
  @Column(length=30)
  public String getModule () {
     return module;
  }
	
 /**
  * @param module 
  */
  public void setModule(String module) {
     this.module = module;
  }
 /**
  * @return 返回 moduleid,模块ID
  */
  
  public Integer getModuleid () {
     return moduleid;
  }
	
 /**
  * @param moduleid 
  */
  public void setModuleid(Integer moduleid) {
     this.moduleid = moduleid;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tsysimgid", this.tsysimgid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsysimg) ) return false;
     Tsysimg castOther = (Tsysimg) other;
     return new EqualsBuilder()
       .append(this.getTsysimgid(), castOther.getTsysimgid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTsysimgid())
            .toHashCode();
  }
 

}


