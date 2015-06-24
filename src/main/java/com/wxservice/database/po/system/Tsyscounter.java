
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
@Table(name = "tsyscounter")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONE)

public class Tsyscounter implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 主键
  */
  protected Integer  counterid;// //字段名称： 主键

  protected String  tablekey;  //字段名称： 表关键字
  protected Integer  countvalue;  //字段名称： 当前计数
  @Id
  @SequenceGenerator(name="TSYSCOUNTER_SEQ",sequenceName="TSYSCOUNTER_SEQ",allocationSize=1)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TSYSCOUNTER_SEQ") 
  public Integer getCounterid () {
     return counterid;
  }
  public void setCounterid(Integer counterid) {
     this.counterid = counterid;
  }
 /**
  * @return 返回 tablekey,表关键字
  */
  @Column(length=20)
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
  * @return 返回 countvalue,当前计数
  */
  
  public Integer getCountvalue () {
     return countvalue;
  }
	
 /**
  * @param countvalue 
  */
  public void setCountvalue(Integer countvalue) {
     this.countvalue = countvalue;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("counterid", this.counterid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsyscounter) ) return false;
     Tsyscounter castOther = (Tsyscounter) other;
     return new EqualsBuilder()
       .append(this.getCounterid(), castOther.getCounterid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getCounterid())
            .toHashCode();
  }
 

}


