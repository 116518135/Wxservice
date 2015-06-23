
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
@Table(name = "trphtmlctrl")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Trphtmlctrl implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统ID
  */
  protected Integer  trphtmlctrlid;// //字段名称： 系统ID

  protected Integer  trphtmlcateid;  //字段名称： 控件分类
  protected String  name;  //字段名称： 名称
  protected String  jsname;  //字段名称： JS名称
  protected String  wheres;  //字段名称： where语句
  protected String  content;  //字段名称： 内容
  protected String  processclass;  //字段名称： 处理类
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Trphtmlctrl")
	@TableGenerator(
	   name = "Trphtmlctrl",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Trphtmlctrl",
	   allocationSize = 1
	)
  public Integer getTrphtmlctrlid () {
     return trphtmlctrlid;
  }
  public void setTrphtmlctrlid(Integer trphtmlctrlid) {
     this.trphtmlctrlid = trphtmlctrlid;
  }
 /**
  * @return 返回 trphtmlcateid,控件分类
  */
  
  public Integer getTrphtmlcateid () {
     return trphtmlcateid;
  }
	
 /**
  * @param trphtmlcateid 
  */
  public void setTrphtmlcateid(Integer trphtmlcateid) {
     this.trphtmlcateid = trphtmlcateid;
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
  * @return 返回 jsname,JS名称
  */
  @Column(length=50)
  public String getJsname () {
     return jsname;
  }
	
 /**
  * @param jsname 
  */
  public void setJsname(String jsname) {
     this.jsname = jsname;
  }
 /**
  * @return 返回 wheres,where语句
  */
  @Column(length=512)
  public String getWheres () {
     return wheres;
  }
	
 /**
  * @param wheres 
  */
  public void setWheres(String wheres) {
     this.wheres = wheres;
  }
 /**
  * @return 返回 content,内容
  */
  @Column(length=4000)
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

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("trphtmlctrlid", this.trphtmlctrlid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Trphtmlctrl) ) return false;
     Trphtmlctrl castOther = (Trphtmlctrl) other;
     return new EqualsBuilder()
       .append(this.getTrphtmlctrlid(), castOther.getTrphtmlctrlid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTrphtmlctrlid())
            .toHashCode();
  }
 

}


