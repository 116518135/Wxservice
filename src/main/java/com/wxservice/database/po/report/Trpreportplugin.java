
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
 * 描述：   Hibernate PO
 * 项目名称：七匹狼运动信息化平台 
 * 创建人：Gererator
 */
@Entity
@Table(name = "trpreportplugin")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Trpreportplugin implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统ID
  */
  protected Integer  trpreportpluginid;// //字段名称： 系统ID

  protected Integer  trpreportid;  //字段名称： 报表id
  protected String  name;  //字段名称： 插件名称
  protected String  springname;  //字段名称： 插件Spring定义
  protected String  fieldalias;  //字段名称： 插件字段别名
  protected String  template;  //字段名称： 插件Sql模板
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Trpreportplugin")
	@TableGenerator(
	   name = "Trpreportplugin",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Trpreportplugin",
	   allocationSize = 1
	)
  public Integer getTrpreportpluginid () {
     return trpreportpluginid;
  }
  public void setTrpreportpluginid(Integer trpreportpluginid) {
     this.trpreportpluginid = trpreportpluginid;
  }
 /**
  * @return 返回 trpreportid,报表id
  */
  
  public Integer getTrpreportid () {
     return trpreportid;
  }
	
 /**
  * @param trpreportid 
  */
  public void setTrpreportid(Integer trpreportid) {
     this.trpreportid = trpreportid;
  }
 /**
  * @return 返回 name,插件名称
  */
  @Column(length=30)
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
  * @return 返回 springname,插件Spring定义
  */
  @Column(length=30)
  public String getSpringname () {
     return springname;
  }
	
 /**
  * @param springname 
  */
  public void setSpringname(String springname) {
     this.springname = springname;
  }
 /**
  * @return 返回 fieldalias,插件字段别名
  */
  @Column(length=50)
  public String getFieldalias () {
     return fieldalias;
  }
	
 /**
  * @param fieldalias 
  */
  public void setFieldalias(String fieldalias) {
     this.fieldalias = fieldalias;
  }
 /**
  * @return 返回 template,插件Sql模板
  */
  @Column(length=4000)
  public String getTemplate () {
     return template;
  }
	
 /**
  * @param template 
  */
  public void setTemplate(String template) {
     this.template = template;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("trpreportpluginid", this.trpreportpluginid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Trpreportplugin) ) return false;
     Trpreportplugin castOther = (Trpreportplugin) other;
     return new EqualsBuilder()
       .append(this.getTrpreportpluginid(), castOther.getTrpreportpluginid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTrpreportpluginid())
            .toHashCode();
  }
 

}


