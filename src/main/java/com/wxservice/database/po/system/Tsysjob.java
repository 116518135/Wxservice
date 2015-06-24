
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
@Table(name = "tsysjob")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tsysjob implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统ID
  */
  protected Integer  tsysjobid;// //字段名称： 系统ID

  protected String  code;  //字段名称： 编号
  protected String  name;  //字段名称： 名称
  protected String  springname;  //字段名称： 业务对象名
  protected String  memo;  //字段名称： 备注
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tsysjob")
	@TableGenerator(
	   name = "Tsysjob",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tsysjob",
	   allocationSize = 1
	)
  public Integer getTsysjobid () {
     return tsysjobid;
  }
  public void setTsysjobid(Integer tsysjobid) {
     this.tsysjobid = tsysjobid;
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
 /**
  * @return 返回 springname,业务对象名
  */
  @Column(length=50)
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
  * @return 返回 memo,备注
  */
  @Column(length=50)
  public String getMemo () {
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
 			   .append("tsysjobid", this.tsysjobid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsysjob) ) return false;
     Tsysjob castOther = (Tsysjob) other;
     return new EqualsBuilder()
       .append(this.getTsysjobid(), castOther.getTsysjobid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTsysjobid())
            .toHashCode();
  }
 

}


