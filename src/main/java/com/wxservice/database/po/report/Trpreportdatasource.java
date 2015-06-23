
package com.wxservice.database.po.report;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

/**
 * 
 * 描述：   Hibernate PO
 * 项目名称：七匹狼运动信息化平台 
 * 创建人：Gererator
 */
@Entity
@Table(name = "trpreportdatasource")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Trpreportdatasource implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统ID
  */
  protected Integer  trpreportdatasourceid;// //字段名称： 系统ID

  protected Integer  trpreportid;  //字段名称： 报表id
  protected String  name;  //字段名称： 名称
  protected String  content;  //字段名称： 内容
  protected Integer  datasourcetype;  //字段名称： 类型
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Trpreportdatasource")
	@TableGenerator(
	   name = "Trpreportdatasource",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Trpreportdatasource",
	   allocationSize = 1
	)
  public Integer getTrpreportdatasourceid () {
     return trpreportdatasourceid;
  }
  public void setTrpreportdatasourceid(Integer trpreportdatasourceid) {
     this.trpreportdatasourceid = trpreportdatasourceid;
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
  * @return 返回 name,名称
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
  * @return 返回 content,内容
  */

  public String getContent () {
     return content;
  }
	
 /**
  * @param content 
  */
  public void setContent(String content) {
     this.content = content;
  }
 /**
  * @return 返回 datasourcetype,类型
  */
  
  public Integer getDatasourcetype () {
     return datasourcetype;
  }
	
 /**
  * @param datasourcetype 
  */
  public void setDatasourcetype(Integer datasourcetype) {
     this.datasourcetype = datasourcetype;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("trpreportdatasourceid", this.trpreportdatasourceid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Trpreportdatasource) ) return false;
     Trpreportdatasource castOther = (Trpreportdatasource) other;
     return new EqualsBuilder()
       .append(this.getTrpreportdatasourceid(), castOther.getTrpreportdatasourceid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTrpreportdatasourceid())
            .toHashCode();
  }
 

}


