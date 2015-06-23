
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
 * 创建人：Gererator
 */
@Entity
@Table(name = "trphtmlcate")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Trphtmlcate implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统ID
  */
  protected Integer  trphtmlcateid;// //字段名称： 系统ID

  protected String  code;  //字段名称： 编号
  protected String  name;  //字段名称： 名称
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Trphtmlcate")
	@TableGenerator(
	   name = "Trphtmlcate",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Trphtmlcate",
	   allocationSize = 1
	)
  public Integer getTrphtmlcateid () {
     return trphtmlcateid;
  }
  public void setTrphtmlcateid(Integer trphtmlcateid) {
     this.trphtmlcateid = trphtmlcateid;
  }
 /**
  * @return 返回 code,编号
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

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("trphtmlcateid", this.trphtmlcateid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Trphtmlcate) ) return false;
     Trphtmlcate castOther = (Trphtmlcate) other;
     return new EqualsBuilder()
       .append(this.getTrphtmlcateid(), castOther.getTrphtmlcateid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTrphtmlcateid())
            .toHashCode();
  }
 

}


