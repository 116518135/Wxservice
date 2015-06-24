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
@Table(name = "tsyscmpparam")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tsyscmpparam implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 参数id
  */
  protected Integer  tsyscmpparamid;// //字段名称： 参数id

  protected String  paramname;  //字段名称： 参数名称
  protected String  paramvalue;  //字段名称： 参数值
  protected java.util.Date  editdate;  //字段名称： 修改日期
  protected String  chinesename;  //字段名称： 中文名称
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tsyscmpparam")
	@TableGenerator(
	   name = "Tsyscmpparam",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tsyscmpparam",
	   allocationSize = 1
	)
  public Integer getTsyscmpparamid () {
     return tsyscmpparamid;
  }
  public void setTsyscmpparamid(Integer tsyscmpparamid) {
     this.tsyscmpparamid = tsyscmpparamid;
  }
 /**
  * @return 返回 paramname,参数名称
  */
  @Column(length=20)
  public String getParamname () {
     return paramname;
  }
	
 /**
  * @param paramname 
  */
  public void setParamname(String paramname) {
     this.paramname = paramname;
  }
 /**
  * @return 返回 paramvalue,参数值
  */
  @Column(length=20)
  public String getParamvalue () {
     return paramvalue;
  }
	
 /**
  * @param paramvalue 
  */
  public void setParamvalue(String paramvalue) {
     this.paramvalue = paramvalue;
  }

 /**
  * @return 返回 editdate,修改日期
  */
  
  public java.util.Date getEditdate () {
     return editdate;
  }
	
 /**
  * @param editdate 
  */
  public void setEditdate(java.util.Date editdate) {
     this.editdate = editdate;
  }
 /**
  * @return 返回 chinesename,中文名称
  */
  @Column(length=50)
  public String getChinesename () {
     return chinesename;
  }
	
 /**
  * @param chinesename 
  */
  public void setChinesename(String chinesename) {
     this.chinesename = chinesename;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tsyscmpparamid", this.tsyscmpparamid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsyscmpparam) ) return false;
     Tsyscmpparam castOther = (Tsyscmpparam) other;
     return new EqualsBuilder()
       .append(this.getTsyscmpparamid(), castOther.getTsyscmpparamid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTsyscmpparamid())
            .toHashCode();
  }
 

}


