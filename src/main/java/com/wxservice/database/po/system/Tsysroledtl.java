
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

@Entity
@Table(name = "tsysroledtl")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tsysroledtl implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 权限组明细ID
  */
  protected Integer  tsysroledtlid;// //字段名称： 权限组明细ID

  protected Integer  tsysroleid;  //字段名称： 权限组ID
  protected Integer  tsysmenuid;  //字段名称： 菜单ID
  protected String  rightvalue;  //字段名称： 权限
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tsysroledtl")
	@TableGenerator(
	   name = "Tsysroledtl",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tsysroledtl",
	   allocationSize = 1
	)
  public Integer getTsysroledtlid () {
     return tsysroledtlid;
  }
  public void setTsysroledtlid(Integer tsysroledtlid) {
     this.tsysroledtlid = tsysroledtlid;
  }
 /**
  * @return 返回 tsysroleid,权限组ID
  */
  
  public Integer getTsysroleid () {
     return tsysroleid;
  }
	
 /**
  * @param tsysroleid 
  */
  public void setTsysroleid(Integer tsysroleid) {
     this.tsysroleid = tsysroleid;
  }
 /**
  * @return 返回 tsysmenuid,菜单ID
  */
  
  public Integer getTsysmenuid () {
     return tsysmenuid;
  }
	
 /**
  * @param tsysmenuid 
  */
  public void setTsysmenuid(Integer tsysmenuid) {
     this.tsysmenuid = tsysmenuid;
  }
 /**
  * @return 返回 rightvalue,权限
  */
  @Column(length=30)
  public String getRightvalue () {
     return rightvalue;
  }
	
 /**
  * @param rightvalue 
  */
  public void setRightvalue(String rightvalue) {
     this.rightvalue = rightvalue;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tsysroledtlid", this.tsysroledtlid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsysroledtl) ) return false;
     Tsysroledtl castOther = (Tsysroledtl) other;
     return new EqualsBuilder()
       .append(this.getTsysroledtlid(), castOther.getTsysroledtlid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTsysroledtlid())
            .toHashCode();
  }
 

}


