
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
@Table(name = "tsysmenu")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tsysmenu implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 菜单ID
  */
  protected Integer  tsysmenuid;// //字段名称： 菜单ID

  protected Integer  parentid;  //字段名称： 父菜单ID
  protected String  name;  //字段名称： 菜单名称
  protected String  accessurl;  //字段名称： 资源URL
  protected Integer  indexno;  //字段名称： 显示顺序
  protected String  rightvalue;  //字段名称： 权限字符串
  protected String  cmprightvalue;  //字段名称： 组织权限字符串
  protected String  memo;  //字段名称： 备注
  protected String  webserver;  //字段名称： WEB服务器
  @Id
  @SequenceGenerator(name="TSYSMENU_SEQ",sequenceName="TSYSMENU_SEQ",allocationSize=1)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TSYSMENU_SEQ")
  public Integer getTsysmenuid () {
     return tsysmenuid;
  }
  public void setTsysmenuid(Integer tsysmenuid) {
     this.tsysmenuid = tsysmenuid;
  }
 /**
  * @return 返回 parentid,父菜单ID
  */
  
  public Integer getParentid () {
     return parentid;
  }
	
 /**
  * @param parentid 
  */
  public void setParentid(Integer parentid) {
     this.parentid = parentid;
  }
 /**
  * @return 返回 name,菜单名称
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
  * @return 返回 accessurl,资源URL
  */
  @Column(length=50)
  public String getAccessurl () {
     return accessurl;
  }
	
 /**
  * @param accessurl 
  */
  public void setAccessurl(String accessurl) {
     this.accessurl = accessurl;
  }
 /**
  * @return 返回 indexno,显示顺序
  */
  
  public Integer getIndexno () {
     return indexno;
  }
	
 /**
  * @param indexno 
  */
  public void setIndexno(Integer indexno) {
     this.indexno = indexno;
  }
 /**
  * @return 返回 rightvalue,权限字符串
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
 /**
  * @return 返回 cmprightvalue,组织权限字符串
  */
  @Column(length=12)
  public String getCmprightvalue () {
     return cmprightvalue;
  }
	
 /**
  * @param cmprightvalue 
  */
  public void setCmprightvalue(String cmprightvalue) {
     this.cmprightvalue = cmprightvalue;
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
 /**
  * @return 返回 webserver,WEB服务器
  */
  @Column(length=50)
  public String getWebserver () {
     return webserver;
  }
	
 /**
  * @param webserver 
  */
  public void setWebserver(String webserver) {
     this.webserver = webserver;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tsysmenuid", this.tsysmenuid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsysmenu) ) return false;
     Tsysmenu castOther = (Tsysmenu) other;
     return new EqualsBuilder()
       .append(this.getTsysmenuid(), castOther.getTsysmenuid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTsysmenuid())
            .toHashCode();
  }
 

}


