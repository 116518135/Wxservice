
package com.wxservice.database.po.regedit;
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
@Table(name = "tfeedback")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tfeedback implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统ID
  */
  protected Integer  tfeedbackid;// //字段名称： 系统ID
  protected String usercode;//字段名称:注册用户
  protected String  content;  //字段名称：反馈信息
  protected java.util.Date  time;  //字段名称： 时间
	
  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tfeedbackid", this.tfeedbackid)
		       .toString();
  }
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tfeedback")
	@TableGenerator(name = "Tfeedback", table = "tsyscounter", pkColumnName = "tablekey", valueColumnName = "countvalue", pkColumnValue = "Tfeedback", allocationSize = 1)
  public Integer getTfeedbackid() {
		return tfeedbackid;
	}

	public void setTfeedbackid(Integer tfeedbackid) {
		this.tfeedbackid = tfeedbackid;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

public boolean equals(Object other) {
     if ( !(other instanceof Tfeedback) ) return false;
     Tfeedback castOther = (Tfeedback) other;
     return new EqualsBuilder()
       .append(this.getTfeedbackid(), castOther.getTfeedbackid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTfeedbackid())
            .toHashCode();
  }
 

}


