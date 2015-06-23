
package com.wxservice.database.po.base;
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
@Table(name = "tbsuser")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tbsuser implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 操作员ID
  */
  protected Integer  tbsuserid;// //字段名称： 操作员ID

  protected String  code;  //字段名称： 编号
  protected String  name;  //字段名称： 名称
  protected String  passwd;  //字段名称： 登录密码
  protected String  tsysroleids;  //字段名称： 权限组ID
  protected String  rolenames;  //字段名称： 权限组
  protected Integer  stopflag;  //字段名称： 停止使用
 

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tbsuser")
	@TableGenerator(
	   name = "Tbsuser",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tbsuser",
	   allocationSize = 1
	)
  public Integer getTbsuserid () {
     return tbsuserid;
  }
  public void setTbsuserid(Integer tbsuserid) {
     this.tbsuserid = tbsuserid;
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
  * @return 返回 passwd,登录密码
  */
  @Column(length=50)
  public String getPasswd () {
     return passwd;
  }
	
 /**
  * @param passwd 
  */
  public void setPasswd(String passwd) {
     this.passwd = passwd;
  }
 /**
  * @return 返回 tsysroleids,权限组ID
  */
  @Column(length=512)
  public String getTsysroleids () {
     return tsysroleids;
  }
	
 /**
  * @param tsysroleids 
  */
  public void setTsysroleids(String tsysroleids) {
     this.tsysroleids = tsysroleids;
  }
 /**
  * @return 返回 rolenames,权限组
  */
  @Column(length=1024)
  public String getRolenames () {
     return rolenames;
  }
	
 /**
  * @param rolenames 
  */
  public void setRolenames(String rolenames) {
     this.rolenames = rolenames;
  }
 /**
  * @return 返回 stopflag,停止使用
  */
  
  public Integer getStopflag () {
     return stopflag;
  }
	
 /**
  * @param stopflag 
  */
  public void setStopflag(Integer stopflag) {
     this.stopflag = stopflag;
  }
 

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tbsuserid", this.tbsuserid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tbsuser) ) return false;
     Tbsuser castOther = (Tbsuser) other;
     return new EqualsBuilder()
       .append(this.getTbsuserid(), castOther.getTbsuserid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTbsuserid())
            .toHashCode();
  }
 

}


