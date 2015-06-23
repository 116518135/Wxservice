
package com.wxservice.database.po.system;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "tsyslog")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONE)

public class Tsyslog implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统编号
  */
  protected Integer  tsyslogid;// //字段名称： 系统编号
  protected String  priority;  //字段名称： 优先级
  protected String  ip;  //字段名称： IP
  protected String  username;  //字段名称： 用户名称
  protected String  cmpname;  //字段名称： 组织名称
  protected String  module;  //字段名称： 模块名
  protected String  action;  //字段名称： 操作
  protected String  message;  //字段名称： 日志信息
  protected String  add1;  //字段名称： add1
  protected Integer createtbsuserid;
  protected Integer createtbscmpid;
  protected java.util.Date  logdate;  //字段名称： log时间
  @Id
  @SequenceGenerator(name="tsyslog_seq",sequenceName="tsyslog_seq",allocationSize=1)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "tsyslog_seq")
  public Integer getTsyslogid () {
     return tsyslogid;
  }
  public void setTsyslogid(Integer tsyslogid) {
     this.tsyslogid = tsyslogid;
  }
 /**
  * @return 返回 priority,优先级
  */
  @Column(length=12)
  public String getPriority () {
     return priority;
  }
	
 /**
  * @param priority 
  */
  public void setPriority(String priority) {
     this.priority = priority;
  }
 /**
  * @return 返回 ip,IP
  */
  @Column(length=30)
  public String getIp () {
     return ip;
  }
	
 /**
  * @param ip 
  */
  public void setIp(String ip) {
     this.ip = ip;
  }
 /**
  * @return 返回 username,用户名称
  */
  @Column(length=20)
  public String getUsername () {
     return username;
  }
	
 /**
  * @param username 
  */
  public void setUsername(String username) {
     this.username = username;
  }
 /**
  * @return 返回 cmpname,组织名称
  */
  @Column(length=30)
  public String getCmpname () {
     return cmpname;
  }
	
 /**
  * @param cmpname 
  */
  public void setCmpname(String cmpname) {
     this.cmpname = cmpname;
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
  * @return 返回 action,操作
  */
  @Column(length=20)
  public String getAction () {
     return action;
  }
	
 /**
  * @param action 
  */
  public void setAction(String action) {
     this.action = action;
  }
 /**
  * @return 返回 message,日志信息
  */
  @Column(length=2000)
  public String getMessage () {
     return message;
  }
	
 /**
  * @param message 
  */
  public void setMessage(String message) {
     this.message = message;
  }
 /**
  * @return 返回 add1,add1
  */
  @Column(length=50)
  public String getAdd1 () {
     return add1;
  }
	
 /**
  * @param add1 
  */
  public void setAdd1(String add1) {
     this.add1 = add1;
  }
 /**
  * @return 返回 logdate,log时间
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
 			   .append("tsyslogid", this.tsyslogid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsyslog) ) return false;
     Tsyslog castOther = (Tsyslog) other;
     return new EqualsBuilder()
       .append(this.getTsyslogid(), castOther.getTsyslogid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTsyslogid())
            .toHashCode();
  }
public Integer getCreatetbsuserid() {
	return createtbsuserid;
}
public void setCreatetbsuserid(Integer createtbsuserid) {
	this.createtbsuserid = createtbsuserid;
}
public Integer getCreatetbscmpid() {
	return createtbscmpid;
}
public void setCreatetbscmpid(Integer createtbscmpid) {
	this.createtbscmpid = createtbscmpid;
}
 

}


