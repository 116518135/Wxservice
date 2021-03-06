
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
@Table(name = "tsysdatadict")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tsysdatadict implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 词典ID
  */
  protected Integer  tsysdatadictid;// //字段名称： 词典ID

  protected String  code;  //字段名称： 编号
  protected String  name;  //字段名称： 名称
  protected String  memo;  //字段名称： 备注

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tsysdatadict")
	@TableGenerator(
	   name = "Tsysdatadict",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tsysdatadict",
	   allocationSize = 1
	)
  public Integer getTsysdatadictid () {
     return tsysdatadictid;
  }
  public void setTsysdatadictid(Integer tsysdatadictid) {
     this.tsysdatadictid = tsysdatadictid;
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
 

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tsysdatadictid", this.tsysdatadictid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsysdatadict) ) return false;
     Tsysdatadict castOther = (Tsysdatadict) other;
     return new EqualsBuilder()
       .append(this.getTsysdatadictid(), castOther.getTsysdatadictid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTsysdatadictid())
            .toHashCode();
  }
 

}


