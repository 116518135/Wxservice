
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
@Table(name = "tbsmaillog")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tbsmaillog implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统ID
  */
  protected Integer  tbsmaillog;// //字段名称： 系统ID
  protected String code;//字段名称:注册用户
  protected String  mail;  //字段名称： 邮箱
  protected Integer  status;  //字段名称： 绑定标志
  protected String  validatecode;  //字段名称： 激活码
  protected java.util.Date  logdate;  //字段名称： 激活时间时间
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tbsmaillog")
	@TableGenerator(
	   name = "Tbsmaillog",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tbsmaillog",
	   allocationSize = 1
	)
  public Integer getTbsmaillog () {
     return tbsmaillog;
  }
  public void setTbsmaillog(Integer tbsmaillog) {
     this.tbsmaillog = tbsmaillog;
  }
  
  
  public String getCode() {
	 return code;
  }
  public void setCode(String code) {
	this.code = code;
  }
/**
  * @return 返回 mail,邮箱
  */
  @Column(length=50)
  public String getMail () {
     return mail;
  }
	
 /**
  * @param mail 
  */
  public void setMail(String mail) {
     this.mail = mail;
  }
 /**
  * @return 返回 status,绑定标志
  */
  
  public Integer getStatus () {
     return status;
  }
	
 /**
  * @param status 
  */
  public void setStatus(Integer status) {
     this.status = status;
  }
 /**
  * @return 返回 validateCode,激活码
  */
  @Column(length=50)
  public String getValidatecode () {
     return validatecode;
  }
	
 /**
  * @param validatecode 
  */
  public void setValidatecode(String validatecode) {
     this.validatecode = validatecode;
  }
 /**
  * @return 返回 logdate,激活时间时间
  */
  
  public java.util.Date getLogdate () {
     return logdate;
  }
	
 /**
  * @param logdate 
  */
  public void setLogdate(java.util.Date logdate) {
     this.logdate = logdate;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tbsmaillog", this.tbsmaillog)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tbsmaillog) ) return false;
     Tbsmaillog castOther = (Tbsmaillog) other;
     return new EqualsBuilder()
       .append(this.getTbsmaillog(), castOther.getTbsmaillog())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTbsmaillog())
            .toHashCode();
  }
 

}


