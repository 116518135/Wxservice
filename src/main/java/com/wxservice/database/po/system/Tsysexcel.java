
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
@Table(name = "Tsysexcel")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tsysexcel implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统ID
  */
  protected Integer  tsysexcelid;// //字段名称： 系统ID

  protected String  code;  //字段名称： 编号
  protected String  name;  //字段名称： 名称
  protected String  processclass;  //字段名称： 处理类
  protected String  tablename;  //字段名称： 表名
  protected String  tablekey;  //字段名称： 主键
  protected Integer  checkflag;  //字段名称： 检查标志
  protected String  springname;  //字段名称： 业务类
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tsysexcel")
	@TableGenerator(
	   name = "Tsysexcel",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tsysexcel",
	   allocationSize = 1
	)
  public Integer getTsysexcelid () {
     return tsysexcelid;
  }
  public void setTsysexcelid(Integer tsysexcelid) {
     this.tsysexcelid = tsysexcelid;
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
  * @return 返回 processclass,处理类
  */
  @Column(length=30)
  public String getProcessclass () {
     return processclass;
  }
	
 /**
  * @param processclass 
  */
  public void setProcessclass(String processclass) {
     this.processclass = processclass;
  }
 /**
  * @return 返回 tablename,表名
  */
  @Column(length=30)
  public String getTablename () {
     return tablename;
  }
	
 /**
  * @param tablename 
  */
  public void setTablename(String tablename) {
     this.tablename = tablename;
  }
 /**
  * @return 返回 tablekey,主键
  */
  @Column(length=30)
  public String getTablekey () {
     return tablekey;
  }
	
 /**
  * @param tablekey 
  */
  public void setTablekey(String tablekey) {
     this.tablekey = tablekey;
  }
 /**
  * @return 返回 checkflag,检查标志
  */
  
  public Integer getCheckflag () {
     return checkflag;
  }
	
 /**
  * @param checkflag 
  */
  public void setCheckflag(Integer checkflag) {
     this.checkflag = checkflag;
  }
 /**
  * @return 返回 springname,业务类
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

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tsysexcelid", this.tsysexcelid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsysexcel) ) return false;
     Tsysexcel castOther = (Tsysexcel) other;
     return new EqualsBuilder()
       .append(this.getTsysexcelid(), castOther.getTsysexcelid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTsysexcelid())
            .toHashCode();
  }
 

}


