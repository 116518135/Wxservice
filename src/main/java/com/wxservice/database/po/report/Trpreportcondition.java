
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
 * 项目名称：七匹狼运动信息化平台 
 * 创建人：Gererator
 */
@Entity
@Table(name = "trpreportcondition")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Trpreportcondition implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统ID
  */
  protected Integer  trpreportconditionid;// //字段名称： 系统ID

  protected Integer  trpreportid;  //字段名称： 报表id
  protected String  name;  //字段名称： 名称
  protected String  jsname;  //字段名称： JS名称
  protected String  wheres;  //字段名称： where语句
  protected String  htmlcontent;  //字段名称： Html内容
  protected Integer  rowindex;  //字段名称： 行
  protected Integer  colindex;  //字段名称： 列
  protected Integer  width;  //字段名称： 宽度
  protected String  processclass;  //字段名称： 处理类
  protected Integer  trpreportpluginid;  //字段名称： 插件
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Trpreportcondition")
	@TableGenerator(
	   name = "Trpreportcondition",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Trpreportcondition",
	   allocationSize = 1
	)
  public Integer getTrpreportconditionid () {
     return trpreportconditionid;
  }
  public void setTrpreportconditionid(Integer trpreportconditionid) {
     this.trpreportconditionid = trpreportconditionid;
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
  * @return 返回 htmlcontent,Html内容
  */
  @Column(length=4000)
  public String getHtmlcontent () {
     return htmlcontent;
  }
	
 /**
  * @param htmlcontent 
  */
  public void setHtmlcontent(String htmlcontent) {
     this.htmlcontent = htmlcontent;
  }
 /**
  * @return 返回 rowindex,行
  */
  
  public Integer getRowindex () {
     return rowindex;
  }
	
 /**
  * @param rowindex 
  */
  public void setRowindex(Integer rowindex) {
     this.rowindex = rowindex;
  }
 /**
  * @return 返回 colindex,列
  */
  
  public Integer getColindex () {
     return colindex;
  }
	
 /**
  * @param colindex 
  */
  public void setColindex(Integer colindex) {
     this.colindex = colindex;
  }
 /**
  * @return 返回 width,宽度
  */
  
  public Integer getWidth () {
     return width;
  }
	
 /**
  * @param width 
  */
  public void setWidth(Integer width) {
     this.width = width;
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
  * @return 返回 trpreportpluginid,插件
  */
  
  public Integer getTrpreportpluginid () {
     return trpreportpluginid;
  }
	
 /**
  * @param trpreportpluginid 
  */
  public void setTrpreportpluginid(Integer trpreportpluginid) {
     this.trpreportpluginid = trpreportpluginid;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("trpreportconditionid", this.trpreportconditionid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Trpreportcondition) ) return false;
     Trpreportcondition castOther = (Trpreportcondition) other;
     return new EqualsBuilder()
       .append(this.getTrpreportconditionid(), castOther.getTrpreportconditionid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTrpreportconditionid())
            .toHashCode();
  }
 

}


